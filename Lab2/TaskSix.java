package Lab2;

public class TaskSix {
    public static int get2DArraySum(int[][] matrix) {
        int sum = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1,3,5},
            {6,2,4},
            {9,8,3}
        };
        int sum = get2DArraySum(matrix);
        System.out.println(sum);
    }
}
