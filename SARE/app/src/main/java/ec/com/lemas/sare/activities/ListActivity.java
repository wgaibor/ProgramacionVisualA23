package ec.com.lemas.sare.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ec.com.lemas.sare.R;
import ec.com.lemas.sare.adapter.FruitAdapter;
import ec.com.lemas.sare.entity.FruitEntity;

public class ListActivity extends AppCompatActivity {

    RecyclerView rvListado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        rvListado = (RecyclerView) findViewById(R.id.rv_list);
        llenarListado();
    }

    private void llenarListado() {
        List<FruitEntity> lstFrutas = new ArrayList<>();
        FruitEntity banana = new FruitEntity();
        banana.setPhoto(R.drawable.banana);
        banana.setNameFruit("Banana");
        lstFrutas.add(banana);

        FruitEntity frutilla = new FruitEntity();
        frutilla.setPhoto(R.drawable.frutilla);
        frutilla.setNameFruit("Frutilla");
        lstFrutas.add(frutilla);

        FruitEntity guanabana = new FruitEntity();
        guanabana.setPhoto(R.drawable.guanabana);
        guanabana.setNameFruit("Guanabana");
        lstFrutas.add(guanabana);

        FruitEntity kiwi = new FruitEntity();
        kiwi.setPhoto(R.drawable.kiwi);
        kiwi.setNameFruit("Kiwi");
        lstFrutas.add(kiwi);

        FruitEntity mango = new FruitEntity();
        mango.setPhoto(R.drawable.mango);
        mango.setNameFruit("Mango");
        lstFrutas.add(mango);

        FruitEntity manzana = new FruitEntity();
        manzana.setPhoto(R.drawable.manzana);
        manzana.setNameFruit("Manzana");
        lstFrutas.add(manzana);

        FruitEntity melon = new FruitEntity();
        melon.setPhoto(R.drawable.melon);
        melon.setNameFruit("Melon");
        lstFrutas.add(melon);

        FruitEntity naranja = new FruitEntity();
        naranja.setPhoto(R.drawable.naranja);
        naranja.setNameFruit("Naranja");
        lstFrutas.add(naranja);

        FruitEntity papaya = new FruitEntity();
        papaya.setPhoto(R.drawable.papaya);
        papaya.setNameFruit("Papaya");
        lstFrutas.add(papaya);

        FruitEntity pera = new FruitEntity();
        pera.setPhoto(R.drawable.pera);
        pera.setNameFruit("Pera");
        lstFrutas.add(pera);

        FruitEntity pina = new FruitEntity();
        pina.setPhoto(R.drawable.pina);
        pina.setNameFruit("Piña");
        lstFrutas.add(pina);

        FruitEntity sandia = new FruitEntity();
        sandia.setPhoto(R.drawable.sandia);
        sandia.setNameFruit("Sandia");
        lstFrutas.add(sandia);

        FruitEntity uva = new FruitEntity();
        uva.setPhoto(R.drawable.uva);
        uva.setNameFruit("Uva");
        lstFrutas.add(uva);

        FruitAdapter fruitAdapter = new FruitAdapter(lstFrutas, this);
        rvListado.setHasFixedSize(true);
        //rvListado.setLayoutManager(new LinearLayoutManager(this));
        rvListado.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        rvListado.setAdapter(fruitAdapter);
    }
}