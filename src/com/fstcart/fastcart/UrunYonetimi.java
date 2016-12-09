package com.fstcart.fastcart;

/**
 * Created by FTHY on 25.06.2014.
 */
public class UrunYonetimi {
    private int adet;
    private String urunısmi;
    private String urunfiyati;
    public UrunYonetimi(String urunısmi, String urunfiyati) {
        super();
        this.urunısmi = urunısmi;
        this.urunfiyati = urunfiyati;
    }
    public UrunYonetimi() {
    }
    public int getadet() {
        return adet;
    }

    public void setadet(int adet) {
        this.adet = adet;
    }

    public String geturunisim() {
        return urunısmi;
    }

    public void seturunisim(String urunısmi) {
        this.urunısmi = urunısmi;
    }

    public String geturunfiyat() {
        return urunfiyati;
    }

    public void setururunfiyat(String urunfiyati) {
        this.urunfiyati = urunfiyati;
    }
}
