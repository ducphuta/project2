package com.example.pj2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";


    // Phiên bản
    private static final int DATABASE_VERSION = 1;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "Project_2";


    // Tên bảng: Note.
    private static final String TABLE_PROJECT2_POST = "post";

    private static final String COLUMN_POST_ACCOUNT ="post_acc";
    private static final String COLUMN_POST_NAME ="post_name";
    private static final String COLUMN_POST_CONTENT = "post_content";
    private static final String COLUMN_POST_DATE = "post_date";
    private static final String COLUMN_POST_TIME = "post_time";
    private static final String COLUMN_POST_SALARY = "post_salary";
    private static final String COLUMN_POST_PLACE = "post_place";

    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(TAG, "DatabaseHelper.onCreate...");
        // Tạo bảng
        String script = "CREATE TABLE " + TABLE_PROJECT2_POST +"(" + COLUMN_POST_ACCOUNT + "TEXT PRIMARY KEY, " + COLUMN_POST_SALARY + "INTEGER," + COLUMN_POST_PLACE + "TEXT," + COLUMN_POST_CONTENT + "TEXT," + COLUMN_POST_DATE + "TEXT," + COLUMN_POST_TIME + "TEXT," + COLUMN_POST_NAME + "TEXT" +")";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "DatebaseHelper.onUpgrade...");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT2_POST);

        onCreate(db);
    }

    public void createDefault(){

    }

    public void addPost(Post Post){
        Log.i(TAG, "DatabaseHelper.addPost...");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_POST_ACCOUNT, Post.getAcc());
        values.put(COLUMN_POST_SALARY, Post.getSalary());
        values.put(COLUMN_POST_PLACE, Post.getPlace());
        values.put(COLUMN_POST_CONTENT, Post.getContent());
        values.put(COLUMN_POST_DATE, Post.getDate());
        values.put(COLUMN_POST_TIME, Post.getTime());
        values.put(COLUMN_POST_NAME, Post.getName());

        db.insert(TABLE_PROJECT2_POST, null, values);

        db.close();
    }

    public Post getPost(String acc){
        Log.i(TAG, "DatabaseHelper.getPost...");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PROJECT2_POST, new String[]{COLUMN_POST_ACCOUNT, COLUMN_POST_SALARY, COLUMN_POST_PLACE, COLUMN_POST_CONTENT, COLUMN_POST_DATE, COLUMN_POST_TIME}, COLUMN_POST_ACCOUNT + "=?", new String[]{String.valueOf(acc)},null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Post Post = new Post(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
        return Post;
    }

    public List<Post> getAllPost(){
        Log.i(TAG, "DatabaseHelper.getAllPost...");

        List<Post> postList = new ArrayList<Post>();

        String selectQuery = "SELECT * FROM " + TABLE_PROJECT2_POST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Post Post = new Post();
                Post.setAcc(cursor.getString(0));
                Post.setSalary(cursor.getString(1));
                Post.setPlace(cursor.getString(2));
                Post.setContent(cursor.getString(3));
                Post.setDate(cursor.getString(4));
                Post.setTime(cursor.getString(5));
                Post.setName(cursor.getString(6));

                postList.add(Post);
            }
            while (cursor.moveToNext());

        }
        return postList;
    }

}
