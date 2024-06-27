package com.example.buyandeat.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.buyandeat.R;
import com.example.buyandeat.databinding.FragmentSuccessBinding;

public class SuccessFragment extends Fragment {

    private FragmentSuccessBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSuccessBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            String orderId = getArguments().getString("orderId");
            binding.orderIdTextView.setText("Pedido: " + orderId);
        }

        binding.buttonReturnToHome.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.homeFragment);
        });
    }
}
