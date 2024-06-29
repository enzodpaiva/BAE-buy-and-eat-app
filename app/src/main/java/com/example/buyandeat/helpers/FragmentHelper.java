package com.example.buyandeat.helpers;

import android.graphics.Color;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.buyandeat.R;
import com.example.buyandeat.databinding.BottonSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class FragmentHelper {

    public static void initToolbar(Fragment fragment, Toolbar toolbar) {
        AppCompatActivity activity = (AppCompatActivity) fragment.getActivity();
        assert activity != null;
        activity.setSupportActionBar(toolbar);
        activity.setTitle("");
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> {
            // Use OnBackPressedDispatcher
            activity.getOnBackPressedDispatcher().onBackPressed();
        });
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
    }

    public static void showBottomSheet(Fragment fragment, Integer titleDialog, Integer titleButton, int message, Runnable onClick) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(fragment.requireContext(), R.style.BottomSheetDialog);
        BottonSheetBinding bottonSheetBinding = BottonSheetBinding.inflate(LayoutInflater.from(fragment.getContext()), null, false);

        bottonSheetBinding.tvTitle.setText(titleDialog != null ? fragment.getString(titleDialog) : fragment.getString(R.string.atencao));
        bottonSheetBinding.tvMessage.setText(fragment.getText(message));
        bottonSheetBinding.btn.setText(titleButton != null ? fragment.getString(titleButton) : fragment.getString(R.string.ok));
        bottonSheetBinding.btn.setOnClickListener(v -> {
            onClick.run();
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.setContentView(bottonSheetBinding.getRoot());
        bottomSheetDialog.show();
    }

    // Overloaded method to provide default onClick behavior
    public static void showBottomSheet(Fragment fragment, Integer titleDialog, Integer titleButton, int message) {
        showBottomSheet(fragment, titleDialog, titleButton, message, () -> {
        });
    }
}
