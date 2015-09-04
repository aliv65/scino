package com.scinobooks.aliv.scino_books;

public class Category {

    int _id;
    String _title;

    public Category(){
    }

    public Category(int id, String _title){
        this._id = id;
        this._title = _title;
    }

    public Category(String _title){
        this._title = _title;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getTitle(){
        return this._title;
    }

    public void setTitle(String title){
        this._title = title;
    }
}
