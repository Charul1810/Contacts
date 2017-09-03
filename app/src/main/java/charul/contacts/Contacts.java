package charul.contacts;

/**
 * Created by User on 20-08-2017.
 */

public class Contacts {
    int _id;
    String _fname;
    String _lname;
    String _phone_number;
    String _email;

    public Contacts(int _id, String _fname,String _lname, String _phone,String _email) {
        this._id = _id;
        this._fname = _fname;
        this._lname = _lname;
        this._phone_number = _phone;
        this._email=_email;
    }

    public Contacts(String _fname,String _lname, String _phone_number,String _email) {
        this._fname = _fname;
        this._lname=_lname;
        this._phone_number = _phone_number;
        this._email=_email;
    }

    public Contacts() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_fname() {
        return _fname;
    }

    public String get_lname() {
        return _lname;
    }

    public void set_fname(String _fname) {
        this._fname = _fname;
    }

    public void set_lname(String _lname) {
        this._lname = _lname;
    }

    public String get_phone() {
        return _phone_number;
    }

    public void set_phone(String _phone) {
        this._phone_number = _phone;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }
}

