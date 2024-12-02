package Dao;

import Models.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DaoStudent {

    private static final String FILE_PATH = "Student.txt";
    private static final String JSON_FILE_PATH = "students.json";
    public List<Student> readStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s*,\\s*");
                if (data.length == 7) {
                    Student student = new Student(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim(),
                            data[3].trim(),
                            data[4].trim(),
                            data[5].trim(),
                            LocalDate.parse(data[6].trim())
                    );
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
        return students;
    }
    public boolean addStudent(Student student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(student.getId() + "," + student.getName() + "," + student.getGender() + ","
                    + student.getPhone() + "," + student.getDepartment() + "," + student.getEmail() + ","
                    + student.getBirthDay().toString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
            return false;
        }
    }

    public boolean updateStudent(Student student) {
        List<Student> students = readStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(student.getId())) {
                students.set(i, student);
                writeStudentsToFile(students);
                return true;
            }
        }
        return false;
    }

    private void writeStudentsToFile(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                writer.write(student.getId() + "," + student.getName() + "," + student.getGender() + ","
                        + student.getPhone() + "," + student.getDepartment() + "," + student.getEmail() + ","
                        + student.getBirthDay().toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }
    public void exportStudentsToJson() {
        List<Student> students = readStudents();
        JSONArray studentsArray = new JSONArray();
        for (Student student : students) {
            JSONObject studentJson = new JSONObject();
            studentJson.put("id", student.getId());
            studentJson.put("name", student.getName());
            studentJson.put("gender", student.getGender());
            studentJson.put("phone", student.getPhone());
            studentJson.put("department", student.getDepartment());
            studentJson.put("email", student.getEmail());
            studentJson.put("birthDay", student.getBirthDay().toString());
            studentsArray.put(studentJson);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JSON_FILE_PATH))) {
            writer.write(studentsArray.toString(4));
        } catch (IOException e) {
            System.out.println("Lỗi khi xuất sinh viên ra file JSON: " + e.getMessage());
        }
    }
    public List<Student> importStudentsFromJson() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(JSON_FILE_PATH))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            JSONArray studentsArray = new JSONArray(jsonContent.toString());
            for (int i = 0; i < studentsArray.length(); i++) {
                JSONObject studentJson = studentsArray.getJSONObject(i);
                Student student = new Student(
                        studentJson.getString("id"),
                        studentJson.getString("name"),
                        studentJson.getString("gender"),
                        studentJson.getString("phone"),
                        studentJson.getString("department"),
                        studentJson.getString("email"),
                        LocalDate.parse(studentJson.getString("birthDay"))
                );
                students.add(student);
            }
        } catch (IOException | org.json.JSONException e) {
            System.out.println("Lỗi khi nhập sinh viên từ file JSON: " + e.getMessage());
        }
        return students;
    }
}
