package com.example.notificationbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view) {
        Intent intent=new Intent(this,MessageService.class);
        intent.putExtra(MessageService.EXTRA_MESSAGE,getString(R.string.button_resposne));
        startService(intent);
    }
}
