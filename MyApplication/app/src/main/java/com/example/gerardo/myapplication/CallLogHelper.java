package com.example.gerardo.myapplication;


import android.content.Context;
import android.net.Uri;
import android.provider.CallLog;
import android.widget.Toast;


public class CallLogHelper {

public void eliminar(Context contexto){
    try {
        Uri CALLLOG_URI = Uri.parse("content://call_log/calls");

    }catch (Exception err){
        Toast.makeText(contexto.getApplicationContext(),err.getMessage(),Toast.LENGTH_LONG).show();
    }
    }










}
