package com.home.control;

import java.util.ArrayList;
import java.util.List;

import com.home.component.Cursor;
import com.home.dao.StudentDao;
import com.home.form.GetStudentForm;
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
        int code;
        while (true) { 
            code= form.control();
            switch(code) { 
                case 0:
                    add();
                    break;
                case 1:
                    remove();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    return;
                default :
                    break;                
            }
        }
    }
    private void add(){
        Cursor.clear();
        Student student = GetStudentForm.getData();
        students.add(student);
        StudentDao.write(students);
        Cursor.clear();
        form.showData(students);
    }
    private void remove(){
        Cursor.clear();
        students = GetStudentForm.remove(students);
        StudentDao.write(students);
        Cursor.clear();
        form.showData(students);
    }
    private void update(){
        Cursor.clear();
        students = GetStudentForm.update(students);
        StudentDao.write(students);
        Cursor.clear();
        form.showData(students);
    }

    public static void main(String[] args) {
        Cursor.clear();
        new StudentControl();
    }
}
