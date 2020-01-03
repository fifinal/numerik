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

public class NewtonActivity extends AppCompatActivity {
    EditText edtX,edtIterasi,edtError;
    Button btnHitung;
    ListView lvHasil;
    String fx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton);
        setTitle("Newton Raphson");
        edtX = findViewById(R.id.edt_x);
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
                    Toast.makeText(NewtonActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void hitungMetode(){

        double x=Double.valueOf(edtX.getText().toString());
        double error=Double.valueOf(edtError.getText().toString());
        int n=Integer.valueOf(edtIterasi.getText().toString());

        NewtonRaphson newtonRaphson=new NewtonRaphson(x,error,n,fx);
        newtonRaphson.run();
        double[][] hasil=newtonRaphson.getResult();
        String items[] =new String[hasil.length];
        for (int i=0; i<hasil.length;i++){
            items[i]="Iterasi ke "+(i+1)+"\n"+
                    "x : \t"+hasil[i][0]+"\n"+
                    "f(x) : \t"+hasil[i][1]+"\n"+
                    "f'(x) \t: "+hasil[i][2]+"\n";
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,items);
        lvHasil.setAdapter(adapter);

    }
}
