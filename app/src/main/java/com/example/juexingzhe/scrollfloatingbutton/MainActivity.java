package com.example.juexingzhe.scrollfloatingbutton;

import android.graphics.Path;
import android.graphics.Point;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity implements SideScrollView.OnScrollListener {

    private SideScrollView mSideScrollView;

//    private FloatingActionButton mFloatBtn;

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSideScrollView = (SideScrollView) findViewById(R.id.side_scroll_view);
        mSideScrollView.setOnScrollListener(this);
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.mipmap.ic_launcher);
        mPopupWindow = new PopupWindow(imageView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.pop_anim_style);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSideScrollView.fullScroll(View.FOCUS_UP);
                if (mPopupWindow.isShowing()){
                    mPopupWindow.dismiss();
                }
            }
        });
//        mFloatBtn = (FloatingActionButton) findViewById(R.id.floatingActionButton);

    }

    @Override
    public void onScroll(int scrollY) {
        Log.d("Side", "scrollY = " + String.valueOf(scrollY));
        if (scrollY > getScreenHeight() / 2) {
//            mFloatBtn.setVisibility(View.VISIBLE);
            if (!mPopupWindow.isShowing()) {
                mPopupWindow.showAtLocation(mSideScrollView, Gravity.BOTTOM | Gravity.RIGHT, getScreenWidth() / 10, getScreenHeight() / 10);
            }
        } else if (scrollY < getScreenHeight() / 2) {
//            mFloatBtn.setVisibility(View.GONE);
            if (mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            }
        }
    }

    private int getScreenHeight() {
        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();

        Point point = new Point();

        defaultDisplay.getSize(point);
        Log.d("Side", "height = " + point.y);

        return point.y;
    }

    private int getScreenWidth() {
        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();

        Point point = new Point();

        defaultDisplay.getSize(point);
        Log.d("Side", "height = " + point.y);

        return point.x;
    }
}
