package com.example.pj2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Personal extends AppCompatActivity {

    String account, ID;
    String urll = "http://192.168.0.104:8080/project2/";
    ArrayList<Post> postArrayList;
    PostAdapter postAdapter;
    ListView lvPersonal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        account = intent.getStringExtra("acc");


        lvPersonal = (ListView) findViewById(R.id.ListViewPersonal);
        postArrayList = new ArrayList<>();
        personalPost(urll+"Post.php", account);
        postAdapter = new PostAdapter(this, R.layout.baidangcanhan, postArrayList);
        lvPersonal.setAdapter(postAdapter);


    }

    public void personalPost(String url, final String acc){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = response.length()-1; i >= 0; i--){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        if (acc.equals(jsonObject.getString("acc"))){
                            postArrayList.add(new Post(jsonObject.getString("id"), jsonObject.getString("acc"), jsonObject.getString("salary"), jsonObject.getString("address"), jsonObject.getString("place"), jsonObject.getString("content"), jsonObject.getString("date"), jsonObject.getString("time"), jsonObject.getString("name")));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                postAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void DeletePost(String url, final String IDdelete){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(Personal.this, "Bài viết đã được xóa!", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(Personal.this, "Chưa xóa bài viết thành công!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Personal.this,"Lỗi", Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> param = new HashMap<>();
                param.put("ID",IDdelete);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    }

