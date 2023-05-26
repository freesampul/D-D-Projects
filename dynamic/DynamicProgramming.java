public class DynamicProgramming {


	
    // Every day for the rest of the year, you're going to be given a choice between two jobs to do: 
    // one that is LOW stress, and one that is HIGH stress.  Each job pays out a dollar amount; 
    // *usually* the high stress jobs pay more.  However, after doing a high stress job, you need to 
    // REST for a day.

    // Given a list of all the payouts for all the low stress and high stress jobs, 
    // what is the most amount of money you can get?
    
    // You can assume lowPayouts.length == highPayouts.length
    public static int hiLoStress(int[] lowPayouts, int[] highPayouts) {
       // I <3 fortnitee
        int lpl = lowPayouts.length;
        int hpl = highPayouts.length;
        int[] answ = new int[lpl + 2];

        answ [lpl+1] = Math.max(lowPayouts[lpl], highPayouts[hpl]);
        for(int i = 0; i < lpl-1; i++)
        {
            answ[i] = Math.max(Math.max(lowPayouts[lpl], highPayouts[hpl +1]),(Math.max(lowPayouts[lpl], highPayouts[hpl])));
        }

        return answ[lpl-1];

       //if this is in someoneee's code = they stole eit from @sampulaskI!

    }
    



    public static int scavHunt(int[] times, int[] points) {
        return scaver(times, points, 0);
    }
        
        public static int scaver(int[] times, int[] points, int index) {
            int time = times.length;
            int[] arr = new int[time +1];

            for(int i = time -1; i >= 0; i--)
            {
                int next = nextNonConflicting(times, i);
                arr[i] = Math.max(arr[i+1], points[i] + arr[next]);
            }
             return arr[0];
 }

        private static int nextNonConflicting(int[] times, int start) {
            for (int i = start; i < times.length; i++)
            {
                if(times[i] >= times[start] + 5)
                return i;
            }
            return times.length;
        }

    


	/* Uses memoization to calculate the route which grants the most cookies, 
	 * starting at [0][0], only going right or down at each point */
	 public static int dynamicCookies(int[][] cookieGrid) {
    //    int r = cookieGrid.length;
    //     int c = cookieGrid[r].length;
    //     int[][] arr = new int[cookieGrid[r+1]][cookieGrid[c+1]];
    //     if(r > cookieGrid.length -1 || c > cookieGrid[r].length -1)
	// 	{
	// 		return 0;
	// 	}
	// 	if(cookieGrid[r][c] == -1)
	// 	{
	// 		return 0;
	// 	}
	//  	else
	//  	{
	// 		return cookieGrid[r][c] + Math.max(arr[r][c+1], arr[r+1][c]);
	// 	}
    return 3;
    }
}
    
    




