package com.picon.agbsn;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SwipeListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<User> movieList;
    private String[] bgColors;
    Context context;

    public SwipeListAdapter(Activity activity, List<User> movieList) {
        this.activity = activity;
        this.movieList = movieList;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.movie_serial_bg);
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int location) {
        return movieList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);


        TextView  id = (TextView) convertView.findViewById(R.id.id);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView age = (TextView) convertView.findViewById(R.id.age);
        TextView fathername = (TextView) convertView.findViewById(R.id.fathername);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        TextView city = (TextView) convertView.findViewById(R.id.city);
        TextView state = (TextView) convertView.findViewById(R.id.state);
        TextView enNumber = (TextView) convertView.findViewById(R.id.enNumber);
        TextView  imageurl = (TextView) convertView.findViewById(R.id.url);
        TextView occupations = (TextView) convertView.findViewById(R.id.occupations);
        TextView mobile1 = (TextView) convertView.findViewById(R.id.mobile1);
        TextView mobile2 = (TextView) convertView.findViewById(R.id.mobile2);
        TextView marital = (TextView) convertView.findViewById(R.id.marital);
        TextView education = (TextView) convertView.findViewById(R.id.education);
        TextView other = (TextView) convertView.findViewById(R.id.other);
        TextView height = (TextView) convertView.findViewById(R.id.height);
        TextView dob = (TextView) convertView.findViewById(R.id.dob);
        TextView dop = (TextView) convertView.findViewById(R.id.dop);
        TextView dot = (TextView) convertView.findViewById(R.id.dot);
        TextView manglik = (TextView) convertView.findViewById(R.id.manglik);
        TextView caste = (TextView) convertView.findViewById(R.id.caste);

        ImageView image = (ImageView) convertView.findViewById(R.id.profile);


         Picasso
                .with(parent.getContext())
                .load(movieList.get(position).imageurl)
                 .placeholder(R.drawable.ic_person)
                .fit() // will explain later
                .into(image);

        id.setText(String.valueOf(movieList.get(position).id));
        name.setText(movieList.get(position).name);
        education.setText(movieList.get(position).education);
        mobile1.setText(movieList.get(position).mobile1);
        age.setText(movieList.get(position).age);
        imageurl.setText(movieList.get(position).imageurl);
        enNumber.setText(movieList.get(position).enNumber);
        fathername.setText(movieList.get(position).fathername);
        address.setText(movieList.get(position).address);
        occupations.setText(movieList.get(position).occupations);
        mobile2.setText(movieList.get(position).mobile2);
        caste.setText(movieList.get(position).caste);
        height.setText(movieList.get(position).height);
        other.setText(movieList.get(position).other);
        dop.setText(movieList.get(position).dop);
        dot.setText(movieList.get(position).dot);
        dob.setText(movieList.get(position).dob);
        marital.setText(movieList.get(position).marital);
        manglik.setText(movieList.get(position).manglik);
        city.setText(movieList.get(position).city);
        state.setText(movieList.get(position).state);

        String color = bgColors[position % bgColors.length];
        id.setBackgroundColor(Color.parseColor(color));

        return convertView;

    }

}