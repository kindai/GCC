package com.game.GCC;

import android.app.Activity;
import android.hardware.*;
import android.os.Bundle;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created with IntelliJ IDEA.
 * User: yuyulin
 * Date: 13-3-14
 * Time: 下午9:18
 * To change this template use File | Settings | File Templates.
 */
public class Play extends Activity {

    Hat hat;


    PlayView view;

    @Override
    protected void onResume() {
        super.onResume();
        view.mountSensor();
    }

    @Override
    protected void onStop() {
        super.onStop();
        view.unmountSensor();
    }

    private void init(){
        hat = new Hat();

        view = new PlayView(this);
        view.setId(1);
        view.setHat(hat);

        LinearLayout layout = new LinearLayout(this);
        setContentView(layout);
        layout.addView(view);


    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
}