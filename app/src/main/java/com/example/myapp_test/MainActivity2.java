package com.example.myapp_test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp_test.databinding.ActivityMain2Binding;
import com.example.myapp_test.databinding.ActivityMainBinding;

import java.util.Optional;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding2;



        private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    switch (result.getResultCode()) {
                        case RESULT_OK -> {
                            Optional.ofNullable(result.getData())
                                    .map(data -> data.getStringExtra("ret"))
                                    .map(text -> "Result: " + text)
                                    .ifPresent(text -> binding2.textView5.setText(text));
                        }
                        case RESULT_CANCELED -> {
                            binding2.textView5.setText("Result: Canceled");
                        }
                        default -> {
                            binding2.textView5.setText("Result: Unknown(" + result.getResultCode() + ")");
                        }
                    }
                }
        );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding2 = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding2.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding2.button3.setOnClickListener(v -> {
            var intent = new Intent(this, MainActivity3.class);
            startActivity(intent);
        });
        binding2.button4.setOnClickListener(v -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.google.com"));
            startActivity(intent);
        });
        binding2.button21.setOnClickListener(v -> {
            var tent = binding2.editTextText2.getText().toString();
            var intent = new Intent(this, MainActivity4.class);
            intent.putExtra("name", tent);
            startActivity(intent);;
        });

        binding2.button22.setOnClickListener(v -> {
            var intent = new Intent(this, MainActivity4.class);
            getActivityResult.launch(intent);
        });

    }
}