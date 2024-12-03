package com.home.form;

import java.util.ArrayList;
import java.util.List;

import com.home.component.Button;
import com.home.component.Color;
import com.home.component.Cursor;
import com.home.component.Key;
import com.home.component.KeyPress;
import com.home.component.Table;
import com.home.models.Student;

public class StudentForm {
    private Button add;
    private Button remove;
    private Button search;
    private Table table;
    private int index;
    private List<Button> buttons;
    // private Button selected;

    public StudentForm(){
        init();
        draw();
    }
    private void init(){
        Cursor.hidden();

        add = new Button(2, 2, "thêm sinh viên");
        remove = new Button(add.getWidth()+add.getX()+3, 2, "xóa sinh viên");
        search = new Button(remove.getX()+remove.getWidth()+3, 2, "tìm sinh viên");
        table = new Table(3, 6);
        buttons = new ArrayList<>();
        buttons.add(add);
        buttons.add(remove);
        buttons.add(search);
        index = 0 ;
        buttons.get(index).setSelected(Color.CYAN);
        table.setField_name(List.of("ID", "tên sinh viên", "ngày sinh", "nghành học", "giới tính", "số điện thoại"));
    }

    public void draw(){
        Cursor.topleft();
        add.draw();
        remove.draw();
        search.draw();
        table.draw();
    }

    public void showData(List<Student> students){
        table.clear();
        Cursor.clear();
        for(Student student : students){
            table.add(List.of(student.getId(), student.getName(), student.getBirthDay(), student.getDepartment(), student.getGender(), student.getPhone()));
        }
        draw();
    }
    private void controlMove(Key key){
        buttons.get(index).setSelected(Color.RESET);
        if(key==Key.RIGHT) index = (index+1) % 3;
        else if(key==Key.LEFT) index = (index-1+3) % 3;
        buttons.get(index).setSelected(Color.CYAN);
        draw();
    }
    public int control(){
        Key key;
        while (true) { 
          key = KeyPress.Listen();
          if(key== Key.LEFT || key== Key.RIGHT){
            controlMove(key);
          }else if(key==Key.ENTER)
            return index;
        }
    }
    public static void main(String[] args) {
        Cursor.clear();
        new StudentForm().control();
    }
    
}
