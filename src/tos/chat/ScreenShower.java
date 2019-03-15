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
        while(true) {
            try {
                int i = Integer.parseInt(readString());
                return i;
            } catch (NumberFormatException e) {
                writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            }
        }
    }

}
