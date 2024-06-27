package com.example.buyandeat.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyandeat.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Product> cartProductList;

    public CartAdapter(List<Product> cartProductList) {
        this.cartProductList = cartProductList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_product, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartProductList.get(position);
        holder.textViewName.setText(product.getName());
        holder.textViewPrice.setText(String.format("R$ %.2f", product.getPrice()));
        holder.textViewQuantity.setText(String.format("Quantidade: %d", product.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return cartProductList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewPrice, textViewQuantity;;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewNameCart);
            textViewPrice = itemView.findViewById(R.id.textViewPriceCart);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantityCart);
        }
    }
}
