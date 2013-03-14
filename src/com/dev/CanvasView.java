package com.dev;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorListener;
import android.hardware.SensorEventListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class CanvasView extends View{

	public CanvasView(Context context) {
		
		super(context);
		// TODO Auto-generated constructor stub
		initialData();
	}
	
    public boolean onTouchEvent(MotionEvent event)
    {

    	float x = event.getX();
    	float y = event.getY();
    	int sIndex = -1;
    	for (int minIndex = 0; minIndex<pointNum; minIndex++)
    	{
    		if(Math.abs(pointList[minIndex].x - x) < pointArc && Math.abs(pointList[minIndex].y - y) < pointArc)
    		{
    			sIndex = minIndex;
    			break;
    		}
    	}
    	if (sIndex>=0)
    	{
    		try{
    			switch(event.getAction())
    			{
    			case MotionEvent.ACTION_DOWN:
    				// change color
    				break;
    			case MotionEvent.ACTION_MOVE:
    				pointList[sIndex].y = (int)y;
    				this.invalidate(new Rect(rcLeftTop.x, rcLeftTop.y, rcRightBottom.x, rcRightBottom.y));
    				break;
    			case MotionEvent.ACTION_UP:
    				break;
    			}
    		}catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	return true;
    }
	
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		int canvasWidth = this.getWidth();
		int canvasHeight = this.getHeight();
		rcLeftTop = new Point(boundary, boundary);
		rcRightBottom = new Point(canvasWidth - boundary, canvasHeight - boundary);
		
		canvas.drawColor(Color.BLACK);
		
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		// ���������ɫ
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(1.5f);
		
		canvas.drawLine(rcLeftTop.x, rcLeftTop.y, rcLeftTop.x, rcRightBottom.y, paint);
		canvas.drawLine(rcLeftTop.x, rcRightBottom.y, rcRightBottom.x, rcRightBottom.y, paint);
		
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(1f);
		for(int i=0; i<pointNum-1; i++)
		{
			canvas.drawLine(pointList[i].x, pointList[i].y, pointList[i+1].x, pointList[i+1].y, paint);
			
		}
		
		
	}
	
	void initialData()
	{
		pointList = new Point[pointNum];
		pointList[0] = new Point(50, 20);
		pointList[1] = new Point(170, 160);
		pointList[2] = new Point(250, 220);
		
		person = new Point(this.getWidth()/2, this.getHeight());
		target = new Point(this.getWidth()/2, this.getHeight()/2);
		
	}
	
	final private int boundary = 30; 
	final private int pointNum  =3;
	// pointArc��Χ�ڵĵ����Ч
	final private int pointArc = 8;
	private Point[] pointList;
	private Point rcLeftTop;
	private Point rcRightBottom;
	
	// sensor
	private SensorManager sm;
	private Point person;
	private Point target;

}
