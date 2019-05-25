package com.example.pj2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private int layout;

    public CommentAdapter(Context context, int layout, List<Comment> commentList) {
        this.context = context;
        this.layout = layout;
        this.commentList = commentList;
    }

    private class ViewHolder{
        TextView txtViewHotenNotice, txtNotice;
    }

    private List<Comment> commentList;

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtViewHotenNotice = (TextView) convertView.findViewById(R.id.textViewHotenNotice);
            holder.txtNotice = (TextView) convertView.findViewById(R.id.textView16);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Comment comment = commentList.get(position);

        holder.txtViewHotenNotice.setText(comment.getName());
        holder.txtNotice.setText(comment.getContent());
        return convertView;
    }
}
