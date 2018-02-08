package com.example.gerardo.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Dios on 07/feb/2018.
 */

public class autoinicio extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent service = new Intent(context,  Servicio.class);
        context.startService(service);
    }

}
