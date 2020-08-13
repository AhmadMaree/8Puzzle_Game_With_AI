package com.example.ai8puzzle;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterHolder> {

    Context context ;



    List<Tiels> mTiels;
    onTileClickedListner listner;

    public MyAdapter(Context context, List<Tiels> mTiels, onTileClickedListner listner) {
        this.context = context;
        this.mTiels = mTiels;
        this.listner = listner;
    }

    public interface onTileClickedListner {
        void onTileClicked(int position);
    }
    public void setmTiels(List<Tiels> mTiels) {
        this.mTiels = mTiels;
    }
    @NonNull
    @Override
    public MyAdapter.MyAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view,parent,false);
        return new MyAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyAdapterHolder holder, int position) {
        if(mTiels.get(position).getFlagnumber() == 9) {
            if (mTiels.get(position).getNum() == 9) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.tilesbtn.setBackground(context.getDrawable(R.drawable.question));
                }
            } else
                holder.tilesbtn.setText(mTiels.get(position).getNum() + "");
        }else if(mTiels.get(position).getFlagnumber() == 16){
            if (mTiels.get(position).getNum() == 16) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.tilesbtn.setBackground(context.getDrawable(R.drawable.question));
                }
            } else
                holder.tilesbtn.setText(mTiels.get(position).getNum() + "");
        }else if(mTiels.get(position).getFlagnumber() == 25) {
            if (mTiels.get(position).getNum() == 25) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.tilesbtn.setBackground(context.getDrawable(R.drawable.question));
                }
            }
            else {
                holder.tilesbtn.setText(mTiels.get(position).getNum() + "");
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mTiels == null)
            return 0;
        return mTiels.size();
    }

    public class MyAdapterHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        Button tilesbtn;
        public MyAdapterHolder(@NonNull View itemView) {
            super(itemView);
            tilesbtn=itemView.findViewById(R.id.rv_btn);
            tilesbtn.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            listner.onTileClicked(getAdapterPosition() + 1);
        }
    }
}
