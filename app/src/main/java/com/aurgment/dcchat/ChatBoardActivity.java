package com.aurgment.dcchat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ChatBoardActivity extends AppCompatActivity {
  /*
  We need an instance of realtime firebase realtime database

   */
  private FirebaseDatabase mDatabase;
  private DatabaseReference databaseReference;

  private EditText messageEdit;
  private ImageButton sendImageBtn;
  private RecyclerView messageRecyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat_boad);
    // initialize firebase viriable
    mDatabase = FirebaseDatabase.getInstance();
    databaseReference = mDatabase.getReference();

    messageEdit = findViewById(R.id.messageTxt);
    messageRecyclerView = findViewById(R.id.messageRecyclerView);
    sendImageBtn = findViewById(R.id.SendImageBtn);

    //get message  from user input then clear the user input

    sendImageBtn.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String message = messageEdit.getText().toString();
        messageEdit.setText("");
        sendMessage(message);
      }
    });
  }


  /*
    we are a message to the database
   */

  private void sendMessage(String message) {
    //From the root database reference, we need to create a child "messages"
    // that is where we will store our message
    //notice push(), please read more, it generates a new key for us

    DatabaseReference messageRef = databaseReference.child("messages").push();

    MessageModel msg = new MessageModel();
    msg.setMessage(message);
    msg.setMessageId(messageRef.getKey());

    messageRef.child("msg").setValue(message);
  }

  /*
     Reading message from firebase
     We will read the messages and show them in the recyclerView.

   */

  private void readMessages() {
    // we need to listen for data changes in the message node root.
    databaseReference.child("messages").addValueEventListener(new ValueEventListener() {
      @Override public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        // the data changed so we need to refresh our layout
        ArrayList<MessageModel> messages = new ArrayList<>();
        //loop dataSnapshot
        for (DataSnapshot dSt : dataSnapshot.getChildren()) {
          MessageModel msg = dSt.getValue(MessageModel.class);
          messages.add(msg);
        }
        // let us show the messages
        showMessage(messages);
      }

      @Override public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  //show the messages
  private void showMessage(ArrayList<MessageModel> messageModels) {
    messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    MessageRecyclerViewAdapter adapter = new MessageRecyclerViewAdapter(this, messageModels);
    messageRecyclerView.setAdapter(adapter);
  }
}
