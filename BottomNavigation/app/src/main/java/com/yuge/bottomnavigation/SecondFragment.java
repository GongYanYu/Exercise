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

public class SecondFragment extends Fragment {

    private SecondViewModel mViewModel;
    private ImageView imageView;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.second_fragment, container, false);
        imageView=view.findViewById(R.id.imageView);//在创建时 关联view
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(SecondViewModel.class);

        final ObjectAnimator objectAnimatorX=ObjectAnimator.ofFloat(imageView,"scaleX",0);
        final ObjectAnimator objectAnimatorY=ObjectAnimator.ofFloat(imageView,"scaleY",0);
        objectAnimatorX.setDuration(500);
        objectAnimatorY.setDuration(500);

        imageView.setScaleX(mViewModel.scaleFloat);
        imageView.setScaleY(mViewModel.scaleFloat);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!objectAnimatorX.isRunning()){
                    objectAnimatorX.setFloatValues(imageView.getScaleX()+0.1f);
                    objectAnimatorY.setFloatValues(imageView.getScaleY()+0.1f);
                    mViewModel.scaleFloat+=0.1f;
                    objectAnimatorX.start();
                    objectAnimatorY.start();
                }
            }
        });

    }

}
