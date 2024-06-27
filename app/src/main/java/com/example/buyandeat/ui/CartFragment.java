package com.example.buyandeat.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyandeat.R;
import com.example.buyandeat.databinding.FragmentCartBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<Product> cartItems;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Recupera os itens do carrinho do Bundle
        if (getArguments() != null) {
            cartItems = (List<Product>) getArguments().getSerializable("cartItems");
        } else {
            cartItems = new ArrayList<>();
        }

        // Configura o RecyclerView
        recyclerView = binding.recyclerViewCart;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Configura o Adapter
        cartAdapter = new CartAdapter(cartItems);
        recyclerView.setAdapter(cartAdapter);

        // Atualiza o preço total
        updateTotalPrice();
        checkCartItems();

        binding.buttonBackToMenu.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.homeFragment);
        });

        binding.confirmOrderButton.setOnClickListener(v -> {
            String orderId = generateOrderId();
            Bundle bundle = new Bundle();
            bundle.putString("orderId", orderId);

            Navigation.findNavController(view).navigate(R.id.successFragment, bundle);
        });
    }

    private void updateTotalPrice() {
        double totalPrice = calculateTotalPrice();
      binding.totalPriceTextView.setText(String.format("Total: R$ %.2f", totalPrice));
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : cartItems) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }

    private String generateOrderId() {
        Random random = new Random();
        int orderId = random.nextInt(900000) + 100000; // Gera um número entre 100000 e 999999
        return String.valueOf(orderId);
    }

    private void checkCartItems() {
        if (cartItems.isEmpty()) {
            binding.textViewEmptyCart.setVisibility(View.VISIBLE);
            binding.recyclerViewCart.setVisibility(View.GONE);
            binding.totalPriceTextView.setVisibility(View.GONE);
            binding.confirmOrderButton.setVisibility(View.GONE);
        } else {
            binding.textViewEmptyCart.setVisibility(View.GONE);
            binding.recyclerViewCart.setVisibility(View.VISIBLE);
            binding.totalPriceTextView.setVisibility(View.VISIBLE);
            binding.confirmOrderButton.setVisibility(View.VISIBLE);
            updateTotalPrice();
        }
    }
}
