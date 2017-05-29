package com.picon.agbsn;

import android.graphics.Bitmap;

public class Config {


    public static String[] names;
    public static String[] urls;
    public static String[] genders;
    public static String[] years;
    public static String[] entrys;
    public static Bitmap[] bitmaps;


    //2017

    public static String[] fathername;
    public static String[] address;
    public static String[] city;
    public static String[] state;
    public static String[] dob;
    public static String[] dot;
    public static String[] dop;
    public static String[] marital;
    public static String[] manglik;
    public static String[] caste;
    public static String[] height;
    public static String[] mobile1;
    public static String[] mobile2;
    public static String[] education;
    public static String[] other;
    public static String[] occupation;




    public static final String GET_URL = "http://agbsnbrahminparichay.com/agbsn/dear.php";
    public static final String TAG_IMAGE_URL = "img";
    public static final String TAG_IMAGE_URL17 = "image";
    public static final String TAG_IMAGE_NAME = "name";
    public static final String TAG_ENTRYNO = "entry_no";
    public static final String TAG_YEAR = "year";
    public static final String TAG_GENDER = "gender";
    public static final String TAG_JSON_ARRAY="agsbs";




    //new full detail of 2017


    public static final String TAG_FATHER = "fname";
    public static final String TAG_MOBILE1 = "mobile1";
    public static final String TAG_MOBILE2 = "mobile2";
    public static final String TAG_DOB = "birthD";
    public static final String TAG_DOP = "birthP";
    public static final String TAG_DOT = "birthT";
    public static final String TAG_STATE = "state";
    public static final String TAG_CITY = "city";
    public static final String TAG_ADDRESS = "address";
    public static final String TAG_EDUCATION = "educ";
    public static final String TAG_OTHEREDUCATION = "otherEducation";
    public static final String TAG_CASTE = "caste";
    public static final String TAG_MARITAL = "marital";
    public static final String TAG_MANGLIK = "manglik";
    public static final String TAG_HEIGHT = "heigh";
    public static final String TAG_OCCUPATION = "profession";


    //2016

    public static final String TAG_STATE16 = "state";
    public static final String TAG_ADDRESS16 = "address";
    public static final String TAG_MANGLIK16 = "manglik";
    public static final String TAG_MARITAL16 = "marital_status";
    public static final String TAG_FATHER16 = "father_name";
    public static final String TAG_OTHEREDUCATION16 = "other_education";
    public static final String TAG_EDUCATION16 = "education";
    public static final String TAG_OCCUPATION16 = "occupation";
    public static final String TAG_DISTRICT16 = "district";
    public static final String TAG_MOBILE16 = "mobile";
    public static final String TAG_DOB16 = "date_of_birth";
    public static final String TAG_DOT16 = "time";
    public static final String TAG_DOP16 = "birth_place";
    public static final String TAG_HEIGHT16 = "height";
    public static final String TAG_SUBCAST16 = "sub_cast";


    public Config(int i){

        //2017

        fathername = new String[i];
        address=new String[i];
        city=new String[i];
        state=new String[i];
        dob=new String[i];
        dot=new String[i];
        dop=new String[i];
        marital=new String[i];
        manglik=new String[i];
        caste=new String[i];
        height=new String[i];
        mobile1=new String[i];
        mobile2=new String[i];
        education=new String[i];
        other=new String[i];
        occupation=new String[i];

// other
        names = new String[i];
        urls = new String[i];
        entrys = new String[i];
        genders = new String[i];
        years = new String[i];
        bitmaps = new Bitmap[i];
    }
}