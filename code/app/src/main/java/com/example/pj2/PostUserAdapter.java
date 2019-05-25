package com.example.pj2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PostUserAdapter extends BaseAdapter {
    private Main3Activity context;

    public PostUserAdapter(Main3Activity context, int layout, List<Post> postList) {
        this.context = context;
        this.layout = layout;
        this.postList = postList;
    }

    private int layout;
    List<Post> postList;
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
        Button buttonBinhluan, buttonSua, buttonLuu, buttonXoa;
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
            holder.buttonSua = (Button) convertView.findViewById(R.id.buttontuychon);
            holder.buttonLuu = (Button) convertView.findViewById(R.id.buttonluu);
            holder.buttonXoa = (Button) convertView.findViewById(R.id.buttonxoa);

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
        holder.buttonXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XacNhanXoa(Post.getId());
            }
        });
        return convertView;
    }

    private void XacNhanXoa(final String ID){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Bạn có muốn xóa bài viết này không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.DeletePost("http://192.168.0.104:8080/project2/deletepost.php", ID);
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }

}
