package character;

import java.io.File;
/*
 * Extended File class
 * 
 * Mostly just because I needed a usable compare method
 */
public class CharFile extends File{
	
	private String title;
	
	public CharFile(String t, String arg1) {
		super(arg1);
		title = t;
		// TODO Auto-generated constructor stub
	}
	
	public boolean equals(CharFile b)
	{
		if (title.compareToIgnoreCase(b.getTitle()) == 0)
			return true;
		
		return false;
	}
	
	public String getTitle()
	{
		return title;
	}

}
