package codeFunctions;

import java.io.*;
import java.util.*;

//created May 2019

public class FileEditor
	extends Basics
{
	private PrintWriter pw;
	private ArrayList<String>file=new ArrayList<String>();
	private Scanner scan;
	private String address;
	
	public FileEditor(String s) 
		throws IOException
	{
		address=s;
		scan=new Scanner(new File(address));
		while(scan.hasNextLine())
			file.add(scan.nextLine());
		pw=new PrintWriter(s);
		this.save();
	}
	
	public FileEditor(FileEditor fe)
	{
		address=fe.address;
		pw=fe.pw;
		scan=fe.scan;
		while(scan.hasNextLine())
			file.add(scan.nextLine());
	}
	
//reading
	
	public String getLine(int lineNum)
	{
		if(file.size()>lineNum)
			return file.get(lineNum);
		System.err.println
			("Error: line "+lineNum+" not found in file "+getFileName());
		System.exit(0);
		return "";
	}
	
	public String getFileName()
	{
		String s=address;
		if(s.indexOf("/")>=0)
			for(int i=s.length()-1;i>=0;i--)
			{
				if((s.charAt(i)+"").equals("/"))
					return s.substring(i+1);
			}
		
		else if(s.indexOf("\\")>=0)
			for(int i=s.length()-1;i>=0;i--)
			{
				if((s.charAt(i)+s.charAt(i-1)+"").equals("\\"))
					return s.substring(i+2);
			}
		return s;
	}
	
	public ArrayList<String> getArrayList()
	{
		return this.file;
	}

	public int[] find(String s)
	{
		for(int i=0;i<file.size();i++)
			if(file.get(i).indexOf(s)>=0)
				return new int[]{i,file.get(i).indexOf(s)};
		return new int[]{-1};
	}
	
	public String find(int line,int index,int length)
	{
		return getLine(line).substring(index,index+length);
	}

	public int lines()
	{
		return file.size();
	}
	
	public void printFile()
	{
		for(String s:file)
			p.l(s);
	}
	
//writing
	
	public void replace(int line, String nu)
		throws IOException
	{
		file.set(line,nu);
		save();
	}
	
	public void switchLines(int l1, int l2)
		throws IOException
	{
		String s=file.get(l1);
		file.set(l1,file.get(l2));
		file.set(l2,s);
		save();
	}

	public void add(String s)
		throws IOException
	{
		file.add(s);
		save();
	}

	public void add(int n, String s)
		throws IOException
	{
		file.add(n,s);
		save();
	}

	public void addBlankLines(int index,int num)
		throws IOException
	{
		for(int i=0;i<num;i++)
			add(index,"");
	}
	
	public void addBlankLines(int num)
		throws IOException
	{
		for(int i=0;i<num;i++)
			add("");
	}

	public void save()
		throws IOException
	{
		pw=new PrintWriter(address);
		pw.flush();
		for(int i=0;i<file.size();i++)
			if(i<file.size()-1)
				pw.write(file.get(i)+"\n");
			else
				pw.write(file.get(i));
		pw.flush();
		file=new ArrayList<String>();
		scan=new Scanner(new File(address));
		while(scan.hasNextLine())
			file.add(scan.nextLine());
	}
	
	public void remove(int line)
		throws IOException
	{
		file.remove(line);
		save();
	}
	
	public void remove(int from, int to)
		throws IOException
	{
		for(int i=to;i>=from;i--)
			file.remove(i);
		save();
	}
	
	public void setTo(ArrayList<String>arr)
		throws IOException
	{
		file=new ArrayList<String>();
		for(String s:arr)
			file.add(s);
		save();
	}
	
	public void clear()
		throws IOException
	{		
		file=new ArrayList<String>();
		pw=new PrintWriter(address);
		pw.flush();
	}
}



























