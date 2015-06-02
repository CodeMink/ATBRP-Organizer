package jfjfjjfjfjfjf;

import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import character.Charries;
public class Main {
	
	private static Frame frame = new Frame();
	private static ArrayList<Charries> chars;
	private static String curCharName;
	private static Charries curChar;
	
	//file containing list of all character iMPORTANT!!
	//remember to change it to real non-test file after		
	private static File master = new File("MasterList_Test.txt");
	
	public static void main(String[] args) throws IOException {
		
		frame.setBounds(200, 200, 500, 500);
		
		if(!master.exists())
			master.createNewFile();
		
		//master list is important IMPORTANT IMPORTANT
		chars = Constr(master);
		
		//"NEW CHARACTER" -> if selected, leads to character creation screen
		chars.add(new Charries("NEW CHARACTER"));
		
		startMenu();
		
		System.exit(0);
	}
	
	//it's the start menu, what more is there to say?
	//lists characters, can make new ones
	public static void startMenu() throws IOException
	{
		
		String[] names = new String[chars.size()];
		for(int i = 0; i < chars.size(); i++)
		{
			names[i] = chars.get(i).getName();
		}
		
		String a = (String) JOptionPane.showInputDialog(frame, "WHAt", "/???", -1, null, names, "Herpicar Derpicus");
		
		if(a.equals("NEW CHARACTER"))
			a = charCreate();
		
		curCharName = a;
		curChar = new Charries (a);
		curChar.linkAll();
		listMenu();
	}
	
	//IMPLEMENT THISSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
	//returns name of new character
	//write down age, name, gender etc. and list it in the inf.txt file
	public static String charCreate() throws IOException
	{
		//text optionpanes
		//so disgust
		String name = (String) JOptionPane.showInputDialog(frame, "WHAt", "/???", -1, null, null, "Enter Name of Character");
		String age = (String) JOptionPane.showInputDialog(frame, "WHAt", "/???", -1, null, null, "Enter Age of Character IN NUMBERS");
		String gender = (String) JOptionPane.showInputDialog(frame, "WHAt", "/???", -1, null, null, "Enter Gender of Character");
		String race = (String) JOptionPane.showInputDialog(frame, "WHAt", "/???", -1, null, null, "Enter Race of character (elf, human, etc. you idiot, I'm not racial profiling)");
		String setting = (String) JOptionPane.showInputDialog(frame, "WHAt", "/???", -1, null, null, "Enter Setting your character is in");
		
		new File(name).mkdir();
		File info = new File(name + "\\inf.txt");
		if(!info.exists())
			info.createNewFile();
		
		PrintWriter p = new PrintWriter(info);
		p.println(gender);
		p.println(age);
		p.println(race);
		p.println(setting);
		p.flush();
		
		//Add character to masterlist
		//THERE IS WHERE YOU LEFT OFF YOU DIDN'T T]EST IT YET
		FileWriter f = new FileWriter(master, true);
		f.append("\n" + name);
		f.close();
		
		return name;
	}
	
	//lists options
	public static String listMenu() throws IOException
	{
		String[] options = {"Actives", "Codex", "Application", "Archive", "MAIN MENU", "EXIT"};
		
		String choice = (String) JOptionPane.showInputDialog(frame, "NO", "NO", -1, null, options, "");
		
		switch(choice)
		{
			case "Actives": Runtime.getRuntime().exec("notepad " + activesMenu(true)); break;
			case "Codex": Runtime.getRuntime().exec("notepad " + curChar.getCodex().getAbsolutePath()); break;
			case "Application": Runtime.getRuntime().exec("notepad " + curChar.getApp().getAbsolutePath()); break;
			case "Archive": Runtime.getRuntime().exec("notepad " + activesMenu(false)); break;
			case "MAIN MENU": startMenu(); break;
			default: System.exit(0); break;
			
		}
		
		return "";
	}
	
	//dropdown menu of all active or archive threads
	//boolean: true = actives list, false = archive list
	//choosing one leads to text document for thread, yes?
	public static String activesMenu(boolean menuChoice) throws IOException
	{
		ArrayList<File> files;
		
		if(menuChoice)
			files = curChar.getActives();
		else
			files = curChar.getArchive();
		
		
		String[] threads = new String[files.size() + 3];
		
		for(int i = 0; i < files.size(); i++)
		{
			threads[i] = files.get(i).getName();
		}
		
		int con = files.size();
		
		threads[con] = "MAIN MENU";
		threads[con + 1] = "FILES MENU";
		threads[con + 2] = "EXIT";
		
		String choice = (String) JOptionPane.showInputDialog(frame, "NO", "NO", -1, null, threads, "");
		
		for(int i = 0; i < files.size(); i++)
		{
			if(threads[i].equals(choice))
				return files.get(i).getAbsolutePath();
		}
		
		//implement menu options here
		switch(choice)
		{
			case "MAIN MENU": startMenu(); break;
			case "FILES MENU": listMenu(); break;
			case "EXIT": System.exit(0); break;
			default: System.out.println("u w0t m8"); break;
		}
		
		
		return "WHAT IS GOING ON";
	}
	
	//actual character file reading
	/*
	 * Aight gotta write down me thoughts
	 * So the constructor reads from "MasterList.txt"
	 * MasterList contains all the character names, thus the master list
	 * each character name has a separate corresponding txt file with their information in it
	 * and the constructor reads that information and passes it into a Charries class in the ArrayList
	 * 
	 * inf order: name, gender, age, race, setting (NGARS)
	 * 
	 * got it mate?
	 */
	public static ArrayList<Charries> Constr(File master) throws IOException
	{
		ArrayList<Charries> derp = new ArrayList<Charries>();
		Scanner scan = new Scanner(master);
		
		while(scan.hasNext())
		{
			String n = scan.nextLine();
			String g = "", r = "", s = "";
			int a = -1;
			File inf = new File(n + "\\inf.txt");
			Charries c;
			
			Scanner read = new Scanner(inf);
			
			while(read.hasNext())
			{
				g = read.nextLine();
				a = Integer.parseInt(read.nextLine());
				r = read.nextLine();
				s = read.nextLine();
			}
			
			c = new Charries(n, g, a, r, s);
			
			File actL = new File (n + "\\actives\\list.txt");
			if(!actL.exists())
				actL.createNewFile();
			else
			{
				read = new Scanner(actL);
				while(read.hasNext())
				{
					c.addActiveThread(new File(n + "\\actives\\" + read.nextLine() + ".txt"));
				}
			}
			
			File arcL = new File (n + "\\archive\\list.txt");
			if(!arcL.exists())
				arcL.createNewFile();
			else
			{
				read = new Scanner(arcL);
				while(read.hasNext())
				{
					c.addArchiveThread(new File(n + "\\archive\\" + read.nextLine() + ".txt"));
				}
			}
			
			File cod = new File(n + "\\codex.txt");
			File appl = new File(n + "\\application.txt");
			
			if(cod.exists())
				c.setCodex(cod);
			if(!appl.exists())
				c.setApp(appl);
			
			derp.add(c);
		}
		return derp;
	}

}
