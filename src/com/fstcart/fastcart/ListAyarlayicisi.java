package com.fstcart.fastcart;

public class ListAyarlayicisi {

	private int resim;
	private String isim;
	public ListAyarlayicisi(int resim,String isim){
		super();
		this.isim=isim;
		this.resim=resim;
	}
	public int getresim(){
		return resim;
	}
	public void setresim(int resim){
		this.resim=resim;
	}
	public String getisim(){
		return isim;
	}
	public void setisim(String isim){
		this.isim=isim;
	}
}
