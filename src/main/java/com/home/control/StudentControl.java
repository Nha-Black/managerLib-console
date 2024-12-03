package com.home.control;

import java.util.ArrayList;
import java.util.List;

import com.home.component.Cursor;
import com.home.dao.StudentDao;
import com.home.form.StudentForm;
import com.home.models.Student;

public class StudentControl {
    private StudentForm form;
    private List<Student> students;

    public StudentControl(){
        init();
        control();
    }

    private void init(){
        form = new StudentForm();
        students = new ArrayList<>();
        students = StudentDao.read();
        form.draw();
        form.showData(students);
    }

    private void control(){
        form.control();
    }

    public static void main(String[] args) {
        Cursor.clear();
        new StudentControl();
    }
}
