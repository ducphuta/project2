package com.example.pj2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ListUser extends AppCompatActivity {

    ListView listViewUser;
    ArrayList<User> userArrayList;
    UserAdapter userAdapter;
    String urll = "http://192.168.0.104:8080/project2/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        userArrayList = new ArrayList<>();
        user(urll+"User.php");
        listViewUser = (ListView) findViewById(R.id.ListViewUser);
        userAdapter = new UserAdapter(this, R.layout.list_user, userArrayList);
        listViewUser.setAdapter(userAdapter);
    }

    private void user(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++)
                {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        userArrayList.add(new User(object.getString("Acc"), object.getString("Pass"), object.getString("Name")));


                    } catch (JSONException e) {

                    }
                }
                userAdapter.notifyDataSetChanged();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListUser.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public void DeleteUser(String url, final String account){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(ListUser.this, "Tài khoản đã được xóa thành công!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ListUser.this, Main3Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ID", "Trang chủ");
                    intent.putExtra("dulieu", bundle);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ListUser.this, "Chưa xóa tài khoản thành công!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ListUser.this,"Lỗi", Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("account",account);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}
