package com.example.dios.futbolito;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                    do {
                        baloncito.setX(baloncito.getX() + 30);
                    }while (baloncito.getX()<100);


                }else if(x>1){
                    do {
                        baloncito.setX(baloncito.getX() - 30);
                    }while (baloncito.getX()>150);

                }

                // eje y

                if(y<-1){
                    do{
                    baloncito.setY(baloncito.getY()-50);
                }while (baloncito.getY()>100);

                }else if (y>1){
                    do {
                        baloncito.setY(baloncito.getY() + 50);
                     }while (baloncito.getY()<150);
                }

            }



            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        start();
    }

    private  void start(){
        sensorManager.registerListener(sensorEventListener,sensor,sensorManager.SENSOR_DELAY_NORMAL);
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
