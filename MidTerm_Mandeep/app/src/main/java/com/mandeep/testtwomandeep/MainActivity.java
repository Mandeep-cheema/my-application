package com.mandeep.testtwomandeep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  Button btnLogin;
  EditText etUsername;
  EditText etPassword;
  EditText etStudentName;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btnLogin = findViewById(R.id.btn_login);
    etUsername = findViewById(R.id.et_username);
    etPassword = findViewById(R.id.et_password);
    etStudentName = findViewById(R.id.et_student_name);

    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!etUsername.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()
            && !etStudentName.getText().toString().isEmpty()) {
          if (etUsername.getText().toString().equals("student1") && etPassword.getText().toString().equals("123456")) {
            Intent intent = new Intent(MainActivity.this, SecondPage.class);
            intent.putExtra("STUDENT_NAME", etStudentName.getText().toString());
            startActivity(intent);
          } else {
            Toast.makeText(MainActivity.this, "invalid username or password",Toast.LENGTH_SHORT).show();
          }
        } else {
          Toast.makeText(MainActivity.this, "please complete the field",Toast.LENGTH_SHORT).show();
        }
      }
    });
  }
}