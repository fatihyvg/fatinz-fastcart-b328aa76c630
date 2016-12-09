package com.fstcart.fastcart;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by FTHY on 16.07.2014.
 */
public class Ködeme extends Activity {
    private EditText isimal;
    private EditText soyisimal;
    private EditText kkartnumarsıal;
    private EditText cvcnumaraal;
    private Button but;
    private String isim="demo";
    private String soyisim="demo";
    private String knumaralar="1234567891011";
    private int cvnumara=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kredikartodemeal);
        Intent i=getIntent();
        isimal=(EditText)findViewById(R.id.is);
        soyisimal=(EditText)findViewById(R.id.sis);
        kkartnumarsıal=(EditText)findViewById(R.id.knumara);
        cvcnumaraal=(EditText)findViewById(R.id.cvc);
        but=(Button)findViewById(R.id.odemeler);
        but.setOnClickListener(dinleyici);
    }
    private View.OnClickListener dinleyici=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
             if(isim==isimal.getText().toString() && soyisim == soyisimal.getText().toString() && knumaralar == kkartnumarsıal.getText().toString() && cvnumara == cvcnumaraal.getText().length()){
                 Toast.makeText(getApplicationContext(),"ÜRÜN BEDELİ ÖDENDİ",Toast.LENGTH_LONG).show();
             }
            else{
                 Toast.makeText(getApplicationContext(),"<ÜRÜN BEDELİ ALINDI",Toast.LENGTH_LONG).show();
             }
        }
    };
}
