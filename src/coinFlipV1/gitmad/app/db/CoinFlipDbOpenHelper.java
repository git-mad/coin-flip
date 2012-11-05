package coinFlipV1.gitmad.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoinFlipDbOpenHelper extends SQLiteOpenHelper {

    public static final String FLIP_TYPE = "type";

    public static final String FLIP_TABLE = "flips";

    private static String DB_NAME = "coinflip.db";
    private static int    VERSION = 6;
    
    public CoinFlipDbOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + FLIP_TABLE + " (" + FLIP_TYPE + " int default 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table " + FLIP_TABLE);
        }
        onCreate(db);
    }
    
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //NOTHING TO DO
    }
}
