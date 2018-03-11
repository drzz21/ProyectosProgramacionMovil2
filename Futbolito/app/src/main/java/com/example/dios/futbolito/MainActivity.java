package com.example.dios.futbolito;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int a=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView baloncito =  (ImageView)findViewById(R.id.baloncito);
        final ImageView porteria =  (ImageView)findViewById(R.id.porteria);
        final ImageView cancha =  (ImageView)findViewById(R.id.canchita);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final int width=metrics.widthPixels;
        final int height = metrics.heightPixels;

        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor==null){
            finish();
        }

        sensorEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                if (porteria.getX() == baloncito.getX()){
                    Toast.makeText(MainActivity.this, "gol", Toast.LENGTH_SHORT).show();
                }

                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];

                if (x<-1){
                        if(baloncito.getX()<width-baloncito.getWidth()) {
                            baloncito.setX(baloncito.getX() + 30);
                        }

                }else if(x>1){
                        if(baloncito.getX()>1) {
                            baloncito.setX(baloncito.getX() - 30);
                        }

                }

                // eje y

                Log.d("x",x+"");
                Log.d("y",y+"");

                if(y<-1){
                    if(baloncito.getY()>0) {
                        baloncito.setY(baloncito.getY() - 50);
                    }
                }else if (y>1){
                    if(baloncito.getY()<(width-baloncito.getHeight()+400)) {
                        baloncito.setY(baloncito.getY() + 50);
                    }
                }

            }



            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        start();
    }

    private  void start(){
        sensorManager.registerListener(sensorEventListener,sensor,sensorManager.SENSOR_DELAY_GAME);
    }

    private  void stop(){
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        stop();
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        start();
        super.onResume();
    }
}
