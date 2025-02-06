package Lab2;

import java.util.Scanner;

public class TaskFive {
    public static int[] getTargetPair(int[] arr, int target) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = i; j < arr.length; j++) {
                if(i == j) continue;
                if(arr[i] + arr[j] == target) {
                    int[] result = {arr[i], arr[j]};
                    return result;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {10, 93, 20, 1, 3, 50, 4, -1, 2};
        Scanner scanner = new Scanner(System.in);
        int[] result = getTargetPair(arr, scanner.nextInt());
        if(result != null) for(int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        } else {
            System.out.println("Пара не найдена");
        }
        scanner.close();
    }
}
