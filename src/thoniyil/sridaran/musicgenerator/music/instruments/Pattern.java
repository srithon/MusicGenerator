package thoniyil.sridaran.musicgenerator.music.instruments;

// 1 beat
public class Pattern
{
	private static final int SUBDIVISION = 2;
	
	private boolean[] onOff;
	
	public Pattern()
	{
		onOff = new boolean[SUBDIVISION];
		
		for (int i = 0; i < onOff.length; i++)
		{
			onOff[i] = Math.random() > 0.80;
		}
	}
	
	public boolean[] getOnOff()
	{
		return onOff;
	}
	
	public static int getSubdivison()
	{
		return SUBDIVISION;
	}
}
