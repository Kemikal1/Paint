package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    public ViewGroup.LayoutParams params;
    private Path path=new Path();
    private Paint brush = new Paint();
    private PaintView paintView;
    FirstFragment frag;

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
        int a =2131230842;
        frag = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        System.out.println(R.id.nav_host_fragment);
        System.out.println(getSupportFragmentManager().findFragmentById(a));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch  (id){
            case R.id.eraser:

                frag.eraser();
                return true;
            case R.id.brush:
               frag.brush();
                return true;
            case R.id.rect_shape:
                frag.rectangle();
                return true;
            case R.id.circ_shape:
                frag.circle();
                return  true;
            case R.id.new_:
                //frag.Startnew();
            {
                AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
                newDialog.setTitle("New drawing");
                newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
                newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        frag.StartNew();
                        frag.brush();
                        dialog.dismiss();
                    }
                });
                newDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                newDialog.show();
                return true;
            }

            case R.id.save:
            {
                AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
                saveDialog.setTitle("Save drawing");
                saveDialog.setMessage("Save drawing to device Gallery?");
                saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        String imgSaved = MediaStore.Images.Media.insertImage(
                                getContentResolver(), frag.getDraw(),
                                UUID.randomUUID().toString()+".png", "drawing");
                        if(imgSaved!=null){
                            Toast savedToast = Toast.makeText(getApplicationContext(),
                                    "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                            savedToast.show();
                        }
                        else{
                            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                    "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                            unsavedToast.show();
                        }
                        //frag.StartNew();
                        // frag.brush();
                    }
                });
                saveDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                saveDialog.show();
            }
            case R.id.fill:
                frag.fill();
        }


        return super.onOptionsItemSelected(item);
    }
}
