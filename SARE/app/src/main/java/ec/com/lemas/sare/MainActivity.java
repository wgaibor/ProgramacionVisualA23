package ec.com.lemas.sare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * https://firebase.google.com/docs/firestore/manage-data/add-data?hl=es-419#java_2
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText cedula;
    TextInputEditText nombre;
    Button btnGuarda;
    Button btnFotoSeleccionada;
    Button btnFotoSubida;
    Button btnEliminarFoto;
    ImageView imgPrevisualizacion;

    // Uri indicates, where the image will be picked from
    private Uri filePath;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;


    FirebaseFirestore db;
    FirebaseStorage storage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cedula = (TextInputEditText) findViewById(R.id.txtCedula);
        nombre = (TextInputEditText) findViewById(R.id.txtNombre);
        btnFotoSeleccionada = (Button) findViewById(R.id.btnSeleccionarFoto);
        btnFotoSubida = (Button) findViewById(R.id.btnSubirFoto);
        imgPrevisualizacion = (ImageView) findViewById(R.id.imgFotoCambiar);
        btnGuarda = (Button) findViewById(R.id.btnGuardar);
        btnEliminarFoto = (Button) findViewById(R.id.btnEliminarFoto);
        btnGuarda.setOnClickListener(this);
        btnFotoSeleccionada.setOnClickListener(this);
        btnFotoSubida.setOnClickListener(this);
        btnEliminarFoto.setOnClickListener(this);

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnGuardar){
            guardarInformacion();
        } else if (v.getId() == R.id.btnSeleccionarFoto) {
            seleccionarImage();
        } else if (v.getId() == R.id.btnSubirFoto) {
            subirFoto();
        } else if (v.getId() == R.id.btnEliminarFoto) {
            eliminarFoto();
        }
    }

    private void eliminarFoto() {
        //Mostrar un progreso

        StorageReference deleteRef = storageReference.child("/imagenes/prueba.png");
        deleteRef.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        mostrarMensajeSnack("Foto eliminada :) ");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mostrarMensajeSnack("No se pudo eliminar la imagen :( ");
                        e.printStackTrace();
                    }
                });
    }

    private void subirFoto() {
        if (filePath != null){
            //Mostrar un progreso
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Subiendo archivo...");
            progressDialog.show();

            // Definir la carpeta donde se van almacenar los archivos
            StorageReference ref = storageReference.child("imagenes/" + UUID.randomUUID().toString());

            // crear un listener para la actualización
            // o es posible que la subida falle y que hacer en ese caso
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            mostrarMensajeSnack("La imagen se ha subido exitosamente :) ");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            mostrarMensajeSnack("No se pudo subir la imagen :( ");
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progesoSubida = (100.0 * taskSnapshot.getBytesTransferred()
                                                    / taskSnapshot.getTotalByteCount() );
                            progressDialog.setMessage("Subiendo "+ (int) progesoSubida + "%");
                        }
                    });
        } else {
            mostrarMensajeSnack("No ha seleccionado una imagen ");
        }
    }

    private void seleccionarImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
            && resultCode == RESULT_OK
            && data != null
            && data.getData() != null){

            // Obtenera la URI de data
            filePath = data.getData();
            try {
                //Configurar para mostrar la imagen en el preview
                Bitmap bitmap = MediaStore
                                    .Images
                                    .Media
                                    .getBitmap(getContentResolver(),
                                            filePath);
                imgPrevisualizacion.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        mostrarMensajeSnack("Se creo el registro "+id);

    }

    private void mostrarMensajeSnack(String mensaje){
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.coordinatorPersona),
                mensaje, Snackbar.LENGTH_INDEFINITE);
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