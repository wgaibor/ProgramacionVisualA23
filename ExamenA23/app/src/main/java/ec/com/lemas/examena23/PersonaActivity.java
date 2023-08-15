package ec.com.lemas.examena23;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PersonaActivity extends AppCompatActivity {

    RecyclerView rvPersonas;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);
        rvPersonas = (RecyclerView) findViewById(R.id.rvListado);
        db = FirebaseFirestore.getInstance();
        consultarPersona();
    }

    private void consultarPersona() {
        List<PersonaEntity> lstPersonaBD = new ArrayList<>();
        db.collection("usuario")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(">>>>>", document.getId() + " => " + document.getData());
                                lstPersonaBD.add(document.toObject(PersonaEntity.class));
                            }
                            Log.d(">>>>>", lstPersonaBD.size()+"");
                            llenarRecyclerView(lstPersonaBD);
                        } else {
                            Log.w(">>>>>", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void llenarRecyclerView(List<PersonaEntity> lstPersonaBD) {
        PersonaAdapter personaAdapter = new PersonaAdapter(this, lstPersonaBD);
        rvPersonas.setHasFixedSize(true);
        rvPersonas.setLayoutManager(new LinearLayoutManager(this));
        rvPersonas.setAdapter(personaAdapter);
    }
}