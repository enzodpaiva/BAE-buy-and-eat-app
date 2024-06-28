package com.example.buyandeat.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyandeat.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private OnAddToCartClickListener onAddToCartClickListener;

    public ProductAdapter(List<Product> productList, OnAddToCartClickListener onAddToCartClickListener) {
        this.productList = productList;
        this.onAddToCartClickListener = onAddToCartClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView tvProductName, tvProductDescription, tvProductPrice;
        private ImageView ivProductImage;
        private Button addToCartButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
            addToCartButton.setOnClickListener(v -> {
                Product product = productList.get(getAdapterPosition());
                onAddToCartClickListener.onAddToCartClicked(product);
            });
        }

        public void bind(Product product) {
            tvProductName.setText(product.getName());
            tvProductDescription.setText(product.getDescription());
            tvProductPrice.setText(String.format("R$ %.2f", product.getPrice()));
            ivProductImage.setImageResource(product.getImageResource());
        }
    }

    public interface OnAddToCartClickListener {
        void onAddToCartClicked(Product product);
    }
}
