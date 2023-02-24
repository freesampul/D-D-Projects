import java.util.ArrayList;
import java.util.Arrays;

public class Recursion {
	
	//How many subsets are there of the numbers 1...n
	// that don't contain any consecutive integers?
	public static long nonConsecutiveSubsets(int n) {
		if(n <= 1) 
			return 1;
		else
			return fibb(n-1) + fibb(n);
	}
	//A kid at the bottom of the stairs can jump up 1, 2, or 3 stairs at a time.
	//How many different ways can they jump up n stairs?
	// Jumping 1-1-2 is considered different than jumping 1-2-1
	public static long waysToJumpUpStairs(int n) {
		if(n <= 1) 
			return 1;
		else  if (n == 2) {
			return n;
		} else if (n==3) {
			return 4;
		}
			return waysToJumpUpStairs(n-1) +  waysToJumpUpStairs(n-2) +  waysToJumpUpStairs(n-3);
	}

	//Prints the value of every node in the singly linked list with the given head, but in reverse
	public static void reverseList(ListNode head) {
		String list = "";
		if(head.getNext() == null) {
			list += head.getValue();
		}
		else {
			  list += head.getValue();
			  reverseList(head.getNext());
		}
		System.out.print(""+ list + "\n");
	}
 
	//For the given 2D array of Strings, replaces the String at index[x][y]
	//with "infected" unless the String is "vaccinated";
	// also, any Strings they are orthogonally adjacent to
	//that are not "vaccinated" will also be infected, and any adjacent to
	//them as well etc.
	public static void infect(String[][] grid, int x, int y) {
		int initialX = x;
		int initialY = y;
		if(!grid[x][y].equals("infected") && !grid[x][y].equals("vaccinated")) {
			grid[x][y] = "infected";
		}
		if(y > 0)
			y--;
		if(grid[x][y] != "vaccinated" && grid[x][y] != "infected" ) {
			infect(grid, x, y);
		}
		y = initialY;
		if(y < grid[1].length -1) 
			y++;
		if(grid[x][y] != "vaccinated" && grid[x][y] != "infected" ) {
			infect(grid, x, y);
		}
		y = initialY;
		
		
		if(x > 0)
			x--;
		if(grid[x][y] != "vaccinated" && grid[x][y] != "infected" ) {
			infect(grid, x, y);
		}
		x = initialX;
		if(x < grid.length -1) 
			x++;
		if(grid[x][y] != "vaccinated" && grid[x][y] != "infected" ) {
			infect(grid, x, y);
		}
		x = initialX;
		 
	}
	
	//Prints all the permutations of str on separate lines
	//You may assume that str has no repeated characters
	//Order is your choice
	//I love bladee. bladee bladee bladee bladee bladee
	//Factorial
	public static void permute(String str) {
		ArrayList<String> permute = swap(1, str, ""+str.charAt(0), str.charAt(1));
		for(int j = 0; j < permute.size(); j++) {
			System.out.println(permute.get(j));
		}
	}
	
	
	
	//This method is a beast so don't ask me to explain it again
	
	
	//The method takes in a main string and begins moving adding in smaller points of a string one char (ch) at a time
	//The string basically changes the order of the position of the character realitve to its position in the main/orginal string
	//The method keeps getting recursively called until every position has been placed and the length of the str passed in is the same as the orgianl string, meaning every permutation has been inputed.
	public static ArrayList<String> swap(int i, String main, String str, char ch) {
		
		ArrayList<String> temp1 = new ArrayList<String>();
		ArrayList<String> temp2 = new ArrayList<String>();
		ArrayList<String> temp3 = new ArrayList<String>();
		if(str.length() == main.length()-1){
			for(int j = 0; j <= str.length(); j++) {
				String mixed = "";
				if(j == 0) {
					mixed = ch + str;
				} else if (j==str.length()) {
					mixed = str + ch;
				} else {
					mixed = "" + str.substring(0, j) + ch + str.substring(j, str.length());
				}
				temp3.add(mixed);
			}
			return temp3;
		}
		else {
			for(int k = 0; k <= str.length(); k++) {
				String mixed = "";
				if(k == 0) {
					mixed = ch + str;
				} else if (k==str.length()) {
					mixed = str + ch;
				} else {
					mixed = "" + str.substring(0, k) + ch + str.substring(k, str.length());
				}
				temp1.add(mixed);
			}
			for (int k = 0; k < temp1.size(); k++) {
				temp2 = swap(i+1, main, temp1.get(k), main.charAt(i+1));
				for(int steve = 0; steve < temp2.size(); steve++) {
					temp3.add(temp2.get(steve));
				}
			}
		}
		return temp3;
		
	}
	
	
	//Be Nice 2 Me - Bladee 
	
	
	


	//Prints all the subsets of str on separate lines
	//You may assume that str has no repeated characters
	//For example, subsets("abc") would print out "", "a", "b", "c", "ab", "ac", "bc", "abc"
	//Order is your choice
	public static void subsets(String str) {
		subsetHelper(str, "", -1);
	}
	
	
	
	//This method takes in a string and begins adding a char at a time to it. 
	//It begins with the orginal string an increments up the starting/prefix chracter in order to get each differeent subset beginning off of that one
	//As the chars grows larger, the position of the letter being adding in is increased and it matches to each letter in the string
	public static void subsetHelper(String str, String chars, int pos) {
		int length = str.length();
		if (pos == length) {
			return;
		}
		System.out.println(chars);
		for(int i = pos+1; i< length; i++) {
			chars += str.charAt(i);
			subsetHelper(str, chars, i);
			chars = chars.substring(0, chars.length()-1);
		}
		
	}
	//Performs a quickSort on the given array of ints
	//Use the middle element (index n/2) as the pivot
	public static void quickSort(int[] ints) {
		if(ints.length == 1) {
			return;
		}
		
		quickHelp(ints, 0, ints.length -1);		 
	}
	
	
	//This method is essentially all of the quicksort, it takes in array, the high value and the low value
	//The method first checks if the high and low values are the same, the base case but also testing to see if it even needs to sort
	//The method then finds the middle, and loops through swapping where things are higher or lower than the pivot
	//The method then recurses inward, moving onto a smaller half of the list because we incremented the pointers of high and low to the halves before and after the pivot
	//The method then repeats over the smaller halves, subdiving into two until the length is one
	public static void quickHelp (int[] ints, int low, int high) {
		if(low >= high)
		return;
		
		int middle = (low + high) / 2;
		int pivot = ints[middle];
		
		int left = low;
		int right = high;
		
		
		while (left <= right)
		{
			while(ints[left] < pivot) {
				left++;
			}
			while (ints[right] > pivot)
			{
				right--;
			}
		
			if(left<=right) {
					swap(ints, left, right);
					left++;
					right--;
			}
		}
	quickHelp(ints, low, right);
	quickHelp(ints, left, high);
	
	}
	
	//This is a basic swapping for array method
	//The first parameter is the array itself while the next two are indexs of the numbers wanting to be swapped
	//The method is not recrusive, and simply saves one value while setting them to eachother.
	public static void swap(int[] ints, int index1, int index2) {
		int temp = ints[index1];
		ints[index1] = ints[index2];
		ints[index2] = temp;
	}

	//Prints a sequence of moves (one on each line)
	// to complete a Towers of Hanoi problem with
	//n disks starting on tower 0, ending on tower 1.
	// The towers are number 0, 1, 2, and each move should be of
	//the form "1 -> 2", meaning "take the top disk of tower 1 and 
	//put it on tower 2" etc.
	public static void solveHanoi(int n) {
		if(n == 1) {
			System.out.println("0 -> 1");
		} else {
			hanoiSolve(n, 0, 1);
		}
	}
	
	// You are partaking in a scavenger hunt!
	// You've gotten a secret map to find many of the more difficult
	// items, but they are only available at VERY specific times at
	// specific places.  You have an array, times[], that lists at which
	// MINUTE an item is available.
	// Items in the ScavHunt are worth varying numbers of points.
	// You also have an array, points[], same length as times[],
	// that lists how many points each of the corresponding items is worth.
	// Problem is: to get from one location to the other takes 5 minutes,
	// so if there is an item, for example, available at time 23 and another
	// at time 27, it's just not possible for you to make it to both: you'll
	// have to choose!
	// (but you COULD make it from a place at time 23 to another at time 28)
	// Write a method that returns the maximum POINTS you can get.
	public static int scavHunt(int[] times, int[] points) {

		int highest = 0;
		for(int i = 0; i < times.length; i++) {
			highest = Math.max(highest, scaver(times, points, i));
		}
		return highest; 
	}
	
	
	
	//This helper method runs through each array in the index and tests the distance from it and the next index
	//If the distance is greater than 5 (it menas you can actually go to it) it runs the method again
	//Everytime a possible time value is hit, the method recurses and checks every path connected to that point
	//Once it exits, it checks what the greatest value was and if it is bigger it returns the highest points achievable
	public static int scaver(int[] times, int[] points, int index) {
		int maxPoints = 0;
		for(int i = index + 1; i < times.length; i++) {
			if(times[i] - times[index] >= 5) {
				maxPoints = Math.max(maxPoints, scaver(times, points, i));
			}
			
		}
		if(maxPoints == 0)
			return points[index];
		else
			return points[index] + maxPoints;
	}
	
	
	//This helper method is the fiibbbonaciii sequence because i thought i would need it late
	//It takes in a number to recurse on like any other fibb sequnce 
	
	public static int fibb(int i) {
		if(i <= 1) 
			return 1;
		else
			return fibb(i-2) + fibb(i-1);
	}

	
	//This is the hanoi solver helper method!
	//It takes in the parameters i, rod1, and rod2
	//The i is the number of discs that the towers have remaining
	//The first rod integer is the starting rod while the second is the end/goal road
	//The method calculates the remaining rod (3- sum of others) and if there is more than one disc,
	//The method recurses on one disc less but with the start and end rods having been moved between the remaing and passed in ones.
	public static void hanoiSolve(int i, int rod1, int rod2) {
		if (i == 1) {
			System.out.println(rod1 + " -> " + rod2);
		}
		else {
			int remainignRod = 3 - (rod1 + rod2);
			hanoiSolve(i-1, rod1, remainignRod);
			System.out.println(rod1 + " -> " + rod2);
			hanoiSolve(i-1, remainignRod, rod2);
		}
	}
	
}
