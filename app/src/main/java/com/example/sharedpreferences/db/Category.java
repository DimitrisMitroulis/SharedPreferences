package com.example.sharedpreferences.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true )
    public int uid;

    @ColumnInfo(name = "Category Name")
    public String categoryName;
}
