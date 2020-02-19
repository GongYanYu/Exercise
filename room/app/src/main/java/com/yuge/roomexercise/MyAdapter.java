package com.yuge.roomexercise;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends Adapter<MyAdapter.MyViewHolder> {

    private boolean useCardView;
    private List<Word> allWords=new ArrayList<>(); //防止空指针
    MyViewModel myViewModel;

    void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }

    MyAdapter(boolean useCardView,MyViewModel myViewModel) {
        this.useCardView = useCardView;
        this.myViewModel=myViewModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView;
        if(useCardView)
            itemView=layoutInflater.inflate(R.layout.cell_card_2,parent,false);
        else
            itemView=layoutInflater.inflate(R.layout.cell_normal_2,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Word word=allWords.get(position);
        holder.textViewNumber.setText(String.valueOf(position+1));
        holder.textViewEnglish.setText(word.getWord());
        holder.textViewMeaning.setText(word.getMeaning());

        //由于用的是可回收的layout 此语句保证switch初始化时 不会因为调用setChecked 而调用setOnCheckedChangeListener
        holder.aSwitchChineseInvisible.setOnCheckedChangeListener(null);

        if(word.isFoo()){
            holder.textViewMeaning.setVisibility(View.GONE);//View.GONE 在视图上直接消除
            holder.aSwitchChineseInvisible.setChecked(true);//set default open.
        }else {
            holder.textViewMeaning.setVisibility(View.VISIBLE);
            holder.aSwitchChineseInvisible.setChecked(false);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //用默认浏览器跳转到指定URI
                 Uri uri= Uri.parse("https://m.youdao.com/dict?le=eng&q="+holder.textViewEnglish.getText());
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.aSwitchChineseInvisible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    holder.textViewMeaning.setVisibility(View.GONE);//gone为直接消失 invisible 仅不可见
                    word.setFoo(true);
                    myViewModel.updateWords(word);
                }else{
                    holder.textViewMeaning.setVisibility(View.VISIBLE);
                    word.setFoo(false);
                    myViewModel.updateWords(word);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber,textViewEnglish,textViewMeaning;
        Switch aSwitchChineseInvisible;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber=itemView.findViewById(R.id.textViewNumber);
            textViewEnglish=itemView.findViewById(R.id.textViewEnglish);
            textViewMeaning=itemView.findViewById(R.id.textViewMeaning);
            aSwitchChineseInvisible=itemView.findViewById(R.id.switchChineseInvisible);
        }
    }
}
