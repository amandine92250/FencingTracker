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
    private static final int DATABASE_VERSION =7;
    SQLiteDatabase db;

    //Table match
    private static final String TABLE_NAME ="T_match";
    private static final String COL_1 ="NAME1";
    private static final String COL_2 ="ATTAQUES1";
    private static final String COL_3 ="CATTAQUES1";
    private static final String COL_4 ="POINTS1";
    private static final String COL_5 ="NAME2";
    private static final String COL_6 ="ATTAQUES2";
    private static final String COL_7 ="CATTAQUES2";
    private static final String COL_8 ="POINTS2";
    private static final String COL_9 ="ARME";




    //Table Joueur
    public static final String JOUEUR_PRENOM = "prenom";
    public static final String JOUEUR_NOM = "nom";
    public static final String JOUEUR_TABLE_NAME = "T_Joueur";

    public DatabaseManger(Context context)
    {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Match
        String strSQLMatch = "create table T_match ("
                + " col_id integer primary key autoincrement,"
                + " name1 text,"
                + " attaques1 text,"
                + " cattaques1 text,"
                + " points1 text,"
                + " name2 text,"
                + " attaques2 text,"
                + " cattaques2 text,"
                + " points2 text,"
                + " arme text"
                + ")";

        db.execSQL(strSQLMatch);

        //Joueur
        String strSQLJoueur = "create table T_Joueur ("
                + " col_id integer primary key autoincrement,"
                + " prenom text,"
                + " nom text"
                + ")";

        db.execSQL(strSQLJoueur);


        Log.i("DATABASE", "oncreate");

    }

    // Mettre a jour la BDD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Match
        String strSQLMatch =  "drop table T_match";
        db.execSQL(strSQLMatch);


        //Joueur
        String strSQLJoueur =  "drop table T_Joueur";
        db.execSQL(strSQLJoueur);

        //creer table
        this.onCreate(db);
        Log.i("kd","upgrade");


    }


    public boolean insertScore(String name1, String name2, String attaques1, String cattaques1, String points1, String attaques2, String cattaques2, String points2,String arme)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1, name1);
        contentValues.put(COL_2, attaques1);
        contentValues.put(COL_3, cattaques1);
        contentValues.put(COL_4, points1);
        contentValues.put(COL_5, name2);
        contentValues.put(COL_6, attaques2);
        contentValues.put(COL_7, cattaques2);
        contentValues.put(COL_8, points2);
        contentValues.put(COL_9, arme);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

//inserer un joueur
    public boolean insertJoueur(String prenom, String nom)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(JOUEUR_PRENOM, prenom);
        contentValues.put(JOUEUR_NOM, nom);

        //inserer une ligne
        long result=db.insert(JOUEUR_TABLE_NAME,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    //*****Fonctions pour la table Match*****

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

    /*
    public List<Match> readTop10() {
        List<Match> matches = new ArrayList<>();

        // 1ère technique : SQL
        String strSql = "select * from T_match";
        Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null );
        Log.i("TOP1°","TOP10");

        // 2nd technique "plus objet"
        Cursor cursor = this.getReadableDatabase().query( "T_match",
                new String[] { "name1", "name2" },
                null, null, null, null, "name desc", "5");
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            Match match = new Match( cursor.getString( 1 ),
                    cursor.getString( 2 ) );
            matches.add( match );
            cursor.moveToNext();
        }
        cursor.close();

        return matches;
    }
*/


    //****Fonctions pour la table Joueur*****

    //Retourne le nom de tous les joueurs de la table
    public List<String> getAllNameJoueur(){
        List<String> joueurName = new ArrayList<>();
        Cursor cursor = db.query(DatabaseManger.JOUEUR_TABLE_NAME,
                new String[] { DatabaseManger.JOUEUR_NOM}, null, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            joueurName.add(cursor.getString(0));
        }
        return joueurName;
    }


}
