package odigeo.nativeteam.firebaseabtesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainactivity_container, MainView.getInstance())
                .commit();

        getSupportActionBar()
                .setTitle(R.string.app_name);
    }
}
