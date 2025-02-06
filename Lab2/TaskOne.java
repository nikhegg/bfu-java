package Lab2;

import java.util.Scanner;

public class TaskOne {
    public static String getSubstring(String line) {
        String result = "";
        for (int i = 0; i < line.length(); i++) {
            StringBuilder buff = new StringBuilder();
            for(int j = i; j < line.length(); j++) {
                char letter = line.charAt(j);
                if(buff.indexOf(String.valueOf(letter)) == -1) buff.append(letter);
                else break;
            }
            if(buff.length() > result.length()) result = buff.toString();
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку для поиска наибольшей подстроки");
        String result = getSubstring(scanner.next());
        System.out.println(result);
        scanner.close();
    }
}
