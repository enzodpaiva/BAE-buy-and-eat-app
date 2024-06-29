package com.example.buyandeat.ui.auth;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.buyandeat.R;
import com.example.buyandeat.databinding.FragmentForgotBinding;
import com.example.buyandeat.helpers.AuthFormValidate;
import com.example.buyandeat.helpers.BaseFragment;
import com.example.buyandeat.helpers.FragmentHelper;

public class ForgotFragment extends BaseFragment {

    private FragmentForgotBinding binding;
    private AuthFormValidate authFormValidate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentForgotBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentHelper.initToolbar(this, binding.toolbar);
        authFormValidate = new AuthFormValidate(binding.edtEmail, binding.tilEmail);

        setupClickListeners(view);
        authFormValidate.validateFormListener();
    }

    private void setupClickListeners(View view) {
        binding.btnRegister.setOnClickListener(v -> {
            if (authFormValidate.isFormValid()) {
                String email = binding.edtEmail.getText().toString();

                hideKeyboard();
                binding.progressBar.setVisibility(View.VISIBLE);

                recover(view, email);
            }
        });
    }

    private void recover(View view, String email) {
        FragmentHelper.showBottomSheet(ForgotFragment.this, R.string.message, R.string.ok, R.string.succes_forgot, () -> {
            findNavController(view).navigate(R.id.action_forgotFragment_to_loginFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}