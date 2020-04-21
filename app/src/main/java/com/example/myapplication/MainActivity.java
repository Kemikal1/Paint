package com.example.myapplication;

import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    public ViewGroup.LayoutParams params;
    private Path path=new Path();
    private Paint brush = new Paint();
    private PaintView paintView;
    FirstFragment frag=new FirstFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setMinimumWidth(20);
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
        frag = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.paint);
        //noinspection SimplifiableIfStatement
        switch  (id){
            case R.id.eraser:
                frag.eraser();
                return true;
            case R.id.brush:
               frag.brush();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
