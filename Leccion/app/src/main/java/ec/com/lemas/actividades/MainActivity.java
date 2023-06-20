package ec.com.lemas.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtValorIngresado;
    Button btnSiguienteActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtValorIngresado = (EditText) findViewById(R.id.edtValor);
        btnSiguienteActividad = (Button) findViewById(R.id.btnSiguiente);
        btnSiguienteActividad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSiguiente){
           llamarAOtraActividad();
        }
    }

    private void llamarAOtraActividad() {
        Intent intentoLLamarOtraActividad = new Intent(MainActivity.this, Saludar.class);
        String resultado = edtValorIngresado.getText().toString();
        intentoLLamarOtraActividad.putExtra("valor1", resultado);
        startActivity(intentoLLamarOtraActividad);
    }
}