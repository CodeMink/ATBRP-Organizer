package jfjfjjfjfjfjf;

import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import character.Charries;
public class Main {

	public static void main(String[] args) throws IOException {
		
		Frame frame = new Frame();
		frame.setBounds(200, 200, 500, 500);
		
//		String[] chars = {"Herpicar Derpicus", "Doop de durr", "Whoopee Goldbegr"};
//		int b;
//		//String a = (String) JOptionPane.showInputDialog(frame, "WHAt", "/???", -1, null, chars, "Herpicar Derpicus");
//		
//		b = JOptionPane.showConfirmDialog(frame, "FUCK Y");
//		
//		System.out.println(b);
//		
//		System.exit(0);
		
		ArrayList<Charries> chars = testConstr();
		
//		ArrayList<Charries> chars = realConstr();
		
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
	public static ArrayList<Charries> realConstr()
	{
		
		
		return null;
	}
	
	//makes a test list of characters
	/*
	 * uses "MasterList_test.txt" so that I have a buncha charries to work with yeh know
	 */
	public static ArrayList<Charries> testConstr() throws FileNotFoundException
	{
		ArrayList<Charries> derp = new ArrayList<Charries>();
		
		File master = new File("MasterList_Test.txt");
		Scanner scan = new Scanner(master);
		
		while(scan.hasNext())
		{
			String n = scan.nextLine();
			String g = "", r = "", s = "";
			int a = -1;
			File inf = new File(n + "\\inf.txt");
			
			Scanner read = new Scanner(inf);
			
			while(read.hasNext())
			{
				g = read.nextLine();
				a = read.nextInt();
				r = read.nextLine();
				s = read.nextLine();
			}
			
			derp.add(new Charries(n, g, a, r, s));
		}
		return null;
	}

}
