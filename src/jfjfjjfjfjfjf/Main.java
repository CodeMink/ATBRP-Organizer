package jfjfjjfjfjfjf;

import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import character.Charries;
public class Main {

	public static void main(String[] args) throws IOException {
		
		Frame frame = new Frame();
		frame.setBounds(200, 200, 500, 500);
		
//		System.out.println(b);
		
		ArrayList<Charries> chars = Constr(new File("MasterList_Test.txt"));
		
		String[] names = new String[chars.size()];
		for(int i = 0; i < chars.size(); i++)
		{
			names[i] = chars.get(i).getName();
		}
		
		String a = (String) JOptionPane.showInputDialog(frame, "WHAt", "/???", -1, null, names, "Herpicar Derpicus");
		
		System.exit(0);
	}
	
	//start menu implementation
	public static int startMenu()
	{
		return 0;
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
