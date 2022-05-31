package com.example.editoria.model;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.editoria.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartaParticipantes extends RecyclerView.Adapter<CartaParticipantes.ViewHolder> {

    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    final CartaParticipantes.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(ListElement item);
    }

    public CartaParticipantes(List<ListElement> itemList, Context context, CartaParticipantes.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartaParticipantes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.carta_participante, null);
        return new CartaParticipantes.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartaParticipantes.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount( ) {
        return mData.size();
    }

    public void setItems(List<ListElement> items){
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icono;//PONER EL ICONO DE LA BASE DE DATOS DEL EDITOR
        TextView name, nlikes;

        ViewHolder(View itemView){
            super(itemView);
                name = itemView.findViewById(R.id.cvNombreEditor);
                icono = itemView.findViewById(R.id.cvIconoEditor);
                nlikes = itemView.findViewById(R.id.nLikes);
        }

        void bindData(final ListElement item){
            icono.setImageResource(R.drawable.ejemplo);
            name.setText(item.getName());
            nlikes.setText(item.getValoracion());
            Picasso.get().load(item.getIcon()).into(icono);

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

            icono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

        }
    }
}
