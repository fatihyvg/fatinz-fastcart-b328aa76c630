package com.fstcart.fastcart;

/**
 * Created by FTHY on 23.06.2014.
 */
public class KullaniciOlustur {
    private String isim;
    private String soyisim;
    private String kulisim;
    private String sifre;
    private String email;
    public KullaniciOlustur(){
        super();
    }
    public KullaniciOlustur(String isim,String soyisim,String kulisim,String sifre){
        super();
        this.isim=isim;
        this.soyisim=soyisim;
        this.kulisim=kulisim;
        this.sifre=sifre;
    }
    public String getemail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getisim(){
        return isim;
    }
    public void setisim(String isim){
        this.isim=isim;
    }
    public String getsoyisim(){
        return soyisim;
    }
    public void setsoyisim(String soyisim){
        this.soyisim=soyisim;
    }
    public String getkulisim(){
        return kulisim;
    }
    public void setkulisim(String kulisim){
        this.kulisim=kulisim;
    }
    public String getsifre(){
        return sifre;
    }
    public void setsifre(String sifre){
        this.sifre=sifre;
    }
}
