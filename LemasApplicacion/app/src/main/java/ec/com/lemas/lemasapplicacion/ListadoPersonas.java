package ec.com.lemas.lemasapplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ListadoPersonas extends AppCompatActivity implements View.OnClickListener {

    EditText numero1;
    EditText numero2;
    Button btnCalcular;
    TextView mostrarResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_personas);
        numero1 = (EditText) findViewById(R.id.edtNumero1);
        numero2 = (EditText) findViewById(R.id.edtNumero2);
        btnCalcular = (Button) findViewById(R.id.btnSumar);
        mostrarResultado = (TextView) findViewById(R.id.txtMuestraResultado);
        btnCalcular.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSumar){
            sumarNumero();
        }
    }

    private void sumarNumero() {
        String valor1 = numero1.getText().toString();
        String valor2 = numero2.getText().toString();
        Float resultado = Float.valueOf(valor1) + Float.valueOf(valor2);
        mostrarResultado.setText(resultado+"");
    }
}