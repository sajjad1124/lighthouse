package xyz.redbooks.elias.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import xyz.redbooks.elias.R;

public class HomeActivity extends AppCompatActivity {

    FragmentManager fm;
    ;
    Fragment fragment;
    FragmentTransaction fragmentTransaction;

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // setUpToolBar();

        drawerLayout = findViewById(R.id.drawer);

        navigationView = findViewById(R.id.nav_view);

        // add the tip of the day fragment
        fm = getSupportFragmentManager();

        fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
//            fragment = new TipOfTheDayFragment();
            fragment = new ContactsConfigFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }


        //set click listener on nav menu items
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Checking if the item is in checked state or not, if not make it in checked state
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (item.getItemId()) {

                    case R.id.trusted_contact_menu:
                        fragment = new ContactsConfigFragment();
                        fragmentTransaction = fm.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
                        return true;

                    case R.id.about:
                        fragment = new AboutFragment();
                        fragmentTransaction = fm.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
                        return true;

                    default:
                        return true;
                }
            }
        });


    }
}
