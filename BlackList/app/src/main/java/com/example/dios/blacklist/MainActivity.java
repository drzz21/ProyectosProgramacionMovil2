package com.example.dios.blacklist;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnhazalgo = (Button) findViewById(R.id.button2);
        final ListView lslog = (ListView) findViewById(R.id.listaLog);





//        btnhazalgo.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {


                String[] projection = new String[]{
                        CallLog.Calls.TYPE,
                        CallLog.Calls.NUMBER};

                Uri llamadasUri = CallLog.Calls.CONTENT_URI;

                ContentResolver cr = getContentResolver();

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                   //permisos
                    return;
                }
                Cursor cur = cr.query(llamadasUri,
                        projection, //Columnas a devolver
                        null,       //Condición de la query
                        null,       //Argumentos variables de la query
                        null);      //Orden de los resultados

                if (cur.moveToFirst())
                {
                    int tipo;
                    String tipoLlamada = "";
                    String telefono;

                    int colTipo = cur.getColumnIndex(CallLog.Calls.TYPE);
                    int colTelefono = cur.getColumnIndex(CallLog.Calls.NUMBER);

                    int indexx=0;
                    String strings[] = new String[100];

                    try {
                        do {

                            tipo = cur.getInt(colTipo);
                            telefono = cur.getString(colTelefono);

                            if (tipo == CallLog.Calls.INCOMING_TYPE) {
                                tipoLlamada = "ENTRADA";
                            } else if (tipo == CallLog.Calls.OUTGOING_TYPE) {
                                tipoLlamada = "SALIDA";
                            } else if (tipo == CallLog.Calls.MISSED_TYPE) {
                                tipoLlamada = "PERDIDA";
                            }

                            strings[indexx++] = new String("" + tipoLlamada + " - " + telefono);
                            lslog.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, strings));


                        } while (cur.moveToNext());
                    }catch (Exception e){

                    }
                }
            }
//        });
//    }






    public void enviame(View view){
        Intent intent = new Intent(this,listacontacts.class);
        startActivity(intent);
    }







}
