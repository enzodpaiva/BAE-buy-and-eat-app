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
import com.example.buyandeat.databinding.FragmentLoginBinding;
import com.example.buyandeat.helpers.AuthFormValidate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

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
                loginUser(view, email, password);
            }
        });
    }

    private void loginUser(View view, String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(requireContext(), "Usuário logado com sucesso!", Toast.LENGTH_LONG).show();
                    findNavController(view).navigate(R.id.action_global_homeFragment);
                } else {
                    Toast.makeText(requireContext(), "Erro ao logar usuário!", Toast.LENGTH_LONG).show();
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
