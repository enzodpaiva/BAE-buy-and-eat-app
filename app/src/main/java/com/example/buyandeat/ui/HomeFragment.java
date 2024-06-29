package com.example.buyandeat.ui;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyandeat.R;
import com.example.buyandeat.databinding.FragmentHomeBinding;
import com.example.buyandeat.helpers.FragmentHelper;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ProductAdapter.OnAddToCartClickListener {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private List<Product> cartItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializa o carrinho
        cartItems = new ArrayList<>();

        // Configura o RecyclerView
        recyclerView = binding.recyclerViewOrders;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Inicializa a lista de produtos estática
        productList = createStaticProducts();

        // Configura o Adapter
        productAdapter = new ProductAdapter(productList, this);
        recyclerView.setAdapter(productAdapter);

        // Configura o botão de logout
        binding.btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            FragmentHelper.showBottomSheet(HomeFragment.this, R.string.message, R.string.ok, R.string.succes_signout, () -> {
                findNavController(view).navigate(R.id.action_homeFragment_to_auth);
            });
        });

        // Configura o botão de navegação para o carrinho
        binding.buttonGoToCart.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("cartItems", new ArrayList<>(cartItems));
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_cartFragment, bundle);
        });

        binding.btnManageProducts.setOnClickListener(v -> {
            findNavController(requireView()).navigate(R.id.action_homeFragment_to_manageProductsFragment);
        });
    }

    // Método para criar produtos estáticos
    private List<Product> createStaticProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product("1", "Hambúrguer", "Delicioso hambúrguer", 15.0, R.drawable.hamburguer));
        products.add(new Product("2", "Pizza", "Pizza saborosa", 25.0, R.drawable.pizza));
        products.add(new Product("3", "Salada", "Opção saudável", 12.0, R.drawable.salada));

        return products;
    }

    @Override
    public void onAddToCartClicked(Product product) {
        // Adicionar produto ao carrinho
        boolean found = false;
        for (Product cartProduct : cartItems) {
            if (cartProduct.getId().equals(product.getId())) {
                cartProduct.setQuantity(cartProduct.getQuantity() + 1);
                found = true;
                break;
            }
        }
        if (!found) {
            cartItems.add(product);
        }
        FragmentHelper.showBottomSheet(HomeFragment.this, R.string.message, R.string.ok, R.string.succes_cart);
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double totalPrice = calculateTotalPrice();
        // Verifique se o elemento UI correspondente ao totalPrice está presente no layout e descomente a linha correta
//        binding.totalPrice.setText(String.format("Total: R$ %.2f", totalPrice));
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : cartItems) {
            totalPrice += product.getPrice() * product.getQuantity(); // Multiplica pelo valor da quantidade
        }
        return totalPrice;
    }
}
