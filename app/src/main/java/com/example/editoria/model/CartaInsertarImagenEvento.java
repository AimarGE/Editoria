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

public class CartaInsertarImagenEvento extends RecyclerView.Adapter<CartaInsertarImagenEvento.ViewHolder> {

    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    private ImageView foto;
    final CartaInsertarImagenEvento.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(ListElement item);
    }

    public CartaInsertarImagenEvento(List<ListElement> itemList, Context context, CartaInsertarImagenEvento.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartaInsertarImagenEvento.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.carta_insertar_imagen_evento, null);
        return new CartaInsertarImagenEvento.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartaInsertarImagenEvento.ViewHolder holder, int position) {
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
        TextView name, titulo, precio;

        ViewHolder(View itemView){
            super(itemView);
                name = itemView.findViewById(R.id.cvNombreParticipante);
                icono = itemView.findViewById(R.id.cvIconoEditor);
                foto = itemView.findViewById(R.id.insertImagenEvent);
        }

        void bindData(final ListElement item){
            icono.setImageResource(R.drawable.ejemplo);
            name.setText(item.getName());
            Picasso.get().load(item.getFoto()).into(foto);

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public ImageView getImageView() {
        return foto;
    }
}
