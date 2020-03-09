package fr.android.test.fencingtracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManger extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="Game_db";
    public static final int DATABASE_VERSION =1;

    public DatabaseManger(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSQL ="create table T_match ("
                + " idScore integer primary key autoincrement,"
                + " name text,"
                + " score integer"
                + ")";

        db.execSQL(strSQL);

    }

    // Mettre a jour la BDD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //String strSQL =  "alter table T_match and column...";
        //String strSQL =  "alter table T_match and column...";

    }

    public void insertScore(String name, int score){

        String strSQL =  "insert into T_match (name,score) values('"
                + name +"' ," + score +")";
        this.getWritableDatabase().execSQL(strSQL);

    }
}
