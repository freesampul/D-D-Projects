import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayUtils {
    
    /* countOdds
     * This method should return the number of odd integers in the array.
     * Odd integers are not divisible by 2.
     * 0 is an even integer.
     */
    public static int countOdds(int[] nums) {
        int count = 0;
        for(int i= 0; i <  nums.length; i++)  {
            if((nums[i] % 2 == 1 || nums[i] %2 == -1) && nums[i] != 0)
            {
                count++;
            }
        }
        return count;
    }
    
    
    /* onDiagonal
     * This method takes a 2D array of integers, and a single integer parameter.
     * It returns true if and only if the value of the parameter lies on a diagonal of the
     * 2D array.
     * 
     * Diagonals are illustrated here (X is on diagonal, . is not):
     * 
     * X.X  X..X  X...X
     * .X.  .XX.  .X.X.  etc.
     * X.X  .XX.  ..X..
     *      X..X  .X.X.
     *            X...X
     *            
     * 2D arrays that are not square do not have diagonals, and should always return false.
     */
    public static boolean onDiagonal(int[][] matrix, int test) {
        int dia1 = 0;
        int dia2 = matrix[0].length - 1;
         for(int r = 0; r < matrix.length; r++)
         {
            if (matrix[r].length != matrix.length) {
                return false;
            }
         }

         for(int r = 0; r < matrix.length; r++)
         {
                if (matrix[r][dia1] == test || matrix[r][dia2] == test)
                {
                    return true;
                }

                dia1++;
                dia2--;
         }

         return false;
    }
    
    /* addElements
     * This method takes two 1D integer arrays: list and increment.
     * The list array is mutated such that the ith element of list will be incremented by the ith
     * element of increment.
     * 
     * e.g.             list -> (0, 0, 1, 1,  2,  2)
     *             increment -> (4, 5, 6, 7,  8,  9)
     * after returning, list -> (4, 5, 7, 8, 10, 11)
     * 
     * If increment is not as long as list, treat unspecified values as 0. s
     * 
     * e.g.             list -> (0, 0, 1, 1, 2, 2)
     *             increment -> (4, 5, 6, 7)
     * after returning, list -> (4, 5, 7, 8, 2, 2)
     * 
     * If increment has more elements than list, ignore the extra elements.
     */
    public static void addElements(int[] list, int[] increment) {
        for(int i = 0; i < list.length; i++)
        {
            if(increment.length -1 < i)
            {
                list[0] =  list[0];
            }
            else {
                list[i] = list[i] + increment[i];
            }
        }
    }
    
    /* embiggen
     * Merges two 1D integer arrays into a single 2D integer array.
     * The [i][j] element of the resulting array is whichever value is larger: array1[i] or array2[j].
     */
    public static int[][] embiggen(int[] array1, int[] array2) {
        int[][] bigArray = new int[array1.length][array2.length];
        for (int i = 0; i < array1.length; i++)
        {
            for(int j = 0; j < array2.length; j++) {
                    bigArray[i][j] = Math.max(array1[i], array2[j]);
        }
    }
        return bigArray;
    }

    
    /* getMountainPeak
     * A "mountain array" is
     * an array for which there is an index i < array.length such that
     * the array is strictly increasing before index i, and
     * decreasing after index i.
     * Index i would be the max, the "peak".
     * If the array is a mountain array, the index of the peak is returned.
     * If the array is NOT a mountain array, -1 is returned!
     */
    public static int getPeakIndex(int[] array) {
       int i = 0, j = array.length -1;

       while(i < array.length -1 && array[i+1] > array[i])
       {
        i++;
       }
       while(j > i && array[j] < array[j-1])
       {
        j--;
       }
       if(j == i)
       return j;
       else
       return -1;
    }

}