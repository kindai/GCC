package com.dev;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.CheckBox;

public class CanvasPanel extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
       // initialComponents();
        initialComponentsManul();
    }
    

    
    //
    void initialComponents()
    {
//    	checkbox = (CheckBox)findViewById(R.id.id_checkbox);
//    	cv = (CanvasView)findViewById(R.id.canvas_textview);
    }
    void initialComponentsManul()
    {
    	AbsoluteLayout alayout = new AbsoluteLayout(this);
    	setContentView(alayout);
        //����һ��button��ť
    	cv = new CanvasView(this);
        //cv.setText("this is a button");
        cv.setId(1);
        //ȷ������ؼ��Ĵ�С��λ��
        int width = this.getWindowManager().getDefaultDisplay().getWidth();
        int height = this.getWindowManager().getDefaultDisplay().getHeight();
       AbsoluteLayout.LayoutParams lp1 = 
                                 new AbsoluteLayout.LayoutParams(
                                      width - 30,
                                      height - 60,
                                      0,0);
       alayout.addView(cv, lp1 );
    }
    private CanvasView cv;
    private CheckBox checkbox;
    
}