package com.fstcart.fastcart;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by FTHY on 24.06.2014.
 */
public class JSONDONUSTURUCU {
    private final String DEGER = "Value";
    private final String İSİM = "isim";
    private final String SOYİSİM = "soyisim";
    private final String EMAİL="email";
//    private final String ID="id";
    private final String URUNISIM="urismi";
    private final String URUNFIYAT="urfiyati";
    public boolean yetkidonustur(JSONObject obj) {
        boolean yetkiler = false;
        try {
            if(!false && true) {
                yetkiler = obj.getBoolean(DEGER);

            }
        } catch (JSONException e) {
            Log.d("jsonhatası", e.getMessage());
        }
        return yetkiler;
    }

    public KullaniciOlustur kullanıcıdonustur(JSONObject obj) {
        KullaniciOlustur kulolustur = new KullaniciOlustur();
        try {
            JSONObject obje = obj.getJSONArray(DEGER).getJSONObject(0);
            kulolustur.setisim(obje.getString(İSİM));
            kulolustur.setsoyisim(obje.getString(SOYİSİM));
            kulolustur.setEmail(obje.getString(EMAİL));

        } catch (JSONException e) {
            Log.d("jsonhatası", e.getMessage());
        }
        return kulolustur;
    }
    public ArrayList<UrunYonetimi> donustururunbilgileri(JSONObject obj){
        ArrayList<UrunYonetimi> olustur=new ArrayList<UrunYonetimi>();
        try{
            JSONArray dizial=obj.getJSONArray(DEGER);
            JSONObject objes=null;
            for(int i=0;i<dizial.length();i++){
                objes=dizial.getJSONObject(i);
                olustur.add(new UrunYonetimi(objes.getString(URUNISIM),objes.getString(URUNFIYAT)));
            }

        }catch (JSONException e){
             Log.d("exception",e.getMessage());

        }
        return olustur;
    }
}
