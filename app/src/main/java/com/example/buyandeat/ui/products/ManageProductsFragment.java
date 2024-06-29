package com.example.buyandeat.ui.products;

import androidx.fragment.app.Fragment;
import android.os.Bundle;

import static androidx.navigation.Navigation.findNavController;

import com.example.buyandeat.databinding.FragmentManageProductsBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.buyandeat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ManageProductsFragment extends Fragment {

    private FragmentManageProductsBinding binding;
    private DatabaseReference databaseReference;
    private List<AddProductFragment.Product> productList;
    private ProductAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentManageProductsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference("products");

        productList = new ArrayList<>();
        adapter = new ProductAdapter(getContext(), productList);
        binding.productListView.setAdapter(adapter);

        binding.btnAddProduct.setOnClickListener(v -> {
            findNavController(requireView()).navigate(R.id.action_manageProductsFragment_to_addProductFragment);
        });

        binding.btnBack.setOnClickListener(v -> {
            findNavController(requireView()).navigate(R.id.action_manageProductsFragment_to_splashFragment);
        });

        loadProductsFromFirebase();
    }

    private void loadProductsFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList.clear();
                for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                    AddProductFragment.Product product = productSnapshot.getValue(AddProductFragment.Product.class);
                    if (product != null) {
                        productList.add(product);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Exibir mensagem de erro
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
