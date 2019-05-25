package com.example.pj2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Post1 extends AppCompatActivity {

    final ArrayList<String> diadiem = new ArrayList<String>();
    Button buttonPost, buttonCancel;
    EditText editTextnoidung, editTextluong, editTextdiadiem;
    Spinner spinner;
    String urll = "http://192.168.0.104:8080/project2/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post1);


        buttonPost =findViewById(R.id.buttonPost);
        buttonCancel = findViewById(R.id.buttonCancel);
        editTextnoidung = findViewById(R.id.editText2);
        editTextluong = findViewById(R.id.editText6);
        editTextdiadiem = findViewById(R.id.editText7);
        spinner = findViewById(R.id.spinner);
        diadiem.add("Hai Bà Trưng");
        diadiem.add("Cầu Giấy");
        diadiem.add("Sơn Tây");
        diadiem.add("Bạch Mai");
        diadiem.add("Hoàn Kiếm");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, diadiem);
        spinner.setAdapter(arrayAdapter);

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (editTextdiadiem.getText().toString().trim().equals("") || editTextluong.getText().toString().trim().equals("") || editTextnoidung.getText().toString().trim().equals(""))
                    {
                        Toast.makeText(Post1.this,
                               "Bạn chưa điền đầy đủ thông tin của bài đăng!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        addPost(urll+"post1.php");
                        Intent intent = getIntent();
                        Bundle bundle = intent.getBundleExtra("dulieu");
                        String name = bundle.getString("name");
                        String acc = bundle.getString("acc");
                        String ID = "Trang chủ";
                        Intent intent1 = new Intent(Post1.this, Main2Activity.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("name", name);
                        bundle1.putString("acc", acc);
                        bundle1.putString("ID", ID);
                        intent1.putExtra("dulieu", bundle);
                        startActivity(intent1);
                    }
                }
        });
    }

    private void addPost(String url){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.trim().equals("success")){
                    Toast.makeText(Post1.this, "Đăng bài thành công", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Post1.this, "Chưa đăng bài thành công", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Post1.this, "Xảy ra lỗi", Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Intent intent = getIntent();
                Bundle bundle = intent.getBundleExtra("dulieu");
                String name = bundle.getString("name");
                String acc = bundle.getString("acc");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat format1 = new SimpleDateFormat("hh:mm:ss");
                Date date = new Date();
                final String dateformat = format.format(date);
                final String dateformat1 = format1.format(date);
                Map<String, String> param = new HashMap<>();
                param.put("content", editTextnoidung.getText().toString().trim());
                param.put("salary", editTextluong.getText().toString().trim());
                param.put("place",spinner.getSelectedItem().toString().trim());
                param.put("address", editTextdiadiem.getText().toString().trim());
                param.put("date", dateformat);
                param.put("time",dateformat1);
                param.put("acc", acc);
                param.put("name", name);

                return param;

            }
        };
        requestQueue.add(stringRequest);
    }
}
