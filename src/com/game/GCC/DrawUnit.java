package com.game.GCC;

import android.graphics.Bitmap;
import android.graphics.Rect;

public interface DrawUnit {
	Bitmap getBitmap();
	Rect oldRectangle();
	Rect newRectangle();
    boolean isDraw();
}
