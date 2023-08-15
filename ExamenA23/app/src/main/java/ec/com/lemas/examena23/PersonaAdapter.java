package ec.com.lemas.examena23;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaHolder>{

    private Context _ctx;
    private List<PersonaEntity> lstPersona;

    public PersonaAdapter(Context _ctx, List<PersonaEntity> lstPersona) {
        this._ctx = _ctx;
        this.lstPersona = lstPersona;
    }

    @NonNull
    @Override
    public PersonaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_persona, parent, false);
        PersonaAdapter.PersonaHolder personaHolder = new PersonaAdapter.PersonaHolder(listItem);
        return personaHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaHolder holder, int position) {
        PersonaEntity persona = lstPersona.get(position);
        holder.dni.setText(persona.getDni());
        holder.nombres.setText(persona.getNombre());
    }

    @Override
    public int getItemCount() {
        return lstPersona.size();
    }

    static class PersonaHolder extends RecyclerView.ViewHolder {
        TextView nombres;
        TextView dni;

        public PersonaHolder(@NonNull View itemView) {
            super(itemView);
            this.nombres = itemView.findViewById(R.id.txtNombresYApellido);
            this.dni = itemView.findViewById(R.id.txtCedula);
        }
    }
}
