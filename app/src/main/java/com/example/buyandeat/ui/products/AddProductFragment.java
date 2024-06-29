package com.example.buyandeat.ui.products;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.buyandeat.databinding.FragmentAddProductBinding;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static androidx.navigation.Navigation.findNavController;

import com.example.buyandeat.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddProductFragment extends Fragment {
    private FragmentAddProductBinding binding;
    private DatabaseReference databaseReference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inicializa o Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("products");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnBack.setOnClickListener(v -> {
            findNavController(requireView()).navigateUp();
        });

        binding.btnSaveProduct.setOnClickListener(v -> {
            saveProductToFirebase();
        });
    }

    private void saveProductToFirebase() {
        String name = binding.etProductName.getText().toString().trim();
        String description = binding.etProductDescription.getText().toString().trim();
        String price = binding.etProductPrice.getText().toString().trim();

        if (name.isEmpty() || description.isEmpty() || price.isEmpty()) {
            // Exibir mensagem de erro se algum campo estiver vazio
            Toast.makeText(getContext(), "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        String productId = databaseReference.push().getKey();
        Product product = new Product(productId, name, description, price);
        if (productId != null) {
            databaseReference.child(productId).setValue(product)
                    .addOnSuccessListener(aVoid -> {
                        // Navegar para a página de listagem de produtos
                        findNavController(requireView()).navigate(R.id.action_addProductFragment_to_manageProductsFragment);
                    })
                    .addOnFailureListener(e -> {
                        // Exibir mensagem de erro
                        Toast.makeText(getContext(), "Falha ao salvar o produto. Tente novamente.", Toast.LENGTH_SHORT).show();
                    });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class Product {
        public String id;
        public String name;
        public String description;
        public String price;

        public Product() {
            // Construtor vazio necessário para Firebase
        }

        public Product(String id, String name, String description, String price) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
        }
    }
}
