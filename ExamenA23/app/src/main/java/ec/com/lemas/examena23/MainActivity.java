package ec.com.lemas.examena23;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText identificacion;
    TextInputEditText nombre;
    TextInputEditText mail;
    Button guardar;
    Button mostrarListado;

    FirebaseFirestore db;
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
        //Iniciar el acceso a la base de datos / conexión a la base datos
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnGuardar){
            guardarRegisto();
        } else if(v.getId() == R.id.btnMostrarListado) {
            Intent actividadNueva = new Intent(this, PersonaActivity.class);
            startActivity(actividadNueva);
        }
    }

    private void guardarRegisto() {
        Map<String, Object> objUsuario = new HashMap<>();
        objUsuario.put("dni", identificacion.getText().toString());
        objUsuario.put("nombre", nombre.getText().toString());
        objUsuario.put("correo", mail.getText().toString());

        db.collection("usuario")
                .add(objUsuario)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(">>>>>>", "Insert realizado exitosamente: " + documentReference.getId());
                        despuesDeGuardar(documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(">>>>>>", "Error adding document", e);
                    }
                });
    }

    private void despuesDeGuardar(String id) {
        identificacion.setText("");
        nombre.setText("");
        mail.setText("");
        mostrarMensajePersonalizado("Se creo el registro  "+id);
    }

    private void mostrarMensajePersonalizado(String mensaje) {
        Snackbar mySnackBar = Snackbar.make(findViewById(R.id.coordinatorPersona), mensaje, Snackbar.LENGTH_INDEFINITE);
        mySnackBar.setAction("Aceptar", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySnackBar.dismiss();
            }
        });
        mySnackBar.show();
    }
}