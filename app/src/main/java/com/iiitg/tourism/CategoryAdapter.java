package com.iiitg.tourism;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.iiitg.tourism.Model.CategoryModel;


public class CategoryAdapter extends FirebaseRecyclerAdapter<CategoryModel,CategoryAdapter.myViewHolder> {

    public CategoryAdapter(@NonNull FirebaseRecyclerOptions<CategoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull CategoryModel model) {
        holder.title.setText(model.getName());
        holder.sub.setText(model.getSub());

       // holder.relativeLayout.setBackgroundResource("fsdssf");
        Glide.with(holder.pic.getContext()).load(model.getPic()).into(holder.pic);


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity) v.getContext();

                Intent intent=new Intent(activity,places.class);
                intent.putExtra("pic",model.getPic());
                intent.putExtra("pic1",model.getPic1());
                intent.putExtra("pic2",model.getPic2());
                intent.putExtra("name",model.getName());
                intent.putExtra("location",model.getSub());
                intent.putExtra("yt",model.getYt());
                intent.putExtra("des1",model.getDesp1());
                intent.putExtra("des2",model.getDesp2());
                intent.putExtra("flink",model.getFlink());
                intent.putExtra("link",model.getLink());
                intent.putExtra("grid",model.getGrid());


//                intent.putExtra("location",model.getSub());
//                intent.putExtra("location",model.getSub());
//
                activity.startActivity(intent);
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_lay,parent,false);
        return new myViewHolder(v);
    }

    static class  myViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView flink,yt;
        TextView sub;
        ImageView pic;
        CardView card;
        RelativeLayout relativeLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            card=itemView.findViewById(R.id.card);
            flink=itemView.findViewById(R.id.flink);
            yt=itemView.findViewById(R.id.yt);
            relativeLayout=itemView.findViewById(R.id.whole_btn);
            title=itemView.findViewById(R.id.name);
            pic=itemView.findViewById(R.id.fpic);
            sub=itemView.findViewById(R.id.sub);

        }
    }
}
