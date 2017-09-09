package samsungkh.com.commute_moblie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper {

    private static final String DB_NAME = "bus_new.db";
    private static final int DB_DATA_VERSION = 3;

    private Context context;

    public DBHelper(Context context) {
        this.context = context;
    }

    public SQLiteDatabase openDatabase() {
        File dbFile = context.getDatabasePath(DB_NAME);

        //TODO DB Server 연결 후 DB Server의 버전과 현재 버전 비교 후 이전버전이면 기존DB삭제 후 DB Copy하는 로직
        if (!dbFile.exists()) {
            try {
                copyDatabase(dbFile);
                SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
                db.setVersion(DB_DATA_VERSION);
                db.close();

            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }else{
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
            if(db.needUpgrade(DB_DATA_VERSION)){

                db.delete("CM_ROUTE", null, null);
                db.delete("CM_STOP", null, null);
                db.delete("CM_TIME", null, null);

                try {
                    copyDatabase(dbFile);
                    db.setVersion(DB_DATA_VERSION);
                    db.close();
                } catch (IOException e) {
                    throw new RuntimeException("Error creating source database", e);
                }
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);
    }

    private void copyDatabase(File dbFile) throws IOException {
        InputStream is = context.getAssets().open(DB_NAME);
        OutputStream os = new FileOutputStream(dbFile);

        byte[] buffer = new byte[1024];
        while (is.read(buffer) > 0) {
            os.write(buffer);
        }

        os.flush();
        os.close();
        is.close();

    }

}