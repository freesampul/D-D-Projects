public class DynamicProgramming {


	
    // Every day for the rest of the year, you're going to be given a choice between two jobs to do: 
    // one that is LOW stress, and one that is HIGH stress.  Each job pays out a dollar amount; 
    // *usually* the high stress jobs pay more.  However, after doing a high stress job, you need to 
    // REST for a day.

    // Given a list of all the payouts for all the low stress and high stress jobs, 
    // what is the most amount of money you can get?
    
    // You can assume lowPayouts.length == highPayouts.length
    public static int hiLoStress(int[] lowPayouts, int[] highPayouts) {
        int length = lowPayouts.length;
    
        if (length == 0) {
            return 0;
        }
    
        int[] dp = new int[length];
        dp[0] = Math.max(lowPayouts[0], highPayouts[0]);
    
        for (int i = 1; i < length; i++) {
            if (i == 1) {
                dp[i] = Math.max(dp[i - 1], lowPayouts[i]);
            } else {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + lowPayouts[i]);
            }
            dp[i] = Math.max(dp[i], dp[i - 1] + highPayouts[i]);
        }
    
        return dp[length - 1];
       //this is so fortnite 
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
        int[][] dynamicCookie = new int[cookieGrid.length][cookieGrid[0].length];

        for(int i = 0; i < dynamicCookie.length; i++)
        {
            for(int k = 0; k < dynamicCookie[0].length; k++)
            {
                dynamicCookie[i][k] = -1;
            }
        }

        for (int i = dynamicCookie.length -1; i >= 0; i--)
        {

            for (int k = dynamicCookie[0].length -1; k >= 0; k--)
            {
                if(cookieGrid[i][k] == -1)
                dynamicCookie[i][k]=0;
                else
                {
                    int right = 0; 
                    if (k + 1 < dynamicCookie[0].length) 
                    right = dynamicCookie[i][k + 1];
                    int down = 0;
                    if(i + 1 < dynamicCookie.length) 
                    down = dynamicCookie[i + 1][k];
                    dynamicCookie[i][k] = cookieGrid[i][k] + Math.max(right, down);
                }
            }

        }
        return dynamicCookie[0][0];
    }
}
    
    




