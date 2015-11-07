package com.example.pc10.grupo04;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ListarContactos extends Activity{
    ListView lista;
    Button regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contactos);
        lista = (ListView) findViewById(R.id.listView);
        regresar = (Button) findViewById(R.id.button3);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayList<Contacto> contactos = new ArrayList<>();
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("contactos.txt")));
            boolean guardar;
            Contacto c = new Contacto();
            String linea;
            boolean nombre = true;

            while((linea = reader.readLine())!= null)
            {
                if(linea.equals("<->")){
                    contactos.add(c);
                    c = new Contacto();
                }else
                    if(nombre)
                    {
                        c.setNombre(linea);
                        nombre = !nombre;
                    }else
                    {
                        c.setNumero(linea);
                        nombre=!nombre;
                    }
            }


            lista.setAdapter(new ContactoAdaptador(this,contactos));
        }catch(Exception ex){

        }


    }


}
