package com.home.view;

import com.home.component.Box;
import com.home.component.Button;
import com.home.component.Color;
import com.home.component.Cursor;
import com.home.component.Field;
import com.home.component.Key;
import com.home.component.KeyPress;
import com.home.component.Label;
import com.home.models.Account;

public class LoginView{
    private String user;
    private String password;
    private static Box selected;
    private static int index;
    private static Field passField;
    private static Field userField;
    private static Label label;
    private static Label label1;
    private static Label label2;
    private static Button but;
    public LoginView(){
        init();
        Cursor.clear();
        draw();
    }
    private void init(){
        label = new Label(25,2,"Login");
        label1 = new Label(5,5, "username");
        label2 = new Label(5, 8, "password");
        userField = new Field(16, 4, 45, 2);
        // userField.setColor("\033[36m");
        passField = new Field(16, 7, 45, 2);
        but = new Button(30, 10, "Login");
        but.setTextColor("\033[36m");
        index=0;
        selected= userField;
        selected.setColor("\033[36m");
    }
    
    private static void draw(){
        System.out.print("\033[H");
        
        userField.draw();
        passField.draw();
        but.draw();
        label.draw();
        label1.draw();
        label2.draw();
    }
    private static void controlMove(Key key){
        selected.setColor("\033[0m");//đặt màu về mặt định
        if(key==Key.DOWN){         
            index= (index+1) % 3;
        } else if(key==Key.UP){
            index = (index-1+3) % 3;
        }
        //index có giá trị từ 0 ->3 đại diện cho 3 thành phần bên dưới
        switch (index) {
                    case 0:
                        selected = userField;
                        break;
                    case 1:
                        selected = passField;
                        break;
                    case 2:
                        selected = but;
                        break;
                    default:
                        break;
                }
        selected.setColor("\033[36m");      
        String text = selected == userField ? userField.getText() : passField.getText();   
        draw();
        Cursor.move(selected.getX()+text.length()+1, selected.getY()+1);
    }
    
    public Account control(){
        Cursor.move(selected.getX()+1, selected.getY()+1);
        Key key;
        while(true){
            key=KeyPress.Listen();
            if(key!=null){ 
                if(key==Key.DOWN || key==Key.UP){
                    controlMove(key);
                }
                else if(key==Key.ENTER){
                    if (selected==but){
                        return new Account(userField.getText(), passField.getText());
                    }
                    break;
                } else if(key==Key.OTHER){   
                    if (selected == userField) {
                        userField.enter(key);
                    } else if (selected == passField) {
                        passField.enter(key);
                    }
                }  
            }
        }
        return null;
    }
    
    public void mess(String mess){
        userField.setTextColor(Color.RED);
        userField.setText(mess);
        userField.drawText();
        userField.setTextColor(Color.RESET);
    }
    public static void main(String[] args) {
        // try {
        //     new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        Cursor.clear();
        LoginView view = new LoginView();
        view.control();
    }
}