package com.example.buyandeat.helpers;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AuthFormValidate {

    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 12;

    private final TextInputEditText edtEmail;
    private final TextInputLayout tilEmail;
    private final TextInputEditText edtPassword;
    private final TextInputLayout tilPassword;
    private final boolean validatePassword;

    public AuthFormValidate(TextInputEditText edtEmail, TextInputLayout tilEmail, TextInputEditText edtPassword, TextInputLayout tilPassword) {
        this.edtEmail = edtEmail;
        this.tilEmail = tilEmail;
        this.edtPassword = edtPassword;
        this.tilPassword = tilPassword;
        this.validatePassword = true;
    }

    public AuthFormValidate(TextInputEditText edtEmail, TextInputLayout tilEmail) {
        this.edtEmail = edtEmail;
        this.tilEmail = tilEmail;
        this.edtPassword = null;
        this.tilPassword = null;
        this.validatePassword = false;
    }

    public void validateFormListener() {
        edtEmail.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                validateEmail();
            }
        });

        if (validatePassword && edtPassword != null) {
            edtPassword.addTextChangedListener(new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    validatePassword();
                }
            });
        }
    }

    public void validateAll() {
        validateEmail();
        if (validatePassword && edtPassword != null) {
            validatePassword();
        }
    }

    private void validateEmail() {
        String email = edtEmail.getText().toString().trim();
        if (email.isEmpty()) {
            tilEmail.setError("Campo obrigatório");
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("E-mail inválido");
        } else {
            tilEmail.setError(null);
        }
    }

    private void validatePassword() {
        if (edtPassword == null || tilPassword == null) {
            return;
        }
        String password = edtPassword.getText().toString().trim();
        if (password.isEmpty()) {
            tilPassword.setError("Campo obrigatório");
        } else if (password.length() < MIN_PASSWORD_LENGTH) {
            tilPassword.setError("Senha curta");
        } else if (password.length() > MAX_PASSWORD_LENGTH) {
            tilPassword.setError("Senha longa");
        } else {
            tilPassword.setError(null);
        }
    }

    public boolean isFormValid() {
        validateAll();
        boolean isEmailValid = tilEmail.getError() == null;
        if (validatePassword && edtPassword != null) {
            boolean isPasswordValid = tilPassword.getError() == null;
            return isEmailValid && isPasswordValid;
        }
        return isEmailValid;
    }

    private class SimpleTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}
