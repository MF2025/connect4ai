package connect4;

//solution class for connect 4
import java.util.*;

public class Solution
{
	public int[]rows;
	public int[]cols;
	
	public Solution(int[]r, int[]c)
	{
		rows=r;
		cols=c;
	}
	
	public String toString()
	{
		return Arrays.toString(rows)
		+"\n"+Arrays.toString(cols);
	}
}
