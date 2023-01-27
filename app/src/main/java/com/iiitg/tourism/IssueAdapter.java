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
import com.iiitg.tourism.Model.IssueModel;


public class IssueAdapter extends FirebaseRecyclerAdapter<IssueModel,IssueAdapter.myViewHolder> {

    public IssueAdapter(@NonNull FirebaseRecyclerOptions<IssueModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull IssueModel model) {
        holder.title.setText(model.getName());
        holder.sub.setText(model.getSub());

        // holder.relativeLayout.setBackgroundResource("fsdssf");
        Glide.with(holder.pic.getContext()).load(model.getPic()).into(holder.pic);


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_SENDTO);
                String mailto="mailto:"+ Uri.encode(model.getEmail())+"?subject=Raising issue against"+
                        Uri.encode("")+Uri.encode("");
                Uri m=Uri.parse(mailto);
                i.setData(m);
                v.getContext().startActivity(Intent.createChooser(i,"Send Email"));
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
