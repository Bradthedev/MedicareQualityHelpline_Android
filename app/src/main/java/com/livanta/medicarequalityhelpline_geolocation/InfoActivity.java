package com.livanta.medicarequalityhelpline_geolocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView t2 = (TextView)findViewById(R.id.thirdpage_middle);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        Button btn_close = (Button)findViewById(R.id.close_button);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoActivity.this, PhoneActivity.class));
            }
        });
    }
}
