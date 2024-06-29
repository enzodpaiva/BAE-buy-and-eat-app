package com.example.buyandeat.ui.products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.buyandeat.R;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<AddProductFragment.Product> {

    public ProductAdapter(@NonNull Context context, @NonNull List<AddProductFragment.Product> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_product, parent, false);
        }

        AddProductFragment.Product product = getItem(position);

        TextView tvProductName = convertView.findViewById(R.id.tvProductName);
        TextView tvProductDescription = convertView.findViewById(R.id.tvProductDescription);
        TextView tvProductPrice = convertView.findViewById(R.id.tvProductPrice);

        if (product != null) {
            tvProductName.setText(product.name);
            tvProductDescription.setText(product.description);
            tvProductPrice.setText(product.price);
        }

        return convertView;
    }
}
