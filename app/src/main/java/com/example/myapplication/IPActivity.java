package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.ApiClient;

public class IPActivity extends AppCompatActivity {

    private EditText etIpAddress;
    private Button btnSaveIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);  // 使用对应的布局文件

        etIpAddress = findViewById(R.id.et_ip);
        btnSaveIp = findViewById(R.id.button_ip);

        // 从 SharedPreferences 加载上次保存的 IP 地址
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        String savedIp = sharedPreferences.getString("base_url", "http://192.168.31.140:8001/"); // 默认值
        etIpAddress.setText(savedIp);

        btnSaveIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newIp = etIpAddress.getText().toString().trim();

                if (!newIp.endsWith("/")) {
                    newIp += "/"; // 确保 URL 以斜杠结尾
                }

                if (isValidUrl(newIp)) {
                    // 保存新 IP 地址到 SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("base_url", newIp);
                    editor.apply();

                    // 更新 ApiClient 中的 BASE_URL
                    ApiClient.updateBaseUrl(newIp);

                    Toast.makeText(IPActivity.this, "IP 地址已更新为: " + newIp, Toast.LENGTH_SHORT).show();
                    finish(); // 关闭当前页面
                } else {
                    Toast.makeText(IPActivity.this, "请输入有效的 IP 地址", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // 验证 URL 的格式
    private boolean isValidUrl(String url) {
        return url.matches("^(http://|https://)[a-zA-Z0-9.-]+(:[0-9]+)?/.*$");
    }
}
