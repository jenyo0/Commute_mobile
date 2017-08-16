package samsungkh.com.commute_moblie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class ReadStopActivity extends AppCompatActivity {

    String rt_id, time, gubun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_stop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        rt_id = intent.getExtras().getString("rt_id");
        time = intent.getExtras().getString("time");
        gubun = intent.getExtras().getString("gubun");

        Toast toast1 = Toast.makeText(this, rt_id +", "+ time +", "+ gubun, Toast.LENGTH_SHORT);
        toast1.show();
    }

}
