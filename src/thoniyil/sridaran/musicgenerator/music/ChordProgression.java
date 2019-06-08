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
		int[] notes = null;
		if (progressionChordTypes[progIndex].equals(ChordType.JAZZ1))
			notes = new int[5];
		else if (progressionChordTypes[progIndex].equals(ChordType.JAZZ2))
			notes = new int[7];
		else
			notes = new int[3];
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
			case JAZZ1:
				notes[1] = progBase.getInterval(Interval.MINOR_THIRD).getNumber();
				notes[2] = progBase.getInterval(Interval.PERFECT_FOURTH).getNumber();
				notes[3] = progBase.getInterval((int) (Math.random() * 2) + Interval.PERFECT_FIFTH.getSemiTones()).getNumber();
				notes[4] = progBase.getInterval(Interval.MINOR_SEVENTH).getNumber();
				break;
			case JAZZ2:
				notes[1] = progBase.getInterval(Interval.MINOR_THIRD).getNumber();
				notes[2] = progBase.getInterval(Interval.PERFECT_FOURTH).getNumber();
				notes[3] = progBase.getInterval(Interval.MINOR_SIXTH.getSemiTones()).getNumber();
				notes[4] = progBase.getInterval(Interval.MINOR_SEVENTH).getNumber();
				notes[5] = progBase.getInterval(Interval.MINOR_NINTH).getNumber();
				notes[6] = progBase.getInterval(Interval.MAJOR_ELEVENTH).getNumber();
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
