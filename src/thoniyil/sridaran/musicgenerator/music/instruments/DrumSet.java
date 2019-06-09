package thoniyil.sridaran.musicgenerator.music.instruments;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

import javax.sound.midi.MidiChannel;

public class DrumSet extends AutonomousInstrument
{
	private Deque<Pattern> patterns;
	private int bpm;
	
	public DrumSet(MidiChannel channel, int bpm)
	{
		super(channel);
		this.bpm = bpm;
		patterns = new ArrayDeque<>();
		// used to be Stack, but stack is synchronized
		// Deque is unsynchronized
	}
	
	public void play(Pattern pattern)
	{
		patterns.push(pattern);
	}
	
	protected void playCont()
	{
		while (patterns == null);
		while (true)
		{
			while (patterns.isEmpty());
			for (boolean b : patterns.pop().getOnOff())
			{
				long start = System.nanoTime();
				if (b)
				{
					getChannel().noteOn(60, (int) (Math.random() * 71 + 30));
					//System.out.println("hit");
				}
				else
					getChannel().allNotesOff();
				
				try
				{ // <subdivision> subdivisions
					Thread.sleep((int) (60.0 / bpm / Pattern.getSubdivison() * 1000) -
							((System.nanoTime() - start) / 1_000_000));
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
			getChannel().allNotesOff();
		}
	}
}
