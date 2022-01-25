package codeFunctions;

import java.util.Scanner;

//ease of reading input
public class Reader extends Basics{
	
	//MVP OG scanner FTW

	Scanner scan=new Scanner(System.in);
	
	//last variables
	String lastT;
	String lastS;
	int lastI;
	double lastD;
	char lastC;
	boolean lastB;

	//with prompt
	
	public String s(String s){
		p.n(s);
		lastT="String";
		lastS=scan.nextLine();
		return lastS;
	}
	
	public int i(String s){
		p.n(s);
		lastT="int";
		lastI=scan.nextInt();
		return lastI;
	}
	
	public double d(String s){
		p.n(s);
		lastT="double";
		lastD=scan.nextDouble();
		return lastD;
	}
	
	public char c(String s){
		p.n(s);
		lastT="char";
		String str=scan.nextLine();
		if(str.length()==1) {
			lastC=str.charAt(0);
			return lastC;
		}else{
			p.l("Error: char expected");
			p.l("\tpackage: personal");
			p.l("\tclass: Reader");
			p.l("\tline: 53");
			System.exit(0);
		}
		return '0';
	}

	public boolean b(String s){
		p.n(s);
		lastT="boolean";
		lastB=scan.nextBoolean();
		return lastB;
	}
	
	public String w(String s){
		p.n(s);
		lastT="String";
		lastS=scan.next();
		return lastS;
	}
	
	//without prompt
	
	public String s(){
		lastT="String";
		lastS=scan.nextLine();
		return lastS;
	}
	
	public int i(){
		lastT="int";
		lastI=scan.nextInt();
		return lastI;
	}
	
	public double d(){
		lastT="double";
		lastD=scan.nextDouble();
		return lastD;
	}
	
	public char c(){
		lastT="char";
		String str=scan.nextLine();
		if(str.length()==1) {
			lastC=str.charAt(0);
			return lastC;
		}else{
			p.l("Error: char expected");
			p.l("\tpackage: personal");
			p.l("\tclass: Reader");
			p.l("\tline: 96");
			System.exit(0);
		}
		return '0';
	}

	public boolean b(){
		lastT="boolean";
		lastB=scan.nextBoolean();
		return lastB;
	}

	public String w(){
		lastT="String";
		lastS=scan.next();
		return lastS;
	}
	
	//wait until enter is pressed
	
	public void standby(){
		scan.nextLine();
	}
	
}



































