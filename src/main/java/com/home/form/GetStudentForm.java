package com.home.form;

import java.util.List;

import com.home.component.DataEntry;
import com.home.models.Student;
import com.home.util.DataProcess;

public class GetStudentForm {
    public static Student getData(){
        DataEntry<Student> entry = new DataEntry<>(2, 2, Student.class);
        entry.add("ID","id");
        entry.add("tên","name");
        entry.add("giới tính","gender");
        entry.add("số đt","phone");
        entry.add("nghành học", "department");
        entry.add("email", "email");
        entry.add("ngày sinh", "birthDay");
        
        entry.control();
        
        return entry.getclass();
    }

    public static String getID(List<Student> students){
        Boolean find = false;
        DataEntry entry = new DataEntry(2, 2);
        entry.add("ID", "id");
        do{
            entry.control();
            String id = entry.getData();
            entry.add("tên sinh viên", "name");
            for(Student student : students){
                if(student.getId().equals(id)){
                    if(student.getBorrowedBook().size()==5){
                        entry.setText("name", "chỉ được mượn tối đa 5 sách", "ERROR");
                    }
                    else{
                        entry.setText("name", student.getName());
                    entry.setButton("xác nhận");
                    entry.control();
                    find=true;
                    return id;
                    }
                    
                }
            }
            if(!find) entry.setText("name", "không tìm thấy", "ERROR");
        } while(!find);
        return null;
    }

    public static List<Student> remove(List<Student> students){
        Boolean find = false;
        DataEntry entry = new DataEntry(2, 2);
        entry.add("ID", "id");
        do{
            entry.control();
            String id = entry.getData();
            entry.add("tên sinh viên", "name");
            for(Student student : students){
                if(student.getId().equals(id)){
                    entry.setText("name", student.getName());
                    entry.setButton("xác nhận xóa");
                    entry.control();
                    students.remove(student);
                    find=true;
                    break;
                }
            }
            if(!find) entry.setText("name", "không tìm thấy", "ERROR");
        } while(!find);
        return students;
    }
    public static List<Student> update(List<Student> students){
        boolean find = false;
        Student stu = new Student();
        DataEntry<Student> entry= new DataEntry<>(2, 2, Student.class);
        entry.add("ID", "id");
        //tìm kiếm sinh viên qua id
        do{
            entry.control();
            String id = entry.getData();
            entry.add("tên sinh viên", "name");
            for(Student student : students){
                if(student.getId().equals(id)){
                    entry.setText("name", student.getName());
                    entry.setButton("chỉnh sửa");
                    entry.control();
                    stu= student;
                    find=true;
                    break;
                }
            }
            if(!find) entry.setText("name", "không tìm thấy", "ERROR");
        } while(!find);
        //chỉnh sửa thông tin
        entry.add("giới tính","gender", stu.getGender());
        entry.add("số đt","phone", stu.getPhone());
        entry.add("nghành học", "department", stu.getDepartment());
        entry.add("email", "email", stu.getEmail());
        entry.add("ngày sinh", "birthDay", DataProcess.dateToStr(stu.getBirthDay()));

        entry.control();
        stu = entry.getclass();
        
        //cập nhật vào list
        for(Student student : students){
            if(student.getId().equals(stu.getId())){
                students.set(students.indexOf(student), stu);
                break;
            }
        }
        return students;
    }
}
