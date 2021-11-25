package com.example.clientapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
public Book(int id, String name) {
this.id = id;
this.name = name;
}

private int id;
private String name;

public Book() {
}

protected Book(Parcel in) {
id = in.readInt();
name = in.readString();
}

public static final Creator<Book> CREATOR = new Creator<Book>() {
@Override
public Book createFromParcel(Parcel in) {
return new Book(in);
}

@Override
public Book[] newArray(int size) {
return new Book[size];
}
};

@Override
public int describeContents() {
return 0;
}

@Override
public void writeToParcel(Parcel dest, int flags) {
dest.writeInt(id);
dest.writeString(name);
}

@Override
public String toString() {
String result = "book = ["+id+
","+name+"];";
return result;
}
}