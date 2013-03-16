package com.game.GCC;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Coin implements DrawUnit {
	
	private final static int STATUS_BIRTH = 1;
	private final static int STATUS_DEAD = 2;
	
	private int birthX;
	private int locationY;
	private int size;
	private int speed;
	private int status;
	private Bitmap bitmap;

	public Coin(int x, int y, int s, Context context, int spd) {
		birthX = x;
		locationY = y;
		size = s;
		speed = spd;
		status = STATUS_BIRTH;
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
	}
	
	public void changeStatus() {
		locationY += speed;
		if (locationY >= 400) {
			status = STATUS_DEAD;
		}
	}
	
	public boolean isDead() {
		return status == STATUS_DEAD;
	}

	@Override
	public Bitmap getBitmap() {
		// TODO Auto-generated method stub
		return bitmap;
	}

	@Override
	public Rect oldRectangle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rect newRectangle() {
		// TODO Auto-generated method stub
		return new Rect(birthX, locationY, birthX + size, locationY + size);
	}

}
