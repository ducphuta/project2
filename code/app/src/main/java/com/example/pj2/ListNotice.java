package com.example.pj2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ListNotice extends AppCompatActivity {

    String account, name, ID, acc1, name1;
    CommentAdapter commentAdapter;
    ArrayList<Comment> commentArrayList;
    ListView lvNotice;
    TextView tv18;
    Button buttonComment;
    EditText editTextcomment;
    String urll = "http://192.168.0.104:8080/project2/";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice);
        tv18 = findViewById(R.id.textView18);
        buttonComment = findViewById(R.id.buttonComment);
        editTextcomment = findViewById(R.id.editText4);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        account = bundle.getString("acc");
        name = bundle.getString("name");
        ID = bundle.getString("id");
        acc1 = bundle.getString("acc1");
        name1 = bundle.getString("name1");
        tv18.setText("Bài viết của "+ name);
        commentArrayList = new ArrayList<>();
        Comment(urll+ "comment.php", account, ID);
        lvNotice = findViewById(R.id.lvnotice);
        commentAdapter = new CommentAdapter(this, R.layout.notice1, commentArrayList);
        lvNotice.setAdapter(commentAdapter);
        buttonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddComment(urll+"comment1.php");
            }
        });

    }

    public void Comment(String url, final String acc, final String ID){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        if (acc.equals(jsonObject.getString("accpost")) && ID.equals(jsonObject.getString("IDpost")))
                        {
                            commentArrayList.add(new Comment(jsonObject.getString("ID"), jsonObject.getString("acc"), jsonObject.getString("accpost"), jsonObject.getString("content"), jsonObject.getString("date"), jsonObject.getString("time"), jsonObject.getString("name"), jsonObject.getString("IDpost")));
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                commentAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void AddComment(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(ListNotice.this, "Bình luận thành công", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(ListNotice.this, ListNotice.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("acc", account);
                    bundle.putString("id", ID);
                    bundle.putString("name", name);
                    bundle.putString("acc1", acc1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ListNotice.this, "Xảy ra lỗi", Toast.LENGTH_LONG).show();
            }
        }){

            protected Map<String, String> getParams(){
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat format1 = new SimpleDateFormat("hh:mm:ss");
                Date date = new Date();
                final String dateformat = format.format(date);
                final String dateformat1 = format1.format(date);
                Map<String, String> param = new HashMap<>();
                param.put("acc", acc1);
                param.put("accpost", account);
                param.put("content",editTextcomment.getText().toString().trim());
                param.put("date", dateformat);
                param.put("time", dateformat1);
                param.put("name", name1);
                param.put("IDpost", ID);
                return param;

            }
        };
        requestQueue.add(stringRequest);
    }
}
