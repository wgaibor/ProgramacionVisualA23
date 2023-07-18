package ec.com.lemas.sare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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

import ec.com.lemas.sare.R;
import ec.com.lemas.sare.adapter.FruitAdapter;
import ec.com.lemas.sare.adapter.PersonAdapter;
import ec.com.lemas.sare.entity.PersonEntity;

public class PersonActivity extends AppCompatActivity {

    RecyclerView rvPersonas;
    FirebaseFirestore db;

    private String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        rvPersonas = (RecyclerView) findViewById(R.id.rvListadoPersona);
        db = FirebaseFirestore.getInstance();
        realizarConsulta();
    }

    private void realizarConsulta() {
        final List<PersonEntity> lstPersonaBD = new ArrayList<>();
        db.collection("usuario")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                lstPersonaBD.add(document.toObject(PersonEntity.class));
                            }
                            Log.d(TAG, lstPersonaBD.size()+"");
                            llenarRecyclerView(lstPersonaBD);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    private void llenarRecyclerView(List<PersonEntity> lstPersonaBD) {
        PersonAdapter personAdapter = new PersonAdapter(lstPersonaBD, this);
        rvPersonas.setHasFixedSize(true);
        rvPersonas.setLayoutManager(new LinearLayoutManager(this));
        rvPersonas.setAdapter(personAdapter);
    }
}