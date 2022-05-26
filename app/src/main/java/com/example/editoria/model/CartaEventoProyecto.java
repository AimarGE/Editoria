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
import com.example.editoria.R;

import java.util.List;

public class CartaEventoProyecto extends RecyclerView.Adapter<CartaEventoProyecto.ViewHolder> {

    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    final CartaEventoProyecto.OnItemClickListener listener;
    boolean guardado;

    public interface OnItemClickListener{
        void onItemClick(ListElement item);
    }


    public CartaEventoProyecto(List<ListElement> itemList, Context context, CartaEventoProyecto.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartaEventoProyecto.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.carta_proyecto_evento, null);
        guardado = false;
        return new CartaEventoProyecto.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartaEventoProyecto.ViewHolder holder, int position) {
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
        TextView name;
        LottieAnimationView favorito;
        ViewHolder(View itemView){
            super(itemView);
                name = itemView.findViewById(R.id.cvNombreEditor);
                icono = itemView.findViewById(R.id.cvIconoEditor);
                favorito = itemView.findViewById(R.id.favorito);
        }

        void bindData(final ListElement item){
            icono.setImageResource(R.drawable.ejemplo);
            name.setText(item.getName());

            //Animacion quitar poner favorito
            favorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("EJEMPLO", "favorito -> "+ item.getName());
                    guardado = likeAnimation(favorito, R.raw.heart_animation, guardado);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("EJEMPLO", "ASDASD");
                    listener.onItemClick(item);
                }
            });
        }
    }

    private boolean likeAnimation(LottieAnimationView imageView, Integer animation, Boolean like) {

        if (!like) {

            imageView.setAnimation(animation);
            imageView.playAnimation();
            Log.i("EJEMPLO", "favorito true");
        } else {
            Log.i("EJEMPLO", "favorito false");
            imageView.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            imageView.setImageResource(R.drawable.favorite_border);
                            imageView.setAlpha(1f);
                        }
                    });
        }

        return !like;
    }
}
