package com.example.buyandeat.ui.auth;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.buyandeat.R;
import com.example.buyandeat.databinding.FragmentLoginBinding;
import com.example.buyandeat.helpers.AuthFormValidate;
import com.example.buyandeat.helpers.BaseFragment;
import com.example.buyandeat.helpers.FragmentHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends BaseFragment {

    private FragmentLoginBinding binding;
    private AuthFormValidate authFormValidate;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authFormValidate = new AuthFormValidate(binding.edtEmail, binding.tilEmail, binding.edtPassword, binding.tilPassword);

        setupClickListeners(view);
        authFormValidate.validateFormListener();
    }

    private void setupClickListeners(View view) {
        binding.btnRegister.setOnClickListener(v -> findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment));
        binding.btnForgotPassword.setOnClickListener(v -> findNavController(view).navigate(R.id.action_loginFragment_to_forgotFragment));
        binding.btnLogin.setOnClickListener(v -> {

            if (authFormValidate.isFormValid()) {
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();

                hideKeyboard();
                binding.progressBar.setVisibility(View.VISIBLE);

                loginUser(view, email, password);
            }
        });
    }

    private void loginUser(View view, String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FragmentHelper.showBottomSheet(LoginFragment.this, R.string.message, R.string.ok, R.string.succes_login, () -> {
                        findNavController(view).navigate(R.id.action_global_homeFragment);
                    });
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    FragmentHelper.showBottomSheet(LoginFragment.this, R.string.atencao, R.string.ok, R.string.erro_login);
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
