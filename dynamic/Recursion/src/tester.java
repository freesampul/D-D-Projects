public class tester extends Recursion
{

public static void main (String [] args) {
	
	//int[] nums = new int[] {6, 2, 5, 4 ,1};
	int[] nums = new int[] {3, 7, 18, 2, 1, 20, 5, 8, 16, 0};
	quickSort(nums);
	for(int i = 0; i < nums.length; i++)
	{
		System.out.println(nums[i]);
	}
	
}



}