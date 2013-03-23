package com.game.GCC;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import common.CoinBitMaps;

import java.util.Iterator;

public class Coin implements DrawUnit {
	
	private final static int STATUS_BIRTH = 1;
	private final static int STATUS_DEAD = 2;
	
	private int birthX;
	private int locationY;
	private int size;
	private int speed;
	private int status;
    private Rect oldRect;
    private Rect newRect;
    private int rotateSpeed;
    private Iterator<Bitmap> coins;

    private void reFreshRect(){
        oldRect = newRect;
        newRect = new Rect(birthX, locationY, birthX + size, locationY + size);
    }

	public Coin(int x, int y, int s, Context context, int spd) {
        birthX = x;
		locationY = y;
		size = s;
        oldRect = new Rect(birthX, locationY, birthX + size, locationY + size);
        newRect = new Rect(birthX, locationY, birthX + size, locationY + size);
		speed = spd;
        rotateSpeed = 10;
		status = STATUS_BIRTH;
        coins = CoinBitMaps.getCoinBitMap();
	}
	
	public void changeStatus() {
		locationY += speed;
		if (locationY >= 400) {
			status = STATUS_DEAD;
		}
        reFreshRect();
	}
	
	public boolean isDead() {
		return status == STATUS_DEAD;
	}

	@Override
	public Bitmap getBitmap() {
		// TODO Auto-generated method stub
        if (coins.hasNext()) {
            Bitmap rst = (Bitmap) coins.next();
            return rst;
        }else{
            coins = CoinBitMaps.getCoinBitMap();
            Bitmap rst = (Bitmap) coins.next();
            return rst;
        }
	}

	@Override
	public Rect oldRectangle() {
		// TODO Auto-generated method stub
		return oldRect;
	}

	@Override
	public Rect newRectangle() {
		return newRect;
	}

    @Override
    public boolean isDraw() {
        return !isDead();
    }

    public boolean checkCollision(Rect r){
        return r.intersect(this.newRectangle());
    }

    public void collide() {
        this.status = STATUS_DEAD;
    }

    public int getRotateSpeed() {
        return rotateSpeed;
    }

    public void setRotateSpeed(int rotateSpeed) {
        this.rotateSpeed = rotateSpeed;
    }
}
