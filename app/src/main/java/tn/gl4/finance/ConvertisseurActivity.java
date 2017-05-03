package tn.gl4.finance;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import tn.gl4.finance.util.Devise;
import tn.gl4.finance.util.TauxParser;

public class ConvertisseurActivity extends AppCompatActivity {

    TauxParser tauxParser;
    float montant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertisseur);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tauxParser = new TauxParser();


        loadSpinners();
        setUpButtons();
        setUpEditText();
    }

    private void setUpEditText() {
        final EditText montantET = (EditText) findViewById(R.id.montantText);
        montantET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String st = s.toString();
                try {
                    montant = Float.parseFloat(st);
                }catch(NumberFormatException nfe){
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void setUpButtons() {
        setUpConvertItButton();

    }

    private void setUpConvertItButton() {
        Button convertButton = (Button) findViewById(R.id.buttonConvertIt);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    public void run() {

                        try {

                            Devise from = Devise.values()[((Spinner) findViewById(R.id.spinnerSourceCurrency)).getSelectedItemPosition()];
                            Devise to = Devise.values()[((Spinner) findViewById(R.id.spinnerDestCurrency)).getSelectedItemPosition()];

                            final float taux = tauxParser.getTaux(from, to);
                            final float resultat = taux* montant ;


                            final TextView currencyRateTV = (TextView) findViewById(R.id.currencyRateTV);
                            final TextView conversionResultTV = (TextView) findViewById(R.id.conversionResultTV);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    currencyRateTV.setText(String.valueOf(taux));
                                    conversionResultTV.setText(String.valueOf(resultat));

                                }
                            });

                        }catch (Exception e){
                            showErrorDialog();
                        }
                    }
                }).start();
            }
        });
    }

    private void showErrorDialog(){
        showDialog("Connexion internet indisponible pour obtenir les taux de change");
    }
    private void showDialog(String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(message);
        builder1.setCancelable(true);


        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void loadSpinners() {
        loadSpinnerWithCurrencies(R.id.spinnerSourceCurrency);
        setDefaultCurrency(R.id.spinnerSourceCurrency, Devise.TND);
        loadSpinnerWithCurrencies(R.id.spinnerDestCurrency);
        setDefaultCurrency(R.id.spinnerDestCurrency, Devise.EUR);
    }

    private void setDefaultCurrency(int spinnerId, Devise defaultSelected) {
        for(int i=0;i<Devise.values().length;i++){
            if (defaultSelected != null && Devise.values()[i].equals(defaultSelected))
            {
                ((Spinner) findViewById(spinnerId)).setSelection(i);
                break ;
            }
        }
    }

    private void loadSpinnerWithCurrencies(int spinnerId) {

        String devises[] = new String[Devise.values().length];
        for(int i=0;i<Devise.values().length;i++){
            devises[i] = Devise.values()[i].fullName();
        }
        Spinner spinner = (Spinner) findViewById(spinnerId);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, devises);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
    }
}
