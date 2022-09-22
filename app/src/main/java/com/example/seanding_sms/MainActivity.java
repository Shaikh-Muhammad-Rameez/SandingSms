package com.example.seanding_sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {



    private static final int PERMISSION = 0;
    Button btn;
    EditText phoneNum;
    EditText message;
    String phone;
    String msg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn= (Button)findViewById(R.id.btnSendMsg);
        phoneNum= (EditText)findViewById(R.id.txtPhoneNumber);
        message = (EditText)findViewById(R.id.txtMessageBox);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS();
            }
        });

    }



    protected  void  sendSMS()
    {

        phone = phoneNum.getText().toString();
        msg = message.getText().toString();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
            PERMISSION);

        }
        else
        {
            SmsManager smsMgr = SmsManager.getDefault();
            smsMgr.sendTextMessage(phone,null,msg,null,null);
            Toast.makeText(getApplicationContext(),"Message Successfully Sent",Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[]grantResults)
    {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        switch (requestCode)
        {
            case PERMISSION:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "SMS Failed you may try again later",
                            Toast.LENGTH_LONG
                            ).show();
                    return;
                }
            }
        }
    }




}