package ec.com.lemas.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button boton9;
    Button boton8;
    Button boton7;
    Button botonSumar;
    Button boton4;
    Button boton5;
    Button boton6;
    Button botonComa;
    Button botonResultado;

    Button botonLimpia;

    TextView pantallaDigital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pantallaDigital = (TextView) findViewById(R.id.txtNumeroIngresado);

        boton9 = (Button) findViewById(R.id.btn9);
        boton9.setOnClickListener(this);

        boton8 = (Button) findViewById(R.id.btn8);
        boton8.setOnClickListener(this);

        boton7 = (Button) findViewById(R.id.btn7);
        boton7.setOnClickListener(this);

        botonSumar = (Button) findViewById(R.id.btnMas);
        botonSumar.setOnClickListener(this);

        boton4 = (Button) findViewById(R.id.btn4);
        boton4.setOnClickListener(this);

        boton5 = (Button) findViewById(R.id.btn5);
        boton5.setOnClickListener(this);

        boton6 = (Button) findViewById(R.id.btn6);
        boton6.setOnClickListener(this);

        botonComa = (Button) findViewById(R.id.btnComa);
        botonComa.setOnClickListener(this);

        botonLimpia = (Button) findViewById(R.id.btnLimpiar);
        botonLimpia.setOnClickListener(this);

        botonResultado = (Button) findViewById(R.id.btnIgual);
        botonResultado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn9) {
            printerTexto(boton9.getText().toString());
        } else if( v.getId() == R.id.btn8) {
            printerTexto(boton8.getText().toString());
        } else if( v.getId() == R.id.btn7) {
            printerTexto(boton7.getText().toString());
        } else if( v.getId() == R.id.btnMas) {
            botonComa.setEnabled(true);
            printerTexto(botonSumar.getText().toString());
        } else if( v.getId() == R.id.btn4) {
            printerTexto(boton4.getText().toString());
        } else if( v.getId() == R.id.btn5) {
            printerTexto(boton5.getText().toString());
        } else if( v.getId() == R.id.btn6) {
            printerTexto(boton6.getText().toString());
        } else if( v.getId() == R.id.btnComa) {
            botonComa.setEnabled(false);
            printerTexto(botonComa.getText().toString());
        } else if( v.getId() == R.id.btnIgual) {
            botonComa.setEnabled(true);
            ejecutarResultado();
        } else if( v.getId() == R.id.btnLimpiar ){
            botonComa.setEnabled(true);
            limpiarTodo();
        }
    }

    private void ejecutarResultado() {
        Double resultado = evaluarExpresion(pantallaDigital.getText().toString());
        pantallaDigital.setText(resultado+"");
    }


    public static Double evaluarExpresion(String expresion) {
        String[] elementos = expresion.split("(?=[+-])");

        Double resultado = 0.0;
        Double signo = 1.0;

        for (String elemento : elementos) {
            if (elemento.equals("+")) {
                signo = 1.0;
            } else if (elemento.equals("-")) {
                signo = -1.0;
            } else {
                Double numero = Double.valueOf(elemento);
                resultado += signo * numero;
            }
        }

        return resultado;
    }

    private void limpiarTodo() {
        pantallaDigital.setText("");
    }

    private void printerTexto(String caracter) {
        String concatenar = pantallaDigital.getText() + caracter;
        pantallaDigital.setText(concatenar);
    }


}