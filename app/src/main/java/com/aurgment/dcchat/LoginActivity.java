package com.aurgment.dcchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

  /*
  We are going to use firebase Auth to allow the users to register and login to their accounts.

  FibaseAuth is a package and we should import it into our project by adding the a dependency to how gradle file.

  I am trying to make this code as simple as possible

   */

  private FirebaseAuth mAuth;
  private EditText emailEdxt;
  private EditText passwordEdxt;
  private Button loginButton;
  private TextView registerNavTv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    //initialize the firebaseAuth

    mAuth = FirebaseAuth.getInstance();

    // bind the views
    emailEdxt = findViewById(R.id.emailEd);
    passwordEdxt = findViewById(R.id.passwordEd);
    loginButton = findViewById(R.id.loginBtn);
    registerNavTv = findViewById(R.id.registerTv);

    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        // get the user input , we would validate  these for now,

        String email = emailEdxt.getText().toString();
        String password = passwordEdxt.getText().toString();
        loginUser(email, password);
      }
    });

    registerNavTv.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        // take the user to the registration page
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        //ignore below line
        finish();
      }
    });
  }

  private void loginUser(String email, String password) {
    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
        new OnCompleteListener<AuthResult>() {
          @Override public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
              // the user is logged in successful, we should to the chatBoardActivity

              startActivity(new Intent(LoginActivity.this, ChatBoardActivity.class));
            } else {
              // the use is not logged in ... we should tell them

              Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
          }
        });
  }
}