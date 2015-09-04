package com.scinobooks.aliv.scino_books;

public class Book {

    int _id;
    String _name;
    String _author;
    String _title;

    public Book(){
    }

    public Book(int id, String _name, String _author, String _title){
        this._id = id;
        this._name = _name;
        this._author = _author;
        this._title = _title;
    }

    public Book(String _name, String _author, String _title){
        this._name = _name;
        this._author = _author;
        this._title = _title;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getAuthor(){
        return this._author;
    }

    public void setAuthor(String author){
        this._author = author;
    }

    public String getTitle(){
        return this._title;
    }

    public void setTitle(String title){
        this._title = title;
    }

}
