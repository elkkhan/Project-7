package tos.chat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ScreenShower {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() {
        while (true) {

            try {
                String str = reader.readLine();
                return str;
            } catch (IOException e) {
                writeMessage("Some error occured.Try it once more!");
            }

        }
    }
    public static int readInt() {
       String num = readString();
       while(!isAllDigits(num)){
           writeMessage("All characters must be digits! Try once more:");
           num = readString();
       }
       int number = Integer.parseInt(num);
       return number;
    }

    public static boolean isAllDigits(String str)
    {
        int size = str.length();

        if(str==null) return false;

        for(int i=0;i<size;i++){
            if(!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

}
