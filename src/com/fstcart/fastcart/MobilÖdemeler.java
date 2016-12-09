package com.fstcart.fastcart;

/**
 * Created by FTHY on 21.07.2014.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MobilÖdemeler extends Activity  {
    private EditText bolgeal;
    private Button onaylamalar;
    private int numara=05325163223;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobilodeme);
        bolgeal=(EditText)findViewById(R.id.tel);
        onaylamalar=(Button)findViewById(R.id.ode);
        onaylamalar.setOnClickListener(dinleyici);
    }
    private View.OnClickListener dinleyici=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(true){
                Toast.makeText(getApplicationContext(),"ÖDEME ALINDI",Toast.LENGTH_LONG).show();
            }
        }
    };
}
