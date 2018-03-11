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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int a=0;
    ImageView baloncito=null;

    int width=0;
    int height=0;
    int equipo1=0,equipo2=0;
    TextView m1,m2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baloncito =  (ImageView)findViewById(R.id.baloncito);
        m1 =  (TextView) findViewById(R.id.MarcadorA);
        m2 =  (TextView) findViewById(R.id.MarcadorB);

        final ImageView cancha =  (ImageView)findViewById(R.id.canchita);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width=metrics.widthPixels;
        height = metrics.heightPixels;



        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor==null){
            finish();
        }

        sensorEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {



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

//                Log.d("x",x+"");
//                Log.d("y",y+"");

                if(y<-1){
                    if(baloncito.getY()>0) {
                        baloncito.setY(baloncito.getY() - 50);
                    }else {
                        if(baloncito.getX()>400&&baloncito.getX()<580) {
                            golecito();
                            equipo1++;
                            m1.setText(equipo1+"");

                        }
                    }

                }else if (y>1){
                    if(baloncito.getY()<(width-baloncito.getHeight()+400)) {
                        baloncito.setY(baloncito.getY() + 50);

                    }else {
                        if(baloncito.getX()>400&&baloncito.getX()<580) {
                            golecito();
                            equipo2++;
                            m2.setText(equipo2+"");
                        }
                    }


                }

            }



            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        start();
    }

    public void golecito(){
        baloncito.setY(645);
        baloncito.setX(410);
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
        golecito();
    }
}
