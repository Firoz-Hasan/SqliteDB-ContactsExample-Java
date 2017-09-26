package com.bdjobs.training.contactbdjobsproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // working perfectly all save update and delete functionality

    EditText ctName, ctNumber, ctEmail;
    Spinner numberSpn, emailSpn;
    Button saveBT, updateBT;
    LinearLayout contactLL, newContactLL;
    String name, number, email, number_type, email_type;
    int id;
    public static final String TAG = "Mainactivity";
    private boolean emailRecognizer;
    private int id_no;
    //static final int REQUEST_CODE = 0;
    int upadeinteger;
    DataStorage dataStorage;

    ArrayList<ContactClass> contactArraylistModelsMain = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializer();
        dataStorage = new DataStorage(MainActivity.this);
        updateBT = (Button) findViewById(R.id.updateBTU);
        //---------------------------------------
        Intent intent = getIntent();

        final String getNameFmBack = intent.getStringExtra("name");
        String getNumberBack = intent.getStringExtra("number");
        String getEmailBack = intent.getStringExtra("email");
        final String getflagstring = intent.getStringExtra("OK");
        Toast.makeText(MainActivity.this, "flag " + String.valueOf(getflagstring), Toast.LENGTH_SHORT).show();
        //final boolean getflag = intent.getBooleanExtra("flag");
        //---------------------------------------
//         final String getIDBack = intent.getStringExtra("updateid");
//        upadeinteger = Integer.parseInt(getIDBack);
        //-----------------------------------------
        ctName.setText(getNameFmBack);
        ctNumber.setText(getNumberBack);
        ctEmail.setText(getEmailBack);
        //--------------------------------------
//        contactArraylistModelsMain = dataStorage.getAllContacts();
//        final int selectedContactID = contactArraylistModelsMain.get(upadeinteger).getId();


        newContactLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
            }
        });

        contactLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(intent);


            }
        });
        updateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = ctName.getText().toString();
                number = ctNumber.getText().toString();
                email = ctEmail.getText().toString().trim();
                number_type = numberSpn.getSelectedItem().toString();
                email_type = emailSpn.getSelectedItem().toString();

                // upadeinteger = Integer.parseInt(getIDBack);
                //-------------------------
                contactArraylistModelsMain = dataStorage.getAllContacts();
                final int selectedContactID = contactArraylistModelsMain.get(upadeinteger).getId();
                //---------------
                ContactClass contactModel = new ContactClass(name, number, email, number_type, email_type, upadeinteger);
                //Log.d(TAG, "id: " + id + "\nname: " + name + "\nnumber: " + number + "\nemail: " + email + "\nnumber_type: " + number_type + "\nemail_type: " + email_type);
                boolean result = dataStorage.updateContact(selectedContactID, contactModel);
                //Toast.makeText(MainActivity.this, String.valueOf(result), Toast.LENGTH_SHORT).show();
                if (result != true) {
                    Toast.makeText(getApplicationContext(), "cant updated", Toast.LENGTH_SHORT).show();
                }


            }
        });
        saveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = ctName.getText().toString();
                number = ctNumber.getText().toString();
                email = ctEmail.getText().toString().trim();
                number_type = numberSpn.getSelectedItem().toString();
                email_type = emailSpn.getSelectedItem().toString();
                emailRecognizer = email_Validate(email);
                boolean updateContact = false;

                try {
                    if (name.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Name field can't be empty", Toast.LENGTH_SHORT).show();
                    } else if (number.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Number field can't be empty", Toast.LENGTH_SHORT).show();
                    } else if (email.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Email can't be empty", Toast.LENGTH_SHORT).show();
                    } else if (emailRecognizer == false) {

                        Toast.makeText(MainActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    } else if (getflagstring == null) {

//                    showContactListMain();
                        insertingData();
                        updateContact = true;
                        Toast.makeText(MainActivity.this, String.valueOf(updateContact), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "insert called", Toast.LENGTH_SHORT).show();
                    } else if (!name.isEmpty() && !number.isEmpty() && !email.isEmpty() && getflagstring.equals("OK")) {
                        Toast.makeText(MainActivity.this, "update ", Toast.LENGTH_SHORT).show();
                        update();

                    }

                } catch (Exception e) {

                }


            }
        });

    }

    private void update() {
        contactArraylistModelsMain = dataStorage.getAllContacts();
        final int selectedContactID = contactArraylistModelsMain.get(upadeinteger).getId();

        ContactClass contactModel = new ContactClass(name, number, email, number_type, email_type, upadeinteger);
        boolean result = dataStorage.updateContact(selectedContactID, contactModel);
        //Toast.makeText(MainActivity.this, String.valueOf(result), Toast.LENGTH_SHORT).show();
        if (result != true) {
            Toast.makeText(getApplicationContext(), "cant updated", Toast.LENGTH_SHORT).show();
        }
    }


    void initializer() {

        ctName = (EditText) findViewById(R.id.nameET);
        ctEmail = (EditText) findViewById(R.id.emailET);
        // ctEmail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        ctNumber = (EditText) findViewById(R.id.numberET);
        numberSpn = (Spinner) findViewById(R.id.numberSPN);
        emailSpn = (Spinner) findViewById(R.id.emailSPN);
        saveBT = (Button) findViewById(R.id.saveBT);
        contactLL = (LinearLayout) findViewById(R.id.contactlistLL);
        newContactLL = (LinearLayout) findViewById(R.id.newContactLL);


    }

    private boolean email_Validate(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void insertingData() {
//        id_no++;
//        id = id_no;
        ContactClass contactModel = new ContactClass(name, number, email, number_type, email_type);
        Log.d(TAG, "id: " + id + "\nname: " + name + "\nnumber: " + number + "\nemail: " + email + "\nnumber_type: " + number_type + "\nemail_type: " + email_type);
        boolean result = dataStorage.insertContact(contactModel);
        //Toast.makeText(MainActivity.this, String.valueOf(result), Toast.LENGTH_SHORT).show();
        if (result != true) {
            Toast.makeText(this, "Duplicate Entry, Your name or phone number or email has been used before", Toast.LENGTH_SHORT).show();
        }
    }

    public void getDataFmUser() {
        name = ctName.getText().toString();
        number = ctNumber.getText().toString();
        email = ctEmail.getText().toString().trim();
        number_type = numberSpn.getSelectedItem().toString();
        email_type = emailSpn.getSelectedItem().toString();
        emailRecognizer = email_Validate(email);
    }


    private void showContactListMain() {

        contactArraylistModelsMain = dataStorage.getAllContacts();
        final int selectedContactID = contactArraylistModelsMain.get(upadeinteger).getId();


    }


}
