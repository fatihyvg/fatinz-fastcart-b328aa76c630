package com.fstcart.fastcart;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class KullaniciKaydet extends Activity {

	private EditText isimler;
	private EditText sisimler;
	private EditText kulisimler;
	private EditText sifreler;
	private Button kaydet;
    private KullaniciOlustur olusturma;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kaydol);
		isimler=(EditText)findViewById(R.id.isim);
		sisimler=(EditText)findViewById(R.id.soyisim);
		kulisimler=(EditText)findViewById(R.id.kulisim);
		sifreler=(EditText)findViewById(R.id.sifre);
		kaydet=(Button)findViewById(R.id.giris);
        kaydet.setOnClickListener(dinleyici);


	}
    private View.OnClickListener dinleyici=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String isim;
            String soyisim;
            String kulisim;
            String sifre;
            isim=isimler.getText().toString();
            soyisim=sisimler.getText().toString();
            kulisim=kulisimler.getText().toString();
            sifre=sifreler.getText().toString();
            olusturmaayarla(isim,soyisim,kulisim,sifre);
            new KullaniciEkleASYNC().execute(olusturma);
        }
    };
	class KullaniciEkleASYNC extends AsyncTask<KullaniciOlustur,Void,Void>{
        @Override
        protected Void doInBackground(KullaniciOlustur... params) {
            RestFull rst=new RestFull();

            try{

                rst.KullaniciEkle(params[0].getisim(),params[0].getsoyisim(),params[0].getkulisim(),params[0].getsifre());
            }catch (Exception e){
                Log.d("exception",e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
              loginegit();
        }
    }
    private KullaniciOlustur olusturmaayarla(String isim,String soyisim,String kulisim,String sifre){
          return olusturma=new KullaniciOlustur(isim,soyisim,kulisim,sifre);
    }
    private void loginegit(){
        Intent i = new Intent(KullaniciKaydet.this, KullaniciGirisi.class);
        startActivity(i);
    }
}
