/**
 * Main Stamp Dispenser program
 * Run-Time O(denominations * request) which is very slow using DynProg
 * Run-Time O(denominations) using greedy approach
 * @author Patrick Do
 *
 */
public class StampProgram {

	public static void main(String[] args)
    {
        int[] denominations = { 90, 30, 24, 10, 6, 2, 1 };
        StampDispenser stampDispenser;
		try {
			stampDispenser = new StampDispenser(denominations);
			
	        assert stampDispenser.calcMinNumStampsToFillRequest(18) == 3;
	        assert stampDispenser.calcMinNumStampsToFillRequest(34) == 2;
		} catch (InputInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
