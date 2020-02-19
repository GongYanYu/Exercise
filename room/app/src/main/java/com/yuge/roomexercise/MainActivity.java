package com.yuge.roomexercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter1,myAdapter2;
    Switch aSwitch;
    WordDatabase wordDatabase;
    WordDao wordDao;
    Button buttonInsert,buttonUpdate,buttonDelete,buttonClear;
    MyViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonInsert=findViewById(R.id.insert);
//        buttonUpdate=findViewById(R.id.update);
//        buttonDelete=findViewById(R.id.delete);
        buttonClear=findViewById(R.id.clear);
        myViewModel=new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MyViewModel.class);
        myAdapter1=new MyAdapter(false,myViewModel);//myViewModel must be first
        myAdapter2=new MyAdapter(true,myViewModel);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter1);

        aSwitch=findViewById(R.id.switch1);

        wordDatabase= Room.databaseBuilder(this,WordDatabase.class,"database_word")
                //.allowMainThreadQueries()
                .build();
        wordDao=wordDatabase.getWordDao();

        myViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> list) {
                int temp=myAdapter1.getItemCount(); //get all words count number
                myAdapter1.setAllWords(list);
                if(temp!=list.size()){//底层数据修改 会两次刷新界面 update不需要二次刷新 减少时耗
                    myAdapter1.notifyDataSetChanged();
                }
                myAdapter2.setAllWords(list);
                myAdapter2.notifyDataSetChanged();
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    recyclerView.setAdapter(myAdapter2);
                else
                    recyclerView.setAdapter(myAdapter1);
            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String english[]={
                        "example",
                        "ok",
                        "yes",
                        "grant",
                        "fund",
                        "succession",
                        "seize"
                };
                String meaning[]={
                        "例子",
                        "好的亲",
                        "是的呢",
                        "赋予",
                        "资金，资助",
                        "连续的，继承人",
                        "抓住"
                };

                for(int i=0;i<english.length;i++){
                    myViewModel.insertWords(new Word(english[i],meaning[i]));
                }
            }
        });
//        buttonUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Word word=new Word("xx","xx");
//                word.setId(20);
//                myViewModel.updateWords(word);
//
//            }
//        });
//        buttonDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Word word=new Word("xx","xx");
//                word.setId(20);
//                myViewModel.deleteWords(word);
//            }
//        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.ClearWords();
            }
        });
    }


}
