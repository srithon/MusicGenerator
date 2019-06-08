package thoniyil.sridaran.musicgenerator.music;

import java.util.Arrays;

public class ChordProgression
{
	private Note root;
	private int[] progressionRootRelative; //relation to the root
	private ChordType[] progressionChordTypes; //type of chord
	private int currentIndex;
	
	public ChordProgression(Note root, int[] progression, ChordType[] chordTypes)
	{
		this.progressionRootRelative = Arrays.copyOf(progression, progression.length);
		this.progressionChordTypes = Arrays.copyOf(chordTypes, chordTypes.length);
		this.root = root;
		currentIndex = 0;
	}
	
	public Note getRoot()
	{
		return root;
	}
	
	public String currentNotes()
	{
		int[] notes = getNotes();
		String r = "";
		for (int i = 0; i < notes.length; i++)
		{
			r += (Note.values()[notes[i] - 60].toString() + " ");
		}
		return r;
	}
	
	public String currentChord()
	{
		return progressionChordTypes[currentIndex].toString() + progressionRootRelative[currentIndex];
	}
	
	public boolean hasNext()
	{
		return currentIndex < progressionRootRelative.length;
	}
	
	private int[] getNotes()
	{
		return getNotes(currentIndex);
	}
	
	public int[] getNextNotes()
	{
		return getNotes(currentIndex++);
	}
	
	public int[] getNotes(int progIndex)
	{
		int[] notes = new int[3];
		Note progBase = root.getInterval(progressionRootRelative[progIndex]);
		notes[0] = progBase.getNumber();
		
		switch (progressionChordTypes[progIndex])
		{
			case MAJOR:
				notes[1] = progBase.getInterval(Interval.MAJOR_THIRD).getNumber();
				notes[2] = progBase.getInterval(Interval.PERFECT_FIFTH).getNumber();
				break;
			case MINOR:
				notes[1] = progBase.getInterval(Interval.MINOR_THIRD).getNumber();
				notes[2] = progBase.getInterval(Interval.PERFECT_FIFTH).getNumber();
				break;
			case DIMINISHED:
				notes[1] = progBase.getInterval(Interval.MINOR_THIRD).getNumber();
				notes[2] = progBase.getInterval(Interval.DIMINISHED_FIFTH).getNumber();
				break;
			case AUGMENTED:
				notes[1] = progBase.getInterval(Interval.MAJOR_THIRD).getNumber();
				notes[2] = progBase.getInterval(Interval.MINOR_SIXTH).getNumber();
				break;
		}
		
		return notes;
	}
	
	public void reset()
	{
		currentIndex = 0;
	}
	
	public int getCurrentIndex()
	{
		return currentIndex;
	}
	
	public String toString()
	{
		String r = "";
		for (int i = 0; i < progressionRootRelative.length; i++)
		{
			r += progressionChordTypes[i].toString() + progressionRootRelative[i];
			if (i != progressionRootRelative.length - 1)
				r += "-";
		}
		return r;
	}
	
}
