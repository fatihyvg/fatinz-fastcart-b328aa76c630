package com.fstcart.fastcart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

public class MainActivity extends Activity {

	private ListView lstg;
	private ArrayAdapter<ListAyarlayicisi> koyma;
	private ArrayList<ListAyarlayicisi> ayarla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstg=(ListView)findViewById(R.id.lst);
        Ayarlama();
        koyma=new listayarla();
        lstg.setAdapter(koyma);
        lstg.setOnItemClickListener(klikle);
        
  
    }
    private OnItemClickListener klikle=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
		    
			
			 if(arg2==0){
				 Intent i=new Intent(MainActivity.this,KullaniciGirisi.class);
				 startActivity(i);
			 }
			 else if(arg2==1){
				 Intent i=new Intent(MainActivity.this,KullaniciKaydet.class);
				 startActivity(i);

			 }
		}
    	
	};
	private void Ayarlama(){
		ayarla=new ArrayList<ListAyarlayicisi>();
		ayarla.add(new ListAyarlayicisi(R.drawable.kullanici, "KULLANICI GİRİŞİ"));
		ayarla.add(new ListAyarlayicisi(R.drawable.kayit,"KAYDOL"));

	}
    class listayarla extends ArrayAdapter<ListAyarlayicisi>{

    	private View view;
    	private ListAyarlayicisi ayr;
    	public listayarla(){
    		super(MainActivity.this,R.layout.listogeleri,ayarla);
    	}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		      view=convertView;
		      if(view==null){
		    	  view=getLayoutInflater().inflate(R.layout.listogeleri, parent,false);
		      }
		     
		      ayr=ayarla.get(position);
		      komponentler();
		      return view;
		}
    	public void komponentler(){
    		
		      ImageView res=(ImageView)view.findViewById(R.id.img);
		      res.setImageResource(ayr.getresim());
		      TextView is=(TextView)view.findViewById(R.id.isim);
		      is.setText(ayr.getisim());
    	}
    }
}
