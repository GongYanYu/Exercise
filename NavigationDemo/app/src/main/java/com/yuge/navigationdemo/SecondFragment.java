package com.yuge.navigationdemo;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yuge.navigationdemo.databinding.FragmentSecondBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_second, container, false);

        MyViewModel myViewModel= new ViewModelProvider(getActivity()).get(MyViewModel.class);
        FragmentSecondBinding binding= DataBindingUtil.inflate(inflater,R.layout.fragment_second,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(getActivity());

        binding.button4.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_secondFragment_to_homeFragment));

        return binding.getRoot();
    }
}
