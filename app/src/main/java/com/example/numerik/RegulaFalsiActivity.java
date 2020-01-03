package com.example.numerik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class RegulaFalsiActivity extends AppCompatActivity {
    EditText edtA,edtB,edtIterasi,edtError;
    Button btnHitung;
    ListView lvHasil;
    String fx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regula_falsi);
        setTitle("Regula Falsi");
        edtA = findViewById(R.id.edt_a);
        edtB = findViewById(R.id.edt_b);
        edtIterasi =findViewById(R.id.edt_n);
        edtError =findViewById(R.id.edt_error);
        btnHitung =findViewById(R.id.btn_hitung);
        lvHasil =findViewById(R.id.lv_hasil);

        fx=getIntent().getStringExtra("FX");

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    View view=getCurrentFocus();
                    imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                    hitungMetode();
                }catch (Exception e){
                    Toast.makeText(RegulaFalsiActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void hitungMetode(){

        double a=Double.valueOf(edtA.getText().toString());
        double b=Double.valueOf(edtB.getText().toString());
        double error=Double.valueOf(edtError.getText().toString());
        int n=Integer.valueOf(edtIterasi.getText().toString());

        RegulaFalsi regulaFalsi=new RegulaFalsi(a,b,error,n,fx);
        regulaFalsi.run();
        double[][] hasil=regulaFalsi.getResult();
        String items[] =new String[hasil.length];
        for (int i=0; i<hasil.length;i++){
            items[i]="Iterasi ke "+(i+1)+"\n"+
                    "a : \t"+hasil[i][0]+"\n"+
                    "f(a) : \t"+hasil[i][1]+"\n"+
                    "b : \t"+hasil[i][2]+"\n"+
                    "f(b) : \t"+hasil[i][3]+"\n"+
                    "x : \t"+hasil[i][4]+"\n"+
                    "f(x) \t: "+hasil[i][5]+"\n";
        }

        Toast.makeText(getApplicationContext(), "x : "+String.valueOf(regulaFalsi.x), Toast.LENGTH_SHORT).show();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,items);
        lvHasil.setAdapter(adapter);

    }
}
