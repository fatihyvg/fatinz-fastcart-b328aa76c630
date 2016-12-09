package com.fstcart.fastcart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by FTHY on 02.07.2014.
 */
public class SplashEkran extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashekran);
        Thread thr=new Thread(){
            @Override
            public void run() {
                try{
                    sleep(5000);
                    Intent i=new Intent(SplashEkran.this,MainActivity.class);
                    startActivity(i);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    finish();

                }
            }
        };
        thr.start();
    }
}
