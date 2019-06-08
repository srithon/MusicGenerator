package thoniyil.sridaran.musicgenerator.music;

public class ChordFactory
{
	private ChordProgression[] progressions;
	private int currentProgression;
	
	public ChordFactory(Note root)
	{
		newProgressions(new NoteWrapper(root));
		currentProgression = 0;
	}
	
	public ChordFactory(ChordProgression[] progressions)
	{
		this.progressions = progressions;
	}
	
	private void newProgressions()
	{
		newProgressions(progressions[0].getRoot().getInterval((Math.random() > 0.5) ? 1 : -1));
	}
	
	private void newProgressions(NoteWrapper root)
	{
		System.out.println("Called newProgressions");
		progressions = new ChordProgression[3];
		for (int i = 0; i < progressions.length; i++)
		{
			progressions[i] = new ChordProgression(root,
					new int[] { 
						(int) (Math.random() * 7 + 1),
						(int) (Math.random() * 7 + 1),
						(int) (Math.random() * 7 + 1)
					},
					new ChordType[] {
						ChordType.randomType(),
						ChordType.randomType(),
						ChordType.randomType()
					});
		}
	}
	
	public ChordProgression next()
	{
		System.out.println("Current Progression: " + currentProgression);
		if (currentProgression % progressions.length == 0)
			newProgressions();
		return progressions[currentProgression++ % progressions.length];
	}
	
}
