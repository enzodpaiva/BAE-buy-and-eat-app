package com.example.buyandeat.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.buyandeat.databinding.FragmentForgotBinding;
import com.example.buyandeat.helpers.AuthFormValidate;

public class ForgotFragment extends Fragment {

    private FragmentForgotBinding binding;
    private AuthFormValidate authFormValidate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentForgotBinding.inflate(inflater, container, false);
        authFormValidate = new AuthFormValidate(binding.edtEmail, binding.tilEmail);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupClickListeners();
        authFormValidate.validateFormListener();
    }

    private void setupClickListeners() {
        binding.btnRegister.setOnClickListener(v -> {
            if (authFormValidate.isFormValid()) {
                String email = binding.edtEmail.getText().toString();
                recover(email);
            }
        });
    }

    private void recover(String email) {
        Toast.makeText(requireContext(), "Email enviado para!\n" + email, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}