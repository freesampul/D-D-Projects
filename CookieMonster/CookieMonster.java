import java.io.File;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/* You are allowed (and expected!) to use either Java's ArrayDeque or LinkedList class to make stacks and queues,
 * and Java's PriorityQueue class to make a priority queue */

public class CookieMonster {

    private int [][] cookieGrid;
    private int numRows;
    private int numCols;
    
    //Constructs a CookieMonster from a file with format:
    //numRows numCols
    //<<rest of the grid, with spaces in between the numbers>>
    public CookieMonster(String fileName) {
		int row = 0;
		int col = 0;
		try
		{
			Scanner input = new Scanner(new File(fileName));

			numRows    = input.nextInt();  
			numCols    = input.nextInt();
			cookieGrid = new int[numRows][numCols];

			for (row = 0; row < numRows; row++) 
				for (col = 0; col < numCols; col++)
					cookieGrid[row][col] = input.nextInt();
			
			input.close();
		}
		catch (Exception e)
		{
			System.out.print("Error creating maze: " + e.toString());
			System.out.println("Error occurred at row: " + row + ", col: " + col);
		}

    }

    public CookieMonster(int [][] cookieGrid) {
        this.cookieGrid = cookieGrid;
        this.numRows    = cookieGrid.length;
        this.numCols    = cookieGrid[0].length;
    }

	/* RECURSIVELY calculates the route which grants the most cookies.
	 * Returns the maximum number of cookies attainable. */
	public int recursiveCookies() {
		return recursiveCookieHelper(0, 0);
	}	

	public int recursiveCookieHelper(int r, int c)
	{
		if(r > cookieGrid.length -1 || c > cookieGrid[r].length -1)
		{
			return 0;
		}
		if(cookieGrid[r][c] == -1)
		{
			return 0;
		}
		else
		{
			return cookieGrid[r][c] + Math.max(recursiveCookieHelper(r, c+1), recursiveCookieHelper(r+1, c));
		}
		
	}
	
	/* Calculate which route grants the most cookies using a QUEUE.
	 * Returns the maximum number of cookies attainable. */
    /* From any given position, always add the path right before adding the path down */
    public int queueCookies() {
    	ArrayDeque<OrphanScout> orphans = new ArrayDeque<OrphanScout>();
    	if (cookieGrid[0][0] == -1) {
    		return 0;
    	}
    	int r =0;
    	int c = 0;
    	int total = 0;
    	OrphanScout harry = new OrphanScout(r, c, cookieGrid[r][c]);
    	orphans.add(harry);
    	while(!orphans.isEmpty()) {
    		OrphanScout starter = orphans.remove();
    		r = starter.getEndingRow();
    		c = starter.getEndingCol();
    		
    		if(isSafe(r+1, c))
    		{
    			orphans.add(new OrphanScout(r+1, c, starter.getCookiesDiscovered()+cookieGrid[r+1][c]));
    		}
    		if(isSafe(r, c+1))
    		{
    			orphans.add(new OrphanScout(r, c+1, starter.getCookiesDiscovered()+cookieGrid[r][c+1]));
    		}
    		if (!isSafe(r+1, c) && !isSafe(r, c+1))
    		{
    			total = Math.max(total, starter.getCookiesDiscovered());
    		}
    	}
    	return total;
    		
    }
    /* Calculate which route grants the most cookies using a stack.
 	 * Returns the maximum number of cookies attainable. */
    /* From any given position, always add the path right before adding the path down */
    public int stackCookies() {
    	Stack<OrphanScout> orphans = new Stack<OrphanScout>();
    	if (cookieGrid[0][0] == -1) {
    		return 0;
    	}
    	int r =0;
    	int c = 0;
    	int total = 0;
    	OrphanScout harry = new OrphanScout(r, c, cookieGrid[r][c]);
    	orphans.push(harry);
    	while(!orphans.isEmpty()) {
    		OrphanScout starter = orphans.pop();
    		r = starter.getEndingRow();
    		c = starter.getEndingCol();
    		
    		if(isSafe(r+1, c))
    		{
    			orphans.push(new OrphanScout(r+1, c, starter.getCookiesDiscovered()+cookieGrid[r+1][c]));
    		}
    		if(isSafe(r, c+1))
    		{
    			orphans.push(new OrphanScout(r, c+1, starter.getCookiesDiscovered()+cookieGrid[r][c+1]));
    		}
    		if (!isSafe(r+1, c) && !isSafe(r, c+1))
    		{
    			total = Math.max(total, starter.getCookiesDiscovered());
    		}
    	}
    	return total;
    		
    }

    /* Calculate which route grants the most cookies using a priority queue.
	 * Returns the maximum number of cookies attainable. */
    /* From any given position, always add the path right before adding the path down */
    public int pqCookies() {
    	PriorityQueue<OrphanScout> orphans = new PriorityQueue<OrphanScout>();
    	if (cookieGrid[0][0] == -1) {
    		return 0;
    	}
    	int r =0;
    	int c = 0;
    	int total = 0;
    	OrphanScout harry = new OrphanScout(r, c, cookieGrid[r][c]);
    	orphans.add(harry);
    	while(!orphans.isEmpty()) {
    		OrphanScout starter = orphans.remove();
    		r = starter.getEndingRow();
    		c = starter.getEndingCol();
    		
    		if(isSafe(r+1, c))
    		{
    			orphans.add(new OrphanScout(r+1, c, starter.getCookiesDiscovered()+cookieGrid[r+1][c]));
    		}
    		if(isSafe(r, c+1))
    		{
    			orphans.add(new OrphanScout(r, c+1, starter.getCookiesDiscovered()+cookieGrid[r][c+1]));
    		}
    		if (!isSafe(r+1, c) && !isSafe(r, c+1))
    		{
    			total = Math.max(total, starter.getCookiesDiscovered());
    		}
    	}
    	return total;
    		
    }

    private boolean isSafe(int r, int c)
    {
    	if( r > cookieGrid.length -1 || c > cookieGrid[r].length -1 || cookieGrid[r][c] == -1)
    	{
    		return false;
    	}
    	return true;
    }
    
}
