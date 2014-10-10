/**
 * Facilitates dispensing stamps for a postage stamp machine.
 */
public class StampDispenser
{
	private StampCollection stamps; 
    /**
     * Constructs a new StampDispenser that will be able to dispense the given 
     * types of stamps.
     *
     * @param stampDenominations The values of the types of stamps that the 
     *     machine should have.  Should be sorted in descending order and 
     *     contain at least a 1.
     * @throws InputInvalidException 
     */
    public StampDispenser(int[] stampDenominations) throws InputInvalidException
    {
    	// First is to do input validation:
    	// Check for 0 and 1
    	// Check for Negative Values
    	// Check for Duplicate Values
    	// Check for Non-Integers (Don't need check this as datatype (int) is enforced in Java)
    	stamps = new StampCollection();    	
    	
    	for(int value : stampDenominations)
    	{
    		if (value < 0)
    		{
    			throw new InputInvalidException("Array contains a value less than 0");
    		} 
    		
    		// We add the stamps and if it fails we throw an exception that we found a duplicate
    		if (!stamps.addStamp(new Stamp(value)))
			{
				throw new InputInvalidException("Array contains a duplicate denomination of " + value);	
    		}
    	}
    }
 
    /**
     * Returns the minimum number of stamps that the machine can dispense to
     * fill the given request.  This is a dynamic programming problem.  Seems
     * like 0-1 Knapsack.  Currently I have a greedy method.
     *
     * @param request The total value of the stamps to be dispensed.
     */
    public int calcMinNumStampsToFillRequest(int request)
    {  
    	if (request == 0)
    		return 0;
    	else if (request == 1)
    		return 1;
    	else
    		// Modular programming you can switch between the two methods to calculate stamps.
    		return dynamicMethod(request);
    }
    
    /**
     * GreedyMethod goes from Largest stamp to Smallest
     * O(denominations)
     * 
     * @param request The total value of the stamps to be dispense
     * @return Minimum number of stamps
     */
    private int greedyMethod(int request)
    {
    	int balance = request;
    	int count = 0;
    	
    	int[] denominations = stamps.getDenominations();
    	
    	for (int i = denominations.length - 1; i > 0 ; i--)
    	{
    		while (balance >= denominations[i])
    		{
    			balance -= denominations[i];
    			count++;
    		}
    	}
    		
        return count;
    }
    
    /**
     * Dynamic Programming Problem
     * Class: Knapsack Problem
     * Problem: Coin Change Problem
     * http://www.ccs.neu.edu/home/jaa/CS7800.12F/Information/Handouts/dyn_prog.pdf
     * 
     * O(denominations * 
     */
    private int dynamicMethod(int request)
    {
    	int n = request;
    	int[] d = stamps.getDenominations();
    	int k = d.length;
    	
    	int[] C = new int[n+1];
    	int[] S = new int[n+1];
    	int coin = 0;
    	
    	C[0] = 0;
    	
    	for (int p = 1; p <= n; p++)
    	{
    		int min = Integer.MAX_VALUE;
    		for (int i = 0; i < k; i++)
    		{
    			if (d[i] <= p)
    			{
    				if (1+C[p-d[i]] < min)
    				{
    					min = 1+C[p-d[i]];
    					coin = i;
    				}
    			}		
    		}
    		C[p] = min;
    		S[p] = coin;
    	}
    	
    	return C[request];
    }
}
