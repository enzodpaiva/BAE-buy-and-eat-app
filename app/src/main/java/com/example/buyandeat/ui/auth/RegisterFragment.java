package com.example.buyandeat.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.buyandeat.databinding.FragmentRegisterBinding;
import com.example.buyandeat.helpers.AuthFormValidate;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private AuthFormValidate authFormValidate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        authFormValidate = new AuthFormValidate(binding.edtEmail, binding.tilEmail, binding.edtPassword, binding.tilPassword);
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
                String password = binding.edtPassword.getText().toString();
                registerUser(email, password);
            }
        });
    }

    private void registerUser(String email, String password) {
        Toast.makeText(requireContext(), "Usuário registrado com sucesso!\n" + email + " " + password, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}