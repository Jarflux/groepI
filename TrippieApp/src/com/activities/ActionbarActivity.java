package com.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 11-3-13
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */
public class ActionbarActivity extends Activity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent;
        switch (item.getItemId())
        {
            case R.id.btnTrips:
                intent = new Intent();
                return true;
            case R.id.btnMaps:
                intent = new Intent();
                return true;
            case R.id.btnChat:
                intent = new Intent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
