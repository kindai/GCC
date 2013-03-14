package com.game.GCC;

import android.content.Context;
import android.graphics.*;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: yuyulin
 * Date: 13-3-14
 * Time: 下午9:23
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

        super.onDraw(canvas);

        Paint p = new Paint();
        p.setColor(Color.RED);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        canvas.drawBitmap(bitmap, hat.getPositionX(), 0, p);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
        hat.setPositionX((int) event.values[2]);
        this.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
