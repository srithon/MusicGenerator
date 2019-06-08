package thoniyil.sridaran.musicgenerator.music;

public enum Note
{
	C, C$, D, D$, E, F, F$, G, G$, A, A$, B;
	
	public int getDefaultNumber()
	{
		return ordinal() + 60;
	}
}
