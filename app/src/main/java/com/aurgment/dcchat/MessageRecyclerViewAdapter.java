package com.aurgment.dcchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/**
 * Created by Korir on 2/13/20.
 * amoskrr@gmail.com
 */
public class MessageRecyclerViewAdapter
    extends RecyclerView.Adapter<MessageRecyclerViewAdapter.MyViewHolder> {
  private Context context;
  private ArrayList<MessageModel> messages = new ArrayList<>();

  public MessageRecyclerViewAdapter(Context context,
      ArrayList<MessageModel> messages) {
    this.context = context;
    this.messages = messages;
  }

  @NonNull @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new MyViewHolder(
        LayoutInflater.from(context).inflate(R.layout.message_layout, parent, false));
  }

  @Override public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    String message = messages.get(position).getMessage();
    TextView messageTv = holder.itemView.findViewById(R.id.messageTxt);
    messageTv.setText(message);
  }

  @Override public int getItemCount() {
    return messages.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {

    MyViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
