package ec.com.lemas.leccionprimerparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int contador = 0;

    TextView txtValorContador;
    Button btnContar;
    Button btnReincio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtValorContador = findViewById(R.id.labelContador);
        btnContar = findViewById(R.id.btnContar);
        btnReincio = findViewById(R.id.btnReiniciar);
        btnContar.setOnClickListener(this);
        btnReincio.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnContar){
            contador++;
        } else if (v.getId() == R.id.btnReiniciar) {
            contador = 0;
        }
        txtValorContador.setText(contador+"");
    }
}