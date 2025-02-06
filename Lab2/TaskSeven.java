package Lab2;

public class TaskSeven {
    public static int[] get2DArrayMaxes(int[][] matrix) {
        int[] maxes = new int[matrix.length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] > maxes[i]) maxes[i] = matrix[i][j];
            }
        }
        return maxes;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1,3,5},
            {6,2,4},
            {9,8,3}
        };
        int[] maximums = get2DArrayMaxes(matrix);
        for(int i = 0; i < maximums.length; i++) {
            System.out.print(maximums[i] + " ");
        }
    }
}
