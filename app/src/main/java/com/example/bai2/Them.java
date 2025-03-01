package com.example.bai2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Model.Contact;

public class Them extends AppCompatActivity {
    private EditText nameEditText;
    private EditText phoneEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.adduser);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.adduser), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameEditText = findViewById(R.id.editTextText);
        phoneEditText = findViewById(R.id.editTextText2);
        addButton = findViewById(R.id.button);

        addButton.setOnClickListener(v -> addItem());
    }

    private void addItem() {
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên và số điện thoại", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = getIntent();
        int lastId = intent.getIntExtra("id", 0);
        Contact newContact = new Contact(name, phone, false, lastId + 1);

        // Return the new contact to MainActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("newContact", newContact);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}