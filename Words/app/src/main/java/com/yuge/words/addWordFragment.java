package com.yuge.words;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class addWordFragment extends Fragment {

    WordViewModel wordViewModel;

    public addWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_word, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wordViewModel = new ViewModelProvider.AndroidViewModelFactory(
                getActivity().getApplication()).create(WordViewModel.class);
        final EditText editTextEnglish = requireActivity().findViewById(R.id.editText_english);
        final EditText editTextChinese = requireActivity().findViewById(R.id.editText_chinese);
        final Button buttonSubmit = requireActivity().findViewById(R.id.button_submit);

        editTextEnglish.requestFocus();//get focus
        //调用系统键盘 必须放在onActivityCreated
        final InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTextEnglish, 0);

        buttonSubmit.setEnabled(false);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String english = editTextEnglish.getText().toString().trim();//trim  blank char from fore and back;
                String chinese = editTextChinese.getText().toString().trim();
                buttonSubmit.setEnabled(!english.isEmpty() && !chinese.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };// ;
        editTextChinese.addTextChangedListener(textWatcher);
        editTextEnglish.addTextChangedListener(textWatcher);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String english = editTextEnglish.getText().toString().trim();//trim  blank char from fore and back;
                String chinese = editTextChinese.getText().toString().trim();

                Word word = new Word();
                word.setWord(english);
                word.setChinese_meaning(chinese);
                wordViewModel.insertWords(word);

                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_addWordFragment_to_homeFragment);

                //shut down keyboard
                imm.hideSoftInputFromWindow(v.getWindowToken(),0);
            }
        });
    }
}
