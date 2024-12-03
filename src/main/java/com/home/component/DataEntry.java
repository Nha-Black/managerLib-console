package com.home.component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.home.models.Student;
import com.home.util.DataProcess;

public class DataEntry<T> extends Box{
    private List<Field> fields;
    private List<String> labels;
    private List<String> properties;
    private Button enter;
    private int maxWidthLabel;
    private int maxWidthField;
    private int index;
    // private Box selected;
    private Class<T> type;

    public DataEntry(int x, int y, Class<T> type) {
        super(x, y, 50,4);
        this.type = type;
        init();
    }
    
    public DataEntry(int x, int y){
        super(x, y, 50, 4);
        this.type = null;
        init();
    }

    public void init(){
        Cursor.expose();
        maxWidthLabel= 0;
        maxWidthField= 0;
        index=0;
        fields = new ArrayList<>();
        labels = new ArrayList<>();
        properties = new ArrayList<>();
        enter = new Button(x+(width-4)/2, y+height-3, "nhập");
        // selected = new Field();
    }
    
    private int getH(){
        return 1+fields.size()*3;
    }
    
    public void add(String label, String property){
        if(properties.indexOf(property)!=-1) return;
        properties.add(property);
        if(label.length()>maxWidthLabel) maxWidthLabel= label.length();
        maxWidthField=width-5-maxWidthLabel;
        
        labels.add(label);
        fields.add(new Field(x+maxWidthLabel+4, y+getH(), maxWidthField, 2));
        height +=3;
        enter.setX(x+(width-4)/2);
        enter.setY(y+height-3);
        for(Field field : fields){
            field.setX(x+maxWidthLabel+4);
            field.setWidth(maxWidthField);
        }
    }
    
    public void add(String label, String property, String text){
        properties.add(property);
        if(label.length()>maxWidthLabel) maxWidthLabel= label.length();
        maxWidthField=width-5-maxWidthLabel;
        
        labels.add(label);
        Field f = new Field(x+maxWidthLabel+4, y+getH(), maxWidthField, 2);
        f.setText(text);
        fields.add(f);
        height +=3;
        enter.setX(x+(width-4)/2);
        enter.setY(y+height-3);
        for(Field field : fields){
            field.setX(x+maxWidthLabel+4);
            field.setWidth(maxWidthField);
        }
    }

    private void controlMove(Key key){
        if(index==fields.size()) {
            enter.setSelected(Color.RESET);
            enter.draw();
        }
        else {
            fields.get(index).setSelected(Color.RESET);
            fields.get(index).draw();
        }
        if(key== Key.UP) index = (index+fields.size())%(fields.size()+1);
        else if(key== Key.DOWN) index= (index+1)%(fields.size()+1);
        if(index==fields.size()){
            enter.setSelected(Color.CYAN);
            enter.draw();
            Cursor.hidden();
        }
        else{
            fields.get(index).setSelected(Color.CYAN);
            fields.get(index).draw();
            Cursor.expose();
            Cursor.move(fields.get(index).getX()+fields.get(index).getText().length()+1, fields.get(index).getY()+1);
        }
    }

    public T getclass(){
        try {
            T object = type.getDeclaredConstructor().newInstance(); 
    
            // Sử dụng Reflection để đặt giá trị cho các thuộc tính
            for (int i = 0; i < fields.size(); i++) {
                String propertyName = properties.get(i); // Tên thuộc tính
                String value = fields.get(i).getText(); // Giá trị nhập từ Field (String)
    
                // Lấy setter tương ứng
                String setterName = "set" + capitalize(propertyName);
                for (var method : type.getMethods()) {
                    if (method.getName().equals(setterName) && method.getParameterCount() == 1) {
                        Class<?> parameterType = method.getParameterTypes()[0]; // Lấy kiểu dữ liệu của setter
    
                        Object convertedValue = convertValue(value, parameterType);
    
                        // Gọi setter với giá trị đã chuyển đổi
                        method.invoke(object, convertedValue);
                        break;
                    }
                }
            }
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }
   
    private Object convertValue(String value, Class<?> targetType) {
        if (targetType == String.class) {
            return value; 
        } else if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value);
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(value);
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (targetType == long.class || targetType == Long.class) {
            return Long.parseLong(value);
        } else if (targetType == float.class || targetType == Float.class) {
            return Float.parseFloat(value);
        } else if (targetType == short.class || targetType == Short.class) {
            return Short.parseShort(value);
        } else if (targetType == byte.class || targetType == Byte.class) {
            return Byte.parseByte(value);
        } else if(targetType == LocalDate.class){
            return DataProcess.strToDate(value);
        } else {
            throw new IllegalArgumentException("Unsupported type: " + targetType.getName());
        }
    }
   
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
   
    public void control(){
        if(fields.size()<1) return;
        fields.get(index).setSelected(Color.CYAN);
        // selected = fields.get(index);
        draw();
        Cursor.move(fields.get(index).getX()+1, fields.get(index).getY()+1);
        Key key;
        while (true) { 
            key = KeyPress.Listen();
            if(key!=null){
                if(key== Key.UP || key== Key.DOWN){
                    controlMove(key);
                }else if(key==Key.ENTER){
                    if(index!=fields.size()){
                        controlMove(Key.DOWN);
                    }else{
                        enter.setSelected(Color.RESET);
                        Cursor.clear();
                        Cursor.expose();
                        return;
                    }
                }else if(key==Key.OTHER){ 
                    if(index!=fields.size()){
                        fields.get(index).enter(key);
                    }
                }
            }
            
        }
    }

    @Override
    public void draw() {
        Cursor.topleft();
        for(int i=0; i<fields.size(); i++){
            Cursor.move(x+1, fields.get(i).getY()+1);
            System.out.print(labels.get(i));
            fields.get(i).draw();
        }
        enter.draw();
        super.draw();
    }
    
    public void setButton(String text){
        enter.setText(text);
        enter.setX(x+(width-4)/2);
    }
    
    public String getData(){
        if (fields.size()>1) return "";
        return fields.get(0).getText().trim();
    }
    public void setText(String property, String text){
        int index = properties.indexOf(property);
        if(index==-1) return;
        this.index= index;
        fields.get(index).setText(text);
    }
    public void setText(String property, String text, String status){
            int index = properties.indexOf(property);
            if(index==-1) return;
            this.index= index;
            if(status.equals("ERROR")){
                fields.get(index).setTextColor(Color.RED);
            }
            fields.get(index).setText(text);

    }
    public static void main(String[] args) {
        Cursor.clear();
        DataEntry<Student> entry = new DataEntry<>(2, 2, Student.class);
        entry.add("ID","id");
        entry.add("tên","name");
        entry.add("giới tính","gender");
        entry.add("số đt","phone");
        entry.add("nghành học", "department");
        entry.add("email", "email");
        entry.add("ngày sinh", "birthDay");
        // entry.draw();
        entry.control();
        System.out.println(entry.getclass());
    }

    
}
