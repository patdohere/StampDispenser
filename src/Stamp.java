/**
 * Facilitates different stamps.  By making a class we can then also store different
 * attributes for each stamp in the future such as Country and Currency.
 * Allowing us to to international postage calculations.
 */
public class Stamp {
	int value;
	
	public Stamp(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
}
