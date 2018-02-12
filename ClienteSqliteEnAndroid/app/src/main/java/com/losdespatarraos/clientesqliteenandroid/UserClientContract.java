package com.losdespatarraos.clientesqliteenandroid;

/**
 * Created by Dios on 11/feb/2018.
 */

public class UserClientContract {
    public  static final String CONTENT_URI =
            "content://net.ivanvega.sqliteenandroidcurso.provider/usuarios";

    public  static final String FIELD_ID = "_id"
            ;

    public  static final String FIELD_NAME = "nombre"
            ;

    public  static final String FIELD_EMAIL = "email"
            ;

    public  static final String FIELD_PASS = "contrasenia"
            ;

}
