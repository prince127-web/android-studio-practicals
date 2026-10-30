package com.example.contentproviderdemo;
import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
public class MainActivity extends AppCompatActivity {
    TextView txtContacts;
    ActivityResultLauncher<String> permissionLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtContacts = findViewById(R.id.txtContacts);
        permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        loadContacts();
                    } else {
                        txtContacts.setText(
                                "Permission denied. Cannot access contacts."
                        );
                    }
                }
        );
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            loadContacts();
        } else {
            permissionLauncher.launch(Manifest.permission.READ_CONTACTS);
        }
    }
private void loadContacts() {
        StringBuilder result = new StringBuilder();
        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                },
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        );
        if (cursor != null) {
            try {
                int nameIndex = cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int numberIndex = cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER);
                while (cursor.moveToNext()) {
                    String name = cursor.getString(nameIndex);
                    String number = cursor.getString(numberIndex);
                    result.append("Name: ")
                            .append(name)
                            .append("\nPhone: ")
                            .append(number)
                            .append("\n\n");
                }
            } finally {
                cursor.close();
            }
        }
        if (result.length() == 0) {
            txtContacts.setText("No contacts found.");
        } else {
            txtContacts.setText(result.toString());
        }
    }
}
