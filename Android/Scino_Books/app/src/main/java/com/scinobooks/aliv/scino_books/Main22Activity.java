package com.scinobooks.aliv.scino_books;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main22Activity extends AppCompatActivity {

    private ListView mListV;
    private SQLiteDatabase mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        setTitle("Список книг Без Категории");

        List<String> mListBk = MainActivity.mDB.getBookByCategory("Без категории");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListBk);
        mListV = (ListView) findViewById(R.id.lvBookData);
        mListV.setAdapter(adapter);

        mListV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //удаление книги
                String str = null;
                Log.d("my_logs", str);
                List<String> listBk = MainActivity.mDB.getBookByCategory("Без категории");
                if (listBk.contains(str)) {
                    MainActivity.mDB.deleteBook(MainActivity.mDB.getBook(str));
                    return true;
                } else {
                    Log.d("my_logs", "Удаление не выполнено");
                    return false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main22, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        mHelper = MainActivity.mDB.getWritableDatabase();
        String orderBy = null;
        Cursor c = null;

        switch(item.getItemId()) {
            case R.id.sortOrderName:
                item.setChecked(true);
                orderBy = "Name";
                break;
            case R.id.sortOrderAuthor:
                item.setChecked(true);
                orderBy = "Author";
                break;
        }

        List<String> mListBk = new ArrayList<String>();

        c = mHelper.query("Books", null, null, null, null, null, orderBy);



        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = "
                                + c.getString(c.getColumnIndex(cn)) + "; ");
                    }

                    Log.d("my_logs", str);
                    //Book list
                    String [] str1 = str.split("; ");
                    String [] name1 = str1[1].split(" ");
                    String [] auth1 = str1[2].split(" ");
                    String name = name1[2];
                    String auth = auth1[2];
                    if (str.contains("Title = Без категории")) {
                        mListBk.add(name + " by " + auth);
                    }
                    else
                        continue;
                } while (c.moveToNext());
            }
            c.close();
        } else
            Log.d("my_logs", "Cursor is null");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListBk);
        mListV = (ListView) findViewById(R.id.lvBookData);
        mListV.setAdapter(adapter);

        return super.onOptionsItemSelected(item);
    }
}
