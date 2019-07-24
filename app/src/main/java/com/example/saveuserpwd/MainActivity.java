package com.example.saveuserpwd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUserName;
    private EditText edtUserPwd;
    private Button btnLogin;
    SharedPreferences sp;
    private String userNmae;
    private String userPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUserName = findViewById(R.id.edt_user_name);
        edtUserPwd = findViewById(R.id.edt_user_pwd);
        btnLogin = findViewById(R.id.btn_login);
        //todo 用户取值 import
        SharedPreferences sp = getSharedPreferences("loginInfo", 0);
        // 通过key 值取出value
        String mobile = sp.getString("username", "");
        String pass = sp.getString("password", "");
        // 显示
        edtUserName.setText(mobile);
        edtUserPwd.setText(pass);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                SharedPreferences sp = getSharedPreferences("loginInfo", 0);
                SharedPreferences.Editor editor = sp.edit();
                userNmae = edtUserName.getText().toString();
                userPwd = edtUserPwd.getText().toString();

                if (userNmae.equals("Wei") && userPwd.equals("123")) {
                    // 获取sp对象
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    editor.putString("username", userNmae);
                    editor.putString("password", userPwd);
                    editor.commit();
                    Intent intent2 = new Intent();
                    intent2.setClass(getApplicationContext(), WelcomeActivity.class);
                    startActivity(intent2);
                    return;
                }
                // 清空
                editor.clear();
                editor.commit();
                Toast.makeText(MainActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
