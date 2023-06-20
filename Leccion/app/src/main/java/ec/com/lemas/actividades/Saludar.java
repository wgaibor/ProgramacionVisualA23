package ec.com.lemas.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Saludar extends AppCompatActivity {

    TextView txtElemento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludar);
        txtElemento = (TextView) findViewById(R.id.txtNombre);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String nombre = extras.getString("valor1", "NO ME ENVIARÓN EL NOMBRE >:| ");
            if(nombre.isEmpty()){
                txtElemento.setText("No identificado");
            } else {
                txtElemento.setText(nombre);
            }
        }
    }
}