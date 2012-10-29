package coinFlipV1.gitmad.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import coinFlipV1.gitmad.app.models.LeaderboardEntry;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: bryan
 * Date: 10/29/12
 * Time: 5:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class LeaderboardDAO {
    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = { SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_NAME, SQLiteHelper.COLUMN_LENGTH };

    public LeaderboardDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public LeaderboardEntry createLeaderboardEntry(String name, int length, long date) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_NAME, name);
        values.put(SQLiteHelper.COLUMN_LENGTH, length);
        values.put(SQLiteHelper.COLUMN_DATE, date);
        long insertId = database.insert(SQLiteHelper.TABLE_HIGHSCORES, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_HIGHSCORES,
                allColumns, SQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        LeaderboardEntry newLeaderboardEntry = cursorToLeaderboardEntry(cursor);
        cursor.close();
        return newLeaderboardEntry;
    }

    public void deleteLeaderboardEntry(LeaderboardEntry entry) {
        long id = entry.getId();
        System.out.println("LeaderboardEntry deleted with id: " + id);
        database.delete(SQLiteHelper.TABLE_HIGHSCORES, SQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<LeaderboardEntry> getAllLeaderboardEntrys() {
        List<LeaderboardEntry> entries = new ArrayList<LeaderboardEntry>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_HIGHSCORES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            LeaderboardEntry entry = cursorToLeaderboardEntry(cursor);
            entries.add(entry);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        //sort the list
        Collections.sort(entries);
        return entries;
    }

    private LeaderboardEntry cursorToLeaderboardEntry(Cursor cursor) {
        LeaderboardEntry entry = new LeaderboardEntry();
        entry.setId(cursor.getLong(0));
        entry.setName(cursor.getString(1));
        entry.setLength(cursor.getInt(2));
        entry.setDate(new Date(cursor.getLong(3)));
        return entry;
    }
}
