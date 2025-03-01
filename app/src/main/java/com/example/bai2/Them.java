package com.example.bai2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Model.Contact;

public class Them extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText nameEditText;
    private EditText phoneEditText;
    private Button addButton;
    private ImageView imageView;
    private Uri imageUri;
    private Button selectImageButton;

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
        selectImageButton = findViewById(R.id.selectImageButton);
        imageView = findViewById(R.id.imageView);

        selectImageButton.setOnClickListener(v -> openImageSelector());
        addButton.setOnClickListener(v -> addItem());
    }

    private void openImageSelector() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
        //hien thi hinh anh len imageView


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
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
        if (imageUri != null) {
            newContact.setImage(imageUri.toString());
        }

        // Return the new contact to MainActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("newContact", newContact);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}