package com.example.test.mynewapptwo;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.SimpleCursorAdapter;

/**
 * Created by 11942 on 2017/12/5.
 */

public class TestActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportLoaderManager().initLoader(1, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader;
        String select = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND ("
                + ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1) AND ("
                + ContactsContract.Contacts.DISPLAY_NAME + " != '' ))";
        cursorLoader = new CursorLoader(this, ContactsContract.Profile.CONTENT_URI,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts._ID},select,null,"");

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1, (Cursor) cursorLoader,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts._ID},new int[]{android.R.id.text1,android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        int mediaTypeImage = MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
//        测试测试测试
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
