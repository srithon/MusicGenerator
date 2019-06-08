package thoniyil.sridaran.musicgenerator.music;

import java.util.Arrays;

public class Chord
{
	private ChordType type;
	private Note[] notes;
	
	public Chord(Note... notes)
	{
		this.notes = Arrays.copyOf(notes, notes.length);
	}
	
	public ChordType getChordType()
	{
		return type;
	}
	
	public Note[] getNotes()
	{
		return notes;
	}
}
