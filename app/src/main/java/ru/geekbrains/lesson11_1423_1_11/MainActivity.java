package ru.geekbrains.lesson11_1423_1_11;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alertDialog1 = findViewById(R.id.alertDialog1);
        alertDialog1.setOnClickListener(clickListenerDialog1); // TODO ссылка на маркет

        Button alertDialog3 = findViewById(R.id.alertDialog3);
        alertDialog3.setOnClickListener(clickListenerDialog3);

        Button alertDialogList = findViewById(R.id.alertDialogList);
        alertDialogList.setOnClickListener(clickListenerDialogList);

        Button alertDialogListSingle = findViewById(R.id.alertDialogListSingle);
        alertDialogListSingle.setOnClickListener(clickListenerDialogListSingle);

        Button alertDialogListMulti = findViewById(R.id.alertDialogListMulti);
        alertDialogListMulti.setOnClickListener(clickListenerDialogListMulti);

        Button alertDialogCustom = findViewById(R.id.alertDialogCustom);
        alertDialogCustom.setOnClickListener(clickListenerDialogCustom);

        Button fragmentDialogBuilder = findViewById(R.id.fragmentDialogBuilder);
        fragmentDialogBuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBuilderFragment dialogBuilderFragment = new DialogBuilderFragment();
                dialogBuilderFragment.show(getSupportFragmentManager(),"TAG");
            }
        });

        Button fragmentCustomBuilder = findViewById(R.id.fragmentCustomBuilder);
        fragmentCustomBuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogCustomFragment dialog = new DialogCustomFragment();
                dialog.show(getSupportFragmentManager(),"TAG");
            }
        });

        Button fragmentBottomDialog = findViewById(R.id.fragmentBottomDialog);
        fragmentBottomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyBottomSheetDialogFragment myBottomSheetDialogFragment = new MyBottomSheetDialogFragment();
                myBottomSheetDialogFragment.setOnDialogListener(onDialogListener);
                myBottomSheetDialogFragment.show(getSupportFragmentManager(),"Tag");
            }
        });
    }

    public void onResultDialogFragment(String answer){
        Log.d("mylogs", String.format("Вернуло: %s", answer));
    }

    private final OnDialogListener onDialogListener = new OnDialogListener() {

        @Override
        public void pressOk() {
            Log.d("mylogs", " pressOk");
        }

        @Override
        public void pressNo() {
            Log.d("mylogs", " pressNo");
        }
    };
    private final View.OnClickListener clickListenerDialogCustom = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            View frameLayout = getLayoutInflater().inflate(R.layout.dialog_custom,null);
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.exclamation)
                    .setView(frameLayout)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            EditText editText = frameLayout.findViewById(R.id.editText);
                            Log.d("mylogs", String.format("Вернуло: %s", editText.getText().toString()));
                        }
                    });
            android.app.AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

    private final View.OnClickListener clickListenerDialog1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.exclamation)
                    .setMessage(R.string.message)
                    .setPositiveButton(R.string.yes, dialogListener);
            android.app.AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

    private final View.OnClickListener clickListenerDialog3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.exclamation)
                    .setMessage(R.string.message)
                    .setPositiveButton(R.string.yes, dialogListener)
                    .setNegativeButton(R.string.no, dialogListener)
                    .setNeutralButton(R.string.neutral, dialogListener);
            android.app.AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

    private final View.OnClickListener clickListenerDialogList = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String[] variants = getResources().getStringArray(R.array.variants);
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.exclamation)
                    .setItems(variants, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.d("mylogs", "Choose " + variants[i]);
                        }
                    });
            android.app.AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

    private int chosenSingle = -1;
    private final View.OnClickListener clickListenerDialogListSingle = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String[] variants = getResources().getStringArray(R.array.variants);
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.exclamation)
                    .setSingleChoiceItems(variants, chosenSingle, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            chosenSingle = i;
                            Log.d("mylogs", "Choose " + variants[i]);
                        }
                    })
                    .setPositiveButton(R.string.yes, dialogListener)
                    .setNegativeButton(R.string.no, dialogListener)
                    .setNeutralButton(R.string.neutral, dialogListener);
            android.app.AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

    private boolean[] chosenMulti = {false,false,false};
    private final View.OnClickListener clickListenerDialogListMulti = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String[] variants = getResources().getStringArray(R.array.variants);
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.exclamation)
                    .setMultiChoiceItems(variants, chosenMulti, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            chosenMulti[i] = b;
                            Log.d("mylogs", "Choose " + variants[i]);
                        }
                    })
                    .setPositiveButton(R.string.yes, dialogListener)
                    .setNegativeButton(R.string.no, dialogListener)
                    .setNeutralButton(R.string.neutral, dialogListener);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

    private DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Log.d("mylogs", "Что-то пришло" + i);
        }
    };
}