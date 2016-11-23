package com.example.pustikom.adapterplay.com.example.pustikom.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pustikom.adapterplay.com.example.pustikom.user.Student;

/**
 * Created by user pc on 11/11/2016.
 */

public class StudentDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="college.db";
    public static final int DATABASE_VERSION=1;

    public StudentDbHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCST = "CREATE TABLE" + StudentContract.TABLE_STUDENT + " " +
                StudentContract._ID + "INTEGER PRIMARY KEY AUTOINCREMENT" +
                StudentContract.COLUMN_NIM + "TEXT NOT NULL" +
                StudentContract.COLUMN_NAME + "TEXT NOT NULL" +
                StudentContract.COLUMN_GENDER + "INTEGER" +
                StudentContract.COLUMN_MAIL + "TEXT" +
                StudentContract.COLUMN_PHONE + "TEXT";
                db.execSQL(sqlCST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //called when the database needs to be upgraded
        //drop older table if exist all data will be gone
        db.execSQL("DROP TABLE IF EXIST" + StudentContract.TABLE_STUDENT);

        //create new table
        onCreate(db);
    }

    public void insertStudent (Student student){
        SQLiteDatabase wdb = this.getWritableDatabase(); //create and/or open a database taht will be used for reading and writing
        ContentValues values = new ContentValues();
        values.put(StudentContract.COLUMN_NIM, student.getNoreg());
        values.put(StudentContract.COLUMN_NAME, student.getName());
        values.put(StudentContract.COLUMN_GENDER, student.getGender());
        values.put(StudentContract.COLUMN_MAIL, student.getMail());
        values.put(StudentContract.COLUMN_PHONE, student.getPhone());
        wdb.insert(StudentContract.TABLE_STUDENT, null, values);
    }

    public void updateStudent (Student student){
        SQLiteDatabase wdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentContract.COLUMN_NIM, student.getNoreg());
        values.put(StudentContract.COLUMN_NAME, student.getName());
        values.put(StudentContract.COLUMN_GENDER, student.getGender());
        values.put(StudentContract.COLUMN_MAIL, student.getMail());
        values.put(StudentContract.COLUMN_PHONE, student.getPhone());
        String condition = StudentContract._ID + "= ?";
        String[] conditionArgs = {student.getId() + " "};
        wdb.update(StudentContract.TABLE_STUDENT, values, condition, conditionArgs);
    }

    public void deleteStudent(Student student){
        SQLiteDatabase wdb = this.getWritableDatabase();
        wdb.delete(StudentContract.TABLE_STUDENT, StudentContract._ID + "= ?", new String[] {String.valueOf(student.getId())});
        wdb.close();
    }

    public void truncateStudent(SQLiteDatabase db){
        SQLiteDatabase wdb = this.getWritableDatabase();
        wdb.execSQL("DELETE FROM" + StudentContract.TABLE_STUDENT);
        wdb.execSQL("VACUUM");
    }

    private void fetchStudent(SQLiteDatabase db) {
        // Create and/or open a database to read from it
        //SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                StudentContract._ID,
                StudentContract.COLUMN_NIM,
                StudentContract.COLUMN_NAME,
                StudentContract.COLUMN_GENDER,
                StudentContract.COLUMN_MAIL,
                StudentContract.COLUMN_PHONE};

        // Perform a query on the pets table
        Cursor cursor = db.query(
                StudentContract.TABLE_STUDENT,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        //TextView displayView = (TextView) findViewById(R.id.text_view_pet);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            /*
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(StudentContract._ID + " - " +
                    StudentContract.COLUMN_NIM + " - " +
                    StudentContract.COLUMN_NAME + " - " +
                    StudentContract.COLUMN_GENDER + " - " +
                    StudentContract.COLUMN_EMAIL + " - " +
                    StudentContract.COLUMN_PHONE + "\n");
            */
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(StudentContract._ID);
            int nimColumnIndex = cursor.getColumnIndex(StudentContract.COLUMN_NIM);
            int nameColumnIndex = cursor.getColumnIndex(StudentContract.COLUMN_NAME);
            int genderColumnIndex = cursor.getColumnIndex(StudentContract.COLUMN_GENDER);
            int mailColumnIndex = cursor.getColumnIndex(StudentContract.COLUMN_MAIL);
            int phoneColumnIndex = cursor.getColumnIndex(StudentContract.COLUMN_PHONE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentNim = cursor.getString(nimColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                String currentMail = cursor.getString(mailColumnIndex);
                String currentPhone = cursor.getString(phoneColumnIndex);
                /* Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentNim + " - " +
                        currentName + " - " +
                        currentGender + " - " +
                        currentMail + " - " +
                        currentPhone));
                        */
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
