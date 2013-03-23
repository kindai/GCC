package common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import com.game.GCC.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yuyulin
 * Date: 13-3-21
 * Time: 下午9:50
 * To change this template use File | Settings | File Templates.
 */
public class CoinBitMaps {
    static private List<Bitmap> coins;
    static private int width;
    static private int height;

    static public Iterator<Bitmap> getCoinBitMap(){
        return coins.iterator();
    }

    public static void loadCoin(Context context, int id){
        coins = new ArrayList<Bitmap>();
        Bitmap originCoin = BitmapFactory.decodeResource(context.getResources(), id);
        Matrix mt = new Matrix();
        for(int i = 0; i<180; i+=5){
            mt.postRotate(i);
            coins.add(Bitmap.createBitmap(originCoin, 0, 0, originCoin.getWidth(), originCoin.getHeight(), mt, true));
        }
    }

    private CoinBitMaps(){

    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        CoinBitMaps.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        CoinBitMaps.height = height;
    }
}
