package com.example.pj2;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    String c,d,f;
    int a,b,e;
    Button buttonDN, buttonDK;
    EditText editTextTK, editTextMK, editTextDKName, editTextDKTK, editTextDKMK;
    TextView txtThongBao;
    ArrayList<User> userArrayList;
    ArrayList<Comment> commentArrayList;
    Button buttonExit, buttonSign;
    ArrayList<Manager> managerArrayList;
    String urll = "http://192.168.0.104:8080/project2/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        userArrayList = new ArrayList<>();
        managerArrayList = new ArrayList<>();
        commentArrayList = new ArrayList<>();
        Comment(urll+"comment.php");
        user(urll+"User.php");
        manager(urll+"Manager.php");
        buttonDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTK.getText().toString().equals("") || editTextMK.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this,"Bạn chưa điền tên tài khoản hoặc mật khẩu!",Toast.LENGTH_LONG).show();

                }
                else {
                    if (CheckAcc() == 1)
                    {

                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        Bundle bundle = new Bundle();
                        Name();
                        Account();
                        bundle.putString("name", c);
                        bundle.putString("acc", d);
                        bundle.putString("pass", f);
                        bundle.putString("ID","Trang chủ");
                        intent.putExtra("dulieu", bundle);
                        startActivity(intent);
                    }
                    else if (CheckAccManager() == 1)
                    {
                        Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                        Bundle bundle = new Bundle();
                        Name();
                        Account();
                        bundle.putString("name", c);
                        bundle.putString("acc", d);
                        bundle.putString("ID","Trang chủ");
                        intent.putExtra("dulieu", bundle);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Sai tên tài khoản hoặc mật khẩu!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        buttonDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DialogSign();
            }
        });

    }
    private void DialogSign()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.sign);
        dialog.setCanceledOnTouchOutside(true);

        buttonExit = (Button) dialog.findViewById(R.id.buttonExit);
        buttonSign = (Button) dialog.findViewById(R.id.buttonSign);
        editTextDKTK = (EditText) dialog.findViewById(R.id.editText);
        editTextDKMK = (EditText) dialog.findViewById(R.id.editText3);
        editTextDKName = (EditText) dialog.findViewById(R.id.editTextName);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextDKTK.getText().toString().trim().equals("") || editTextDKMK.getText().toString().trim().equals("") || editTextDKName.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, "Bạn chưa điền đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                } else {
                    if (editTextDKTK.getText().toString().length() != 10) {
                        Toast.makeText(MainActivity.this, "Bạn điền sai số điện thoại!", Toast.LENGTH_LONG).show();
                    } else {
                        for (User arr : userArrayList) {
                            if (editTextDKTK.getText().toString().equals(arr.account)) {
                                b = 1;
                                break;
                            } else {
                                b = 0;
                            }
                        }
                        if (b == 1) {
                            Toast.makeText(MainActivity.this, "Tài khoản đá tồn tại", Toast.LENGTH_LONG).show();
                        } else {
                            AddAccount(urll+"account.php");
                            dialog.dismiss();
                        }
                    }
                }
            }
        });
        dialog.show();
    }


    private void AddAccount(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.trim().equals("success")){
                    Toast.makeText(MainActivity.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Đăng ký không thành công", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Xảy ra lỗi", Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("account", editTextDKTK.getText().toString().trim());
                param.put("password", editTextDKMK.getText().toString().trim());
                param.put("name", editTextDKName.getText().toString().trim());

                return param;

            }
        };
        requestQueue.add(stringRequest);
    }
    public void anhxa()
    {
        buttonDN = (Button) findViewById(R.id.buttonĐN);
        buttonDK = (Button) findViewById(R.id.buttonDK);
        editTextTK = (EditText) findViewById(R.id.editTextTK);
        editTextMK = (EditText) findViewById(R.id.editTextMK);
        txtThongBao = (TextView) findViewById(R.id.textViewTB);
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

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void manager(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        managerArrayList.add(new Manager(jsonObject.getString("Acc"), jsonObject.getString("Pass"), jsonObject.getString("Name")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public void Comment(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        commentArrayList.add(new Comment(jsonObject.getString("ID"), jsonObject.getString("acc"), jsonObject.getString("accpost"), jsonObject.getString("content"), jsonObject.getString("date"), jsonObject.getString("time"), jsonObject.getString("name"), jsonObject.getString("IDpost")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public int CheckAcc()
    {
        for (User arr : userArrayList) {
            if (editTextTK.getText().toString().equals(arr.account) && editTextMK.getText().toString().equals(arr.pass))
            {
                a = 1;
                break;
            }
            else {
                a = 0;
            }
        }
        return a;
    }

    public int CheckAccManager(){
        for(Manager manager : managerArrayList)
        {
            if (editTextTK.getText().toString().equals(manager.username) && editTextMK.getText().toString().equals(manager.password)){
                e = 1;
                break;
            }
            else {
                e = 0;
            }
        }
        return e;
    }
    public String Account(){
        for ( User u: userArrayList
        ) {
            if (editTextTK.getText().toString().equals(u.account)) {
                d = u.account;
                break;
            } else {
                d = "Sai rồi!";
            }
        }
        return d;

    }


    public String Name(){
        for ( User u: userArrayList
             ) {
            if (editTextTK.getText().toString().equals(u.account)) {
                c = u.name;
                f = u.pass;
                break;
            } else {
                c = "Sai rồi!";
            }
        }
            return c;

    }




}
