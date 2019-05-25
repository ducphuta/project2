package com.example.pj2;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

public class UpdatePost extends AppCompatActivity {

    String id, name, noidung, luong, diadiem1, diadiem2, accpost, acc;
    TextView txthoten;
    EditText editTextnoidung, editTextluong, editTextdiadiem;
    String urll = "http://192.168.0.104:8080/project2/";
    Button buttonupdate;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_post);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dulieu");
        id = bundle.getString("id");
        name = bundle.getString("name");
        noidung = bundle.getString("noidung");
        accpost = bundle.getString("accpost");
        acc = bundle.getString("acc");
        luong = bundle.getString("luong");
        diadiem1 = bundle.getString("diadiem1");
        diadiem2 = bundle.getString("diadiem");
        AnhXa();
        txthoten.setText(name);
        editTextnoidung.setText(noidung);
        editTextluong.setText(luong);
        editTextdiadiem.setText(diadiem1);
        ArrayList<String> diadiem = new ArrayList<String>();
        diadiem.add(diadiem2);
        diadiem.add("Hai Bà Trưng");
        diadiem.add("Cầu Giấy");
        diadiem.add("Sơn Tây");
        diadiem.add("Bạch Mai");
        diadiem.add("Hoàn Kiếm");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, diadiem);
        spinner.setAdapter(arrayAdapter);
        Toast.makeText(UpdatePost.this, id, Toast.LENGTH_LONG).show();
        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdatePost("http://192.168.0.104:8080/project2/updatepost.php");
            }
        });
    }

    public void AnhXa(){
        editTextnoidung = findViewById(R.id.textViewnoidung);
        editTextdiadiem = findViewById(R.id.textViewdiadiem1);
        editTextluong = findViewById(R.id.textViewtienluong1);
        txthoten = findViewById(R.id.textViewhovaten);
        buttonupdate = findViewById(R.id.buttontuychon);
        spinner = findViewById(R.id.spinner2);
    }

    public void UpdatePost(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success")){
                    Toast.makeText(UpdatePost.this, "Cập nhật thành công!", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(UpdatePost.this, "Cập nhật chưa thành công!", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdatePost.this, "Lỗi!", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat format1 = new SimpleDateFormat("hh:mm:ss");
                Date date = new Date();
                final String dateformat = format.format(date);
                final String dateformat1 = format1.format(date);
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("content", editTextnoidung.getText().toString());
                params.put("salary", editTextluong.getText().toString());
                params.put("place", spinner.getSelectedItem().toString());
                params.put("address", editTextdiadiem.getText().toString());
                params.put("date", dateformat);
                params.put("time", dateformat1);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
