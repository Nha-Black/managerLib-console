package com.home.component;
import com.sun.jna.Library;
import com.sun.jna.Native;

public class KeyPress {

    public interface CLibrary extends Library {
        CLibrary INSTANCE = Native.load("msvcrt", CLibrary.class);

        int _kbhit();  
        int _getch();  
    }
    public static Key Listen(){
        if (CLibrary.INSTANCE._kbhit() != 0){
            int code = CLibrary.INSTANCE._getch();
            if(code!=224){
                return Key.fromCode(code);
            } else{
                int specialCode = CLibrary.INSTANCE._getch();
                return Key.fromCode(specialCode);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Press a key (ESC to exit):");
        while (true) {
            Key key = Listen();
            if (key != null) {
                if(key==Key.OTHER) System.out.println("Key pressed " + key+": " + (char)key.getCode());
                else System.out.println("Key pressed: " + key);
                if (key == Key.ESC) {
                    System.out.println("Exiting...");
                    break;
                }
            }
        }
    }
}
