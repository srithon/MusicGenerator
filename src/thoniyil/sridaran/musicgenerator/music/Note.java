package thoniyil.sridaran.musicgenerator.music;

public enum Note
{
	C, C$, D, D$, E, F, F$, G, G$, A, A$, B;
	
	public Note getInterval(Interval i)
	{
		return getInterval(i.getSemiTones());
	}
	
	public Note getInterval(int semiTones)
	{
		return Note.values()[(ordinal() + semiTones) % Note.values().length];
	}
	
	public int getNumber()
	{
		return ordinal() + 60;
	}
}
