package thoniyil.sridaran.musicgenerator.music.instruments;

import java.util.Stack;

import javax.sound.midi.MidiChannel;

public class DrumSet
{
	private MidiChannel channel;
	private Stack<Pattern> patterns;
	private Thread t;
	private int bpm;
	
	public DrumSet(MidiChannel channel, int bpm)
	{
		this.channel = channel;
		this.bpm = bpm;
		patterns = new Stack<>();
		t = new Thread(this::playCont);
		t.start();
	}
	
	public void play(Pattern pattern)
	{
		patterns.push(pattern);
	}
	
	private void playCont()
	{
		while (true)
		{
			while (patterns.isEmpty());
			for (boolean b : patterns.pop().getPattern())
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
}
