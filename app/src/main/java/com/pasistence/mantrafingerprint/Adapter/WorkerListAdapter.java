package com.pasistence.mantrafingerprint.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pasistence.mantrafingerprint.Models.ModelWorker;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;

import java.util.ArrayList;

public class WorkerListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<ModelWorker> recordList;

    public WorkerListAdapter(Context context, int layout, ArrayList<WorkerModel> recordList) {
        this.context = context;
        this.layout = layout;
       // this.recordList = recordList;
    }



    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private  class ViewHolder
    {
        ImageView imageView;
        TextView txtName, txtAge,txtPhone;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row==null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtName = row.findViewById(R.id.txtName);
            holder.txtAge = row.findViewById(R.id.txtAge);
            holder.txtPhone = row.findViewById(R.id.txtPhone);
            holder.imageView = row.findViewById(R.id.imgIcon);

            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)row.getTag();
        }

        ModelWorker modelWorker = recordList.get(i);
        holder.txtName.setText(modelWorker.getName());
        holder.txtAge.setText(modelWorker.getAge());
        holder.txtPhone.setText(modelWorker.getPhone());

        byte[] recordImage = modelWorker.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage,0,recordImage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }
}
