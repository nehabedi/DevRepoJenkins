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
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.IOException;
import java.util.Map;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_login,btn_signup;
    private EditText et_username,et_password;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        initialiseview();
        //authenticateuser();

    }

    public void authenticateuser()
    {
        Firebase myFirebaseRef = new Firebase("https://dazzling-fire-1750.firebaseio.com/");
        myFirebaseRef.createUser("dushyant@firebase.com", "123", new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });
    }

    public void setupfirebase()
    {
        Firebase myFirebaseRef = new Firebase("https://dazzling-fire-1750.firebaseio.com/");
        myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");
    }

    public void initialiseview()
    {
        btn_login=(Button)findViewById(R.id.login);
        btn_signup=(Button)findViewById(R.id.signup);
        et_password=(EditText)findViewById(R.id.et_password);
        et_username=(EditText)findViewById(R.id.et_username);

        btn_signup.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        pd=new ProgressDialog(this);
        pd.setMessage("Please wait...");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId())
         {
             case R.id.login:
                 if(TextUtils.isEmpty(et_username.getText().toString()))
                 {
                     /*Snackbar snackbar = Snackbar
                             .make(v, "User name empty", Snackbar.LENGTH_LONG);
                     snackbar.show();*/
                     Toast.makeText(this,"user name empty",Toast.LENGTH_LONG).show();
                 }
                 else if(TextUtils.isEmpty(et_password.getText().toString()))
                 {
                     Toast.makeText(this,"password empty",Toast.LENGTH_LONG).show();
                 }
                 else if (!et_username.getText().toString().trim().equalsIgnoreCase("user") || (!et_password.getText().toString().trim().equalsIgnoreCase("123")))
                 {
                     showConfirmationDialog();
                 }
                 else {
                    pd.show();
                     Handler handler = new Handler();
                     handler.postDelayed(new Runnable() {
                         @Override
                         public void run() {
                             et_username.setText("");
                             et_password.setText("");
                             pd.dismiss();
                             Intent in = new Intent(Login_Activity.this, Dashboard.class);
                             startActivity(in);


                         }
                     }, 5000);

                     /*Firebase ref = new Firebase("https://dazzling-fire-1750.firebaseio.com");
                     ref.authWithPassword("dushyant@firebase.com", "123", new Firebase.AuthResultHandler() {
                         @Override
                         public void onAuthenticated(AuthData authData) {
                             setdata();

                             Log.v("firebase data--------",authData.getProvider());
                             System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                         }
                         @Override
                         public void onAuthenticationError(FirebaseError firebaseError) {

                             Log.v("firebase data error",firebaseError.toString());
                             // there was an error
                         }
                     });*/
                 }

                 break;
             case R.id.signup:
                 Intent in = new Intent(Login_Activity.this, SignUp_Activity.class);
                 startActivity(in);
                 break;

            }

    }

    public void setdata()
    {

        Firebase myFirebaseRef = new Firebase("https://dazzling-fire-1750.firebaseio.com");
        myFirebaseRef.child("username").setValue("Dushyant");
        myFirebaseRef.child("age").setValue("25");
        myFirebaseRef.child("location").setValue("Noida");


    }

    public void showConfirmationDialog() {

        /*Intent in = new Intent(SignUp_Activity.this, Login_Activity.class);
        startActivity(in);*/
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login_Activity.this);
        alertDialogBuilder.setTitle("Login");
        alertDialogBuilder.setMessage("Invalid Credentials");

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int arg1) {
                dialogInterface.dismiss();
             }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
