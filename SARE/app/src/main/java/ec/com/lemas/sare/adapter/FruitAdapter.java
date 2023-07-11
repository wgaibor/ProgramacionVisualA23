package ec.com.lemas.sare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ec.com.lemas.sare.R;
import ec.com.lemas.sare.entity.FruitEntity;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitHolder> {

    private List<FruitEntity> lstFruitsArg;
    private Context _ctx;
    public FruitAdapter(List<FruitEntity> lstFruit, Context ctx) {
        this.lstFruitsArg = lstFruit;
        this._ctx = ctx;
    }

    @NonNull
    @Override
    public FruitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.fruit_details_card, parent, false);
        FruitHolder fruitHolder = new FruitHolder(listItem);
        return fruitHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FruitHolder holder, int position) {
        FruitEntity objFruit = lstFruitsArg.get(position);
        holder.imgFruit.setImageDrawable(ContextCompat.getDrawable(_ctx, objFruit.getPhoto()));
        holder.txtName.setText(objFruit.getNameFruit());
    }

    @Override
    public int getItemCount() {
        return lstFruitsArg.size();
    }

    static class FruitHolder extends RecyclerView.ViewHolder {
        private ImageView imgFruit;
        private TextView txtName;
        public FruitHolder(View itemView){
            super(itemView);
            this.imgFruit = (ImageView) itemView.findViewById(R.id.imgImageFruit);
            this.txtName = (TextView) itemView.findViewById(R.id.txtNameFruit);
        }
    }
}
