package com.example.cooperativapp;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//Librerias de SQLite
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
public class FormActivity extends AppCompatActivity {
    //Declarar variables
    EditText edtRut, edtNom, edtPla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        //Defino los campos del formulario
        edtRut = (EditText) findViewById(R.id.edtRut);
        edtNom = (EditText) findViewById(R.id.edtNom);
        edtPla = (EditText) findViewById(R.id.edtPla);
    }

    public void CargarLista() {
        DataHelper dh = new DataHelper(this, "socios.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        Cursor c = bd.rawQuery("Select rut, nombre, tipoplan from socio", null);
        String[] arr = new String[c.getCount()];
        if (c.moveToFirst() == true) {
            int i = 0;
            do {
                String linea = "||" + c.getInt(0) + "||" + c.getString(1)
                        + "||" + c.getString(2) + "||" + c.getString(3) + "||";
                arr[i] = linea;
                i++;
            } while (c.moveToNext() == true);
            bd.close();
        }
    }

    public void onClickAgregar(View view) {
        DataHelper dh = new DataHelper(this, "socios.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        reg.put("rut", edtRut.getText().toString());
        reg.put("nombre", edtNom.getText().toString());
        reg.put("tipoplan", edtPla.getText().toString());
        long resp = bd.insert("socio", null, reg);
        bd.close();
        if (resp == -1) {
            Toast.makeText(this, "No se ´puede ingresar el socio", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Registro Agregado", Toast.LENGTH_LONG).show();
        }
        CargarLista();
        limpiar();
    }

    //Metodo para limpiar los campos de texto
    public void limpiar() {
        edtRut.setText("");
        edtNom.setText("");
        edtPla.setText("");
    }

    //Metodo para modificar un campo
    public void onClickModificar(View view) {
        //Conexion a la BDD para manipular los registros
        DataHelper dh = new DataHelper(this, "socios.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        //Envio los datos a modificar
        reg.put("rut", edtRut.getText().toString());
        reg.put("nombre", edtNom.getText().toString());
        reg.put("tipoplan", edtPla.getText().toString());
        //Actualizo el registro de la BBD por el RUT
        long respuesta = bd.update("socio", reg, "rut=?", new String[]{edtRut.getText().toString()});
        bd.close();
        //Envio la respuesta al usuario
        if (respuesta == -1) {
            Toast.makeText(this, "Dato No Modificado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Dato Modificado", Toast.LENGTH_LONG).show();
        }
        limpiar();
        CargarLista();
    }
    public void onClickEliminar(View view){
        //Conecto la BDD para eliminar un registro de tabla alumno
        DataHelper dh = new DataHelper(this, "socios.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        //Ingreso el rut del registro a modificar
        String RutEliminar = edtRut.getText().toString();
        //Query para eliminar el registro
        long respuesta = bd.delete("socio", "rut=" + RutEliminar, null);
        bd.close();
        //Verifico que el registro se elimine
        if (respuesta == -1){
            Toast.makeText(this,"Dato No Eliminado", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Dato Eliminado", Toast.LENGTH_LONG).show();
        }
        limpiar();
        CargarLista();
    }
}




