package com.example.buyandeat.ui.auth;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.buyandeat.R;
import com.example.buyandeat.databinding.FragmentForgotBinding;
import com.example.buyandeat.helpers.AuthFormValidate;

public class ForgotFragment extends Fragment {

    private FragmentForgotBinding binding;
    private AuthFormValidate authFormValidate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentForgotBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authFormValidate = new AuthFormValidate(binding.edtEmail, binding.tilEmail);

        setupClickListeners(view);
        authFormValidate.validateFormListener();
    }

    private void setupClickListeners(View view) {
        binding.btnRegister.setOnClickListener(v -> {
            if (authFormValidate.isFormValid()) {
                String email = binding.edtEmail.getText().toString();
                recover(view, email);
            }
        });
    }

    private void recover(View view, String email) {
        Toast.makeText(requireContext(), "Email enviado para!\n" + email, Toast.LENGTH_LONG).show();
        findNavController(view).navigate(R.id.action_forgotFragment_to_loginFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}