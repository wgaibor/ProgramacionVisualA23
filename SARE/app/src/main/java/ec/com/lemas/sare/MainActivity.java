package ec.com.lemas.sare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * https://firebase.google.com/docs/firestore/manage-data/add-data?hl=es-419#java_2
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText cedula;
    TextInputEditText nombre;
    Button btnGuarda;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cedula = (TextInputEditText) findViewById(R.id.txtCedula);
        nombre = (TextInputEditText) findViewById(R.id.txtNombre);
        btnGuarda = (Button) findViewById(R.id.btnGuardar);
        btnGuarda.setOnClickListener(this);
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnGuardar){
            guardarInformacion();
        }
    }

    private void guardarInformacion() {
        Map<String, Object> user = new HashMap<>();
        user.put("cedula", cedula.getText().toString());
        user.put("nombre", nombre.getText().toString());

        db.collection("usuario")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        despuesDeGuardar(documentReference.getId());
                        Log.i(">>>>>", "Insert realizado exitosamente  "+documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("<<<<<<", e);
                    }
                });
    }

    private void despuesDeGuardar(String id) {
        cedula.setText("");
        nombre.setText("");
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.coordinatorPersona),
                "Se creo el registro "+id, Snackbar.LENGTH_INDEFINITE);
        mySnackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.naranja));
        mySnackbar.setActionTextColor(ContextCompat.getColor(this, R.color.magenta));
        mySnackbar.setAction("Aceptar", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySnackbar.dismiss();
            }
        });
        mySnackbar.show();

    }
}