package thoniyil.sridaran.musicgenerator.main;

import java.util.Arrays;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import thoniyil.sridaran.musicgenerator.music.ChordFactory;
import thoniyil.sridaran.musicgenerator.music.ChordProgression;
import thoniyil.sridaran.musicgenerator.music.Interval;
import thoniyil.sridaran.musicgenerator.music.Note;
import thoniyil.sridaran.musicgenerator.music.instruments.Bass;
import thoniyil.sridaran.musicgenerator.music.instruments.DrumSet;
import thoniyil.sridaran.musicgenerator.music.instruments.Pattern;
import thoniyil.sridaran.musicgenerator.music.instruments.Piano;

public class Main {
	public static void main(String[] args)
	{
		improvise(60);
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
			
			System.out.println(Arrays.toString(instr));

			midiSynth.loadInstrument(instr[0]);//load an instrument
			midiSynth.loadInstrument(instr[118]); //118: synth drum 114: steel drums
			midiSynth.loadInstrument(instr[34]);
			
			mChannels[1].programChange(instr[118].getPatch().getProgram());
			mChannels[2].programChange(instr[34].getPatch().getProgram());
			
			Piano piano = new Piano(mChannels[0]);
			DrumSet ds = new DrumSet(mChannels[1], bpm);
			Bass bass = new Bass(mChannels[2]);
			
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
					
					//int[] bass = currentProgression.getBassNotes();
					int[] notes = currentProgression.getNextNotes();
					
					mChannels[0].allNotesOff();
					mChannels[1].allNotesOff();
					
					piano.playChord(notes);
					ds.play(new Pattern(notes[0]));
					bass.playChord(notes, -Interval.PERFECT_OCTAVE.getSemiTones());
					
					//for (int i = 0; i < bass.length; i++)
					//	mChannels[1].noteOn(bass[i], (int) (Math.random() * 70 + 30));
					
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
