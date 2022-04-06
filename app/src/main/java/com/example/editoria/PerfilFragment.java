package com.example.editoria;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;

public class PerfilFragment extends Fragment {

    LottieAnimationView favorito;
    boolean guardado;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        favorito = view.findViewById(R.id.favorito);

        guardado = false;


        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardado = likeAnimation(favorito, R.raw.heart_animation, guardado);
            }
        });

        /*favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getActivity(), "toggle favorito", Toast.LENGTH_SHORT).show();
            }
        });*/


        // Inflate the layout for this fragment
        return view;
    }


    private boolean likeAnimation(LottieAnimationView imageView, Integer animation, Boolean like){

        if (!like){
            imageView.setAnimation(animation);
            imageView.playAnimation();
        }else{
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