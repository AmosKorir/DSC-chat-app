package com.aurgment.dcchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

  private FirebaseAuth mAuth;
  private EditText emailEdxt;
  private EditText passwordEdxt;
  private Button registerButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    //initial FirebaseAuth
    mAuth = FirebaseAuth.getInstance();

    emailEdxt = findViewById(R.id.emailEd);
    passwordEdxt = findViewById(R.id.passwordEd);
    registerButton = findViewById(R.id.registerBtn);

    registerButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        // get the user inputs, we won't validate
        String email = emailEdxt.getText().toString();
        String password = passwordEdxt.getText().toString();
        registerUser(email, password);
      }
    });
  }

  /*
   *FirebaseAuth has more auth methods but for this case we are using email and password.
   *password should be 6 + characters
   * Explore other methods
   */
  private void registerUser(String email, String password) {
    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
        new OnCompleteListener<AuthResult>() {
          @Override public void onComplete(@NonNull Task<AuthResult> task) {
            // check whether the user is registered. If he/she registered , navigate to chatBoard

            if (task.isSuccessful()) {
              startActivity(new Intent(RegisterActivity.this, ChatBoardActivity.class));
              //ignore finish
              finish();
            }
          }
        });
  }
}
