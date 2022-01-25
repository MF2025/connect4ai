package codeFunctions;

public class Maths
{
	//rounds to preference with a double returned
		//up to 5 decimal places
	public double round(double r,double rNum){
		String rs=""+r;
		int decPlace=rs.length();
		for(int x=0;x<rs.length();x++)
			if(rs.charAt(x)=='.')
			{
				decPlace=x;
				rs=rs.substring(0,x)+rs.substring(x+1);
				break;
			}
		r*=Math.pow(10,rs.length()-decPlace);
		rNum*=Math.pow(10,rs.length()-decPlace);
		r+=rNum/2;
		r-=r%rNum;
		rs=""+r;
		rs=rs.substring(0,rs.indexOf('.'))+rs.substring(rs.indexOf('.')+1);
		rs=rs.substring(0,decPlace)+"."+rs.substring(decPlace);
		r=Double.parseDouble(rs);
		r*=100000;
		rs=""+round(r);
		rs=rs.substring(0,rs.length()-5)+"."+rs.substring(rs.length()-5);
		r=Double.parseDouble(rs);
		return r;
	}
	
	//rounds to nearest whole number
	public int round(double num){
		if(Math.abs(num)>num)
			return (int)(num-0.5);
		else if(num==0)
			return 0;
		else
			return (int)(num+0.5);
	}
	
	//division function (without decimals or remainders)
	public int div(double d,double dNum){
		d-=d%dNum;
		d/=dNum;
		return (int)d;
	}
	
	//calculates an average of given numbers
	public double average(double[]n){
		double a=0;
		for(int i=0;i<n.length;i++){
			a+=n[i];
		}
		return a/n.length;
	}
	
	//squares a number
	public double sqr(double d){
		return d*d;
	}
	
	//random number generator
	public double random(double min, double max, double interval){
		if(min==max)
			return max;
		if(min>max){
			double storage=min;
			min=max;
			max=storage;			
		}
		if(interval>max-min || (max-min)%interval!=0)
			return min;
		max-=min;
		max+=interval;
		double num=Math.random();
		num*=max;
		num+=min;
		num-=num%interval;
		return num;
	}
	
	//rng of ints
	public int random(int min, int max)
	{
		return (int)random(min,max,1);
	}
	
	//returns greatest common factor of 2 numbers
	public int GCF2(int a,int b){
		//if only one is 0
		//changes to positive
		a=Math.abs(a);
		b=Math.abs(b);
		//formats least to greatest
		if(a>=b){
			int c=b;
			b=a;
			a=c;
		}
		int factor=1;
		//finds GCF
		for(int i=0;i<=a;i++){
			if( ( (double)a/i )%1 == 0 && ( (double)b/i )%1 == 0){
				factor=i;
			}
		}
		return factor;
	}
	
	//returns 2 numbers that add to the 1st and multiply to the 2nd
	public int[] fa(int b,int c){
		int ans1=Integer.MAX_VALUE;
		int ans2=Integer.MAX_VALUE;
		boolean resume=true;
		if(c>b){
			for(int x=-Math.abs(c);x<=Math.abs(c);x++){
				for(int y=-Math.abs(c);y<=Math.abs(c);y++){
					if(x+y==b && x*y==c){
						ans1=x;
						ans2=y;
						resume=false;
					}
					if(!resume)break;
				}
				if(!resume)break;
			}
		}else{
			for(int x=-Math.abs(b);x<=Math.abs(b);x++){
				for(int y=-Math.abs(b);y<=Math.abs(b);y++){
					if(x+y==b && x*y==c){
						ans1=x;
						ans2=y;
						resume=false;
					}
					if(!resume)break;
				}
				if(!resume)break;
			}
		}
		int[] ans={ans1,ans2};
		return ans;
	}
	
	//in progress
	//returns least common multiple of 2 numbers
	public int LCM(int a,int b){
	return 0;
}
	
	//converts number to binary number
	public String bin(int a)
	{
		return Integer.toBinaryString(a);
	}
	
	//turns binary int to its two's complement
	public String twos(String y)
	{
		int x=Integer.parseInt(y);
		char[]a=new char[y.length()];
		for(int i=0;i<a.length;i++)
			a[i]=y.charAt(i);
		String s="";
		for(int i=0;i<a.length;i++)
		{
			if(a[i]=='-')
			{
				s=s+"-";
			}
			else if(a[i]=='0')
			{
				s=s+"1";
			}
			else
			{
				s=s+"0";
			}
		}
		x=dec(s,"bin");
		x++;
		String fin=bin(x);
		while(fin.length()<s.length())
			fin="0"+fin;
		return fin;			
	}
	
	//converts number of non-decimal base to decimal
	public int dec(String s, String base)
	{
		int dec=0;
		if(base.equals("bin"))
		{
			for(int i=s.length()-1;i>=0;i--)
			{
				if(s.charAt(i)=='1')
					dec+=Math.pow( 2, s.length()-i-1 );
			}
		}
		return dec;
	}
	
	
	//returns a mod b
	public double modulus(double a, double b)
	{
		if(a < 0 && b < 0)
		{
			a = Math.abs(a);
			b = Math.abs(b);
		}
		double c = (int)(a / b);
		if(a * b < 0)
		{
		  return b + a - (b * c);
		}
		return a - (b * c);
	}
}
























