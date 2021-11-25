package com.example.clientapp;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

private int id;
private String name;
private int sex;

public User(int id, String name, int sex) {
this.id = id;
this.name = name;
this.sex = sex;
}

@Override
public String toString() {
return "id= "+id+" ,name ="+name+" ,sex= "+sex;
}

protected User(Parcel in) {
id = in.readInt();
name = in.readString();
sex = in.readInt();
}

public static final Creator<User> CREATOR = new Creator<User>() {
@Override
public User createFromParcel(Parcel in) {
return new User(in);
}

@Override
public User[] newArray(int size) {
return new User[size];
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
dest.writeInt(sex);
}
}
