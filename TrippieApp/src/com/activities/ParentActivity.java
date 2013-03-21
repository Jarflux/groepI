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
public class ParentActivity extends Activity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayShowHomeEnabled(false);
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
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
                intent = new Intent(this,ChatActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void stylePage(){

    }

    public void addContent(){
        //To be filled in by child classes
    }
}
