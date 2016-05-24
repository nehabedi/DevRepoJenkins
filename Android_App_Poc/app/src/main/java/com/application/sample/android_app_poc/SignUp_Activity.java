package com.application.sample.android_app_poc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp_Activity extends AppCompatActivity implements View.OnClickListener{

    private Button signup_done;
    private EditText name,email,signup_password,confirm_password;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initialiseview();
      }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup_done:

                if (TextUtils.isEmpty(name.getText().toString()))
                {
                    Toast.makeText(this,"Enter user name",Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(email.getText().toString()))
                {
                    Toast.makeText(this,"Enter email",Toast.LENGTH_LONG).show();

                } else if (TextUtils.isEmpty(signup_password.getText().toString()))
                {
                    Toast.makeText(this,"Enter password",Toast.LENGTH_LONG).show();

                } else if (TextUtils.isEmpty(confirm_password.getText().toString())) {
                    Toast.makeText(this,"Enter confirm password",Toast.LENGTH_LONG).show();

                } else {
                    pd.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                        /*CustomDialog cd=new CustomDialog(SignUp_Activity.this);
                        cd.show();*/

                            showConfirmationDialog();

                        }
                    }, 5000);


                }
        }
    }

    private void initialiseview()
    {
        signup_done=(Button)findViewById(R.id.btn_signup_done);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        signup_password=(EditText)findViewById(R.id.signup_password);
        confirm_password=(EditText)findViewById(R.id.confirm_password);
        signup_done.setOnClickListener(this);
        pd=new ProgressDialog(this);
        pd.setMessage("Please wait...");
    }

    public void showConfirmationDialog() {

        /*Intent in = new Intent(SignUp_Activity.this, Login_Activity.class);
        startActivity(in);*/
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SignUp_Activity.this);
        alertDialogBuilder.setTitle("Signup");
        alertDialogBuilder.setMessage("Signup Successful");

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int arg1) {
                dialogInterface.dismiss();
                Intent in = new Intent(SignUp_Activity.this, Login_Activity.class);
                startActivity(in);
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
