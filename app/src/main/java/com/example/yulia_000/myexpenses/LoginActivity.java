package com.example.yulia_000.myexpenses;

/**
 * Created by Marija on 09.01.2018.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    EditText txtUserName;
    EditText txtPassword;
    Button btnLogin;
    Button btnCancel;
    Button btnReg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUserName=(EditText)this.findViewById(R.id.txtUname);
        txtPassword=(EditText)this.findViewById(R.id.txtPwd);
        btnReg=(Button)this.findViewById(R.id.btnReg);
        btnReg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub


                Intent intent = new Intent(LoginActivity.this, RegistrActivity.class);
                startActivity(intent);

            }
        });
        btnLogin=(Button)this.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                String stringName=txtUserName.getText().toString();
                String stringPwd=txtPassword.getText().toString();
                //   if((txtUserName.getText().toString()).equals(txtPassword.getText().toString())){
                if(stringName.length() > 1 && stringPwd.length() > 3 ){
                    Toast.makeText(LoginActivity.this, "LogIn Successful",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, Guthaben.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(LoginActivity.this, "Invalid Login or Password",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

