package com.example.gerardo.myapplication;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gerardo.myapplication.Model.DaoContactos;
import com.example.gerardo.myapplication.Pojos.Contacto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private ViewStub stubList;
    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private List<Contacto> listacontactos;

    private Contacto contacto;

    private int operacion = 0;
    private int indice = 0;
    private EditText txtbuscar;


    String operaciones[] =
            new String[]
                    {"Desbloquear"};



    public void  btnList_click(){
        AlertDialog dialog =
                new AlertDialog.Builder(getContext())
                        .setTitle("")
                        .setIcon(R.mipmap.ic_launcher)
                        .setItems(operaciones, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(operaciones[which].equalsIgnoreCase(operaciones[0])){
                                   confirmacion();
                                }

                                dialog.dismiss();
                            }
                        })
                        .create();

        dialog.show();
    }



    public void confirmacion(){

        AlertDialog dialog =
                new AlertDialog.Builder(getContext())
                        .setTitle("Desbloquear")
                        .setIcon(android.R.drawable.ic_delete)
                        .setMessage("Esta seguro de querer desbloquer este contacto?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                DaoContactos dao = new DaoContactos(getContext());
                                dao.delete(listacontactos.get(indice).getID()+"");
                                setAdapters();

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Toast.makeText(getContext(),"Cancelar",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();

        dialog.show();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment,container,false);


        stubList = (ViewStub) view.findViewById(R.id.stub_list);


        //inflar ViewStub antes de obtener la vista
        stubList.inflate();


        listView = (ListView) view.findViewById(R.id.mylistview);


        clic();


            setAdapters();
            botonflotante(view);

        return view;
    }

    public void clic(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                indice = i;
                btnList_click();
            }
        });
    }
    private void botonflotante(View view){
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operacion=1;
                Intent contactos = new Intent(getContext(), contactos.class);
                startActivity(contactos);
                setAdapters();
            }
        });

    }

    private void setAdapters() {
           DaoContactos daocontact = new DaoContactos(getContext());
        listacontactos = new ArrayList<>();
        listacontactos = daocontact.getContactos();

            listViewAdapter = new ListViewAdapter(getContext(), R.layout.list_item, daocontact.getContactos());
            listView.setAdapter(listViewAdapter);

    }


    AdapterView.OnItemLongClickListener onItemClick = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            indice = i;
          //  btnList_click();
            return false;
        }
    };




    @Override
    public void onResume(){
        super.onResume();
        setAdapters();
    }





}//
