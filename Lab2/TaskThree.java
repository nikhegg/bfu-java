package Lab2;

public class TaskThree {
    public static int getMaxArraySum(int[] arr) {
        int maxSum = 0;
        int currentSum = 0;

        for(int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            if (currentSum > maxSum) maxSum = currentSum;
            if (currentSum < 0) currentSum = 0;
        }
        return maxSum;
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 2, -4, 3, 5, 10, -2};
        int sum = getMaxArraySum(arr);
        System.out.println("Результат: " + sum);
    }
}
