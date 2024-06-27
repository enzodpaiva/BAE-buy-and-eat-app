package com.example.buyandeat.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buyandeat.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Product> cartProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().findFragmentById(binding.navHostFragment.getId());
    }

    public void addToCart(Product product) {
        cartProductList.add(product);
    }

    public List<Product> getCartProductList() {
        return cartProductList;
    }
}