package com.yuge.first.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.yuge.first.*;
import com.yuge.first.R;

public class HomeFragment extends Fragment {

    private Button submit;
    private EditText editTextPut;
    private EditText editTextAns;
    private MyViewModel myViewModel;
    private MyStringsUtils myStringsUtils;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myViewModel=new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(MyViewModel.class);
        editTextPut=requireActivity().findViewById(R.id.editTextPut);
        submit=requireActivity().findViewById(R.id.button);
        myStringsUtils=new MyStringsUtils();

        editTextPut.setText(myViewModel.getFormerPut());
        editTextAns.setText(myViewModel.getFormerAns());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=editTextPut.getText().toString();
                if(str.isEmpty())
                    Toast.makeText(requireContext(), "请输入", Toast.LENGTH_SHORT).show();
                else{
                    String ans=myStringsUtils.rChinese(str);
                    ans=myStringsUtils.rChineseMarks(ans);
                    ans=myStringsUtils.rT(ans);
                    ans=myStringsUtils.rEnglishMarks(ans);
                    ans=ans.charAt(0)=='/'?ans:ans+"/";
                    ans+="https://pan.baidu.com";
                    editTextAns.setText(ans);
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        myViewModel.setFormer(editTextPut.getText().toString(),editTextAns.getText().toString());
    }
}
