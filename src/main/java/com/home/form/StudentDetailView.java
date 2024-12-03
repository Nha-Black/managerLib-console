package com.home.form;

import com.home.component.Box;
import com.home.component.Button;
import com.home.component.Cursor;
import com.home.component.Field;
import com.home.component.Key;
import com.home.component.KeyPress;
import com.home.component.Label;
import com.home.models.Student;

public class StudentDetailView {
    private static Field idField, nameField, birthDayField, genderField, phoneField, departmentField, emailField, borrowBookField;
    private static Button addButton;
    private static Label label, labelId, labelName, labelBirthDay, labelGender, labelPhone, labelDepartment, labelEmail, labelBorrowBook;
    private static Box selected;
    private static int index;

    public StudentDetailView() {
        init();
        draw();
    }

    private void init() {
        label = new Label(25, 2, "Add New Student");
        labelId = new Label(5, 5, "ID");
        idField = new Field(30, 4, 45, 2);

        labelName = new Label(5, 8, "Name");
        nameField = new Field(30, 7, 45, 2);

        labelBirthDay = new Label(5, 11, "Birth Date (yyyy-MM-dd)");
        birthDayField = new Field(30, 10, 45, 2);

        labelGender = new Label(5, 14, "Gender");
        genderField = new Field(30, 13, 45, 2);

        labelPhone = new Label(5, 17, "Phone");
        phoneField = new Field(30, 16, 45, 2);

        labelDepartment = new Label(5, 20, "Department");
        departmentField = new Field(30, 19, 45, 2);

        labelEmail = new Label(5, 23, "Email");
        emailField = new Field(30, 22, 45, 2);

        labelBorrowBook = new Label(5, 26, "Borrowed Books");
        borrowBookField = new Field(30, 25, 45, 2);

        addButton = new Button(30, 28, "Add");
        addButton.setTextColor("\033[36m");

        index = 0;
        selected = idField;
        selected.setColor("\033[36m");
    }

    private static void draw() {
        System.out.print("\033[H"); 

        label.draw();
        labelId.draw();
        idField.draw();

        labelName.draw();
        nameField.draw();

        labelBirthDay.draw();
        birthDayField.draw();

        labelGender.draw();
        genderField.draw();

        labelPhone.draw();
        phoneField.draw();

        labelDepartment.draw();
        departmentField.draw();

        labelEmail.draw();
        emailField.draw();

        labelBorrowBook.draw();
        borrowBookField.draw();

        addButton.draw();
    }

    private static void controlMove(Key key) {
        selected.setColor("\033[0m"); 
        if (key == Key.DOWN) {
            index = (index + 1) % 9;
        } else if (key == Key.UP) {
            index = (index - 1 + 9) % 9;
        }

        switch (index) {
            case 0 -> selected = idField;
            case 1 -> selected = nameField;
            case 2 -> selected = birthDayField;
            case 3 -> selected = genderField;
            case 4 -> selected = phoneField;
            case 5 -> selected = departmentField;
            case 6 -> selected = emailField;
            case 7 -> selected = borrowBookField;
            default -> selected = addButton;
        }

        selected.setColor("\033[36m");
        String text = (selected instanceof Field) ? ((Field) selected).getText() : "";
        draw();
        Cursor.move(selected.getX() + text.length() + 1, selected.getY() + 1);
    }

    private static Student control() {
        Cursor.move(selected.getX() + 1, selected.getY() + 1);
        Key key;

        while (true) {
            key = KeyPress.Listen();
            if (key != null) {
                if (key == Key.DOWN || key == Key.UP) {
                    controlMove(key);
                } else if (key == Key.ENTER) {
                    if (selected == addButton) {
                        return null;
                        // return new Student(
                        //         idField.getText(),
                        //         nameField.getText(),
                        //         LocalDate.parse(birthDayField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        //         genderField.getText(),
                        //         phoneField.getText(),
                        //         departmentField.getText(),
                        //         emailField.getText(),
                        //         new ArrayList<>(List.of(borrowBookField.getText().split(",")))
                        // );
                    }
                } else if (key == Key.OTHER && selected instanceof Field) {
                    ((Field) selected).enter(key);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        new StudentDetailView();
        Student student = control();
        System.out.println("\nStudent Added Successfully:");
        System.out.println(student);
    }
}
