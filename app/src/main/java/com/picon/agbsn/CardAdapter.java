package com.picon.agbsn;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {


    List<ListItem> items;
    Context mContext;

    public CardAdapter(String[] names, String[] urls,String[] entrys,String[] genders,String[] years, Bitmap[] images,
                         String[] fathername, String[] address, String[] city, String[] state, String[] dob,
                         String[] dot, String[] dop, String[] marital, String[] manglik, String[] caste,
                         String[] height, String[] mobile1, String[] mobile2, String[] education, String[] other, String[] occupation){
        super();
        items = new ArrayList<ListItem>();
        for(int i =0; i<names.length; i++){

            ListItem item = new ListItem();

            item.setName(names[i]);
            item.setUrl(urls[i]);
            item.setEntryno(entrys[i]);
            item.setImage(images[i]);
            item.setGender(genders[i]);
            item.setYear(years[i]);

            //2017

            item.setFathername(fathername[i]);
            item.setAddress(address[i]);
            item.setCity(city[i]);
            item.setState(state[i]);
            item.setDob(dob[i]);
            item.setDot(dot[i]);
            item.setDop(dop[i]);
            item.setMarital(marital[i]);
            item.setManglik(manglik[i]);
            item.setCaste(caste[i]);
            item.setHeight(height[i]);
            item.setMobile1(mobile1[i]);
            item.setMobile2(mobile2[i]);
            item.setEducation(education[i]);
            item.setOther(other[i]);
            item.setOccupation(occupation[i]);


            items.add(item);
        }
    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_card_view, parent, false);
        CardAdapter.ViewHolder viewHolder = new CardAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, final int position) {

        final ListItem list =  items.get(position);
        holder.imageView.setImageBitmap(list.getImage());
        holder.textViewName.setText(Capital.toSentenceCase(list.getName()));
        holder.textViewUrl.setText(list.getUrl());
        holder.textViewDob.setText(list.getDob());
        holder.textViewPro.setText(Capital.toSentenceCase(list.getOccupation()));


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = list.getName().toUpperCase();
                String entry_no = list.getEntryno();
                String url = list.getUrl();
                String year = list.getYear();
                String gender = list.getGender();

                //2017
                String fathername = Capital.toSentenceCase(list.getFathername());
                String address = Capital.toSentenceCase(list.getAddress());
                String city = Capital.toSentenceCase(list.getCity());
                String state = Capital.toSentenceCase(list.getState());
                String dob = list.getDob();
                String dot = list.getDot();
                String dop = Capital.toSentenceCase(list.getDop());
                String marital = Capital.toSentenceCase(list.getMarital());
                String manglik = list.getManglik();
                String caste = Capital.toSentenceCase(list.getCaste());
                String height = list.getHeight();
                String mobile1 = list.getMobile1();
                String mobile2 = list.getMobile2();
                String education = Capital.toSentenceCase(list.getEducation());
                String other = Capital.toSentenceCase(list.getOther());
                String occupation = Capital.toSentenceCase(list.getOccupation());


                Intent ii = new Intent(view.getContext(),FullDisplay16.class);
                ii.putExtra("name",title);
                ii.putExtra("id",position);
                ii.putExtra("gender",gender);
                ii.putExtra("url",url);
                ii.putExtra("year",year);
                ii.putExtra("entry_no",entry_no);

                //2017
                ii.putExtra("fathername",fathername);
                ii.putExtra("address",address);
                ii.putExtra("city",city);
                ii.putExtra("state",state);
                ii.putExtra("dob",dob);
                ii.putExtra("dot",dot);
                ii.putExtra("dop",dop);
                ii.putExtra("marital",marital);
                ii.putExtra("manglik",manglik);
                ii.putExtra("caste",caste);
                ii.putExtra("height",height);
                ii.putExtra("mobile1",mobile1);
                ii.putExtra("mobile2",mobile2);
                ii.putExtra("education",education);
                ii.putExtra("other",other);
                ii.putExtra("occupation",occupation);


                view.getContext().startActivity(ii);
                Log.d("Status :",position+gender+year+entry_no+fathername+address+city+state+dob+dot+dop+marital+manglik+caste+height+mobile1+mobile2+education+other+occupation);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewUrl;
        public TextView textViewEntery;
        public TextView textViewPro;
        public TextView textViewDob;

        public CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewUrl = (TextView) itemView.findViewById(R.id.textViewUrl);
            textViewEntery = (TextView) itemView.findViewById(R.id.textViewEntery);
            textViewPro = (TextView) itemView.findViewById(R.id.textViewPro);
            textViewDob = (TextView) itemView.findViewById(R.id.textViewDob);


        }
    }

}