package com.yuge.bottomnavigation;

import androidx.lifecycle.ViewModelProviders;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Random;

public class ThirdFragment extends Fragment {

    private ThirdViewModel mViewModel;
    private ImageView imageView;

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.third_fragment, container, false);
        imageView=view.findViewById(R.id.imageView);//在创建时 关联view
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(ThirdViewModel.class);

        final ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(imageView,"X",0);
        objectAnimator.setDuration(500);
        imageView.setX(imageView.getX()+mViewModel.XFloat);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!objectAnimator.isRunning()){
                    Float f=new Random().nextBoolean() ?100f:-100f;
                    objectAnimator.setFloatValues(imageView.getX()+f);
                    mViewModel.XFloat+=f;
                    objectAnimator.start();
                }
            }
        });
    }

}
