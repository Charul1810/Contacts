package charul.contacts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText txtid,txtfname,txtlname,txtnumber,txtemail;
    DatabaseHandler db;
    ListView listview;
    List<Contacts> myList;
   AppAdapter mAdapter;


    /*  EditText txt1,txt2,txt3,txt4;
       String fname;
       String lname;
       String phone;
       String email;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtfname = (EditText) findViewById(R.id.txtfname);
        txtlname = (EditText) findViewById(R.id.txtlname);
        txtnumber = (EditText) findViewById(R.id.txtnumber);
        txtlname = (EditText) findViewById(R.id.txtemail);
        listview=(ListView) findViewById(R.id.listview);
        db = new DatabaseHandler(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),AddContact.class));



                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


            load();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
   public void load() {

            List<Contacts> list = db.getAllContacts();
            myList = list;

       Toast.makeText(getApplicationContext(),myList.size()+"",Toast.LENGTH_LONG).show();
            mAdapter = new AppAdapter();
            this.listview.setAdapter(mAdapter);

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
                tv_fname = (TextView) view.findViewById(R.id.txtname);
               // tv_lname = (TextView) view.findViewById(R.id.txtlname);
                tv_num = (TextView) view.findViewById(R.id.txtnumber);
                //tv_email = (TextView) view.findViewById(R.id.txtemail);
                row = (LinearLayout) view.findViewById(R.id.row);
                view.setTag(this);
            }
        }
    }
}
