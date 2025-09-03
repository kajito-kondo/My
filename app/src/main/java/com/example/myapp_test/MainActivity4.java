package com.example.myapp_test;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp_test.databinding.ActivityMain4Binding;
import com.example.myapp_test.databinding.ActivityMainBinding;

public class MainActivity4 extends AppCompatActivity {
    private ActivityMain4Binding binding4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding4 = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding4.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        var editText = getIntent().getStringExtra("name");
        binding4.textView4.setText(editText);

        //OK
        binding4.button23.setOnClickListener(v -> {
            var intent = getIntent();
            intent.putExtra("ret", "OK");
            setResult(RESULT_OK, intent);
            finish();
        });
        //CANCEL
        binding4.button24.setOnClickListener(v -> {
            var intent = getIntent();
            intent.putExtra("ret", "CANCEL");
            setResult(RESULT_CANCELED, intent);
            finish();
        });
    }
    }
