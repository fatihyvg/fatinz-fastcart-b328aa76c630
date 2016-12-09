package com.fstcart.fastcart;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.*;
import android.widget.*;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by FTHY on 14.07.2014.
 */
public class KullaniciİslemMenusu extends Activity {
    private ListView lst;
    private ListView analst;
    private ActionBarDrawerToggle barekle;
    private DrawerLayout slidecizim;
    private AdaptorAyarla ayarla;
    private AlertDialog dialog;
    private AlertDialog ikincidialog;
    private EditText sonuctext;
    private EditText adetg;
    private Button sonuckoyma;
    private TextView toplamal;
    private ArrayList<String> listele;
    private ArrayAdapter<String> adaptor;
    private DecimalFormat defformatla=new DecimalFormat("0.00##");
    private double toplamkoy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kulislemleri);
        lst=(ListView)findViewById(R.id.acmenu);
        analst=(ListView)findViewById(R.id.analist);
        toplamal=(TextView)findViewById(R.id.toplam);
        ayarla=new AdaptorAyarla(this);
        slidecizim=(DrawerLayout)findViewById(R.id.govde);
        lst.setAdapter(ayarla);
        lst.setOnItemClickListener(dinleyici);
        analst.setOnItemClickListener(anaalıcı);
        listele=new ArrayList<String>();
        adaptor=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listele);
        analst.setAdapter(adaptor);
        Intent ic=getIntent();
        String al=ic.getStringExtra("id");
        new ASYNCKULLANICIBİLGİLERİ().execute(al);
        barekle=new ActionBarDrawerToggle(this,slidecizim,R.drawable.op,0,1){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        slidecizim.setDrawerListener(barekle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult ints=SonuclaraKoy(requestCode,resultCode,data);
        if(ints != null && data != null){
            String barkod=ints.getContents();
            sonuctext.setText(barkod);
        }
    }
    private IntentResult SonuclaraKoy(int istekkodu,int sonuckodu,Intent data){
        return IntentIntegrator.parseActivityResult(istekkodu,sonuckodu,data);
    }
    private AdapterView.OnItemClickListener dinleyici=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              secilenoge(i);
        }
    };
    private AdapterView.OnItemClickListener anaalıcı=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              ikincidialog.show();
        }
    };
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        barekle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(barekle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        barekle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        View v=menu.findItem(R.id.barkodlar).getActionView();
        sonuctext=(EditText)v.findViewById(R.id.alınankod);
        sonuckoyma=(Button)v.findViewById(R.id.onayla);
        sonuckoyma.setOnClickListener(dinle);
        return super.onCreateOptionsMenu(menu);

    }
    private View.OnClickListener dinle=new View.OnClickListener() {
        @Override
        public void onClick(View view) {

             if(sonuctext.getText().length() > 0){
                 String alıcıkoy=sonuctext.getText().toString();
                 new ASYNCURUNBİLGİLERİAL().execute(alıcıkoy);
             }else{
                 IntentIntegrator ints = new IntentIntegrator(KullaniciİslemMenusu.this);
                 ints.initiateScan();
             }
        }
    };

    public void secilenoge(int position){
        lst.setItemChecked(position,true);
        switch (position){
            case 0:
                dialog.show();
                break;
            case 1:
                Intent i=new Intent(KullaniciİslemMenusu.this,KrediKartOdeme.class);
                i.putExtra("fiyatal",toplamal.getText().toString());
                startActivity(i);
                break;
        }
    }
    public void baslıkayarla(String baslık){
        getActionBar().setTitle(baslık);
    }
    class ASYNCURUNBİLGİLERİAL extends AsyncTask<String,JSONObject,ArrayList<UrunYonetimi>>{
        ArrayList<UrunYonetimi> yonet=null;
        @Override
        protected ArrayList<UrunYonetimi> doInBackground(String... params) {
            RestFull rst=new RestFull();
            try{
               JSONObject obj=rst.UrunBilgileriAl(params[0]);
               JSONDONUSTURUCU donustur=new JSONDONUSTURUCU();
               yonet=donustur.donustururunbilgileri(obj);

            }catch (Exception e){
                Log.d("exception",e.getMessage());
                e.printStackTrace();
            }
            return yonet;
        }

        @Override
        protected void onPostExecute(final ArrayList<UrunYonetimi> urunYonetimis) {
           for(final UrunYonetimi yonetimler : urunYonetimis) {
               listele.add(yonetimler.geturunisim() + " " + yonetimler.geturunfiyat());

               View view = getLayoutInflater().inflate(R.layout.adetayarla, null);
               adetg = (EditText) view.findViewById(R.id.adetgirisi);
               final AlertDialog.Builder dialog = new AlertDialog.Builder(KullaniciİslemMenusu.this);
               dialog.setTitle("ADET GİRİNİZ");
               dialog.setCancelable(false);
               dialog.setView(view);
               dialog.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                       try {
                           int adetgir = Integer.valueOf(adetg.getText().toString());
                           yonetimler.setadet(adetgir);
                           int adetal = yonetimler.getadet();
                           double fiyatlama = Double.valueOf(yonetimler.geturunfiyat());
                           toplamkoy += fiyatlama * adetal;
                           toplamal.setText(defformatla.format(toplamkoy) + ".TL");
                       }catch (Exception e){
                           Log.d("Boş Bir Değer Girildi",e.getMessage());
                           e.getCause();
                           e.printStackTrace();
                           e.fillInStackTrace();
                           e.getLocalizedMessage();
                       }

                   }
               })
              .setNegativeButton("SİL", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               for(int iz=urunYonetimis.size()-1;iz>=0;iz--){
                                  listele.remove(iz);
                                  double fiyatdonustur=Double.valueOf(yonetimler.geturunfiyat());
                                  toplamkoy-=fiyatdonustur*yonetimler.getadet();


                               }
                               adaptor.notifyDataSetChanged();
                               toplamal.setText(String.valueOf(defformatla.format(toplamkoy))+".TL");
                           }
                       });
               ikincidialog=dialog.create();
               adaptor.notifyDataSetChanged();
           }

        }
    }
    class ASYNCKULLANICIBİLGİLERİ extends AsyncTask<String,Void,KullaniciOlustur>{
        @Override
        protected KullaniciOlustur doInBackground(String... args) {
            KullaniciOlustur olustur=null;
            RestFull rst=new RestFull();
            try{
                JSONObject obj=rst.DetaylariAl(args[0]);
                JSONDONUSTURUCU donustur=new JSONDONUSTURUCU();
                olustur=donustur.kullanıcıdonustur(obj);

            }catch (Exception e){
                Log.d("exception",e.getMessage());
                e.printStackTrace();
                e.fillInStackTrace();
                e.getCause();
            }
            return olustur;
        }

        @Override
        protected void onPostExecute(KullaniciOlustur kullaniciOlustur) {
            View view=getLayoutInflater().inflate(R.layout.kullanicialertbilgileri,null);
            TextView bir=(TextView)view.findViewById(R.id.isimbilgileri);
            TextView iki=(TextView)view.findViewById(R.id.isimemailbilgileri);
            bir.setText(kullaniciOlustur.getisim()+" "+kullaniciOlustur.getsoyisim());
            iki.setText(kullaniciOlustur.getemail());
            AlertDialog.Builder ydialog=new AlertDialog.Builder(KullaniciİslemMenusu.this);
            ydialog.setTitle("KULLANICI BİLGİLERİ");
            ydialog.setCancelable(false);
            ydialog.setView(view);
            ydialog.setPositiveButton("TAMAM",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                }
            });
            dialog=ydialog.create();

        }
    }
}

class AdaptorAyarla extends BaseAdapter{
    private String[] bilgiler;
    private int[] resimler={R.drawable.bilgi,R.drawable.odeme};
    private Context context;
    public AdaptorAyarla(Context context) {
        this.context=context;
        bilgiler=context.getResources().getStringArray(R.array.diziler);
    }

    @Override
    public int getCount() {
        return bilgiler.length;
    }

    @Override
    public Object getItem(int i) {
        return bilgiler[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View satır;
        if(view==null){
            LayoutInflater inf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            satır=inf.inflate(R.layout.slidelist,viewGroup,false);
        }
        else{
            satır=view;
        }
        TextView a=(TextView)satır.findViewById(R.id.baslık);
        ImageView b=(ImageView)satır.findViewById(R.id.res);
        a.setText(bilgiler[i]);
        b.setImageResource(resimler[i]);
        return satır;
    }
}
