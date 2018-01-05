package com.livanta.medicarequalityhelpline_geolocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyApplication app;

    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (MyApplication)getApplicationContext();

        phoneNumber = app.getPhoneNumberPrefs();
        if (phoneNumber != null) {
            // User has selected state before
            startActivity(new Intent(MainActivity.this, PhoneActivity.class));
        } else {
            setContentView(R.layout.activity_main);
            Button btn_ak = (Button) findViewById(R.id.button_ak);
            btn_ak.setOnClickListener(this);

            Button btn_az = (Button) findViewById(R.id.button_az);
            btn_az.setOnClickListener(this);

            Button btn_ca = (Button) findViewById(R.id.button_ca);
            btn_ca.setOnClickListener(this);

            Button btn_ct = (Button) findViewById(R.id.button_ct);
            btn_ct.setOnClickListener(this);

            Button btn_hi = (Button) findViewById(R.id.button_hi);
            btn_hi.setOnClickListener(this);

            Button btn_id = (Button) findViewById(R.id.button_id);
            btn_id.setOnClickListener(this);

            Button btn_ma = (Button) findViewById(R.id.button_ma);
            btn_ma.setOnClickListener(this);

            Button btn_me = (Button) findViewById(R.id.button_me);
            btn_me.setOnClickListener(this);

            Button btn_nh = (Button) findViewById(R.id.button_nh);
            btn_nh.setOnClickListener(this);

            Button btn_nj = (Button) findViewById(R.id.button_nj);
            btn_nj.setOnClickListener(this);

            Button btn_nv = (Button) findViewById(R.id.button_nv);
            btn_nv.setOnClickListener(this);

            Button btn_ny = (Button) findViewById(R.id.button_ny);
            btn_ny.setOnClickListener(this);

            Button btn_or = (Button) findViewById(R.id.button_or);
            btn_or.setOnClickListener(this);

            Button btn_pa = (Button) findViewById(R.id.button_pa);
            btn_pa.setOnClickListener(this);

            Button btn_pr = (Button) findViewById(R.id.button_pr);
            btn_pr.setOnClickListener(this);

            Button btn_ri = (Button) findViewById(R.id.button_ri);
            btn_ri.setOnClickListener(this);

            Button btn_vi = (Button) findViewById(R.id.button_vi);
            btn_vi.setOnClickListener(this);

            Button btn_vt = (Button) findViewById(R.id.button_vt);
            btn_vt.setOnClickListener(this);

            Button btn_wa = (Button) findViewById(R.id.button_wa);
            btn_wa.setOnClickListener(this);

            Button btn_none = (Button) findViewById(R.id.button_none);
            btn_none.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_ak:
            case R.id.button_az:
            case R.id.button_ca:
            case R.id.button_hi:
            case R.id.button_id:
            case R.id.button_nv:
            case R.id.button_or:
            case R.id.button_wa:
                // handle phone number change here AREA 5
                // 1-866-316-6977
                app.setPhoneNumberPrefs("18663166977");
                startActivity(new Intent(MainActivity.this, PhoneActivity.class));
                break;

            case R.id.button_ct:
            case R.id.button_ma:
            case R.id.button_me:
            case R.id.button_nh:
            case R.id.button_nj:
            case R.id.button_ny:
            case R.id.button_pa:
            case R.id.button_pr:
            case R.id.button_ri:
            case R.id.button_vi:
            case R.id.button_vt:
                // handle phone number change here AREA 1
                // 1-855-937-4774
                app.setPhoneNumberPrefs("18559374774");
                startActivity(new Intent(MainActivity.this, PhoneActivity.class));
                break;

            case R.id.button_none:
                // handle phone number change here
                startActivity(new Intent(MainActivity.this, PhoneActivity.class));
                break;
        }
    }
}
