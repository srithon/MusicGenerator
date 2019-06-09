package thoniyil.sridaran.musicgenerator.music.instruments;

import javax.sound.midi.MidiChannel;

public class Piano extends AutonomousInstrument
{
	private volatile boolean newChord;
	private int[] notes;
	private Object lock;
	
	public Piano(MidiChannel channel)
	{
		super(channel);
		lock = new Object();
	}
	
	public void playChord(int[] notes)
	{
		synchronized (lock)
		{
			this.notes = notes;
			newChord = true;
		}
	}
	
	protected void playCont()
	{
		while (true)
		{
			while (!newChord);
			synchronized (lock)
			{
				for (int i = 0; i < notes.length; i++)
				{
					if (Math.random() > 0.80)
						try
						{
							Thread.sleep(50);	
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();	
						}
					//System.out.println("Note On");
					getChannel().noteOn(notes[i], (int) (Math.random() * 70 + 30));
				}
			}
			newChord = false;
		}
	}
}
