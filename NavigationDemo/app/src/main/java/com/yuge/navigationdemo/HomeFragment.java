package com.yuge.navigationdemo;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.yuge.navigationdemo.databinding.FragmentHomeBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final MyViewModel myViewModel=new ViewModelProvider(getActivity()).get(MyViewModel.class);
        FragmentHomeBinding binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(getActivity());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller=Navigation.findNavController(view);
                controller.navigate(R.id.action_homeFragment_to_secondFragment);
            }
        });

        binding.seekBar.setProgress(myViewModel.getNumber().getValue());

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                myViewModel.getNumber().setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }
}
