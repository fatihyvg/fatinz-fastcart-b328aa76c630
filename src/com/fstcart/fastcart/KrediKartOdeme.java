package com.fstcart.fastcart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by FTHY on 16.07.2014.
 */
public class KrediKartOdeme extends Activity {
    private RadioGroup grupal;
    private RadioButton secenek;
    private RadioButton secenekiki;
    private TextView ödenecktutar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.odemeekrani);
        grupal=(RadioGroup)findViewById(R.id.secenekler);
        secenek=(RadioButton)findViewById(R.id.bir);
        secenekiki=(RadioButton)findViewById(R.id.iki);
        Intent i=getIntent();
        final String al=i.getStringExtra("fiyatal");
        ödenecktutar=(TextView)findViewById(R.id.tutar);
        ödenecktutar.setText(al);
        secenek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(secenek.isChecked()){
                    Intent i=new Intent(KrediKartOdeme.this,Ködeme.class);
                    i.putExtra("ködeme",al);
                    startActivity(i);
                }
            }
        });
        secenekiki.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                  if(secenekiki.isChecked()){
                      Intent i=new Intent(KrediKartOdeme.this,MobilÖdemeler.class);
                      i.putExtra("mödeme",al);
                      startActivity(i);
                  }
            }
        });
    }
}
