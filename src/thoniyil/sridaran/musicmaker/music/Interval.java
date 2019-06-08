package thoniyil.sridaran.musicmaker.music;

public enum Interval
{
	MINOR_SECOND, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH,
	DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH;
	
	public int getSemiTones()
	{
		return ordinal() + 1;
	}
}
