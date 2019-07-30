package xyz.redbooks.elias.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import xyz.redbooks.elias.R;
import xyz.redbooks.elias.database.AppDatabase;

public class SuppliesChain extends AppCompatActivity {

    TextView textView;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    Button submit;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplies_chain);
        submit= findViewById(R.id.submit);
        checkBox1= findViewById(R.id.checkbox1);
        checkBox2= findViewById(R.id.checkbox2);
        checkBox3= findViewById(R.id.checkbox3);
        checkBox4= findViewById(R.id.checkbox4);
        checkBox5= findViewById(R.id.checkbox5);


        checkBox1Loc();
        checkBox2Loc();
        checkBox3Loc();
        checkBox4Loc();
        checkBox5Loc();
        submitButton();

    }

    private void submitButton() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox1.isChecked()){

                    checkBox1Loc();
                    //Toast.makeText(SuppliesChain.this, "Message has been sent", Toast.LENGTH_SHORT).show();

                }
                else if(checkBox2.isChecked()){

                    checkBox2Loc();

                }
                else if(checkBox3.isChecked()){
                    checkBox3Loc();

                }
                else if(checkBox4.isChecked()){
                    checkBox4Loc();

                }
                else if(checkBox5.isChecked()){
                    checkBox5Loc();

                }
            }
        });
    }

    private void checkBox5Loc() {
        Location location = getLocation();
        String longi, lati;
        if(location != null){

            longi = Double.toString(location.getLongitude());
            lati = Double.toString(location.getLatitude());
        } else {
            lati = "\'Not Available\'";
            longi = "\'Not Available\'";
        }

        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());

        List<String> mobNumber = db.contactDao().getAllContactsNumber();
        SmsManager smsManager = SmsManager.getDefault();

        String gmapLink = "http://maps.google.com/maps?q=" + lati + ","+ longi;
        msg = "I am here at " + gmapLink + ". Need Fire service support. Please help.";

        for(String number : mobNumber) {
            smsManager.sendTextMessage(number,null, msg, null, null);
            Log.d("MSG", "sent message to " + number);
        }

    }

    private void checkBox4Loc() {
        Location location = getLocation();
        String longi, lati;
        if(location != null){

            longi = Double.toString(location.getLongitude());
            lati = Double.toString(location.getLatitude());
        } else {
            lati = "\'Not Available\'";
            longi = "\'Not Available\'";
        }

        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());

        List<String> mobNumber = db.contactDao().getAllContactsNumber();
        SmsManager smsManager = SmsManager.getDefault();

        String gmapLink = "http://maps.google.com/maps?q=" + lati + ","+ longi;
        msg = "I am here at " + gmapLink + ". Need Ambulance support. Please help";

        for(String number : mobNumber) {
            smsManager.sendTextMessage(number,null, msg, null, null);
            Log.d("MSG", "sent message to " + number);
        }

    }

    private void checkBox3Loc() {
        Location location = getLocation();
        String longi, lati;
        if(location != null){

            longi = Double.toString(location.getLongitude());
            lati = Double.toString(location.getLatitude());
        } else {
            lati = "\'Not Available\'";
            longi = "\'Not Available\'";
        }

        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());

        List<String> mobNumber = db.contactDao().getAllContactsNumber();
        SmsManager smsManager = SmsManager.getDefault();

        String gmapLink = "http://maps.google.com/maps?q=" + lati + ","+ longi;
        msg = "I am here at " + gmapLink + ". Need water support.";

        for(String number : mobNumber) {
            smsManager.sendTextMessage(number,null, msg, null, null);
            Log.d("MSG", "sent message to " + number);
        }

    }

    private void checkBox2Loc() {
        Location location = getLocation();
        String longi, lati;
        if(location != null){

            longi = Double.toString(location.getLongitude());
            lati = Double.toString(location.getLatitude());
        } else {
            lati = "\'Not Available\'";
            longi = "\'Not Available\'";
        }

        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());

        List<String> mobNumber = db.contactDao().getAllContactsNumber();
        SmsManager smsManager = SmsManager.getDefault();

        String gmapLink = "http://maps.google.com/maps?q=" + lati + ","+ longi;
        msg = "I am here at " + gmapLink + ". Need food. please help me.";

        for(String number : mobNumber) {
            smsManager.sendTextMessage(number,null, msg, null, null);
            Log.d("MSG", "sent message to " + number);
        }

    }

    private void checkBox1Loc() {
        Location location = getLocation();
        String longi, lati;
        if(location != null){

            longi = Double.toString(location.getLongitude());
            lati = Double.toString(location.getLatitude());
        } else {
            lati = "\'Not Available\'";
            longi = "\'Not Available\'";
        }

        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());

        List<String> mobNumber = db.contactDao().getAllContactsNumber();
        SmsManager smsManager = SmsManager.getDefault();

        String gmapLink = "http://maps.google.com/maps?q=" + lati + ","+ longi;
        msg = "I am here at " + gmapLink + ". Need transport support.";

        for(String number : mobNumber) {
            smsManager.sendTextMessage(number,null, msg, null, null);
            Log.d("MSG", "sent message to " + number);
        }

    }
    public Location getLocation(){
        boolean gps_enabled = false;
        boolean network_enabled = false;

        LocationManager lm = (LocationManager) getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);

        if(lm != null) {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }

        Location net_loc = null, gps_loc = null, finalLoc = null;

        if (gps_enabled)
            gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (network_enabled)
            net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (gps_loc != null && net_loc != null) {

            //smaller the number more accurate result will
            if (gps_loc.getAccuracy() > net_loc.getAccuracy())
                finalLoc = net_loc;
            else
                finalLoc = gps_loc;

            // I used this just to get an idea (if both avail, its upto you which you want to take as I've taken location with more accuracy)

        } else {

            if (gps_loc != null) {
                finalLoc = gps_loc;
            } else if (net_loc != null) {
                finalLoc = net_loc;
            }
        }
        return finalLoc;
    }
}
