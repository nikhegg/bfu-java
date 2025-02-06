package Lab2;

public class TaskTwo {
    public static int[] concatSortedArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];

        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if(arr1[i] < arr2[j]) result[k++] = arr1[i++];
            else result[k++] = arr2[j++];
        }
        // Если вдруг один массив оказался длиннее другого, то нужно "добежать"
        while(i < arr1.length) {
            result[k++] = arr1[i++];
        }
        while(j < arr2.length) {
            result[k++] = arr2[j++];
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[] arr1 = {10, 12, 432, 843};
        int[] arr2 = {2, 11, 74, 96, 594};
        int[] result = concatSortedArrays(arr1, arr2);

        for(int l = 0; l < result.length; l++) {
            System.out.print(result[l] + " ");
        }
    }
}
