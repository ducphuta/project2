package com.example.pj2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PostAdapter1 extends BaseAdapter {
    TextView txtname;
    private Main2Activity context;
    private int layout;
    public PostAdapter1(Main2Activity context, int layout, List<Post> postList) {
        this.context = context;
        this.layout = layout;
        this.postList = postList;
    }

    private List<Post> postList;
    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtHoTen, txtThoigian, txtNoidung, txtLuong, txtDiadiem, txtLuong1, txtLuong2, txtDiadiem1;
        Button buttonBinhluan, buttonTuychon, buttonLuu;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtHoTen = (TextView) convertView.findViewById(R.id.textViewhovaten);
            holder.txtThoigian = (TextView) convertView.findViewById(R.id.textViewthoigian);
            holder.txtNoidung = (TextView) convertView.findViewById(R.id.textViewnoidung);
            holder.txtLuong = (TextView) convertView.findViewById(R.id.textViewtienluong1);
            holder.txtDiadiem = (TextView) convertView.findViewById(R.id.textViewdiadiem1);
            holder.buttonBinhluan = (Button) convertView.findViewById(R.id.buttonbinhluan);
            holder.txtLuong1 = (TextView) convertView.findViewById(R.id.textViewtienluong);
            holder.txtLuong2 = (TextView) convertView.findViewById(R.id.textViewtiengluong3);
            holder.txtDiadiem1 = (TextView) convertView.findViewById(R.id.textViewdiadiem);
            holder.buttonTuychon = (Button) convertView.findViewById(R.id.buttontuychon);
            holder.buttonLuu = (Button) convertView.findViewById(R.id.buttonluu);
            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Post Post = postList.get(position);
        holder.txtHoTen.setText(Post.getName());
        holder.txtThoigian.setText(Post.getDate() + " " + Post.getTime());
        holder.txtNoidung.setText(Post.getContent());
        holder.txtLuong.setText(Post.getSalary());
        holder.txtLuong1.setText("Tiền lương: ");
        holder.txtLuong2.setText("Đồng/giờ. ");
        holder.txtDiadiem1.setText("Địa điểm: ");
        holder.txtDiadiem.setText(Post.getAddress()+ " - " + Post.getPlace() + " - Hà Nội");

        holder.buttonBinhluan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListNotice.class);
                Bundle bundle = new Bundle();
                bundle.putString("acc", Post.getAcc());
                bundle.putString("id", Post.getId());
                bundle.putString("name", Post.getName());
                bundle.putString("acc1", context.acc);
                bundle.putString("name1", context.name);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

        holder.buttonLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.save("http://192.168.0.104:8080" +
                        "" +
                        "/project2/save.php", context.acc, Post.getId(), Post.getAcc());
            }
        });
        return convertView;

    }

}
