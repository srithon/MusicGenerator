package thoniyil.sridaran.musicgenerator.music;

public enum ChordType
{
	MAJOR(3), MINOR(3), DIMINISHED(3), AUGMENTED(3), JAZZ1(5), RANDOM(3), MAJOR7(4), MINOR7(4), DOM7(4);
	
	private int numNotes;
	
	private ChordType(int n)
	{
		numNotes = n;
	}
	
	public int getNumNotes()
	{
		return numNotes;
	}
	
	public static ChordType randomType()
	{
		return ChordType.values()[(int) (Math.random() * ChordType.values().length)];
	}
}

/*

Major Chord: Root, Major Third, Perfect Fifth

Minor Chord: Root, Minor Third, Perfect Fifth

Diminished Chord: Root, Minor Third, Tritone (6 half steps)

Augmented Chord: Root, Major Third, Minor Sixth

*/