package com.example.editoria.model;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.editoria.GlobalVariable;
import com.example.editoria.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    final ListAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(ListElement item);
    }

    public ListAdapter(List<ListElement> itemList, Context context, ListAdapter.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.carta_proyectos, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
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
        ImageView icono, fotoP;//PONER EL ICONO DE LA BASE DE DATOS DEL EDITOR
        TextView name, titulo, precio, valoraciones;

        ViewHolder(View itemView){
            super(itemView);
                name = itemView.findViewById(R.id.cvNombreEditor);
                icono = itemView.findViewById(R.id.cvIconoEditor);
                titulo = itemView.findViewById(R.id.tituloProyecto);
                precio = itemView.findViewById(R.id.cvPrecioProyecto);
                fotoP = itemView.findViewById(R.id.imagenProyecto);
                valoraciones = itemView.findViewById(R.id.cvValoracionesValue);
        }

        void bindData(final ListElement item){
            icono.setImageResource(R.drawable.image_placeholder);
            name.setText(item.getName());
            titulo.setText(item.getTitulo());
            precio.setText("Precio: "+item.getPrecio()+"€");


            if (item.getValoracion() != null && !item.getValoracion().equalsIgnoreCase("")){
                valoraciones.setText(item.getValoracion()+" (1)");
            }

            if (item.getFoto() != null && !item.getFoto().equalsIgnoreCase("")){
                Picasso.get().load(item.getFoto()).into(fotoP);
            }

            if (item.getIcon() != null && !item.getIcon().equalsIgnoreCase("")){
                Picasso.get().load(item.getIcon()).into(icono);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

}
