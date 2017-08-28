package samsungkh.com.commute_moblie;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jungyong on 2017-08-27.
 */

public class ReadStopActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String route_id;
    String route_name;
    String time;
    String gubun;
    String time_info;
    ArrayList<StopVO> datas;
    ArrayList<TimeVO> time_datas;
    ListView listView;
    TextView time_table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_stop);

        Intent intent = getIntent();
        route_id = intent.getExtras().getString("rt_id");
        route_name = intent.getExtras().getString("rt_nm");
        time = intent.getExtras().getString("time");
        gubun = intent.getExtras().getString("gubun");

        this.setTitle(route_name);

//        Toast toast1 = Toast.makeText(this, route_id +", " + route_name +", "+ time +", "+ gubun, Toast.LENGTH_SHORT);
//        toast1.show();

        listView = (ListView) findViewById(R.id.stop_list);
        listView.setOnItemClickListener(this);

        serchStopList(route_id);
        serchTimeList(route_id, time, gubun);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, MapActivity.class);
        //detail 조회를 위한 id값 intent로 넘기기
        intent.putExtra("stop_id", datas.get(i).stop_id);
        intent.putExtra("stop_desc", datas.get(i).stop_desc);

        String[] location = datas.get(i).gps.split(",");
        String longitude = location[0]; // 경도
        String latitude = location[1]; // 위도
        intent.putExtra("longitude", longitude);
        intent.putExtra("latitude", latitude);

        startActivity(intent);
    }
    public void serchTimeList(String route_id, String time, String gubun){
        //정류장 화면 시간 리스트 데이터 가져오기
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.openDatabase();
        Cursor cursor;

        cursor = db.rawQuery("SELECT DISTINCT DEPART_TIME "
                + " FROM CM_TIMETABLE "
                + " WHERE ROUTE_ID=? AND DIRECTION=? AND "
                + "cast(replace(DEPART_TIME,':','') as integer) > cast(replace(?,':','') as integer) "
                        + "ORDER BY cast(replace(DEPART_TIME,':','') as integer)",
                new String[]{route_id, gubun, time});

        time_info = "";
        while(cursor.moveToNext()) {
            time_info += cursor.getString(0);
            if (cursor.isLast()) continue;
               time_info += "/ ";
        }

        db.close();

        time_table = (TextView) findViewById(R.id.time_table);
        time_table.setText(time_info);
    }

    public void serchStopList(String route_id){
        //정류장 화면 정류장 리스트 데이터 가져오기
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.openDatabase();
        Cursor cursor;

        cursor = db.rawQuery("SELECT route_id, stop_id, stop_desc, gps "
                        + " FROM CM_ROUTE "
                + " WHERE ROUTE_ID=? "
                + " ORDER BY STOP_ID ", new String[]{route_id});

        datas = new ArrayList<StopVO>();

        while(cursor.moveToNext()){
            StopVO vo = new StopVO();

            vo.rt_id = cursor.getString(0);
            vo.stop_id = cursor.getString(1);
            vo.stop_desc = cursor.getString(2);
            vo.gps = cursor.getString(3);

            datas.add(vo);
        }
        db.close();

        //Adapter 처리, 즉, 커스텀 리스트뷰를 만들어서 Stop activity의 stop_list_item로 세팅해주는 부분
        StopListAdapter adapter = new StopListAdapter(this, R.layout.stop_list_item, datas);
        listView.setAdapter(adapter);
    }
}
