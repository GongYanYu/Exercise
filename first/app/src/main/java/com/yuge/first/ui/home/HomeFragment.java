package com.yuge.first.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.yuge.first.

import com.yuge.first.R;

public class HomeFragment extends Fragment {

    private Button submit;
    private EditText editText;
    private MyViewModel myViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myViewModel=new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(MyViewModel.class);
        editText=requireActivity().findViewById(R.id.editText);
        submit=requireActivity().findViewById(R.id.button);
        editText.setText(myViewModel.getFormer());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=editText.getText().toString();
                if(str.isEmpty())
                    Toast.makeText(requireContext(), "请输入", Toast.LENGTH_SHORT).show();
                else{
                    StringBuffer sign=new StringBuffer("");
                    for (int i=0;i<str.length();i++){
                        if(str.charAt())
                    }
                }
            }
        });
    }
}
