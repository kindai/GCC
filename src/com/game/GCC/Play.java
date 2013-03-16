package com.game.GCC;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.hardware.*;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created with IntelliJ IDEA.
 * User: yuyulin
 * Date: 13-3-14
 * Time: 涓��9:18
 * To change this template use File | Settings | File Templates.
 */
public class Play extends Activity implements SensorEventListener{

    // 
	private final static int INTERVAL = 20;
	private int counter;
	
	private SensorManager sensor;
    private Sensor orient_sensor;
    private long timestamp;
    private Hat hat;
    private List<DrawUnit> draw_list;
    private Timer timer;
    private TimerTask timerTask;
    private Handler timerHandler;

    PlayView view;
    
    private void init(){
        hat = new Hat(300, 300, 50, this);
        
        draw_list = new ArrayList<DrawUnit>();
        draw_list.add(hat);
        
        view = new PlayView(this);
        view.setId(1);

        LinearLayout layout = new LinearLayout(this);
        setContentView(layout);
        layout.addView(view);

        sensor = (SensorManager)getSystemService(this.SENSOR_SERVICE);
        orient_sensor = sensor.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        timestamp = 0;
        
        counter = 0;
        timer = new Timer();
		mountTimer();
        timer.schedule(timerTask, 0, 100);    
    }
    
    private Coin generateCoin() {
    	return new Coin((int)(100 + Math.random()*200), 0, 40, this, 20);
    }
    
    public List<DrawUnit> getDrawUnits()
    {
    	return draw_list;
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mountSensor();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unmountSensor();
    }
    
    // sensor
    private void mountSensor(){
        sensor.registerListener(this, orient_sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    private void unmountSensor(){
        sensor.unregisterListener(this);
    }
    
    private void mountTimer(){
    	
    	timerHandler = new Handler() {
    		public void handleMessage(Message msg) {
    			switch(msg.what) {
    			case 1: // timer handler
    				counter = (counter + 1) % INTERVAL;
    				Log.e("timercc", String.format("%d", counter));
        			if (counter == 0) {
        				draw_list.add(generateCoin());
        			}
        			for (int i = 1; i < draw_list.size(); i ++) {
        				((Coin)draw_list.get(i)).changeStatus();
        				if (!((Coin)draw_list.get(i)).isDead()) {
        					view.reDraw(draw_list.get(i));
        				}
        			}
    				break;
    			}
    		}
    	};
    	
    	timerTask = new TimerTask() {
    		public void run(){
    			Log.e("BCC", String.format("%d", counter));
    			Message msg = new Message();
    			msg.what = 1;
    			timerHandler.sendMessage(msg);		
    			
    		}
    	};
    }

    // 
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (timestamp != 0) {
		  hat.setPositionX(hat.getPositionX() + (int) (event.values[2] * 300 * (event.timestamp - timestamp) / 1000000000));
//        Log.e("acc", String.format("%f", event.values[2]));
          view.reDraw(hat);	
		}
		timestamp = event.timestamp;
	}
}