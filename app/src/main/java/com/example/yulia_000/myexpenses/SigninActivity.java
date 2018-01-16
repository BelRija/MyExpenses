package com.example.yulia_000.myexpenses;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.yulia_000.myexpenses.data.model.User;
import com.example.yulia_000.myexpenses.data.repo.UserRepo;

/**
 * Created by Yulia_000 on 15.01.2018.
 */

public class SigninActivity extends Activity {
    EditText txtUserName;
    EditText txtPassword;
    Button btnLogin;

    public static final String MSG = "MSG";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);

        txtUserName=(EditText)this.findViewById(R.id.txtUname);
        txtPassword=(EditText)this.findViewById(R.id.txtPwd);

        btnLogin=(Button)this.findViewById(R.id.btnSignin);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            // TODO Auto-generated method stub
                String stringName=txtUserName.getText().toString();
                String stringPwd=txtPassword.getText().toString();
                //   if((txtUserName.getText().toString()).equals(txtPassword.getText().toString())){
                if(stringName.length() > 1 && stringPwd.length() > 3 ){

                    UserRepo userRepo = new UserRepo();
                    User user = new User();
                    user.setName(stringName);
                    user.setPassword(stringPwd);
                    userRepo.insert(user);

                    Toast.makeText(SigninActivity.this, "SignIn Successful",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SigninActivity.this, Guthaben.class);
                    String message =stringName;
                    intent.putExtra(MSG, message);
                    startActivity(intent);
                } else{
                    Toast.makeText(SigninActivity.this, "Invalid Name or Password",Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(SigninActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.RadioSignup:
                if (checked)
                    rbtnR.setTypeface(null, Typeface.BOLD);
                rbtnL.setTypeface(null, Typeface.NORMAL);

                break;
        }
    }

}
