package character;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  ORIGINAL PRGRM DO NUT STEEL
 * cREATIVE cOMMONS 16.92D53
 * GNU PG OPEN SOURCE ROTYALTY FREE COPYLEFT FREEWARE
 * PRAISE STALLMAN
 * 
 * Note: "Charries" refers to a character in a TRPG environment. It was decided against using "Character"
 * as a class name since it would override the actual Character class
 * 
 * Charries class contains all the information and associated files paths of an individual character
 * 
 * @author JDAWG
 *
 */
public class Charries {
	
	private String name, gender, race, setting;
	private int age;
	private ArrayList<File> actives = new ArrayList<File>();
	private ArrayList<File> archive = new ArrayList<File>();
	private File codex = null, appl = null;
	
	public Charries(String n)
	{
		name = n;
	}
	
	public Charries(String n, String g, int a, String r, String s)
	{
		name = n;
		gender = g;
		age = a;
		race = r;
		setting = s;
	}
	
	public Charries(String n, String g, int a, String r, String s, File c, File ap)
	{
		this(n, g, a, r, s);
		
		codex = c;
		appl = ap;
	}
	
	public void addActiveThread(File t)
	{
		actives.add(t);
	}
	
	public void removeActiveThread(File t)
	{
		for(int i = 0; i < actives.size(); i++)
		{
			if(actives.get(i).equals(t))
				actives.remove(i);
		}
	}
	
	public void removeArchiveThread(File t)
	{
		for(int i = 0; i < archive.size(); i++)
			if(archive.get(i).equals(t))
				archive.remove(i);
	}
	
	//finds and links all associated character files and information to this object
	//creates the files if there aren't any, so that new characters can work with this shite
	public void linkAll() throws IOException
	{
		Scanner s;
		
		//basic info scan
		File info = new File (name + "\\inf.txt");
		s = new Scanner(info);
		
		gender = s.nextLine();
		age = Integer.parseInt(s.nextLine());
		race = s.nextLine();
		setting = s.nextLine();
		
		
		//links codex and app
		codex = new File(name + "\\codex.txt");
		if(!codex.exists())
			codex.createNewFile();
		appl = new File(name + "\\app.txt");
		if(!appl.exists())
			appl.createNewFile();

		//links actives and archives
		new File(name + "\\actives").mkdir();
		info = new File(name + "\\actives\\list.txt");
		if(!info.exists())
			info.createNewFile();
		s = new Scanner(info);
		
		while(s.hasNextLine())
		{
			actives.add(new File(name + "\\actives\\" + s.nextLine()));
		}
		
		new File(name + "\\archive").mkdir();
		info = new File(name + "\\archive\\list.txt");
		if(!info.exists())
			info.createNewFile();
		s = new Scanner(info);
		
		while(s.hasNextLine())
		{
			archive.add(new File(name + "\\archive\\" + s.nextLine()));
		}
	}
	
	public void addArchiveThread(File t)
	{
		archive.add(t);
	}
	
	public int getAge()
	{
		return age;
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getRace()
	{
		return race;
	}
	
	public File getCodex()
	{
		return codex;
	}
	
	public File getApp()
	{
		return appl;
	}
	
	public String getSet()
	{
		return setting;
	}
	
	public ArrayList<File> getActives()
	{
		return actives;
	}
	
	public ArrayList<File> getArchive()
	{
		return archive;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setAge(int a)
	{
		age = a;
	}
	
	public void setGender(String g)
	{
		gender = g;
	}
	
	public void setCodex(File c)
	{
		codex = c;
	}
	
	public void setApp(File a)
	{
		appl = a;
	}

}
