package thoniyil.sridaran.musicgenerator.music;

public class NoteWrapper
{
	private int pitch;
	
	public NoteWrapper(Note n)
	{
		pitch = n.getDefaultNumber();
	}
	
	public NoteWrapper(int pitch)
	{
		this.pitch = pitch;
	}
	
	public NoteWrapper getInterval(Interval i)
	{
		return getInterval(i.getSemiTones());
	}
	
	public NoteWrapper getInterval(Interval i, int multiplier)
	{
		return getInterval(i.getSemiTones() * multiplier);
	}
	
	public NoteWrapper getInterval(int semiTones)
	{
		return new NoteWrapper(pitch + semiTones);
	}
	
	public int getNumber()
	{
		return pitch;
	}
}
