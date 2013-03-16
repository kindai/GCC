package com.game.GCC;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created with IntelliJ IDEA.
 * User: yuyulin
 * Date: 13-3-14
 * Time: ä¸??9:10
 * To change this template use File | Settings | File Templates.
 */
public class Hat implements DrawUnit {
	
	public Hat(int x, int y, int _size, Context context) {
		positionX = x;
		positionY = y;
		size = _size;
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
	}
	
    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
    	if (positionX >= 600 || positionX <= 0) {
    		return;
    	}
        this.positionX = positionX;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int positionX;
    private int positionY;
    private int size;
    private Bitmap bitmap;
    
	@Override
	public Bitmap getBitmap() {
//		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		return bitmap;
	}

	@Override
	public Rect oldRectangle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rect newRectangle() {
		return new Rect(positionX, positionY, positionX + size, positionY + size);
	}

}
