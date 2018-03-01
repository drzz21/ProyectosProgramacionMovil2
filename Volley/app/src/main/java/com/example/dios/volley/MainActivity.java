package com.example.dios.volley;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {


    SeekBar alto;
    SeekBar ancho;
    ImageView imagen;
    CheckBox cb;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alto = findViewById(R.id.alto);
        ancho= findViewById(R.id.largo);
        imagen = findViewById(R.id.imageView);
        cb= findViewById(R.id.checkBox);
        spinner = findViewById(R.id.spinner);


        final TextView lblAlto = findViewById(R.id.textView);
        final TextView lblLargo = findViewById(R.id.textView2);


        alto.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                lblAlto.setText("Alto: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ancho.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                lblLargo.setText("Largo: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, new String[]{
                "brazil",
                "rio",
                "paris",
                "girl",
                "dog"
        });
        spinner.setAdapter(adapter);




    }

    public void clicke(View v){
        String url = "https://loremflickr.com/";
        if(cb.isActivated())
            url += "g/";
        url += ancho.getProgress() + "/";
        url += alto.getProgress() + "/";
        url += spinner.getSelectedItem();
        RequestQueue queue = Volley.newRequestQueue(this);
        ImageRequest peticion = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imagen.setImageBitmap(bitmap);
                    }
                }, 0, 0, null, // maxWidth, maxHeight, decodeConfig
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "no jala", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(peticion);

    }


}
