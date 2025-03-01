package Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bai2.R;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private List<Contact> contacts;

    public ContactAdapter(Context context, List<Contact> contacts) {
        super(context, 0, contacts);
        this.context = context;
        this.contacts = contacts;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        }

        Contact contact = getItem(position);
        CheckBox checkBox = convertView.findViewById(R.id.contactCheckBox);
        TextView idTextView = convertView.findViewById(R.id.contactId);
        TextView nameTextView = convertView.findViewById(R.id.contactName);
        TextView phoneTextView = convertView.findViewById(R.id.contactPhone);
        TextView favoriteTextView = convertView.findViewById(R.id.contactFavorite);

        if (contact != null) {
            idTextView.setText("ID: " + contact.getId());
            nameTextView.setText(contact.getName());
            phoneTextView.setText(contact.getPhone());
            favoriteTextView.setText(contact.isFavorite() ? "Yêu thích" : "");
            checkBox.setChecked(contact.isFavorite());

            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                contact.setFavorite(isChecked);
            });

            // Set the checkbox state based on the ListView's checked state
            checkBox.setChecked(((ListView) parent).isItemChecked(position));
        }

        return convertView;
    }


}