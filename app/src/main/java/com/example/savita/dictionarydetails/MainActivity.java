package com.example.savita.dictionarydetails;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.UserDictionary;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView dictTextView = (TextView)findViewById(R.id.dictionary_text_view);

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);

        try{
            dictTextView.setText("The UserDictionary contains " + cursor.getCount() + " words\n");
            dictTextView.append("COLUMNS: " + UserDictionary.Words._ID + " - " + UserDictionary.Words.FREQUENCY + " - "
                    + UserDictionary.Words.WORD);

            int idColumn = cursor.getColumnIndex(UserDictionary.Words._ID);
            int frequencyColumn = cursor.getColumnIndex(UserDictionary.Words.FREQUENCY);
            int wordColumn = cursor.getColumnIndex(UserDictionary.Words.WORD);

            while(cursor.moveToNext()){

                int id = cursor.getInt(idColumn);
                int frequency = cursor.getInt(frequencyColumn);
                String word = cursor.getString(wordColumn);

                dictTextView.append("\n" + id + "  - " + frequency + " - " + word);
            }
        }finally {
            cursor.close();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
