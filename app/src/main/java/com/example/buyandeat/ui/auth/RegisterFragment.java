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
import com.example.buyandeat.databinding.FragmentRegisterBinding;
import com.example.buyandeat.helpers.AuthFormValidate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment {

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
        authFormValidate = new AuthFormValidate(binding.edtEmail, binding.tilEmail, binding.edtPassword, binding.tilPassword);
        mAuth = FirebaseAuth.getInstance();

        setupClickListeners(view);
        authFormValidate.validateFormListener();
    }

    private void setupClickListeners(View view) {
        binding.btnRegister.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);

            if (authFormValidate.isFormValid()) {
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();
                registerUser(view, email, password);
            }
        });
    }

    private void registerUser(View view, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(requireContext(), "Usuário registrado com sucesso!\n" + email + " " + password, Toast.LENGTH_LONG).show();
                    findNavController(view).navigate(R.id.action_global_homeFragment);
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(requireContext(), "Erro ao registrar usuário", Toast.LENGTH_LONG).show();
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