package com.home.form;

import com.home.component.Box;
import com.home.component.Button;
import com.home.component.Cursor;
import com.home.component.Field;
import com.home.component.Key;
import com.home.component.KeyPress;
import com.home.component.Label;

public class BookDetailView {
    private static Field idField, titleField, authorField, publisherField, yearField, languageField, genreField, availableField, pageCountField;
    private static Button addButton;
    private static Label label, labelId, labelTitle, labelAuthor, labelPublisher, labelYear, labelLanguage, labelGenre, labelAvailable, labelPageCount;
    private static Box selected;
    private static int index;

    public BookDetailView() {
        init();
        draw();
    }

    private void init() {
        label = new Label(25, 2, "Add New Book");
        labelId = new Label(5, 5, "ID");
        idField = new Field(25, 4, 45, 2); 

        labelTitle = new Label(5, 8, "Title");
        titleField = new Field(25, 7, 45, 2);

        labelAuthor = new Label(5, 11, "Author");
        authorField = new Field(25, 10, 45, 2);

        labelPublisher = new Label(5, 14, "Publisher");
        publisherField = new Field(25, 13, 45, 2);

        labelYear = new Label(5, 17, "Publication Year");
        yearField = new Field(25, 16, 45, 2);

        labelLanguage = new Label(5, 20, "Language");
        languageField = new Field(25, 19, 45, 2);

        labelGenre = new Label(5, 23, "Genre");
        genreField = new Field(25, 22, 45, 2);

        labelAvailable = new Label(5, 26, "Available Copies");
        availableField = new Field(25, 25, 45, 2);

        labelPageCount = new Label(5, 29, "Page Count");
        pageCountField = new Field(25, 28, 45, 2);

        addButton = new Button(40, 32, "Add"); 
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

        labelTitle.draw();
        titleField.draw();

        labelAuthor.draw();
        authorField.draw();

        labelPublisher.draw();
        publisherField.draw();

        labelYear.draw();
        yearField.draw();

        labelLanguage.draw();
        languageField.draw();

        labelGenre.draw();
        genreField.draw();

        labelAvailable.draw();
        availableField.draw();

        labelPageCount.draw();
        pageCountField.draw();

        addButton.draw();
    }

    private static void controlMove(Key key) {
        selected.setColor("\033[0m"); 
        if (key == Key.DOWN) {
            index = (index + 1) % 10; 
        } else if (key == Key.UP) {
            index = (index - 1 + 10) % 10;
        }

        switch (index) {
            case 0 -> selected = idField;
            case 1 -> selected = titleField;
            case 2 -> selected = authorField;
            case 3 -> selected = publisherField;
            case 4 -> selected = yearField;
            case 5 -> selected = languageField;
            case 6 -> selected = genreField;
            case 7 -> selected = availableField;
            case 8 -> selected = pageCountField;
            case 9 -> selected = addButton; 
        }

        selected.setColor("\033[36m");
        String text = (selected instanceof Field) ? ((Field) selected).getText() : "";
        draw();
        Cursor.move(selected.getX() + text.length() + 1, selected.getY() + 1);
    }

    private static Book control() {
        Cursor.move(selected.getX() + 1, selected.getY() + 1);
        Key key;

        while (true) {
            key = KeyPress.Listen();
            if (key != null) {
                if (key == Key.DOWN || key == Key.UP) {
                    controlMove(key);
                } else if (key == Key.ENTER) {
                    if (selected == addButton) {
                        return new Book(
                            idField.getText(),
                            titleField.getText(),
                            authorField.getText(),
                            publisherField.getText(),
                            yearField.getText(),
                            languageField.getText(),
                            genreField.getText(),
                            Integer.parseInt(availableField.getText()),
                            Integer.parseInt(pageCountField.getText())
                        );
                    }
                } else if (key == Key.OTHER && selected instanceof Field) {
                    ((Field) selected).enter(key);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        new BookDetailView();
        Book book = control();
        System.out.println("\nBook Added Successfully:");
        System.out.println(book);
    }
}
