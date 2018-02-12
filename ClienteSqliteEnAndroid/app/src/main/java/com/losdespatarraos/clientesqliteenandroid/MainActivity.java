package com.losdespatarraos.clientesqliteenandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final Uri URI_CP = Uri.parse(
            "content://net.ivanvega.sqliteenandroidcurso.provider/usuarios");

    private Uri uri;
    private Cursor c;

    private int id;
    private String nombre;
    private String email;
    private String passsword;

    ArrayList<Integer> listaids = new ArrayList<>();
    ArrayList<String> listanombres = new ArrayList<>();
    ArrayList<String> listaemails = new ArrayList<>();
    ArrayList<String> listapassswords = new ArrayList<>();
    ListView lst ;
    int _idUsuario=0;
    int operacion=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);








        try {



            lst = (ListView) findViewById(R.id.lista);
            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                    _idUsuario = posicion;
                    btnList_click();
                }

            });


            cargarUsuarios();
        }catch (Exception err){}

    }

    public void abreme(View v){
        operacion=0;
        opt();

    }

    String operaciones[]= {"Eliminar","Actualizar"};
    public void  btnList_click(){

        AlertDialog dialog =
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Editar")
                        .setItems(operaciones, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(operaciones[which].equalsIgnoreCase(operaciones[0])){
                                    eliminar();
                                }else if(operaciones[which].equalsIgnoreCase(operaciones[1])){
                                    operacion=1;
                                    opt();


                                }
                                dialog.dismiss();
                            }
                        })
                        .create();

        dialog.show();
    }

    private ContentValues setVALORES(int id, String nom, String email, String contrasenia) {
        ContentValues valores = new ContentValues();
        //valores.put("_id", id);
        valores.put("nombre", nom);
        valores.put("email", email);
        valores.put("contrasenia", contrasenia);
        return valores;
    }

    public void opt(){

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.activity_datos);

        final Button btnguardar = dialog.findViewById(R.id.btnAceptar);
        final TextView txtnombre = dialog.findViewById(R.id.txtName);
        final TextView txtemail = dialog.findViewById(R.id.txtMail);
        final TextView txtpass = dialog.findViewById(R.id.txtPass);

        if(operacion==0) {
            dialog.setTitle("Agregar Usuariio");
            Toast.makeText(this, "hi0", Toast.LENGTH_SHORT).show();
            dialog.show();
        }else if(operacion==1){
            dialog.setTitle("Agregar Usuariiod");
            txtnombre.setText(listanombres.get(_idUsuario));
            txtemail.setText(listaemails.get(_idUsuario));
            txtpass.setText(listapassswords.get(_idUsuario));
            dialog.show();
        }



        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentResolver CR = getContentResolver();

                // Insertar Registro en el Content Provider
                try {
                    if(operacion==0) {
                        CR.insert(URI_CP, setVALORES(0, txtnombre.getText().toString(), txtemail.getText().toString(), txtpass.getText().toString()));
                        Toast.makeText(getBaseContext(), "Dato Insertado", Toast.LENGTH_SHORT).show();
                    }else if(operacion==1){
                        String idactualizar = listaids.get(_idUsuario).toString();
                        uri = Uri.parse("content://net.ivanvega.sqliteenandroidcurso.provider/usuarios/"+idactualizar);
                        CR.update(uri, setVALORES(Integer.parseInt(idactualizar), txtnombre.getText().toString(), txtemail.getText().toString(), txtpass.getText().toString()),
                                null, null);
                        Toast.makeText(getBaseContext(), "Dato Actualizado", Toast.LENGTH_SHORT).show();
                    }
                    cargarUsuarios();

                }catch (Exception err){
                    Toast.makeText(getBaseContext(), err.getMessage(), Toast.LENGTH_SHORT).show();


                }

                dialog.dismiss();
            }
        });

        dialog.show();
        cargarUsuarios();


    }

    public void cargarUsuarios(){

        try {
            Cursor c = getContentResolver().query(Uri.parse(UserClientContract.CONTENT_URI), null, null,
                    null, null);

            SimpleCursorAdapter sca =
                    new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                            c, new String[]{
                            UserClientContract.FIELD_ID, UserClientContract.FIELD_NAME
                    },
                            new int[]{android.R.id.text1, android.R.id.text2},
                            SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
                    );

            lst.setAdapter(sca);
            getIDs();
        }catch (Exception err)
        {
            Toast.makeText(getBaseContext(), err.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    public void getIDs(){
        // Recuperamos todos los registros de la tabla
        try {
            ContentResolver CR = getContentResolver();
            listaids.clear();
            listanombres.clear();
            listaemails.clear();
            listapassswords.clear();

            String[] valores_recuperar = {"_id,nombre,email,contrasenia"};
            c = CR.query(URI_CP, valores_recuperar, null, null, null);
            c.moveToFirst();
            do {
                id = c.getInt(0);
                nombre = c.getString(1);
                email = c.getString(2);
                passsword = c.getString(3);

                listaids.add(id);
                listanombres.add(nombre);
                listaemails.add(email);
                listapassswords.add(passsword);

            } while (c.moveToNext());
        }catch (Exception err){
           
        }
    }



    private void eliminar() {

        try {

            ContentResolver CR = getContentResolver();
                    String ideliminar = listaids.get(_idUsuario).toString();
                    uri = Uri.parse("content://net.ivanvega.sqliteenandroidcurso.provider/usuarios/" + ideliminar);

            CR.delete(uri, null, null);
            listaids.remove(_idUsuario);
            listanombres.remove(_idUsuario);
            listaemails.remove(_idUsuario);
            listapassswords.remove(_idUsuario);
                    cargarUsuarios();


        }catch (Exception err){}
    }

}
