package samsungkh.com.commute_moblie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class StopMapActivity extends AppCompatActivity {
    String stop_id;
    String stop_desc;
    String longitude ;
    String latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_map);

        Intent intent = getIntent();
        stop_id = intent.getExtras().getString("stop_id");
        stop_desc = intent.getExtras().getString("stop_desc");
        longitude = intent.getExtras().getString("longitude");
        latitude = intent.getExtras().getString("latitude");

        Toast toast1 = Toast.makeText(this, stop_id +", "+ stop_desc +", "+ longitude + ", " + latitude, Toast.LENGTH_SHORT);
        toast1.show();
    }
}
