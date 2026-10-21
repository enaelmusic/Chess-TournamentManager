package com.example.swisstournament2.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.swisstournament2.R;

public class ConfirmationPlayerOut extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            return new AlertDialog.Builder(requireContext())
                    .setMessage(getString(R.string.confirmationOut))
                    .setPositiveButton(getString(R.string.ok), (dialog, which) -> {} )
                    .create();
        }

        public static String TAG = "Joueur OUT";

}
