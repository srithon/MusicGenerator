package thoniyil.sridaran.musicgenerator.music.instruments;

// 1 beat
public class Pattern
{
	private static final int SUBDIVISION = 8;
	
	private boolean[] onOff;
	
	private int pitch;
	
	public Pattern(int rootNotePitch)
	{
		onOff = new boolean[SUBDIVISION];
		
		for (int i = 0; i < onOff.length; i++)
		{
			onOff[i] = Math.random() > 0.80;
		}
		
		this.pitch = rootNotePitch;
	}
	
	public boolean[] getOnOff()
	{
		return onOff;
	}
	
	public static int getSubdivison()
	{
		return SUBDIVISION;
	}
	
	public int getPitch()
	{
		return pitch;
	}
}
