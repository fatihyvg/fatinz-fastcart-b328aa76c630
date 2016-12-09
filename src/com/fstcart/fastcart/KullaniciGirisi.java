package com.fstcart.fastcart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;

/**
 * Created by FTHY on 24.06.2014.
 */
public class KullaniciGirisi extends Activity {
    private EditText isim;
    private EditText sifreal;
    private Button giris;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kulgirisi);
        isim = (EditText) findViewById(R.id.isimal);
        sifreal = (EditText) findViewById(R.id.sfreal);
        giris = (Button) findViewById(R.id.girisal);
        giris.setOnClickListener(dinleyici);
    }

    private View.OnClickListener dinleyici = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
                String kulisim = isim.getText().toString();
                String sifre = sifreal.getText().toString();
                new KulGirisiASYNC().execute(kulisim, sifre);
        }

    };
        class KulGirisiASYNC extends AsyncTask<String, JSONObject, Boolean> {

            String kulisler = null;

            @Override
            protected Boolean doInBackground(String... params) {
                RestFull rst = new RestFull();
                boolean yetki = false;
                try {
                    JSONObject obj = rst.YetkiGirisi(params[0], params[1]);
                    JSONDONUSTURUCU donustur = new JSONDONUSTURUCU();
                    yetki = donustur.yetkidonustur(obj);
                    kulisler = params[0];

                } catch (Exception e) {
                    Log.d("exception", e.getMessage());
                }
                return yetki;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    Intent i = new Intent(KullaniciGirisi.this, KullaniciİslemMenusu.class);
                    i.putExtra("id", kulisler);
                    startActivity(i);
                } else {
                    Toast.makeText(context, "LÜTFEN KULLANICI ADINIZI VE ŞİFRENİZİ KONTROL EDİNİZ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected void onPreExecute() {
                Toast.makeText(context, "LÜTFEN BEKLEYİN", Toast.LENGTH_LONG).show();
            }
        }
    }
