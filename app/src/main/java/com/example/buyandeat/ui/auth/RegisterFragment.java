package com.example.buyandeat.ui.auth;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.buyandeat.R;
import com.example.buyandeat.databinding.FragmentRegisterBinding;
import com.example.buyandeat.helpers.AuthFormValidate;
import com.example.buyandeat.helpers.BaseFragment;
import com.example.buyandeat.helpers.FragmentHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends BaseFragment {

    private FragmentRegisterBinding binding;
    private AuthFormValidate authFormValidate;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentHelper.initToolbar(this, binding.toolbar);
        authFormValidate = new AuthFormValidate(binding.edtEmail, binding.tilEmail, binding.edtPassword, binding.tilPassword);
        mAuth = FirebaseAuth.getInstance();

        setupClickListeners(view);
        authFormValidate.validateFormListener();
    }

    private void setupClickListeners(View view) {
        binding.btnRegister.setOnClickListener(v -> {

            if (authFormValidate.isFormValid()) {
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();

                hideKeyboard();
                binding.progressBar.setVisibility(View.VISIBLE);

                registerUser(view, email, password);
            }
        });
    }

    private void registerUser(View view, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FragmentHelper.showBottomSheet(RegisterFragment.this, R.string.message, R.string.ok, R.string.succes_register, () -> {
                        findNavController(view).navigate(R.id.action_global_homeFragment);
                    });

                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    FragmentHelper.showBottomSheet(RegisterFragment.this, R.string.atencao, R.string.ok, R.string.erro_register);
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}