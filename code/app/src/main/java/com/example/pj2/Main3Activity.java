package com.example.pj2;

import android.app.Dialog;
import android.content.Intent;
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
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main3Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String ID, name, acc;
    ArrayList<Post> postArrayList;
    PostUserAdapter postUserAdapter;
    ListView lvPost;
    String urll = "http://192.168.0.104:8080/project2/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Post(urll+"Post.php");

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dulieu");
        ID = bundle.getString("ID");
        name = bundle.getString("name");
        acc = bundle.getString("acc");
        if (ID.equals("Trang chủ")) {
            lvPost = (ListView) findViewById(R.id.listviewPost1);
            postArrayList = new ArrayList<>();
            postUserAdapter = new PostUserAdapter(this, R.layout.post, postArrayList);
            lvPost.setAdapter(postUserAdapter);
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main3, menu);
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

        if (id == R.id.nav_gallery) {
            Intent intent = new Intent(Main3Activity.this,ListUser.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(Main3Activity.this, Main3Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString("ID", "Trang chủ");
            intent.putExtra("dulieu", bundle);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Post(String url){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = response.length()-1; i >= 0; i--) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        postArrayList.add(new Post(jsonObject.getString("id"), jsonObject.getString("acc"), jsonObject.getString("salary"), jsonObject.getString("address"), jsonObject.getString("place"), jsonObject.getString("content"), jsonObject.getString("date"), jsonObject.getString("time"), jsonObject.getString("name")));

                    } catch (Exception e) {

                    }
                }
                postUserAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Main3Activity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void dialogFindUser(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.find_user);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public void DeletePost(String url, final String IDdelete){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(Main3Activity.this, "Bài viết đã được xóa!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Main3Activity.this, Main3Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ID", "Trang chủ");
                    intent.putExtra("dulieu", bundle);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Main3Activity.this, "Chưa xóa bài viết thành công!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Main3Activity.this,"Lỗi", Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("ID",IDdelete);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

}
