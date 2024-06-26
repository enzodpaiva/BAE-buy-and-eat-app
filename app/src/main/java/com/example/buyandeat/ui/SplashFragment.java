package com.example.buyandeat.ui;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.buyandeat.R;
import com.example.buyandeat.databinding.FragmentSplashBinding;
import com.google.firebase.auth.FirebaseAuth;


public class SplashFragment extends Fragment {

    private static final int SPLASH_TIME_SECONDS = 1;

    private FragmentSplashBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Handler(Looper.getMainLooper()).postDelayed(this::checkUserLogged, SPLASH_TIME_SECONDS * 1000);
    }

    private void checkUserLogged() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            findNavController(requireView()).navigate(R.id.action_splashFragment_to_homeFragment);
        } else {
            findNavController(requireView()).navigate(R.id.action_splashFragment_to_auth);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}