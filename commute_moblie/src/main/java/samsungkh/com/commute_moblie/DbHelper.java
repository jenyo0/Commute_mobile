package samsungkh.com.commute_moblie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sangwon on 2017-08-08.
 * DB 관리적인 코드 추상화 목적
 */

public class DbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    Context mContext;

    public DbHelper (Context context){
        //studentDB : db file 명, 원한다면 동적으로 여러파일 가능
        //상위 클래스에 db version 정보 들어간다.
        super(context, "commutedb", null, DATABASE_VERSION);
        this.mContext = context;
    }

    //App Install 후 helper 클래스가 최초로 사용되는 순간 한번만 호출
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String routeSql = "create table tb_route (_id integer primary key autoincrement, rt_id not null, rt_nm, gubun, mon_yn, reg_dt)";
        String stopSql = "create table tb_stop (_id integer primary key autoincrement, rt_id not null, sn not null, stop_nm not null, reg_dt)";
        String timeSql = "create table tb_timetable (_id integer primary key autoincrement, rt_id not null, time not null, cnt, reg_dt)";
        sqLiteDatabase.execSQL(routeSql);
        sqLiteDatabase.execSQL(stopSql);
        sqLiteDatabase.execSQL(timeSql);

        //DbHelper helper = new DbHelper(this.mContext);
        //SQLiteDatabase db = helper.getWritableDatabase();

        /* 샘플데이터 */
        sqLiteDatabase.execSQL("insert into tb_route (rt_id, rt_nm, gubun, mon_yn, reg_dt) values (?,?,?,?,?)", new String[]{"ki_su", "수원역", "출근", "Y", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_route (rt_id, rt_nm, gubun, mon_yn, reg_dt) values (?,?,?,?,?)", new String[]{"ki_gang", "강남역", "출근", "Y", "20170801"});

        /* 샘플데이터 */
        sqLiteDatabase.execSQL("insert into tb_timetable (rt_id, time, cnt, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "05:30", "1", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_timetable (rt_id, time, cnt, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "06:00", "1", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_timetable (rt_id, time, cnt, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "06:30", "1", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_timetable (rt_id, time, cnt, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "07:00", "1", "20170801"});

        sqLiteDatabase.execSQL("insert into tb_timetable (rt_id, time, cnt, reg_dt) values (?,?,?,?)", new String[]{"ki_gang", "06:00", "1", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_timetable (rt_id, time, cnt, reg_dt) values (?,?,?,?)", new String[]{"ki_gang", "07:00", "2", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_timetable (rt_id, time, cnt, reg_dt) values (?,?,?,?)", new String[]{"ki_gang", "08:00", "1", "20170801"});

        /* 샘플데이터 */
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "1", "수원역", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "2", "시민외과", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "3", "수원결혼만들기(세곡사거리)", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "4", "수원결혼회관", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "5", "하나주유소", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "6", "수원경기약국(세류사거리)", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "7", "수원세류동미영아파트사거리", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "8", "세류역", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "9", "화성화남사거리(새마을금고)", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "10", "화성병점주공805동정문", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "11", "병점신미주아파트", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "12", "화성H1", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "13", "화성H2", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_su", "14", "기흥", "20170801"});

        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_gang", "1", "서초래미안스위트", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_gang", "2", "강남역5번출구(우리은행)", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_gang", "3", "뱅뱅사거리", "20170801"});
        sqLiteDatabase.execSQL("insert into tb_stop (rt_id, sn, stop_nm, reg_dt) values (?,?,?,?)", new String[]{"ki_gang", "4", "기흥", "20170801"});
    }

    //상위에 전달되는 db version 정보가 변경될 때
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table tb_route");
        sqLiteDatabase.execSQL("drop table tb_timetable");
        sqLiteDatabase.execSQL("drop table tb_stop");
        onCreate(sqLiteDatabase);
    }
}
