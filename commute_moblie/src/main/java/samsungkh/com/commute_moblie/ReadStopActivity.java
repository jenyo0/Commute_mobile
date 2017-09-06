package samsungkh.com.commute_moblie;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
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
    TableLayout time_tl;

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

        listView = (ListView) findViewById(R.id.stop_list);
        listView.setOnItemClickListener(this);

        findViewById(R.id.stop_map_button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intel_act = new Intent(ReadStopActivity.this, MapActivity.class);
                       intel_act.putExtra("stop_datas", datas);
                        startActivity(intel_act);
                    }
                }
        );

        serchStopList(route_id);
        serchTimeLayoutList(route_id, time, gubun);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, MapActivity.class);

        ArrayList<StopVO> data_point= new ArrayList<StopVO>();
        data_point.add(datas.get(i));
        intent.putExtra("stop_datas", data_point);

        startActivity(intent);
    }

    public void serchStopList(String route_id){
        //정류장 화면 정류장 리스트 데이터 가져오기
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.openDatabase();
        Cursor cursor;

        cursor = db.rawQuery(
                " SELECT A.ROUTE_ID, A.STOP_ID, B.LONGI, B.LATI "
                + "FROM CM_ROUTE A, CM_STOP B "
                + " WHERE A.STOP_ID = B.STOP_ID "
                + " AND A.ROUTE_ID=? "
                + " ORDER BY SEQ ", new String[]{route_id});

        datas = new ArrayList<StopVO>();

        while(cursor.moveToNext()){
            StopVO vo = new StopVO();

            vo.rt_id = cursor.getString(0);
            vo.stop_id = cursor.getString(1);
            vo.longi = cursor.getString(2);
            vo.lati = cursor.getString(3);

            datas.add(vo);
        }
        db.close();

        //Adapter 처리, 즉, 커스텀 리스트뷰를 만들어서 Stop activity의 stop_list_item로 세팅해주는 부분
        StopListAdapter adapter = new StopListAdapter(this, R.layout.stop_list_item, datas);
        listView.setAdapter(adapter);
    }

    public void serchTimeLayoutList(String route_id, String time, String gubun){

        //정류장 화면 시간 리스트 데이터 가져오기
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.openDatabase();
        Cursor cursor;

        cursor = db.rawQuery(
                          " SELECT DISTINCT                   "
                        + " DEPART_TIME ,                     "
                        + " case WHEN MONDAY_FLAG='Y'         "
                        + " 	then monday_time               "
                        + " 	ELSE '-'                       "
                        + " 	END AS MON_TIME                "
                        + "  FROM CM_TIME                     "
                        + "  WHERE ROUTE_ID=? AND DIRECTION=? "
                        + " AND cast(replace(DEPART_TIME,':','') as integer) > cast(replace(?,':','') as integer) "
                        + " ORDER BY cast(replace(DEPART_TIME,':','') as integer)",
                new String[]{route_id, gubun, time});

        time_info = "";

        time_tl = (TableLayout)findViewById(R.id.time_tableLayout);


        while(cursor.moveToNext()) {
            TableRow tr = new TableRow(this);
            TextView tv1 =  new TextView(this);
            TextView tv2 =  new TextView(this);
            tv1.setGravity(Gravity.CENTER);
            tv2.setGravity(Gravity.CENTER);
            tv1.setText(cursor.getString(0)) ;
            tv2.setText(cursor.getString(1)) ;
            tr.addView(tv1);
            tr.addView(tv2);
            time_tl.addView(tr);
        }

        db.close();

    }


}
