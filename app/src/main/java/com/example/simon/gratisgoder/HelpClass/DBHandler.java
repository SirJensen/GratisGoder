package com.example.simon.gratisgoder.HelpClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;

/**
 * Created by Tobias on 04-12-2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final String DBNAME = "userDatabase";
    private static final String TABLE_VistedExp = "vistedExp";
    private static final int VERSION = 1;
   // String titel, String sted, String adresse, String img, String beskrivelse
    public static final String titel = "titel";
    public static final String sted = "sted";
    public static final String adresse = "adresse";
    public static final String img = "image";
    public static final String beskrivelse = "beskrivelse";
    SQLiteDatabase SQDB;




    public DBHandler(Context context) {
        super(context, DBNAME, null, VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
SQDB = db;

        db.execSQL("create table " + TABLE_VistedExp + " ("
                + titel + " TEXT, "
                + sted + " TEXT, "
                + adresse + " TEXT, "
                +  img + " TEXT, "
                + beskrivelse + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_VistedExp);
        this.onCreate(db);
    }

    public void addTestResult (String tit, String ste, String adr, String image, String besk){


        SQLiteDatabase db = getWritableDatabase();



        ContentValues række = new ContentValues();
        række.put(titel, tit);
        række.put(sted, ste);
        række.put(adresse, adr);
        række.put(img, image);
        række.put(beskrivelse, besk);

        db.insert(TABLE_VistedExp, null, række);
        // onUpgrade(db, db.getVersion(), db.getVersion()+1);
        db.close();

    }
    public void removeSingleContact(String title) {
        //Open the database
        SQLiteDatabase database = this.getWritableDatabase();

        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
        database.execSQL("DELETE FROM " + TABLE_VistedExp + " WHERE " + titel + "= '" + title + "'");

        //Close the database
        database.close();
    }
    public ArrayList<Result> getContent(){

        ArrayList<Result> results = new ArrayList<>();
        String[] kolonner = {titel, sted, adresse, img, beskrivelse};
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(TABLE_VistedExp);


        Cursor cursor = builder.query(db, null, null, null, null, null, null);
        if(cursor.moveToNext()) {
            while (!cursor.isAfterLast()) {

                //Log.i("DEBUG", cursor.getString(0));
                results.add(new Result(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)));
                cursor.moveToNext();
            }
        }
        cursor.close();

        db.close();
        return  results;
    }



}
