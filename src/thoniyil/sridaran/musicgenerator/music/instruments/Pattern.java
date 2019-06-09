package thoniyil.sridaran.musicgenerator.music.instruments;

// 1 beat
public class Pattern
{
	private static final int SUBDIVISION = 8;
	
	private boolean[] pattern;
	
	public Pattern()
	{
		pattern = new boolean[SUBDIVISION];
		
		for (int i = 0; i < pattern.length; i++)
		{
			pattern[i] = Math.random() > 0.80;
		}
	}
	
	public boolean[] getPattern()
	{
		return pattern;
	}
	
	public static int getSubdivison()
	{
		return SUBDIVISION;
	}
}
