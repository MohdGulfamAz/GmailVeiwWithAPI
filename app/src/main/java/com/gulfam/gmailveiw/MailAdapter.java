package com.gulfam.gmailveiw;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.viewHolder> {


    List<GmailAPI> mGmailAPIList;
    Context mContext;

    public MailAdapter(List<GmailAPI> list,Context context) {
        this.mGmailAPIList = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_recylclerview, parent, false);
        return new viewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final viewHolder holder, final int position) {

        String url = "";
        url += mGmailAPIList.get(position).getImage();
        Picasso.get().load(url).into(holder.mImageView);
        holder.mTextView1.setText(mGmailAPIList.get(position).getTitle());
        holder.mTextView2.setText(mGmailAPIList.get(position).getBody());

        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,InsideListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                String path = "";
                path = mGmailAPIList.get(position).getImage();
                intent.putExtra("image",path);
                intent.putExtra("titelName",mGmailAPIList.get(position).getTitle());
                intent.putExtra("description",mGmailAPIList.get(position).getBody());
                mContext.startActivity(intent);
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGmailAPIList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView1, mTextView2;
        LinearLayout mLinearLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mLinearLayout = itemView.findViewById(R.id.mail_layout_btn);
            mTextView1 = itemView.findViewById(R.id.BigText);
            mTextView2 = itemView.findViewById(R.id.description);
            mImageView = itemView.findViewById(R.id.imageView);
        }
    }
}
