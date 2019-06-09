package thoniyil.sridaran.musicgenerator.music.instruments;

import javax.sound.midi.MidiChannel;

import thoniyil.sridaran.musicgenerator.music.NoteWrapper;

public class Bass
{
	private MidiChannel channel;
	
	public Bass(MidiChannel channel)
	{
		this.channel = channel;
	}
	
	public void playPitch(int pitch)
	{
		channel.noteOn(pitch, (int) (Math.random() * 40 + 60));
	}
	
	public void playChord(int[] pitch, int modifier)
	{
		channel.allNotesOff();
		for (int p : pitch)
			playPitch(p + modifier);
	}
}
