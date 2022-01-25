package codeFunctions;

public class StringParser extends Basics{
	
	Printer p=new Printer();
	
	//booleans

	//if String is an int
	public boolean isI(String s){
		if(s.length()==0)
			return false;
		else
			for(int i=0;i<s.length();i++){
				if(s.charAt(i)<48 || s.charAt(i)>57)
					return false;
			}
		return true;
	}

	//if String is a double (excluding scientific notation)
	public boolean isD(String s){
		int decimals=0;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='.')
				decimals++;
		}
		for(int i=0;i<s.length();i++){
			if(!(cu.isDigit(s.charAt(i)) || s.charAt(i)=='.'))
				return false;	
		}
		if(decimals==0 || decimals==1)
			return true;
		return false;
	}

	//if String is a char
	public boolean isC(String s){
		if(s.length()!=1)
			return false;
		return true;
	}

	//if String is a boolean
	public boolean isB(String s){
		if(s.equals("true") || s.equals("false"))
			return true;
		return false;
	}

	//conversions

	//converts String to int
	public int toI(String s){
		/*if(!isI(s)){
			p.l("Error: Cannot convert from String to primitive type int");
			p.l("Package: personal");
			p.l("Class: StringParser");
			p.n("Method: toI(String)");
			System.exit(0);
		}
		int integer=0;
		int place=0;
		for(int activ=s.length()-1;activ>=0;activ--){
			integer+=toInt(s.charAt(activ))*pow(10,place);
			place++;
		}
		return integer;*/
		return Integer.parseInt(s);
	}

	//converts String to double
	public double toD(String s){
		/*if(!isD(s)){
			p.l("Error: Cannot convert from String to primitive type double");
			p.l("Package: personal");
			p.l("Class: StringParser");
			p.n("Method: toD(String)");
			System.exit(0);
		}
		int decPlaces=0;
		for(int activ=s.length()-1;activ>=0;activ--){
			if(s.charAt(activ)=='.'){
				decPlaces=s.length()-activ-1;
			}
		}
		double decimal=0;
		int place=-decPlaces;
		for(int activ=s.length()-1;activ>=0;activ--){
			if(s.charAt(activ)!='.'){
				decimal+=toInt(s.charAt(activ))*pow(10,place);
				place++;
			}
		}
		decimal*=pow(10,decPlaces);
		decimal=(int)decimal;
		decimal/=pow(10,decPlaces);
		return decimal;*/
		return Double.parseDouble(s);
	}

	//converts String to char
	public char toC(String s){
		if(!isC(s)){
			p.l("Error: Cannot convert from String to primitive type char");
			p.l("Package: personal");
			p.l("Class: StringParser");
			p.n("Method: toC(String)");
			System.exit(0);
		}
		return s.charAt(0);
	}

	//converts String to boolean
	public boolean toB(String s){
		if(s.equals("true"))
			return true;
		if(s.equals("false"))
			return false;
		p.l("Error: Cannot convert from String to primitive type boolean");
		p.l("Package: personal");
		p.l("Class: StringParser");
		p.n("Method: toB(String)");
		System.exit(0);
		return false;
	}

	//subtracts substring from string if able
	public String subtract(String og, String sub)
	{
		int index=og.indexOf(sub);
		if(index>=0)
			og=og.substring(0,index)+og.substring(index+sub.length());
		return og;
	}
	
	//subtracts last substring from string if able
	public String subtractLast(String og, String sub)
	{
		int i=lastIndexOf(og,sub);
		return og.substring(0,i)+og.substring(i+sub.length());
	}
	
	//returns last index of string s in string og
	public int lastIndexOf(String og, String s)
	{
		if(og.indexOf(s)==-1)
			return -1;
		for(int i=og.length()-s.length();i>=0;i--)
			if(og.substring(i,i+s.length()).equals(s))
				return i;
		return -1;
	}
	
	//returns number of times a substring occurs in string
	public int numOf(String og, String sub)
	{
		int num=0;
		while(og.indexOf(sub)>-1)
		{
			og=subtract(og,sub);
			num++;
		}
		return num;
	}
	
}































