package ec.com.lemas.lemasapplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button botonIngresar;
    EditText usuario;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonIngresar = (Button) findViewById(R.id.btnIngresar);
        usuario = (EditText) findViewById(R.id.edtUsuario);
        password = (EditText) findViewById(R.id.edtContrasenia);
        botonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = "el usuario es "+usuario.getText().toString()+" y la contraseña es "+password.getText().toString();
                Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }
}