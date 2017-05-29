package com.picon.agbsn;

/**
 * Created by user on 3/20/2017.
 */

public class Message {


    public int _id;
    public String _message;
    public String _title;
    public String _time;


    public Message()
    {

    }

    public Message(String _message, String _title, String _time) {
        this._message = _message;
        this._title = _title;
        this._time = _time;
    }

    public Message(int id, String _message, String _title, String _time) {
        this._id = id;
        this._message = _message;
        this._title = _title;
        this._time = _time;

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }


    public String get_message() {
        return _message;
    }

    public void set_message(String _message) {
        this._message = _message;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }


}
