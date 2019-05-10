package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.presenter.IPresenter;
import com.example.myapplication.presenter.RegisterPresenter;
import com.example.myapplication.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IView {

    private EditText et_userName;
    private EditText et_password;
    private Button btn_register;
    private IPresenter iPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        iPresenter = new RegisterPresenter(this);
    }

    private void initView() {
        et_userName = (EditText) findViewById(R.id.et_userName);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                String phone = et_userName.getText().toString().trim();
                String pwd = et_password.getText().toString().trim();
                if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(this,"有空值",Toast.LENGTH_LONG).show();
                }else{
                    iPresenter.startRequest(phone,pwd);
                }
                break;
        }
    }

    @Override
    public void getResponse(String data) {
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
    }

    public class getclass{
        
    }
}
