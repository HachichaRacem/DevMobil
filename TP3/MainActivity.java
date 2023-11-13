package com.example.hachicha_racem_mesure_glycemie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etValeur;
    private SeekBar sbAge;
    private TextView tvAge, tvResultat;
    private RadioButton rbtOui;
    private Button btnConsulter;
    private void init()
    {
        etValeur = findViewById(R.id.etValeur);
        sbAge = findViewById(R.id.sbAge);
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.i("Information", "onProgressChanged " + progress);
                tvAge.setText("Votre âge : " + progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        rbtOui = findViewById(R.id.rbtOui);
        tvResultat = findViewById(R.id.tvResultat);
        tvAge = findViewById(R.id.tvAge);
        btnConsulter = findViewById(R.id.btnConsulter);
    }
    public void calculer(){
        boolean isNormal = true;
        double vGlycemie = Double.parseDouble(etValeur.getText().toString());
        int age = sbAge.getProgress();
        if(rbtOui.isChecked()){
            if(vGlycemie > 10.5){
                isNormal = false;
            }
        }else{
            if(age < 6){
                if(!(vGlycemie > 5.5 && vGlycemie < 10.0)){
                    isNormal = false;
                }
            }else{
                if(age < 13){
                    if(!(vGlycemie > 5.0 && vGlycemie < 10.0)){
                        isNormal = false;
                    }
                }else{
                    if(!(vGlycemie > 5.0 && vGlycemie < 7.2)){
                        isNormal = false;
                    }
                }
            }
        }
        String response = isNormal ? "Niveau Normale" : "Niverau Anormale";
        tvResultat.setText(response);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculer();
            }
        });
    }

}