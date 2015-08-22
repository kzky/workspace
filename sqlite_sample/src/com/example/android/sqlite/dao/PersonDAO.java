package com.example.android.sqlite.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.sqlite.entity.Person;

/**
 * 下記のように作成するのが定石と思われる．
 *  
 * @author kzk
 *
 */
public class PersonDAO {
    private static final String TAG = "PersonDAO";
    private PersonDAO(){};
    private static PersonDAO instance = null;
    private static SQLiteOpenHelper helperInstance = null;
    private static final String DB_NAME = "PersonDAO";
    private static final int VERSION = 1; 
    public static final String ID = "_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String AGE = "age";
    public static final String DESC = "desc";
    public static final String[] COLUMNS = 
            new String[] {ID, FIRST_NAME, LAST_NAME, AGE, DESC};

    public static PersonDAO getPersonDAO(Context context) {
        if (instance == null) {
            instance = new PersonDAO();
            helperInstance = new PersonDAOHelper(context, DB_NAME, null, VERSION);
        }
        return instance;
    }
    
    private SQLiteDatabase getWritableDatabase() {
        return helperInstance.getWritableDatabase();
    }
    
    /*
     * select
     * 
     */

    public List<Person> select() {
        SQLiteDatabase db = helperInstance.getReadableDatabase();
        Cursor cursor = db.query(DB_NAME, COLUMNS, null, null, null, null, null);
        List<Person> persons = new ArrayList<Person>();
        Person person = null;
        while (cursor.moveToNext()) {
            // get column indices
            int idIndex = cursor.getColumnIndex(ID);
            int firstNameIndex = cursor.getColumnIndex(FIRST_NAME);
            int lastNameIndex = cursor.getColumnIndex(LAST_NAME);
            int ageIndex = cursor.getColumnIndex(AGE);
            int descIndex = cursor.getColumnIndex(DESC);
            
            // get values
            int id = cursor.getInt(idIndex);
            String firstName = cursor.getString(firstNameIndex);
            String lastName = cursor.getString(lastNameIndex);
            int age = cursor.getInt(ageIndex);
            String desc = cursor.getString(descIndex);
        
            // set values to person
            person = new Person();
            person.setId(id);
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setAge(age);
            person.setDesc(desc);
            
            persons.add(person);
        }
        
        return new ArrayList<Person>();
    }
    
    /*
     * insert
     */

    private ContentValues createContentValuesFrom(Person person) {
        ContentValues cv = new ContentValues();     
        cv.put("first_name", person.getFirstName());
        cv.put("last_name", person.getLastName());
        cv.put("age", person.getAge());
        cv.put("desc", person.getDesc());
        return cv;
    }
    
    public long insert(Person person) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = createContentValuesFrom(person);
        return db.insert(DB_NAME, null, cv); 
    }
    
    public long insertPersons(List<Person> persons) {
        SQLiteDatabase db = getWritableDatabase();
        long retvalue = 1;
        db.beginTransaction();
        ContentValues cv = null;
        try {
            for (Person person : persons) {
                cv = createContentValuesFrom(person);
                retvalue *= db.insert(DB_NAME, null, cv);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();            
        }
        return retvalue;
    }

    /*
     * update
     */
    
    public long updateAge(long age, String firstName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("age", age);
        return db.update(DB_NAME, cv, "first_name = ?", new String[]{firstName});
    }
    
    /*
     * delete
     */
    
    public long deleteWith(String firstName) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(DB_NAME, "first_name = ", new String[] {firstName});
    }
    
    private static class PersonDAOHelper extends SQLiteOpenHelper {
        
        public PersonDAOHelper(Context context, String name,
                CursorFactory factory, int version) {
            super(context, name, factory, version);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create db
            db.execSQL(
                    "create table " + DB_NAME + "(" +
                            "_id integer primary key autoincrement not null," +
                            "first_name text," +
                            "last_name text," + 
                            "age integer," + 
                            "desc text" + 
                            ");"
                    );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            
        }
        
    }
    
    public void showPersons() {
        List<Person> persons= instance.select();
        for (Person person: persons) {
            String personInfo = String.format(
                    "_id: %d, first_name: %s, last_name: %s, age: %d, desc: %s", 
                    person.get_id(), person.getFirstName(), person.getLastName(), person.getAge(), person.getDesc());
            Log.d(TAG, personInfo);
        }
    }
    
    /*
     * close
     * aplicationの終了時に明示的にcloseすること．
     */
    public void close() {
        helperInstance.close();
    }
}
