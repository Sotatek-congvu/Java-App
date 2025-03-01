package com.example.bai2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import Model.Contact;
import Model.ContactAdapter;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_CONTACT = 1;
    private List<Contact> contactList;
    private ContactAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        Button deleteButton = findViewById(R.id.button3);
        Button addButton = findViewById(R.id.button2);

        contactList = new ArrayList<>();
        contactList.add(new Contact("Mot", "34567", false, 1));
        contactList.add(new Contact("Hai", "0987", false, 2));
        contactList.add(new Contact("Ba", "56789", false, 3));

        adapter = new ContactAdapter(this, contactList);
        listView.setAdapter(adapter);

        // Set ListView to multiple choice mode
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Them.class);
            int id = contactList.get(contactList.size() - 1).getId();
            intent.putExtra("id", id);
            startActivityForResult(intent, REQUEST_CODE_ADD_CONTACT);
        });

        deleteButton.setOnClickListener(v -> {
            List<Contact> itemsToRemove = new ArrayList<>();

            for (int i = 0; i < listView.getCount(); i++) {
                View itemView = listView.getChildAt(i);
                if (itemView != null) {
                    CheckBox checkBox = itemView.findViewById(R.id.contactCheckBox);
                    if (checkBox.isChecked()) {
                        itemsToRemove.add(contactList.get(i));
                    }
                }
            }

            if (!itemsToRemove.isEmpty()) {
                contactList.removeAll(itemsToRemove);
                adapter.notifyDataSetChanged();
                listView.clearChoices();
                Toast.makeText(this, "Đã xóa " + itemsToRemove.size() + " mục", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Vui lòng chọn ít nhất một mục để xóa", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_CONTACT && resultCode == RESULT_OK) {
            Contact newContact = (Contact) data.getSerializableExtra("newContact");
            if (newContact != null) {
                contactList.add(newContact);
                adapter.notifyDataSetChanged();
            }
        }
    }
}