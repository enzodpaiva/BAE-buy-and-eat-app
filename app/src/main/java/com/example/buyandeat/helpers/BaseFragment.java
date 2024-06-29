package com.example.buyandeat.helpers;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(requireView().getWindowToken(), 0);
    }

}
