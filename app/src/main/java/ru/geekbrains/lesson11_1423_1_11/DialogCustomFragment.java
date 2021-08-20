package ru.geekbrains.lesson11_1423_1_11;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogCustomFragment extends DialogFragment {


    private EditText editText;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_dialog_custom,container,false);
        initView(view);
        initListeners();

        //TODO cancelable
        setCancelable(false);
        return view;
    }

    private void initListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer  = editText.getText().toString();
                ((MainActivity)  requireActivity()).onResultDialogFragment(answer);
                dismiss();
            }
        });
    }

    private void initView(View view) {
        editText = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.button);
    }
}
