package com.app.skynet.tulocation.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.app.skynet.tulocation.database.APtoDB;
import com.app.skynet.tulocation.list.AccessPoint;
import com.app.skynet.tulocation.trilateration.DeviceLocation;
import com.app.skynet.tulocation.trilateration.Trilateration;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by init0 on 11.02.17.
 */

public class CustomShape extends View implements Observer {
    private ShapeDrawable mDrawable;
    private final Paint paint;
    private Canvas canvas;
    private DeviceLocation dev;
    private Rect rect;
    private APtoDB db;
    private List<AccessPoint> apeki = new ArrayList<>();
    private Path mPath;
    private Paint mPaint;
    double x = 0;
    double y = 0;

    public CustomShape(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.db = new APtoDB(context);
        mPath = new Path();
        paint = new Paint();
        canvas = new Canvas();
        paint.setColor(Color.GRAY);
        setWillNotDraw(false);
    }


    public CustomShape(Context context) {
        super(context);
        this.db = new APtoDB(context);
        paint = new Paint();
        mPath = new Path();
        rect = new Rect(2, 2, 200, 200);
        Bitmap b = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(b);
        paint.setColor(Color.GRAY);
        setWillNotDraw(false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // draw the mPath with the mPaint on the canvas when onDraw
        canvas.drawPath(mPath, paint);

        paint.setColor(Color.RED);

        canvas.drawColor(Color.BLUE);

        canvas.drawCircle((float) this.x, (float) this.y, 20, paint);
        Log.i(CustomShape.class.getName(), "W ON DROW " + x + " " + y);
        canvas.save();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        db.open();
        apeki.clear();
        apeki.addAll(db.getAllComments());
        db.close();
        processPos();
        invalidate();
        Log.i(CustomShape.class.getName(), "W ON TOUCH " + x + " " + y);

        return false;
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.i(CustomShape.class.getName(), "Trigger");
        invalidate();


    }

    private void processPos() {
        Trilateration tri = new Trilateration(apeki.get(0), apeki.get(1), apeki.get(2));
        Double[] tmp = tri.calculateCords();
        this.x = tmp[0];
        this.y = tmp[1];
        Log.i(CustomShape.class.getName(), "W ON PROC" + x + " " + y);
    }
}
