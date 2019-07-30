package xyz.redbooks.elias.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import xyz.redbooks.elias.R;

public class Home extends AppCompatActivity {

    TextView title1, title2;
    Button contact, supply, track, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        title1= findViewById(R.id.title1);
        title2= findViewById(R.id.title2);
        contact= findViewById(R.id.contact);
        supply= findViewById(R.id.supply);
        track= findViewById(R.id.track);
        // about= findViewById(R.id.about);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });

        supply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SuppliesChain.class));
            }
        });
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Track.class));
            }
        });
       



                Typeface customFont= Typeface.createFromAsset(getAssets(),"fonts/nnn.ttf");
                Typeface customFont2= Typeface.createFromAsset(getAssets(),"fonts/nnn.ttf");
                title1.setTypeface(customFont);
                title2.setTypeface(customFont2);


    }
}


