package br.sptrans.transportepublico.pcc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by rreimberg on 10/26/13.
 */
class BaseActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void openActivity(@SuppressWarnings("rawtypes") Class activity)
    {
        startActivity(new Intent(getApplicationContext(), activity));
    }

    protected void defaultNavigation()
    {
        findViewById(R.navigation_menu.journey).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivity(JourneyActivity.class);
            }
        });

        findViewById(R.navigation_menu.traffic).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivity(TrafficActivity.class);
            }
        });

        findViewById(R.navigation_menu.stocking).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivity(StockingActivity.class);
            }
        });

        findViewById(R.navigation_menu.alert).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivity(AlertActivity.class);
            }
        });
    }
}