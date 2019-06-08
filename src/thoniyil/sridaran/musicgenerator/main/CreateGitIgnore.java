package thoniyil.sridaran.musicgenerator.main;

import java.io.File;
import java.io.IOException;

public class CreateGitIgnore
{
	public static void main(String[] args) throws IOException
	{
		File f = new File(".gitignore");
		f.createNewFile();
	}
}
