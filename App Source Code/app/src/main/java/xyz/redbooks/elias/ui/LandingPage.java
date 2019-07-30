package xyz.redbooks.elias.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import xyz.redbooks.elias.R;

public class LandingPage extends AppCompatActivity {

    TextView textView;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        textView= findViewById(R.id.team_id);
        next= findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Home.class));
            }
        });

        Typeface customFont= Typeface.createFromAsset(getAssets(),"fonts/obj.ttf");
        textView.setTypeface(customFont);


    }
}
