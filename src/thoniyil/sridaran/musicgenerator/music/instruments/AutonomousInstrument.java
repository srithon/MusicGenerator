package thoniyil.sridaran.musicgenerator.music.instruments;

import javax.sound.midi.MidiChannel;

public abstract class AutonomousInstrument
{
	private Thread t;
	private MidiChannel channel;
	
	protected AutonomousInstrument(MidiChannel c)
	{
		this.channel = c;
		t = new Thread(this::playCont);
		t.start();
	}
	
	protected MidiChannel getChannel()
	{
		return channel;
	}
	
	protected abstract void playCont();
}
