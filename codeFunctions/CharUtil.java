package codeFunctions;

public class CharUtil
	extends Basics
{
	//returns whether the given char is a digit
	public boolean isDigit(char c){
		if(c<48 || c>57)
			return false;
		else
			return true;
	}
	
	//returns whether the given char is a letter
	public boolean isLetter(char c){
		if(c>65 && c<90)
			return true;
		if(c>97 && c<122)
			return true;
		return false;
	}
	
	//returns whether the given char is upper case
	public boolean isUpperCase(char c){
		if(c>64 && c<91)
			return true;
		return false;
	}
	
	//returns whether the given char is lower case
	public boolean isLowerCase(char c){
		if(c>96 && c<123)
			return true;
		return false;
	}
	
	//makes a char in upper case
	public char toUpperCase(char c){
		if(isLowerCase(c))
			return (char)(c-32);
		return c;
	}
	
	//makes a char in lower case
	public char toLowerCase(char c){
		if(isUpperCase(c))
			return (char)(c+32);
		return c;
	}
	
	//converts digit char to digit int
	public int toI(char c){
		if(isDigit(c))
			return (int)(c-48);
		p.l("Error: Cannot convert from char to int");
		p.l("Package: personal");
		p.l("Class: Basics");
		p.n("Method: toInt(char)");
		System.exit(0);
		return 0;
	}

	//does what the compareTo string method does but with chars
	public int compare(char a,char b){
		return a-b;
	}
	
}
