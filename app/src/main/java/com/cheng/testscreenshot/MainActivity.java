package com.cheng.testscreenshot;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ScreenShotListenManager screenShotListenManager;
    private boolean isHasScreenShotListener = false;
    private TextView tv_path;
    private ImageView iv_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_path = findViewById(R.id.tv_path);
        iv_pic = findViewById(R.id.iv_pic);
        screenShotListenManager = ScreenShotListenManager.newInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startScreenShotListen();
    }

    private void startScreenShotListen() {
        if (!isHasScreenShotListener && screenShotListenManager != null) {
            screenShotListenManager.setListener(new ScreenShotListenManager.OnScreenShotListener() {
                @Override
                public void onShot(final String imagePath) {
                    tv_path.setText("获得截图路径：" + imagePath);
                    iv_pic.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                }
            });
            screenShotListenManager.startListen();
            isHasScreenShotListener = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopScreenShotListen();
    }

    private void stopScreenShotListen() {
        if (isHasScreenShotListener && screenShotListenManager != null) {
            screenShotListenManager.stopListen();
            isHasScreenShotListener = false;
        }
    }

}
