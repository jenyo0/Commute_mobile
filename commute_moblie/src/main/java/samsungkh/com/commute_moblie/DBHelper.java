package samsungkh.com.commute_moblie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper{

    private static final String DB_NAME = "sqliteDB.db";

    private Context context;

    public DBHelper(Context context) {
        this.context = context;
    }

    public SQLiteDatabase openDatabase() {
        File dbFile = context.getDatabasePath(DB_NAME);

        //TODO DB Server 연결 후 DB Server의 버전과 현재 버전 비교 후 이전버전이면 기존DB삭제 후 DB Copy하는 로직
        if (!dbFile.exists()) {
            try {
                Log.d("jojo", "dbFile does no Exists!!!!");
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
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

        Log.d("jojo", "DB copy complete!");
    }

}