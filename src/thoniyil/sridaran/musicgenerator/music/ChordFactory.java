package thoniyil.sridaran.musicgenerator.music;

//TODO make endless
public class ChordFactory
{
	private ChordProgression[] progressions;
	private int currentProgression;
	
	public ChordFactory(Note root)
	{
		newProgressions(root);
		currentProgression = 0;
	}
	
	public ChordFactory(ChordProgression[] progressions)
	{
		this.progressions = progressions;
	}
	
	private void newProgressions()
	{
		boolean error = true;
		System.out.println("Called newProgressions");
		while (error)
			try
			{
				newProgressions(progressions[0].getRoot().getInterval((Math.random() > 0.5) ? 1 : -1));
				error = false;
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				
			}
	}
	
	private void newProgressions(Note root)
	{
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
		if (currentProgression % progressions.length == 0)
			newProgressions();
		return progressions[currentProgression++ % progressions.length];
	}
	
}
