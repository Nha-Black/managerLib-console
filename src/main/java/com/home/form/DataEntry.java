package com.home.form;

import java.util.ArrayList;
import java.util.List;

import com.home.component.Box;
import com.home.component.Button;
import com.home.component.Cursor;
import com.home.component.Field;
import com.home.models.Book;

public class DataEntry<T> extends Box{
    private List<Field> fields;
    private List<String> labels;
    private Button enter;
    private int maxWidthLabel;
    private int maxWidthField;

    public DataEntry(int x, int y) {
        super(x, y, 50,4);
        init();
    }

    public void init(){
        maxWidthLabel= 0;
        maxWidthField= 0;
        fields = new ArrayList<>();
        labels = new ArrayList<>();
        enter = new Button(x+(width-4)/2, y+height-3, "nhập");
    }
    private int getH(){
        return 1+fields.size()*3;
    }
    public void add(String label){
        if(label.length()>maxWidthLabel) maxWidthLabel= label.length();
        maxWidthField=width-5-maxWidthLabel;
        
        labels.add(label);
        fields.add(new Field(x+maxWidthLabel+4, y+getH(), maxWidthField, 2));
        height +=3;
        enter.setX(x+(width-4)/2);
        enter.setY(y+height-3);
    }

    @Override
    public void draw() {
        Cursor.topleft();
        for(int i=0; i<fields.size(); i++){
            Cursor.move(x+1, fields.get(i).getY()+1);
            System.out.print(labels.get(i));
            fields.get(i).draw();
        }
        enter.draw();
        super.draw();
    }
    
    public static void main(String[] args) {
        Cursor.clear();
        DataEntry<Book> entry = new DataEntry<>(2, 2);
        entry.add("ID");
        entry.add("tên");
        entry.add("tác giả");
        entry.add("thể loại");

        entry.draw();
    }

    
}
