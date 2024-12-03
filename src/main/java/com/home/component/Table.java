package com.home.component;

import java.util.ArrayList;
import java.util.List;

public class Table extends Box{
    private List<String> field_name;
    private List<Integer> widths;
    private List<List<String>> datas;

    public Table(){
        init();
    }
    public Table(int x, int y){
        super(x, y, 0,0);
        init();
    }
    private void init(){
        field_name = new ArrayList<>();
        datas = new ArrayList<>();
        widths = new ArrayList<>();
        widths.add(0);
    }
    public void setField_name(List<String> field_name) {
        this.field_name = field_name;
        for(int i=0; i<field_name.size(); i++){
            widths.add(field_name.get(i).length()+4);
        }
        width=getW(widths.size()-1);
        height=3;
    }
    
    public void add(List<String> data) {
        datas.add(data);
        for (int i=0; i<data.size(); i++){
            if(data.get(i).length()+2>widths.get(i+1)){
                widths.set(i+1, data.get(i).length()+2);
            }
        }
        height++;
    }

    public void clear(){
        datas.clear();
    }
   
    private int getW(int index){
        int sum=0;
        for(int i=0; i<=index;i++){
            sum+=widths.get(i);
        }
        return sum;
    }
   
    private void drawHeader(){

        Cursor.move(x, y+2);
        System.out.print("├");
        Cursor.move(x+width, y+2);
        System.out.print("┤");
        for (int i=x+1; i<x+width; i++){ 
            Cursor.move(i,y+2);
            System.out.print("─"); 
        }
        for(int i=1; i<widths.size()-1; i++){
            Cursor.move(x+getW(i), y);
            System.out.print("┬");
            Cursor.move(x+getW(i), y+2);
            System.out.print("┼");
            Cursor.move(x+getW(i), y+1);
            System.out.println("│");
        }
        for(int i=0; i<field_name.size(); i++){
            Cursor.move(x+getW(i)+(widths.get(i+1)-field_name.get(i).length())/2, y+1);
            System.out.print(field_name.get(i));
        }

    }
    
    public void drawBody(){
        for (int i=1; i<widths.size()-1; i++){
            Cursor.move(x+getW(i), y+height);
            System.out.println("┴");
            for(int j=y+3; j<y+height; j++){
                Cursor.move(x+getW(i), j);
                System.out.print("│");
            }
        }
        for (int i=0; i<datas.size();i++){
            for (int j=0; j<widths.size()-1; j++){
                Cursor.move(x+getW(j)+(widths.get(j+1)-datas.get(i).get(j).length())/2,y+i+3);
                System.out.print(datas.get(i).get(j));
            }
        }
        
    }
    
    public void draw(){
        super.draw();
        drawHeader();
        drawBody();
        Cursor.move(0, 13);
    }
    
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        Table table = new Table(2,1);
        table.setField_name(List.of("ID", "Name", "Age"));
        table.add(List.of("N", "Nha", "19"));
        table.add(List.of("M", "Miên", "19"));
        table.add(List.of("H", "Hiền", "19"));
        table.draw();
    }
    
    
}
