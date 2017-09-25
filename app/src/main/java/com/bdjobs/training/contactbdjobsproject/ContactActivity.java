package com.bdjobs.training.contactbdjobsproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    ListView contactLV;
    SimpleCursorAdapter simpleCursorAdapter;
    DBHelper dbHelper;
    public static final String TAG = "ContactActivity";
    LinearLayout updateUser;
    ArrayList<ContactClass> contactArraylistModels = new ArrayList<>();
    DataStorage dataStorage;
    ImageView update;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        dataStorage = new DataStorage(getApplicationContext());
        contactLV = (ListView) findViewById(R.id.contactList);


        showContactList();

    }

    @Override
    protected void onStart() {
        super.onStart();
        showContactList();
    }

    public void showContactList() {

        contactArraylistModels = dataStorage.getAllContacts();
        ContactListAdapter contactListAdapter = new ContactListAdapter(ContactActivity.this, contactArraylistModels);
        contactLV.setAdapter(contactListAdapter);
        contactListAdapter.notifyDataSetChanged();

        contactLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ContactActivity.this, "done", Toast.LENGTH_SHORT).show();

                // final String selectedContactName = contactArraylistModels.get(position).getName();
                final int selectedContactID = contactArraylistModels.get(position).getId();
//                Toast.makeText(ContactActivity.this, selectedContactID, Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (!isFinishing()) {
                            new AlertDialog.Builder(ContactActivity.this)
                                    .setTitle("Delete Contact")
                                    .setIcon(R.drawable.ic_warning)
                                    .setMessage("Do You Want To Delete Selected Contact?")
                                    .setCancelable(true)
                                    .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dataStorage.deleteRow(selectedContactID);
                                            showContactList();

                                        }
                                    })
                                    .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Whatever...
                                        }

                                    }).show();

                        }
                    }
                });
                //   ------------------------------------------------------------------------------------------

            }
        });

    }


}
