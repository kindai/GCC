package com.game.GCC;

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
public class PlayView extends View implements SensorEventListener {

    SensorManager sensor;
    Sensor orient_sensor;
    Hat hat;


    public Hat getHat() {
        return hat;
    }

    public void setHat(Hat hat) {
        this.hat = hat;
    }

    public PlayView(Context context) {
        super(context);

        sensor = (SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        orient_sensor = sensor.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }
    
    public void reDraw(DrawUnit du){
    	this.invalidate(du.oldRectangle());
    	this.invalidate(du.newRectangle());
    }

    // sensor
    public void mountSensor(){
        sensor.registerListener(this, orient_sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unmountSensor(){
        sensor.unregisterListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);    //To change body of overridden methods use File | Settings | File Templates.
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
        p.setColor(Color.RED);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        canvas.drawBitmap(bitmap, hat.getPositionX(), 0, p);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.

        float alpha = 0.8f;
        float[] gravity = {9.8f, 9.8f, 9.8f};

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        float linear_acceleration = event.values[0] - gravity[0];
        //linear_acceleration[1] = event.values[1] - gravity[1];
        //linear_acceleration[2] = event.values[2] - gravity[2];

        hat.setPositionX((int) event.values[2]);
        Log.e("acc", String.format("%f", event.values[2]));
        this.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
