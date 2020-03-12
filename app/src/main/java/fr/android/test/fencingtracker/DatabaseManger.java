package fr.android.test.fencingtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class DatabaseManger extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="Match.db";
    private static final int DATABASE_VERSION =3;
    private static final String TABLE_NAME ="T_match";
    SQLiteDatabase db;
    private static final String COL_1 ="NAME1";
    private static final String COL_2 ="NAME2";


    public DatabaseManger(Context context)
    {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
        db=getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSQL = "create table T_match ("
                + " col_id integer primary key autoincrement,"
                + " name1 text,"
                + " name2 text"
                + ")";

        db.execSQL(strSQL);
        Log.i("DATABASE", "oncreate");

    }

    // Mettre a jour la BDD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String strSQL =  "drop table T_match";
        db.execSQL(strSQL);
this.onCreate(db);
Log.i("kd","upgrade");
        //String strSQL =  "alter table T_match and column...";
        //On supprime la table puis on la recreer

    }


    public boolean insertScore(String name1, String name2)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1, name1);
        contentValues.put(COL_2, name2);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
        /*

        String strSQL =  "insert into T_match (name1,name2) values('"
                + name1 +"' ," + name2 +")";
        this.getWritableDatabase().execSQL(strSQL);

         */


    }




    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_NAME,null);
        return result;

    }

    public Cursor get5matches()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_NAME,null);
        return result;

    }




    public List<Match> readTop10() {
        List<Match> matches = new ArrayList<>();

        // 1ère technique : SQL
        String strSql = "select * from T_match";
        Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null );
        Log.i("TOP1°","TOP10");

        // 2nd technique "plus objet"
        /*Cursor cursor = this.getReadableDatabase().query( "T_match",
                new String[] { "name1", "name2" },
                null, null, null, null, "name desc", "5");
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            Match match = new Match( cursor.getString( 1 ),
                    cursor.getString( 2 ) );
            matches.add( match );
            cursor.moveToNext();
        }
        cursor.close();*/

        return matches;
    }


}
