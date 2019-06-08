package thoniyil.sridaran.musicgenerator.music;

public enum ChordType
{
	MAJOR, MINOR, DIMINISHED, AUGMENTED, JAZZ;
	
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