package thoniyil.sridaran.musicgenerator.main;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import thoniyil.sridaran.musicgenerator.music.ChordFactory;
import thoniyil.sridaran.musicgenerator.music.ChordProgression;
import thoniyil.sridaran.musicgenerator.music.Note;

public class Main {
	public static void main(String[] args)
	{
		improvise(30);
	}

	public static void improvise(int bpm)
	{
		//https://stackoverflow.com/questions/16462854/midi-beginner-need-to-play-one-note
		try
		{
			Synthesizer midiSynth = MidiSystem.getSynthesizer(); 
			midiSynth.open();

			//get and load default instrument and channel lists
			Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
			MidiChannel[] mChannels = midiSynth.getChannels();

			midiSynth.loadInstrument(instr[0]);//load an instrument
			
			ChordFactory factory = new ChordFactory(Note.C);
			
			while (true)
			{
				ChordProgression currentProgression = factory.next();
				System.out.println(currentProgression);
				currentProgression.reset();
				
				while (currentProgression.hasNext())
				{
					System.out.println(currentProgression.currentChord());
					System.out.println(currentProgression.currentNotes());
					int[] notes = currentProgression.getNextNotes();
					
					for (int i = 0; i < notes.length; i++)
					{
						mChannels[i].allNotesOff();
						mChannels[i].noteOn(notes[i], (int) (Math.random() * 70 + 30));
					}
					
					try
					{
						Thread.sleep((int) (60.0 / bpm * 1000));
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
						break;
					}
				}
			}
		}
		catch (MidiUnavailableException e)
		{
			e.printStackTrace();
		}
	}
}
