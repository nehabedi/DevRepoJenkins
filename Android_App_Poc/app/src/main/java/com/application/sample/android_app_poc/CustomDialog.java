package com.application.sample.android_app_poc;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by Dushyant.Singh on 26-04-2016.
 */
public class CustomDialog extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes;

    public CustomDialog(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.btn_yes);
        yes.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_yes:
                ((SignUp_Activity) c).showConfirmationDialog();
        }

    }
}
