package Dao;

import Models.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends Dao {

	public List<Student> read() {
		List<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM Students";
		try (Connection conn = Dao.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Student student = new Student(rs.getString("id"), rs.getString("name"), rs.getString("gender"),
						rs.getString("phone"), rs.getString("department"), rs.getString("email"),
						rs.getDate("birthDay").toLocalDate());
				students.add(student);
			}
		} catch (SQLException e) {
			System.out.println("Error : " + e.getMessage());
		}
		return students;
	}

	public void add(Student student) {
		String sql = "INSERT INTO Students (name, gender, phone, department, email, birthDay) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = Dao.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getGender());
			pstmt.setString(3, student.getPhone());
			pstmt.setString(4, student.getDepartment());
			pstmt.setString(5, student.getEmail());
			pstmt.setDate(6, Date.valueOf(student.getBirthDay()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : " + e.getMessage());
		}
	}

	public boolean updateStudent(Student student) {
		String sql = "UPDATE Students SET name = ?, gender = ?, phone = ?, department = ?, email = ?, birthDay = ? WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Dao.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getGender());
			pstmt.setString(3, student.getPhone());
			pstmt.setString(4, student.getDepartment());
			pstmt.setString(5, student.getEmail());
			pstmt.setDate(6, Date.valueOf(student.getBirthDay()));
			pstmt.setString(7, student.getId());
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.out.println("Error : " + e.getMessage());
			return false;
		}
	}

	public void exportStudentsToJson() {
		List<Student> students = read();
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

		try (FileWriter file = new FileWriter("students.json")) {
			file.write(studentsArray.toString(4));
			file.flush();
			System.out.println("Dữ liệu sinh viên đã được xuất thành công ra file students.json");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}