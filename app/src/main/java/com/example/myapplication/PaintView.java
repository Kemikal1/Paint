package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class PaintView extends View {
    Bitmap drawing;
    public LayoutParams params;
    private Path path=new Path();
    private Paint brush = new Paint();
    private Paint defBrush= new Paint();
    List<Path> paths=new ArrayList<>();
    List<Paint> brushes=new ArrayList<>();
    public PaintView(Context context) {
        super(context);
        defBrush.setAntiAlias(true);
        defBrush.setColor(Color.RED);
        defBrush.setStyle(Paint.Style.STROKE);
        defBrush.setStrokeJoin(Paint.Join.ROUND);
        defBrush.setStrokeWidth(8f);

        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {









       // path=new Path();
        brush=new Paint(defBrush);
        super.onTouchEvent(event);
        float pointX = event.getX();
        float pointY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_UP:

                brushes.add(brush);


                paths.add(path);
                path=new Path();
                break;
            default:
                return false;

        }
        invalidate();
        return true;

    }
    @Override
    protected void onDraw(Canvas canvas){

       for(int i=0;i<paths.size();i++){
        canvas.drawPath(paths.get(i),brushes.get(i));
        System.out.println(brushes.get(i).getColor());
       }
        canvas.drawPath(path,brush);System.out.println("daasdsadsadasdsad");
        System.out.println(brushes.size());
        invalidate();
    }
    public void eraser(){
        defBrush.setColor(Color.WHITE);
        //invalidate();

    }
    public void brush()
    {
        defBrush.setColor(Color.RED);
       // invalidate();
    }
}