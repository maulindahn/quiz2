package com.example.pustikom.adapterplay.com.example.pustikom.db;

import android.provider.BaseColumns;

/**
 * Created by user pc on 11/11/2016.
 */

public class StudentContract  implements BaseColumns {
    public static final String TABLE_STUDENT = "student";
    public static final String _ID = BaseColumns._ID;
    public final static String COLUMN_NIM ="noreg";
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_GENDER = "gender";
    public final static String COLUMN_MAIL = "mail";
    public final static String COLUMN_PHONE = "phone";

}
