package net.ivanvega.misserviciosandroida;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by Dios on 31/ene/2018.
 */

public class ServicioEnlazado extends Service {

    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();


    public class LocalBinder extends Binder {
        ServicioEnlazado getService() {
            // Return this instance of LocalService so clients can call public methods
            return ServicioEnlazado.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}
