package com.example.yulia_000.myexpenses;

/**
 * Created by Marija on 09.01.2018.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.yulia_000.myexpenses.data.model.User;
import com.example.yulia_000.myexpenses.data.repo.UserRepo;

public class LoginActivity extends Activity {

    EditText txtUserName;
    EditText txtPassword;
    Button btnLogin;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

/*  Button btnCancel;
    Button btnReg;*/

    public static final String MSG = "MSG";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUserName=(EditText)this.findViewById(R.id.txtUname);
        txtPassword=(EditText)this.findViewById(R.id.txtPwd);

        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

      //  if(sharedpreferences.getString("name", "" ) != null){
        //    Intent intent = new Intent(LoginActivity.this, DonutActivity.class);
          //  startActivity(intent);
        //}

        btnLogin=(Button)this.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                String stringName=txtUserName.getText().toString();
                String stringPwd=txtPassword.getText().toString();
                //   if((txtUserName.getText().toString()).equals(txtPassword.getText().toString())){
                if(stringName.length() > 1 && stringPwd.length() > 3 ){

                    UserRepo userRepo = new UserRepo();



                    if(userRepo.isLoggedIn(stringName,stringPwd)){
                        Toast.makeText(LoginActivity.this, "LogIn Successful",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, Guthaben.class);
                        String message =stringName;
                        intent.putExtra(MSG, message);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Name or Password",Toast.LENGTH_LONG).show();
                    }


                } else{
                    Toast.makeText(LoginActivity.this, "Invalid Name or Password",Toast.LENGTH_LONG).show();
                }

            }
        });
    }



    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        RadioButton rbtnL=(RadioButton)this.findViewById(R.id.RadioLogin);
        RadioButton rbtnR=(RadioButton)this.findViewById(R.id.RadioSignup);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.RadioLogin:
                if (checked)
                    rbtnL.setTypeface(null, Typeface.BOLD);
                    rbtnR.setTypeface(null, Typeface.NORMAL);

                    break;
            case R.id.RadioSignup:
                if (checked)
                    rbtnR.setTypeface(null, Typeface.BOLD);
                    rbtnL.setTypeface(null, Typeface.NORMAL);
                    Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
                    startActivity(intent);
                    break;
        }
    }
}

