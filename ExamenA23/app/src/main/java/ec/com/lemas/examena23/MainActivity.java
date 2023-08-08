package ec.com.lemas.examena23;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText identificacion;
    TextInputEditText nombre;
    TextInputEditText mail;
    Button guardar;
    Button mostrarListado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        identificacion =(TextInputEditText) findViewById(R.id.txtIdentificacion);
        nombre =(TextInputEditText) findViewById(R.id.txtNombre);
        mail = (TextInputEditText) findViewById(R.id.txtMail);
        guardar = (Button) findViewById(R.id.btnGuardar);
        mostrarListado = (Button) findViewById(R.id.btnMostrarListado);
        guardar.setOnClickListener(this);
        mostrarListado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnGuardar){
            //Escribir lógica para guardar en firebase
        } else if(v.getId() == R.id.btnMostrarListado) {
            //Escribir lógica para mostrar listado guardado
        }
    }
}