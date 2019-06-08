package thoniyil.sridaran.musicgenerator.music;

import java.util.Arrays;

public class ChordProgression
{
	private NoteWrapper root;
	private int[] progressionRootRelative; //relation to the root
	private ChordType[] progressionChordTypes; //type of chord
	private int currentIndex;
	
	public ChordProgression(NoteWrapper root, int[] progression, ChordType[] chordTypes)
	{
		this.progressionRootRelative = Arrays.copyOf(progression, progression.length);
		this.progressionChordTypes = Arrays.copyOf(chordTypes, chordTypes.length);
		this.root = root;
		currentIndex = -1;
	}
	
	public int[] getBassNotes()
	{
		NoteWrapper base = root.getInterval(progressionRootRelative[currentIndex]).getInterval(Interval.PERFECT_OCTAVE, -1);
		
		switch (progressionChordTypes[currentIndex])
		{
			case MAJOR:
			case MINOR:
			case DIMINISHED:
			case AUGMENTED:
				return getNotes(base, currentIndex);
			case JAZZ1:
				int[] bass = new int[4];
				base = base.getInterval(Interval.MINOR_THIRD, -1);
				bass[0] = base.getNumber();
				bass[1] = base.getInterval(Interval.MAJOR_THIRD).getNumber();
				bass[2] = base.getInterval(Interval.PERFECT_FIFTH).getNumber();
				bass[3] = base.getInterval(Interval.MINOR_SEVENTH).getNumber();
				return bass;
			default:
				int[] bassJ2 = new int[3];
				base = base.getInterval(Interval.MINOR_THIRD, -1);
				bassJ2[0] = base.getNumber();
				bassJ2[1] = base.getInterval(Interval.PERFECT_FOURTH).getNumber();
				bassJ2[2] = base.getInterval(Interval.MINOR_SEVENTH).getNumber();
				return bassJ2;
		}
	}
	
	public NoteWrapper getRoot()
	{
		return root;
	}
	
	public String currentNotes()
	{
		int[] notes = getNotes();
		String r = "";
		for (int i = 0; i < notes.length; i++)
		{
			// if C is 60
			r += (notes[i] + " ");
		}
		return r;
	}
	
	public String currentChord()
	{
		return progressionRootRelative[currentIndex] + progressionChordTypes[currentIndex].toString();
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
	
	public int[] getNotes(NoteWrapper progBase, int progIndex)
	{
		int[] notes = new int[progressionChordTypes[progIndex].getNumNotes()];
		
		notes[0] = progBase.getNumber();
		
		switch (progressionChordTypes[progIndex])
		{
			case MAJOR7:
				notes[3] = progBase.getInterval(Interval.MAJOR_SEVENTH).getNumber();
			case DOM7:
				if (progressionChordTypes[progIndex].equals(ChordType.DOM7))
					notes[3] = progBase.getInterval(Interval.MINOR_SEVENTH).getNumber();
			case MAJOR:
				notes[1] = progBase.getInterval(Interval.MAJOR_THIRD).getNumber();
				notes[2] = progBase.getInterval(Interval.PERFECT_FIFTH).getNumber();
				break;
			case MINOR7:
				notes[3] = progBase.getInterval(Interval.MINOR_SEVENTH).getNumber();
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
			case RANDOM:
				NoteWrapper f = progBase.getInterval((int) (Math.random() * 3) + 2);
				notes[1] = f.getNumber();
				notes[2] = f.getInterval((int) (Math.random() * 3) + 2).getNumber();
				break;
		}
		
		return notes;
	}
	
	public int[] getNotes(int progIndex)
	{
		NoteWrapper progBase = root.getInterval(progressionRootRelative[progIndex]);
		return getNotes(progBase, progIndex);
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
