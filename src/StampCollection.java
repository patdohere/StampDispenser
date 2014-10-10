import java.util.HashMap;
import java.util.Iterator;

/**
 * Facilitates collection of stamps in the stamp dispenser.
 */
public class StampCollection {
	
	// Reason we use a HashMap is for O(1) lookup time, and O(1) insertion time.
	// Allowing us to do input validation to check for duplicate entries and invalid entrys.
	// Also by using a HashMap style data structure it mimics SQL database functions
	// allowing us to eventually integrate our app into a backend.
	
	// However because we are using a hashmap it reduces the calculation time for
	// calculations of denominations so we made a method to generate an order list
	// of denominations.
	private HashMap<Integer, Stamp> collection;
	private Integer size;
	
	/**
     * Constructs a new Stamps that will store all the different kinds of
     * denominations of stamps.
     */
	public StampCollection()
	{
		collection = new HashMap<Integer, Stamp>();
		size = 1;
		
		// We need to add a single cent to the collection 
		// because there always needs to be a single cent
		Stamp temp = new Stamp(1);
		collection.put(HashFunction(temp), temp);
	}
	
	/**
	 * @param stamp The value of the stamp to add to our collection of stamps
	 * @return if the addition of the stamp succeeded (true) or failed (false)
	 */
	public boolean addStamp(Stamp stamp)
	{
		// We check if the stamp is already in the collection
		// if not we add the stamp to the collection
		// else we return that it already exist
		// Also automatically let stamp of 
		if (collection.get(HashFunction(stamp)) == null  || stamp.getValue() == 1)
		{
			if (stamp.getValue() != 1)
			{
				collection.put(HashFunction(stamp), stamp);
				size++;
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 
	 * @return The list of denominations in our collections in ascending order
	 */
	public int[] getDenominations()
	{
		// we create an array to store the denominations
		int[] denominations = new int[size];
		
		// we then retrieve all the stamps from the collection
		Iterator<Stamp> itr = collection.values().iterator();
		for (int i = 0; itr.hasNext(); i++)
		{
			denominations[i] = itr.next().getValue();
		}
		
		// Now we sort all the stamps in the collection
		RadixSort radixsort = new RadixSort();
		return radixsort.sort(denominations);
	}

	/**
     * Private function to map the value of the stamp to a key for our collection
     *
     * @param value The value of the stamp that we will use to compute the key
     */
	private int HashFunction(Stamp stamp)
	{
		return stamp.getValue();
	}
}
