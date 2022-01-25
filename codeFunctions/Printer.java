package codeFunctions;

import java.util.concurrent.TimeUnit;


//ease of printing
public class Printer{
	
	//print normal
	public void n(String pv){
		System.out.print(pv);
	}
	//println
	public void l(String pv){
		System.out.println(pv);
	}
	public void n(){
		System.out.println();
	}
	public void l(){
		System.out.println();
	}
	
	//overloaded print function
	public void n(char pv){
		System.out.print(pv);
	}
	public void l(char pv){
		System.out.println(pv);
	}
	public void n(int pv){
		System.out.print(pv);
	}
	public void l(int pv){
		System.out.println(pv);
	}
	public void n(double pv){
		System.out.print(pv);
	}
	public void l(double pv){
		System.out.println(pv);
	}
	public void n(boolean pv){
		System.out.print(pv);
	}
	public void l(boolean pv){
		System.out.println(pv);
	}
	public void n(Object pv){
		System.out.print(pv.toString());
	}
	public void l(Object pv){
		System.out.println(pv.toString());
	}
	
	//lit print slow
	public void lns(String pv)throws InterruptedException{
		int activ=0;
		while(activ<pv.length()){
			System.out.print(pv.charAt(activ));
			if(pv.charAt(activ)!=' ')
				TimeUnit.MILLISECONDS.sleep(100);
			activ++;			
		}
	}
	
	//lit println slow
	public void lls(String pv)throws InterruptedException{
		int activ=0;
		while(activ<pv.length()){
			System.out.print(pv.charAt(activ));
			if(pv.charAt(activ)!=' ')
				TimeUnit.MILLISECONDS.sleep(100);
			activ++;
		}
		l();
	}
	
	//lit print fast
	public void lpf(String pv)throws InterruptedException{
		int activ=0;
		while(activ<pv.length()){
			System.out.print(pv.charAt(activ));
			if(pv.charAt(activ)!=' ')
				TimeUnit.MILLISECONDS.sleep(50);
			activ++;
		}
	}
	
	//lit println fast
	public void llf(String pv)throws InterruptedException{
		int activ=0;
		while(activ<pv.length()){
			System.out.print(pv.charAt(activ));
			if(pv.charAt(activ)!=' ')
				TimeUnit.MILLISECONDS.sleep(50);
			activ++;
		}
		l();
	}
	
	//lit print customizable
	public void lnc(String pv,int time)throws InterruptedException{
		int activ=0;
		while(activ<pv.length()){
			System.out.print(pv.charAt(activ));
			if(pv.charAt(activ)!=' ')
				TimeUnit.MILLISECONDS.sleep(time);
			activ++;
		}
	}
	
	//lit println customizable
	public void llc(String pv,int time)throws InterruptedException{
		int activ=0;
		while(activ<pv.length()){
			System.out.print(pv.charAt(activ));
			if(pv.charAt(activ)!=' ')
				TimeUnit.MILLISECONDS.sleep(time);
			activ++;
		}
		l();
	}

	
}
