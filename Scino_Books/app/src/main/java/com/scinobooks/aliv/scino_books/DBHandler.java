package com.scinobooks.aliv.scino_books;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class DBHandler extends SQLiteOpenHelper implements IDBHandler {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BookDB";

    private static final String TABLE_BOOKS = "Books";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "Name";
    private static final String KEY_AUTH = "Author";
    private static final String KEY_TITLE = "Title";

    private static final String TABLE_CATEGORIES = "Categories";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "Title";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_AUTH + " TEXT," + KEY_TITLE + " TEXT" + ");";
        db.execSQL(CREATE_BOOKS_TABLE);
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TITLE + " TEXT" + ");";
        db.execSQL(CREATE_CATEGORIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        onCreate(db);
    }

    @Override
    public void addBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, book.getName());
        values.put(KEY_AUTH, book.getAuthor());
        values.put(KEY_TITLE, book.getTitle());

        db.insert(TABLE_BOOKS, null, values);
        db.close();
    }

    @Override
    public void addCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, category.getTitle());

        db.insert(TABLE_CATEGORIES, null, values);
        db.close();
    }

    @Override
    public Book getBook(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BOOKS, new String[] { KEY_ID,
                        KEY_NAME, KEY_AUTH, KEY_TITLE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Book book = new Book(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return book;
    }

    @Override
    public Category getCategory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CATEGORIES, new String[] { COLUMN_ID, COLUMN_TITLE }, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Category category = new Category(Integer.parseInt(cursor.getString(0)), cursor.getString(1));

        return category;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<Book>();
        String selectQuery = "SELECT  * FROM " + TABLE_BOOKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setID(Integer.parseInt(cursor.getString(0)));
                book.setName(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setTitle(cursor.getString(3));
                bookList.add(book);
            } while (cursor.moveToNext());
        }

        return bookList;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<Category>();
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setID(Integer.parseInt(cursor.getString(0)));
                category.setTitle(cursor.getString(3));
                categoryList.add(category);
            } while (cursor.moveToNext());
        }

        return categoryList;
    }

    @Override
    public int updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, book.getName());
        values.put(KEY_AUTH, book.getAuthor());
        values.put(KEY_TITLE, book.getTitle());

        return db.update(TABLE_BOOKS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(book.getID()) });
    }

    @Override
    public int updateCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, category.getTitle());

        return db.update(TABLE_BOOKS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(category.getID()) });
    }

    @Override
    public void deleteBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOKS, KEY_ID + " = ?", new String[]{String.valueOf(book.getID())});
        db.close();
    }

    @Override
    public void deleteCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORIES, COLUMN_ID + " = ?", new String[] { String.valueOf(category.getID()) });
        db.close();
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOKS, null, null);
        db.delete(TABLE_CATEGORIES, null, null);
        db.close();
    }

    @Override
    public int getBooksCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BOOKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

}
