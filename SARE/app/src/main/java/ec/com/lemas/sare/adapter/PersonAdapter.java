package ec.com.lemas.sare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ec.com.lemas.sare.R;
import ec.com.lemas.sare.entity.PersonEntity;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonHolder> {

    private List<PersonEntity> lstPersonas;
    private Context _ctx;

    public PersonAdapter(List<PersonEntity> lstPersonas, Context _ctx) {
        this.lstPersonas = lstPersonas;
        this._ctx = _ctx;
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.person_details_card, parent, false);
        PersonAdapter.PersonHolder personHolder = new PersonAdapter.PersonHolder(listItem);
        return personHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {
        PersonEntity persona = lstPersonas.get(position);
        holder.nombre.setText(persona.getNombre());
        holder.identificacion.setText(persona.getCedula());
    }

    @Override
    public int getItemCount() {
        return lstPersonas.size();
    }

    static class PersonHolder extends RecyclerView.ViewHolder {
        private TextView identificacion;
        private TextView nombre;
        public PersonHolder(@NonNull View itemView) {
            super(itemView);
            this.identificacion = (TextView) itemView.findViewById(R.id.txtIdentificacionPersona);
            this.nombre = (TextView) itemView.findViewById(R.id.txtNombrePersona);
        }
    }
}
