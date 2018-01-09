package com.example.yulia_000.myexpenses;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Marija on 08.01.2018.
 */

public class RegistrActivity extends Activity{

    EditText txtUserName;
    EditText txtPassword;
    Button btnReg;
    Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        txtUserName=(EditText)this.findViewById(R.id.txtNname);
        txtPassword=(EditText)this.findViewById(R.id.txtPwd);

        btnReg=(Button)this.findViewById(R.id.btnReg);
        btnReg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub

                String stringName=txtUserName.getText().toString();
                String stringPwd=txtPassword.getText().toString();
                //   if((txtUserName.getText().toString()).equals(txtPassword.getText().toString())){
                if(stringName.length() > 1 && stringPwd.length() > 3 ){
                    Toast.makeText(RegistrActivity.this, "Successful",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegistrActivity.this, Guthaben.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(RegistrActivity.this, "Invalid Login or Password",Toast.LENGTH_LONG).show();
                }


            }
        });

        btnLogin=(Button)this.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegistrActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }


}
