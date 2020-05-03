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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class PaintView extends View {
    Bitmap drawing;
    public LayoutParams params;
    private Path path=new Path();
    private Paint brush = new Paint();
    private Paint defBrush= new Paint();
    private Color background;
    private Color foreground;
    private float Rcx=0,Rcy=0,Lcx=0,Lcy=0;
    private List<Path> paths=new ArrayList<>();
    private List<Paint> brushes=new ArrayList<>();
    private List<Paint> cBrushes=new ArrayList<>();
    private List<Paint> rBrushes = new ArrayList<>();
    int sem=0;


    protected class cCenter{
        cCenter(){};
        cCenter(float x,float y,double r){this.x=x;this.y=y;this.r=r;};
         float x;
         float y;
         double r;
    }
    protected class rCorner{
        rCorner(){};
        rCorner(float x,float y,float x1,float y1){this.rcx=x1;this.rcy=y1;this.lcx=x;this.lcy=y;}
         float rcx;
         float rcy;
         float lcx,lcy;

    }
    private List<cCenter> cCenters=new ArrayList<>();
    private List<rCorner> rCorners=new ArrayList<>();

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
        cCenter a;
        rCorner b;

       // path=new Path();
        brush=new Paint(defBrush);
        super.onTouchEvent(event);
        float pointX = event.getX();
        float pointY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(sem==0)
                    path.moveTo(pointX, pointY);
                else
                {
                    Lcx=pointX;
                    Lcy=pointY;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(sem==0)
                    path.lineTo(pointX, pointY);
                else
                {
                    Rcx=pointX;
                    Rcy=pointY;
                }
                return true;
            case MotionEvent.ACTION_UP:

                Rcx=pointX;
                Rcy=pointY;
                if(sem==0)
                {   brushes.add(brush);
                    paths.add(path);
                    path=new Path();}
                if(sem ==1)
                {
                    cBrushes.add(brush);
                    a=new cCenter((Lcx+Rcx)/2,(Lcy+Rcy)/2,Math.pow(Math.pow(Lcx-Rcx,2)+Math.pow(Rcy-Lcy,2),0.5));
                    cCenters.add(a);
                }
                if(sem ==2)
                {
                    rBrushes.add(brush);
                    b=new rCorner(Rcx,Rcy,Lcx,Lcy);
                    rCorners.add(b);
                }
                Rcx=Lcx=Lcy=Rcy=0;
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
        //System.out.println(brushes.get(i).getColor());
       }
       for (int i=0;i<rCorners.size();i++)
       {
           canvas.drawRect(rCorners.get(i).lcx,rCorners.get(i).lcy,rCorners.get(i).rcx,rCorners.get(i).rcy,rBrushes.get(i));

       }
       for (int i=0;i<cCenters.size();i++)
       {
           canvas.drawCircle(cCenters.get(i).x,cCenters.get(i).y,(float)(cCenters.get(i).r),cBrushes.get(i));

       }

        if(sem==0)
            canvas.drawPath(path,brush);
        if(sem==1 && Lcx!=0 && Rcx!=0)
            canvas.drawCircle((Lcx+Rcx)/2,(Lcy+Rcy)/2,(float)Math.pow(Math.pow(Lcx-Rcx,2)+Math.pow(Rcy-Lcy,2),0.5),brush);
        if(sem==2 && Lcx!=0 && Rcx!=0)
            canvas.drawRect(Lcx,Lcy,Rcx,Rcy,brush);

        System.out.println(sem);
        //System.out.println(brushes.size());
        invalidate();
    }
    public void eraser(){
        defBrush.setColor(Color.WHITE);
        //invalidate();
        sem=0;
    }
    public void brush()
    {
        defBrush.setColor(Color.RED);
        sem=0;
       // invalidate();
    }
    public void circle()
    {
        defBrush.setColor(Color.RED);
        sem=1;
        Rcx=Lcx=Lcy=Rcy=0;
    }
    public void rectangle()
    {
        defBrush.setColor(Color.RED);
        sem=2;
        Rcx=Lcx=Lcy=Rcy=0;
    }

    public void fill()
    {
        brush.setColor(Color.GREEN);
        brush.setStyle(Paint.Style.FILL);
        //brush.setStyle(Paint.Style.FILL);
        //System.out.println("ceva");
        //System.out.println(event.getX());
        //System.out.println(event.getY());

        //drawCanvas.drawColor(Color.WHITE);
        //paths = new ArrayList<>();
        //invalidate();
        //brush.setErase
        //invalidate();
    }


    //
    public void startNew(){
        paths = new ArrayList<>();
        path= new Path();
        brushes= new ArrayList<>();
        cBrushes=new ArrayList<>();
        rBrushes=new ArrayList<>();
        cCenters= new ArrayList<>();
        rCorners = new ArrayList<>();
        invalidate();
    }
    //
    public Bitmap getBitmap()
    {
        //this.measure(100, 100);
        //this.layout(0, 0, 100, 100);
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(this.getDrawingCache());
        bmp.setHasAlpha(true);
        this.setDrawingCacheEnabled(false);

        Canvas canvas = new Canvas(bmp);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bmp, 0, 0, null);
        draw(canvas);

        return bmp;
    }
}