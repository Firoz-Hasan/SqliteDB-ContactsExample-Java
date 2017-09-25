package com.bdjobs.training.contactbdjobsproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by FIROZ HASAN on 7/31/2017.
 */

public class ContactListAdapter extends ArrayAdapter {

    ArrayList<ContactClass> contactClassArrayList = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    static String nameCHK;


    public ContactListAdapter(Context context, ArrayList<ContactClass> contactClassArrayList) {
        super(context, R.layout.contact_list_view_item, contactClassArrayList);
        this.context = context;
        this.contactClassArrayList = contactClassArrayList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ContactViewHolder contactViewHolder = new ContactViewHolder();

        if (convertView == null) {

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.contact_list_view_item, parent, false);
            //mapping between XML & java
            contactViewHolder.nametextViewHolder = (TextView) convertView.findViewById(R.id.nameTV);
            contactViewHolder.numbertextViewHolder = (TextView) convertView.findViewById(R.id.numberTV);
            contactViewHolder.phonetypeViewHolder = (TextView) convertView.findViewById(R.id.phonetypeTV);
            contactViewHolder.emailtextViewHolder = (TextView) convertView.findViewById(R.id.emailTV);
            contactViewHolder.emailtypeViewHolder = (TextView) convertView.findViewById(R.id.emailtypeTV);
            contactViewHolder.updateContact = (ImageView) convertView.findViewById(R.id.updateUser);

            convertView.setTag(contactViewHolder);


            //---------------
        } else {
            contactViewHolder = (ContactViewHolder) convertView.getTag();

        }

        final String contactName = contactClassArrayList.get(position).getName();
        final String contactPhone = contactClassArrayList.get(position).getPhoneNumber();
        final String contactPhoneType = contactClassArrayList.get(position).getPhonetype();
        final String contactemail = contactClassArrayList.get(position).getEmail();
        final String contactEmailType = contactClassArrayList.get(position).getEmailtype();

        final int selectedContactIDForUpdate = contactClassArrayList.get(position).getId();

        final boolean flag = true ;
        final String flagstring = "OK";
        //String address = dietMenu+","+diteTime+","+dietDate;

        contactViewHolder.nametextViewHolder.setText(contactName);
        contactViewHolder.numbertextViewHolder.setText(contactPhone);
        contactViewHolder.emailtextViewHolder.setText(contactemail);
        contactViewHolder.phonetypeViewHolder.setText(contactPhoneType);
        contactViewHolder.emailtypeViewHolder.setText(contactEmailType);


        contactViewHolder.updateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, contactName, Toast.LENGTH_SHORT).show();
                //finalContactViewHolder.nametextViewHolder.setText(null);
                //nameCHK = contactName;

                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("name", contactName);
                intent.putExtra("number", contactPhone);
                intent.putExtra("email", contactemail);
                intent.putExtra("OK", flagstring);
                // intent.putExtra("updateid",selectedContactIDForUpdate );

                context.startActivity(intent);


            }
        });


        return convertView;
    }

    public class ContactViewHolder {

        TextView nametextViewHolder, numbertextViewHolder, emailtextViewHolder, phonetypeViewHolder, emailtypeViewHolder;
        ImageView updateContact;
    }
}
