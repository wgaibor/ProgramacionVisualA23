package ec.com.lemas.lemasapplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button botonIngresar;
    EditText usuario;
    EditText password;

    TextView mensajePantalla;

    boolean isPrimeraVez = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonIngresar = (Button) findViewById(R.id.btnIngresar);
        usuario = (EditText) findViewById(R.id.edtUsuario);
        password = (EditText) findViewById(R.id.edtContrasenia);
        mensajePantalla = (TextView) findViewById(R.id.txtMensaje);
        botonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = "el usuario es "+usuario.getText().toString()+" y la contraseña es "+password.getText().toString();
                Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                Intent intentClaseS4 = new Intent(MainActivity.this, ListadoPersonas.class);
                startActivity(intentClaseS4);
            }
        });
        Log.i(getClass().getSimpleName(), "Ingreso por el mètodo onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(getClass().getSimpleName(), "Ingreso por el mètodo onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!isPrimeraVez){
            mensajePantalla.setText("Estamos de vuelta");
        }
        isPrimeraVez = false;
        Log.i(getClass().getSimpleName(), "Ingreso por el mètodo onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(getClass().getSimpleName(), "La aplicaciòn ha sido parada");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(getClass().getSimpleName(), "He seleccionado la aplicaciòn nuevamente");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(getClass().getSimpleName(), "Se ha finalizado la aplicaciòn");
    }
}