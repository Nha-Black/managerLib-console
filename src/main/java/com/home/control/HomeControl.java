package com.home.control;

import com.home.component.Cursor;
import com.home.view.HomeView;

public class HomeControl {
    private HomeView view;
    public HomeControl(){
        view = new HomeView();
        // view.draw();
        control();
    }
    private void control(){
        int index;
        while (true) { 
            Cursor.clear();
            view.draw();
            index = view.control();      
            switch (index) {
                case 0:
                    new BookControl();
                    break;
                case 1:
                    new StudentControl();
                    break;
                case 2:
                    Cursor.clear();
                    new BorrowControl();
                    break;
                case 3:
                    Cursor.clear();
                    System.out.println("kêt thúc chương trình");
                    System.exit(0);
                    break;
                default:
                    break;
            }   
        }
        
    }
}
