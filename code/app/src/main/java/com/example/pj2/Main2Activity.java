package com.example.pj2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String ID, luongMin, luongMax, name, acc, pass;
    int a,b;
    Button buttonFindSalary, buttonCancelSalary, buttonFindPlace, buttonCancelPlace, buttonChangePass, buttonCancelChange;
    ArrayList<Post> postArrayList;
    ArrayList<Post> salaryArrayList;
    ArrayList<Post> placeArrayList;
    ArrayList<Post> saveArrayList;
    ArrayList<Comment> commentArrayList;
    ListView lvPost, lvComment;
    EditText editTextluongMin, editTextluongMax, editTextFindPlace, editTextOldPass, editTextNewPass1, editTextNewPass2;
    PostAdapter1 postAdapter1;
    CommentAdapter commentAdapter;
    String urll = "http://192.168.0.104:8080/project2/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
        commentArrayList = new ArrayList<>();
        Comment(urll+"comment.php");

        Intent intent2 = getIntent();
        Bundle bundle = intent2.getBundleExtra("dulieu");
        ID = bundle.getString("ID");
        name = bundle.getString("name");
        acc = bundle.getString("acc");
        Post(urll+"Post.php");
        if(ID.equals("Trang chủ")) {
            HomePage();
        }

        else if(ID.equals("Lưu")){

            lvPost = (ListView) findViewById(R.id.listviewPost);
            saveArrayList = new ArrayList<>();
            saveList(urll+"listsave.php", acc);
            postAdapter1 = new PostAdapter1(this, R.layout.baidang, saveArrayList);
            lvPost.setAdapter(postAdapter1);
        }

        else if(ID.equals("salary")){
            Intent intent1 = getIntent();
            Bundle bundle1 = intent1.getBundleExtra("dulieu");
            String luongMax = bundle1.getString("luongMax");
            String luongMin = bundle1.getString("luongMin");
            FindSalary(luongMin, luongMax);
        }

        else if(ID.equals("place")){
           Intent intent1 = getIntent();
           Bundle bundle1 = intent1.getBundleExtra("dulieu");
           String place = bundle1.getString("place");
           FindPlace(place);
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
        getMenuInflater().inflate(R.menu.main2, menu);
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
            ID = "Trang chủ";
            String acc = Account();
            String name = Name();
            Intent intent = new Intent(Main2Activity.this, Main2Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("acc", acc);
            bundle.putString("ID", ID);
            intent.putExtra("dulieu", bundle);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {
            String acc = Account();
            Intent intent = new Intent(Main2Activity.this,Personal.class);
            intent.putExtra("acc", acc);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

            Intent intent = new Intent(Main2Activity.this,Post1.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("acc", acc);
            bundle.putString("ID", ID);
            intent.putExtra("dulieu", bundle);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            ID = "Lưu";
            Intent intent = new Intent(Main2Activity.this, Main2Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("acc", acc);
            bundle.putString("ID", ID);
            intent.putExtra("dulieu", bundle);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            dialogFindBySalary();
        } else if (id == R.id.nav_send) {
            dialogFindByPlace();

        }
        else if(id == R.id.nav_change){
            dialogChangePassword();
        }

        else if(id == R.id.nav_logout){
            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private  void dialogFindBySalary()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.find_by_salary);
        dialog.setCanceledOnTouchOutside(true);
        buttonFindSalary = dialog.findViewById(R.id.buttonFindSalary);
        buttonCancelSalary = dialog.findViewById(R.id.buttonCancelSalary);
        editTextluongMin = dialog.findViewById(R.id.editTextluong1);
        editTextluongMax = dialog.findViewById(R.id.editTextluong2);
        buttonCancelSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        buttonFindSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                luongMin = editTextluongMin.getText().toString();
                luongMax = editTextluongMax.getText().toString();
                int luong1 = Integer.parseInt(luongMin);
                int luong2 = Integer.parseInt(luongMax);
                if (luong1 > luong2) {
                    Toast.makeText(Main2Activity.this, "Tiền lương bạn điền không hợp lệ!", Toast.LENGTH_LONG).show();
                } else {
                    ID = "salary";
                    Intent intent = new Intent(Main2Activity.this, Main2Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("luongMin", luongMin);
                    bundle.putString("luongMax", luongMax);
                    bundle.putString("name", name);
                    bundle.putString("acc", acc);
                    bundle.putString("ID", ID);
                    intent.putExtra("dulieu", bundle);
                    startActivity(intent);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    private  void dialogFindByPlace()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.find_by_place);
        dialog.setCanceledOnTouchOutside(true);
        editTextFindPlace = dialog.findViewById(R.id.editText9);
        buttonFindPlace = dialog.findViewById(R.id.buttonFindPlace);
        buttonCancelPlace = dialog.findViewById(R.id.buttonCancelPlace);
        buttonCancelPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        buttonFindPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String place = editTextFindPlace.getText().toString();
                String ID = "place";
                String acc = Account();
                String name = Name();
                Intent intent = new Intent(Main2Activity.this, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("place", place);
                bundle.putString("name", name);
                bundle.putString("acc", acc);
                bundle.putString("ID", ID);
                intent.putExtra("dulieu", bundle);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void dialogChangePassword(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.changepassword);
        dialog.setCanceledOnTouchOutside(true);
        editTextOldPass = dialog.findViewById(R.id.editText5);
        editTextNewPass1 = dialog.findViewById(R.id.editText8);
        editTextNewPass2 = dialog.findViewById(R.id.editText10);
        buttonChangePass = dialog.findViewById(R.id.button);
        buttonCancelChange = dialog.findViewById(R.id.button3);

        buttonCancelChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        buttonChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bundle = intent.getBundleExtra("dulieu");
                pass = bundle.getString("pass");
                String oldPass = editTextOldPass.getText().toString().trim();
                String newPass1 = editTextNewPass1.getText().toString().trim();
                String newPass2 = editTextNewPass2.getText().toString().trim();

                if(oldPass.equals("") || newPass1.equals("") || newPass2.equals("")){
                    Toast.makeText(Main2Activity.this, "Bạn chưa điền đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                }
                else {
                    if(oldPass.equals(pass)){
                        if (newPass1.equals(newPass2)){

                            UpdatePass(urll + "changePass.php", newPass1);
                            dialog.dismiss();

                        }

                        else {
                            Toast.makeText(Main2Activity.this, "Mật khẩu mới không khớp nhau!", Toast.LENGTH_LONG).show();
                        }
                    }

                    else {
                       Toast.makeText(Main2Activity.this, "Mật khẩu cũ không đúng!", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        dialog.show();
    }

    public void UpdatePass(String url, final String newPass){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success")){
                    Toast.makeText(Main2Activity.this, "Thay đổi mật khẩu thành công!", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(Main2Activity.this, "Thay đổi mật khẩu chưa thành công!", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this, "Lỗi!", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("acc", acc);
                params.put("pass", newPass );
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void Post(String url){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = response.length()-1; i >= 0; i--) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        postArrayList.add(new Post(jsonObject.getString("id"), jsonObject.getString("acc"), jsonObject.getString("salary"),jsonObject.getString("address"), jsonObject.getString("place"), jsonObject.getString("content"), jsonObject.getString("date"), jsonObject.getString("time"), jsonObject.getString("name")));

                    } catch (Exception e) {

                    }
                }
                postAdapter1.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Main2Activity.this, error.toString(), Toast.LENGTH_LONG).show();
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

    public void PostSalary(String url, final int luongMin, final int luongMax){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = response.length()-1; i >= 0; i--){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int salary = Integer.parseInt(jsonObject.getString("salary"));
                        if(salary >= luongMin && salary <= luongMax)
                        {
                            salaryArrayList.add(new Post(jsonObject.getString("id"), jsonObject.getString("acc"), jsonObject.getString("salary"),jsonObject.getString("address"), jsonObject.getString("place"), jsonObject.getString("content"), jsonObject.getString("date"), jsonObject.getString("time"), jsonObject.getString("name")));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                postAdapter1.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public void PostPlace(String url, final String place){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = response.length()-1; i >= 0; i--){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        if (place.equals(jsonObject.getString("place"))){
                            placeArrayList.add(new Post(jsonObject.getString("id"), jsonObject.getString("acc"), jsonObject.getString("salary"),jsonObject.getString("address"), jsonObject.getString("place"), jsonObject.getString("content"), jsonObject.getString("date"), jsonObject.getString("time"), jsonObject.getString("name")));

                    }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                postAdapter1.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public void saveList(String url, final String account){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = response.length()-1; i >= 0; i--) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        if (account.equals(jsonObject.getString("account"))) {
                            saveArrayList.add(new Post(jsonObject.getString("id"), jsonObject.getString("acc"), jsonObject.getString("salary"), jsonObject.getString("address"), jsonObject.getString("place"), jsonObject.getString("content"), jsonObject.getString("date"), jsonObject.getString("time"), jsonObject.getString("name")));
                        }
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                postAdapter1.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    public String Account()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dulieu");
        String acc = bundle.getString("acc");

        return acc;
    }

    public String Name(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dulieu");
        String name = bundle.getString("name");
        return name;
    }

    public void FindSalary(String min, String max) {

        a = Integer.parseInt(min);
        b = Integer.parseInt(max);
        if (a > b) {
            Toast.makeText(Main2Activity.this, "Tiền lương không hợp lệ!", Toast.LENGTH_LONG).show();
        } else {
            lvPost = (ListView) findViewById(R.id.listviewPost);
            postArrayList = new ArrayList<>();
            salaryArrayList = new ArrayList<>();
            PostSalary(urll+"Post.php", a, b);
            postAdapter1 = new PostAdapter1(this, R.layout.baidang, salaryArrayList);
            lvPost.setAdapter(postAdapter1);
        }
    }

    public void HomePage(){
        lvPost = (ListView) findViewById(R.id.listviewPost);
        postArrayList = new ArrayList<>();
//        Post("http://192.168.1.201/project2/Post.php");

        postAdapter1 = new PostAdapter1(this, R.layout.baidang, postArrayList);
        lvPost.setAdapter(postAdapter1);
    }

    public void FindPlace(String place){
        lvPost = (ListView) findViewById(R.id.listviewPost);
        placeArrayList = new ArrayList<>();
        PostPlace(urll+"Post.php", place);
        postAdapter1 = new PostAdapter1(this, R.layout.baidang, placeArrayList);
        lvPost.setAdapter(postAdapter1);
    }


    public void Notice(String accpost){
        lvComment = (ListView) findViewById(R.id.lvnotice);
        commentAdapter = new CommentAdapter(this, R.layout.notice1, commentArrayList);
        lvComment.setAdapter(commentAdapter);

    }



    public void save(String url, final String account1, final String IDpost1, final String accPost){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(Main2Activity.this, "Lưu bài viết thành công", Toast.LENGTH_LONG).show();
                }
                else{
//                    Toast.makeText(Main2Activity.this, "Lưu bài viết không thành công!", Toast.LENGTH_LONG).show();
                    cancelSave(account1, IDpost1, accPost);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this, "Bài viết đã được lưu trước đó", Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> param = new HashMap<>();
                param.put("account", account1);
                param.put("IDpost", IDpost1);
                param.put("accPost", accPost);
                return param;
            }

        };
        requestQueue.add(stringRequest);
    }

    public void cancelSave(final String account1, final String IDpost1, final String accPost)
    {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bài viết này đã được lưu trước đó, bạn có muốn hủy bỏ lưu giữ bài viết này không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteSave(urll+"deletesave.php",account1,IDpost1, accPost);
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }

    public void deleteSave(String url, final String account1, final String IDpost1, final String accPost){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")){
                    Toast.makeText(Main2Activity.this, "Bạn đã bỏ lưu bài viết thành công", Toast.LENGTH_LONG).show();
                }

                else Toast.makeText(Main2Activity.this, "Bạn đã bỏ lưu bài viết không thành công", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this, "Chưa thành công!", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("account", account1);
                param.put("IDpost", IDpost1);
                param.put("accPost", accPost);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


}
