package com.game.GCC;

import java.util.List;

import android.content.Context;
import android.graphics.*;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: yuyulin
 * Date: 13-3-14
 * Time: 涓��9:23
 * To change this template use File | Settings | File Templates.
 */
public class PlayView extends View {

	private Context play_context;

    public PlayView(Context context) {
        super(context);
        play_context = context;
    }
    
    private List<DrawUnit> getDrawUnits(){
    	return ((Play)play_context).getDrawUnits();
    }
    
    public void reDraw(DrawUnit du){
    	//this.invalidate(du.oldRectangle());
    	this.invalidate(du.newRectangle());
    }

    @Override
    protected void onDraw(Canvas canvas) {
    	// coins.each do |c|
    	//drawBitmap(c.getBitmap, c.newRectangle)
    	//end

        super.onDraw(canvas);
        int screenWidth = this.getWidth();
        int screenHeight = this.getHeight();
        Paint p = new Paint();

        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        List<DrawUnit> units = getDrawUnits();
        for (int i = 0; i < units.size(); i ++ ) {
        	canvas.drawBitmap(units.get(i).getBitmap(), null, units.get(i).newRectangle(), p);
        }
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);    //To change body of overridden methods use File | Settings | File Templates.
    }

}
