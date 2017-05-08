package com.example.administrator.timeviewdemo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.view.yuanf;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private TimeView time_view;
    private Button button;
    private yuanf yf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yf = (yuanf) findViewById(R.id.yf);
        yf.setOnTouchListener(this);

        time_view = (TimeView)findViewById(R.id.time_view);
        time_view.setTime(9,35,43);
        time_view.start();
        button = (Button) findViewById(R.id.button);
        String data = StringData();

        button.setText(data);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }
});
    }
    public static String StringData(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if("1".equals(mWay)){
            mWay ="天";
        }else if("2".equals(mWay)){
            mWay ="一";
        }else if("3".equals(mWay)){
            mWay ="二";
        }else if("4".equals(mWay)){
            mWay ="三";
        }else if("5".equals(mWay)){
            mWay ="四";
        }else if("6".equals(mWay)){
            mWay ="五";
        }else if("7".equals(mWay)){
            mWay ="六";
        }
        return "星期"+mWay;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                //      钟表
//                if (x > 130 && y > 180 && x < 250  && y < 250) {
//                    Toast.makeText(MainActivity.this,"点击了",Toast.LENGTH_SHORT).show();
//                }

                //圆方

                //圆心坐标
                int lastX =  yf.getWidth() / 2;
                int lastY =  yf.getHeight() / 2;

                Log.i("zzz","x  y " + x +"  "+ y + " last x y " + lastX + " " + lastY );

                //圆半径 通过左右坐标计算获得getLeft
                int r = (yf.getRight()-yf.getLeft())/2-5;

                //点击位置x坐标与圆心的x坐标的距离
                int distanceX = Math.abs((int)x-lastX);
                //点击位置y坐标与圆心的y坐标的距离
                int distanceY = Math.abs((int)y-lastY);
                //点击位置与圆心的直线距离
                int distanceZ = (int) Math.sqrt(Math.pow(distanceX,2)+Math.pow(distanceY,2));

                //如果点击位置与圆心的距离小于圆的半径，证明点击位置在圆内
                if(distanceZ < r){

                    float x1 = x - lastX;
                    float y1 = (float) Math.floor((double) (y - lastY));

                    if( r-Math.abs(x1) > Math.abs(y1)){

                        if (x>lastX-r/2 && x< lastX+r/2 && y >lastY-r /2&& y<lastY+r/2){
                            Toast.makeText(MainActivity.this,"在方内",Toast.LENGTH_SHORT).show();
                            return true;
                        }


                        Toast.makeText(MainActivity.this,"在菱形内",Toast.LENGTH_SHORT).show();
                        return true;
                    }


                    Toast.makeText(MainActivity.this,"在圆内",Toast.LENGTH_SHORT).show();

                }


                break;
        }
        return true;

    }
}
