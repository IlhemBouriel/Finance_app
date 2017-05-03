package tn.gl4.finance;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Map;

import tn.gl4.finance.util.CoursParser;

public class MarcheTitresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marche_titres);
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

        retrieveCours();
    }

    private void createTable(Map<String,String[]> cours) {
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
        TableLayout tableNomsTitres = (TableLayout) findViewById(R.id.tableNomsTitres); //new TableRow(this);

        TextView tv1 = new TextView(this) ; tv1.setText((" Nom Titre ")); TableRow headrowTitre = new TableRow(this);headrowTitre.addView(tv1);
        tableNomsTitres.addView(headrowTitre);

        TableRow headrow = new TableRow(this);
        TextView tv2 = new TextView(this) ; tv2.setText((" Ouverture ")); headrow.addView(tv2);
        TextView tv3 = new TextView(this) ; tv3.setText((" +haut ")); headrow.addView(tv3);
        TextView tv4 = new TextView(this) ; tv4.setText((" +bas ")); headrow.addView(tv4);
        TextView tv5 = new TextView(this) ; tv5.setText((" volume(titres) ")); headrow.addView(tv5);
        TextView tv6 = new TextView(this) ; tv6.setText((" volume(DT) ")); headrow.addView(tv6);
        TextView tv7 = new TextView(this) ; tv7.setText((" Dernier ")); headrow.addView(tv7);
        TextView tv8 = new TextView(this) ; tv8.setText((" Variation ")); headrow.addView(tv8);
        headrow.setGravity(Gravity.CENTER);
        table.addView(headrow);


        int s = 0 ;
        for (String[] titreData : cours.values()) {
            ++s ;
            TableRow row = new TableRow(this); //create a new tablerow
            row.setGravity(Gravity.CENTER); //set it to be center (you can remove this if you don't want the row to be in the center)


            TableRow nomTitreRow = new TableRow(this);
            if(s%3==0) {
                nomTitreRow.setBackgroundColor(getResources().getColor(R.color.colorDarkStripe));
                row.setBackgroundColor(getResources().getColor(R.color.colorDarkStripe));
            }else if (s%3==2) {
              //  nomTitreRow.setBackgroundColor(getResources().getColor(R.color.colorMediumStripe));
             //   row.setBackgroundColor(getResources().getColor(R.color.colorMediumStripe));
            }


            for(int i=0;i<titreData.length;i++)
            {
                TextView cellule = new TextView(this) ;
                cellule.setText(titreData[i]);
                cellule.setGravity(Gravity.CLIP_VERTICAL);

                if(i==0) nomTitreRow.addView(cellule);
                else row.addView(cellule);
            }
            tableNomsTitres.addView(nomTitreRow);
            table.addView(row);
        }
    }


    //----------------------------------------------


    private void retrieveCours() {
        Log.i("Network", "retrieve data");
        new Thread(new Runnable() {
            public void run() {
                CoursParser p = new CoursParser();
                final Map<String, String[]> m = p.getAllCours();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        createTable(m);
                    }
                });
            }
        }).start();
    }

    }
