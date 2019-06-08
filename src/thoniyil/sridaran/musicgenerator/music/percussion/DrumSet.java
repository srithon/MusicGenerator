package thoniyil.sridaran.musicgenerator.music.percussion;

import javax.sound.midi.MidiChannel;

public class DrumSet
{
	private MidiChannel channel;
	
	public DrumSet(MidiChannel channel)
	{
		this.channel = channel;
	}
	
	public void play(Pattern pattern, int bpm)
	{
		new Thread(() -> playMeasure(pattern, bpm)).start();
	}
	
	private void playMeasure(Pattern pattern, int bpm)
	{
		for (boolean b : pattern.getPattern())
		{
			if (b)
				channel.noteOn(60, (int) (Math.random() * 71 + 30));
			else
				channel.allNotesOff();
			
			try
			{ // <subdivision> subdivisions
				Thread.sleep((int) (60.0 / bpm / Pattern.getSubdivison() * 1000));
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		channel.allNotesOff();
	}
}
