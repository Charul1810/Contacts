package charul.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class AddContact extends AppCompatActivity {

    EditText txtid, txtfname, txtlname, txtnumber, txtemail;
    DatabaseHandler db;
    List<Contacts> myList;
     AppAdapter mAdapter;
    ListView listview;
    Button btnsave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtid = (EditText) this.findViewById(R.id.txtid);
        txtfname = (EditText) this.findViewById(R.id.txtfname);
        txtlname = (EditText) this.findViewById(R.id.txtlname);
        txtnumber = (EditText) this.findViewById(R.id.txtnumber);
        txtemail = (EditText) this.findViewById(R.id.txtemail);
         listview = (ListView) findViewById(R.id.listview);
        btnsave =(Button) findViewById(R.id.btnsave);
        db = new DatabaseHandler(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    db.addContact(new Contacts(txtfname.getText().toString(), txtlname.getText().toString(), txtnumber.getText().toString(), txtemail.getText().toString()));
                    Toast.makeText(getApplicationContext(), "Contact saved successfully!", Toast.LENGTH_LONG).show();
                    load();
                    clear();

                finish();
                Toast.makeText(getApplicationContext(), "abc", Toast.LENGTH_LONG).show();

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action",null)
                        .show();

            }
        });


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addContact(new Contacts(txtfname.getText().toString(), txtlname.getText().toString(), txtnumber.getText().toString(), txtemail.getText().toString()));
                Toast.makeText(getApplicationContext(), "Contact saved successfully!", Toast.LENGTH_LONG).show();
                load();
                clear();
            }
        });

        try {
            load();
        }
        catch (Exception e)
        {}

    }

    public void load()  {
        try {
            List<Contacts> list = db.getAllContacts();
            myList = list;
            mAdapter = new AppAdapter();
            this.listview.setAdapter(mAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        txtid.setText("");
        txtnumber.setText("");
        txtfname.setText("");
        txtlname.setText("");
        txtemail.setText("");
    }
    class AppAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return myList.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.list_item, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();

            holder.tv_id.setText(myList.get(position).get_id() + "");
            holder.tv_fname.setText(myList.get(position).get_fname());
            // holder.tv_lname.setText(myList.get(position).get_lname());
            holder.tv_num.setText(myList.get(position).get_phone());
            //holder.tv_email.setText(myList.get(position).get_email());


            holder.row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtid.setText(myList.get(position).get_id() + "");
                    txtfname.setText(myList.get(position).get_fname());
                    //txtlname.setText(myList.get(position).get_lname());
                    txtnumber.setText(myList.get(position).get_phone());
                    // txtemail.setText(myList.get(position).get_email());
                }
            });

            return convertView;
        }

        class ViewHolder {
            TextView tv_id;
            TextView tv_fname;
            TextView tv_lname;
            TextView tv_num;
            TextView tv_email;
            LinearLayout row;

            public ViewHolder(View view) {
                tv_id = (TextView) view.findViewById(R.id.txtid);
                tv_fname = (TextView) view.findViewById(R.id.txtfname);
                // tv_lname = (TextView) view.findViewById(R.id.txtlname);
                tv_num = (TextView) view.findViewById(R.id.txtnumber);
                //tv_email = (TextView) view.findViewById(R.id.txtemail);
                row = (LinearLayout) view.findViewById(R.id.row);
                view.setTag(this);
            }
        }
    }


}



