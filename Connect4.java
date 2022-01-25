package connect4;

import codeFunctions.*;
import java.util.*;

public class Connect4
	extends Basics
	//Connect 4 by Mark Friant
		//created June-July 2019
{
	
	static char[][]board;
		//game board
		//6 rows of length 7
		//whether player goes 1st
	static int c1=46,c2=56,c3=99,
			p1=91,p2=52,p3=43,
			cDZ1=36,cDZ2=91,cDZ3=62,
			pDZ1=62,pDZ2=40,pDZ3=65;
		//weight of each type of solution
		 //defaults from failed machine learning attempt
	static int cDrop;
		//where the computer just dropped
	static int moves=0;
		//how many moves have been made
	static int aggression;
	
 	static public void main(String[]notArgs)
 		throws InterruptedException
	{
	//initializing
		board=new char[6][7];
		for(int y=0;y<6;y++)
			for(int x=0;x<7;x++)
				board[y][x]='-';
		board(board);
		
	//aggressive, defensive, or neutral
		//-1 def
		//0 neu
		//1 agg
		aggression=m.random(-1,1);
		
		if(aggression==-1)
		{
			c1=5;c2=15;c3=30;
			p1=6;p2=18;p3=36;
			cDZ1=20;cDZ2=50;cDZ3=100;
			pDZ1=24;pDZ2=60;pDZ3=120;
		}
		else if(aggression==1)
		{
			c1=6;c2=18;c3=36;
			p1=5;p2=15;p3=30;
			cDZ1=24;cDZ2=60;cDZ3=120;
			pDZ1=20;pDZ2=50;pDZ3=100;
		}
		else
		{
			c1=5;c2=15;c3=30;
			p1=5;p2=15;p3=30;
			cDZ1=20;cDZ2=50;cDZ3=100;
			pDZ1=20;pDZ2=50;pDZ3=100;
		}
		
	//determining who goes 1st
		String first=r.s("Would you like to go first? ").toLowerCase();
		while(!first.equals("yes") && !first.equals("no"))
			first=r.s("Yes or no? ");
	//playing
		if(first.equals("no"))
		{
			drop(3,'c',board);
			moves++;
			wait(500);
			p.l("The computer dropped in column "+cDrop+".");
			wait(500);
			board(board);
			wait(500);
		}
		while(winner(board)==0 && moves<42)
		{
			player();
			moves++;
			board(board);
			if(winner(board)==0)
			{
				computer();
				moves++;
				wait(500);
				p.l("The computer dropped in column "+cDrop+".");
				wait(500);
				board(board);
				wait(500);
			}
		}
	//declares winner
		if(winner(board)==1)
		{
			wait(500);
			p.n("You Win!");
		}
		else if(winner(board)==2)
		{
			p.l("Computer Wins!");
			/*p.l("You were beat by an AI.");
			wait(500);
			p.n("You should be ashamed of yourself.");*/
		}
		else
		{
			wait(500);
			p.n("Tie!");
		}
	}

 	//determines first move if computer goes first
 	public static int move1()
 	{
 		int i=m.random(1,100);
 		if(i>98)
 			return 0;
 		else if(i>96)
 			return 1;
 		else if(i>94)
 			return 2;
 		else if(i>92)
 			return 4;
 		else if(i>90)
 			return 5;
 		else if(i>88)
 			return 6;
 		return 3;
 	}
 	
	//prints board
	public static void board(char[][]brd)
	{
		p.l("             Board");
		for(int y=0;y<6;y++)
		{
			p.n(" ");
			for(int x=0;x<7;x++)
			{
				p.n("|");
				if(brd[y][x]=='-')
					p.n(" - ");
				else if(brd[y][x]=='p')
					p.n(" O ");
				else
					p.n(" # ");
			}
			p.l("|");
		}
		p.l("===============================");
				
	}
	
	//player play
	public static void player()
	{
		int col;
		String s=r.s("Column: ");
		while( !sp.isI(s) || sp.toI(s) < 1 || sp.toI(s) > 7 ||
				lowest( sp.toI(s)-1, board ) < 0 )
		{
			if( !sp.isI(s) || sp.toI(s) < 1 || sp.toI(s) > 7 )
				s=r.s("Enter a number from 1 to 7: ");
			else
				s=r.s("Play in a free column: ");
		}
		col=sp.toI(s)-1;
		drop(col,'p',board);
	}
	
	//determines lowest available space in column
	public static int lowest(int col, char[][]brd)
	{
		if(col<0 || col>6)
			return -1;
		for(int i=5;i>-1;i--)
			if(brd[i][col]=='-')
				return i;
		return -1;
	}
	
	//computer play
	public static void computer()
	{
		char[][]potBrd=new char[6][7];
			//dummy board used for predictions
		int[]scores=new int[7];
			//scores of each column
		
		//preventing drop in full column
		int full=0;
		for(int i=0;i<7;i++)
			if(lowest(i,board)==-1)
			{
				scores[i]=Integer.MIN_VALUE;
				full++;
			}
		if(full==7)
			return;
		
		int firstValid=6;
			//first column the computer can play in
		for(int i=6;i>=0;i--)
			if(scores[i]!=Integer.MIN_VALUE)
				firstValid=i;
		
		for(int a=0;a<7;a++)
		{
			for(int b=0;b<7;b++)
			{
				for(int y=0;y<6;y++)
					for(int x=0;x<7;x++)
						potBrd[y][x]=board[y][x];
				if(lowest(a,potBrd)>-1)
				{
					drop(a,'c',potBrd);
					while(lowest(b,potBrd)<0)
					{
						b++;
						if(b==8)
							break;
					}
					if(b<8)
					{
						drop(b,'p',potBrd);
						int c=evaluate('c',potBrd);
						if(c<scores[a] || b==firstValid)
							scores[a]=c;
					}
				}
			}
		}
		//choosing best column
		int col=0;
		for(int i=1;i<7;i++)
		{
			if(scores[i]>scores[col])
				col=i;
		}
		//dropping
		drop(col,'c',board);
	}
	
	//method for dropping a piece
	public static void drop(int col,char p,char[][]brd)
	{
		brd[lowest(col,brd)][col]=p;
		cDrop=col+1;
	}
	
	//returns how "good" the board is for the computer
	public static int evaluate(char p,char[][]brd)
	{
		int score=0;
			//score of board
		ArrayList<Integer>cr=new ArrayList<Integer>();
			//stands for couputer rows
		ArrayList<Integer>cc=new ArrayList<Integer>();
			//computer columns
		ArrayList<Integer>pr=new ArrayList<Integer>();
			//player rows
		ArrayList<Integer>pc=new ArrayList<Integer>();
			//all empty points of traps of 3 so all
			 //deathzones can be seen
		int ptrap3=0;
		int ctrap3=0;
			//how many traps of 3 are open on the bottom
			//if 2 or more are present, the score will plummet or skyrocket
		//going thru all solutions to fetch trap type
		 //and changing score accordingly
		Solution[]sols=allSols(brd);
		for(int i=0;i<69;i++)
		{
			if(trapType(sols[i],p,brd)==4)
				score+=1000000;
			if(trapType(sols[i],p,brd)==3)
			{
				score+=c3;
				for(int a=0;a<4;a++)
					if(brd[ sols[i].rows[a] ][sols[i].cols[a] ]=='-'
						&& sols[i].rows[a]>0)
					{
						cr.add(sols[i].rows[a]);
						cc.add(sols[i].cols[a]);
					//ground traps
						if(sols[i].rows[a]==lowest(sols[i].cols[a],brd))
						{
							ctrap3++;
						}
					}
			}
			if(trapType(sols[i],p,brd)==2)
				score+=c2;
			if(trapType(sols[i],p,brd)==1)
				score+=c1;
			if(trapType(sols[i],p,brd)==-4)
				score-=100000;
			if(trapType(sols[i],p,brd)==-3)
			{
				score-=p3;
				for(int a=0;a<4;a++)
					if(brd[ sols[i].rows[a] ][sols[i].cols[a] ]=='-'
						&& sols[i].rows[a]>0)
					{
						pr.add(sols[i].rows[a]);
						pc.add(sols[i].cols[a]);
					//ground traps
						if(sols[i].rows[a]==lowest(sols[i].cols[a],brd))
						{
							ptrap3++;
						}
					}
			}
			if(trapType(sols[i],p,brd)==-2)
				score-=p2;
			if(trapType(sols[i],p,brd)==-1)
				score-=p1;
		}
		if(ptrap3>1)
			score-=100000;
		score+=10000*ctrap3;
		//deleting double counts for computer traps of 3
		if(cr.size()>0)
		{
			ArrayList<Integer>tr=new ArrayList<Integer>();
			ArrayList<Integer>tc=new ArrayList<Integer>();
				//temporary arrays
			for(int i:cr)
				tr.add(i);
			for(int i:cc)
				tc.add(i);
			int n=0;
			while(tr.size()>0)
			{
				int a=tr.remove(0);
				int b=tc.remove(0);
				if(tr.indexOf(a)>-1 && tc.get(tr.indexOf(a))==b)
				{
					cr.remove(n);
					cc.remove(n);
				}
				else
					n++;
			}
		}
		//for player
		if(pr.size()>0)
		{
			ArrayList<Integer>tr=new ArrayList<Integer>();
			ArrayList<Integer>tc=new ArrayList<Integer>();
				//temporary arrays
			for(int i:pr)
				tr.add(i);
			for(int i:pc)
				tc.add(i);
			int n=0;
			while(tr.size()>0)
			{
				int a=tr.remove(0);
				int b=tc.remove(0);
				if(tr.indexOf(a)>-1 && tc.get(tr.indexOf(a))==b)
				{
					pr.remove(n);
					pc.remove(n);
				}
				else
					n++;
			}
		}
		//changing score for partial deathzones
		for(int i=0;i<cr.size();i++)
		{
			if(partialDeath(cr.get(i),cc.get(i),p,brd)==3)
				score+=cDZ3;
			if(partialDeath(cr.get(i),cc.get(i),p,brd)==2)
				score+=cDZ2;
			if(partialDeath(cr.get(i),cc.get(i),p,brd)==1)
				score+=cDZ1;
		}
		for(int i=0;i<pr.size();i++)
		{
			if(partialDeath(pr.get(i),pc.get(i),opp(p),brd)==3)
				score-=pDZ3;
			if(partialDeath(pr.get(i),pc.get(i),opp(p),brd)==2)
				score-=pDZ2;
			if(partialDeath(pr.get(i),pc.get(i),opp(p),brd)==1)
				score-=pDZ1;
		}
		return score;
	}
	
	//returns how many slots are filled in the second part of a deathzone
	//returns 0 if neutral
	public static int partialDeath(int row,int col,char p,char[][]brd)
	{
		char[][]bd=new char[6][7];
		for(int y=0;y<6;y++)
			for(int x=0;x<7;x++)
				bd[y][x]=brd[y][x];
		drop(col,opp(p),bd);
		Solution[]s=solsXV(col,p,bd);
		int best=0;
		for(int i=0;i<s.length;i++)
			if(trapType(s[i],p,brd)>best)
				best=trapType(s[i],p,brd);
		return best;
	}
	
	//returns an array of all the possible solutions
	public static Solution[] allSols(char[][]brd)
	{
		Solution[]s=new Solution[69];
		int n=0;
	//horizontals
		for(int y=5;y>=0;y--)
		{
			for(int x=0;x<4;x++)
			{
				s[n]=new Solution(
					new int[]{y,y,y,y},
					new int[]{x,x+1,x+2,x+3});
				n++;
			}
		}
	//verticals
		for(int y=5;y>2;y--)
		{
			for(int x=0;x<7;x++)
			{
				s[n]=new Solution(
					new int[]{y,y-1,y-2,y-3},
					new int[]{x,x,x,x});
				n++;
			}
		}
	//growth diagonals
		for(int y=5;y>2;y--)
		{
			for(int x=0;x<4;x++)
			{
				s[n]=new Solution(
					new int[]{y,y-1,y-2,y-3},
					new int[]{x,x+1,x+2,x+3});
				n++;
			}
		}
	//decay diagonals
		for(int y=5;y>2;y--)
		{
			for(int x=3;x<7;x++)
			{
				s[n]=new Solution(
					new int[]{y,y-1,y-2,y-3},
					new int[]{x,x-1,x-2,x-3});
				n++;
			}
		}
		return s;
	}
	
	//returns if a solution is partial to one user
	 //or if it's neutral
	//n for computer trap
	//-n for player trap
	//0 for neutral
	public static int trapType(Solution s,char p,char[][]brd)
	{
		int P=0;
		int c=0;
		for(int i=0;i<4;i++)
			if(brd[s.rows[i]][s.cols[i]]==p)
				c++;
			else if(brd[s.rows[i]][s.cols[i]]==opp(p))
				P++;
		if( c==P || (c>0 && P>0) )
			return 0;
		if(c>P)
			return c;
		return -P;
	}

	//returns array of potential solutions for a point
	//returns a 3D array
		//0 is solution number
		//1 is rows
		//2 is columns
	public static Solution[] sols(int col, char p, char[][]brd)
	{
		ArrayList<Integer>rows=new ArrayList<Integer>();
		ArrayList<Integer>cols=new ArrayList<Integer>();
		p=opp(p);
		int row=lowest(col,brd);
	//horizontals
		for(int x=0;x<4;x++)
			if(isInSol(row,col,new Solution(
				new int[]{row,row,row,row},
				new int[]{x,x+1,x+2,x+3})) &&
			brd[row][x]!=p && brd[row][x+1]!=p &&
			brd[row][x+2]!=p && brd[row][x+3]!=p)
			{
				for(int i=0;i<4;i++)
				{
					rows.add(row);
					cols.add(x+i);
				}
			}
	//verticals
		for(int y=0;y<3;y++)
			if(isInSol(row,col,new Solution(
				new int[]{y,y+1,y+2,y+3},
				new int[]{col,col,col,col})) &&
			brd[y][col]!=p && brd[y+1][col]!=p &&
			brd[y+2][col]!=p && brd[y+3][col]!=p)
			{
				for(int i=0;i<4;i++)
				{
					rows.add(y+i);
					cols.add(col);
				}
			}
	//growth diag
		int[]c=gDiag(row,col);
		while(isValid(c[0]-3,c[1]+3))
		{
			if(isInSol(row,col,new Solution(
					new int[]{c[0],c[0]-1,c[0]-2,c[0]-3},
					new int[]{c[1],c[1]+1,c[1]+2,c[1]+3})) &&
				brd[c[0]][c[1]]!=p && brd[c[0]-1][c[1]+1]!=p &&
				brd[c[0]-2][c[1]+2]!=p && brd[c[0]-3][c[1]+3]!=p)
			{
				for(int i=0;i<4;i++)
				{
					rows.add(c[0]-i);
					cols.add(c[1]+i);
				}
			}
			c[0]--;
			c[1]++;
		}
	//decay diag
		c=dDiag(row,col);
		while(isValid(c[0]-3,c[1]-3))
		{
			if(isInSol(row,col,new Solution(
					new int[]{c[0],c[0]-1,c[0]-2,c[0]-3},
					new int[]{c[1],c[1]-1,c[1]-2,c[1]-3})) &&
				brd[c[0]][c[1]]!=p && brd[c[0]-1][c[1]-1]!=p &&
				brd[c[0]-2][c[1]-2]!=p && brd[c[0]-3][c[1]-3]!=p)
			{
				for(int i=0;i<4;i++)
				{
					rows.add(c[0]-i);
					cols.add(c[1]-i);
				}
			}
			c[0]--;
			c[1]--;
		}
		Solution[]sols=new Solution[rows.size()/4];
		int i=0;
		for(int n=0;n<sols.length;n++)
		{
			sols[n]=new Solution(
				new int[]{rows.get(i),rows.get(i+1),
					rows.get(i+2),rows.get(i+3)},
				new int[]{cols.get(i),cols.get(i+1),
					cols.get(i+2),cols.get(i+3)});
			i+=4;
		}
		return sols;
	}

	//same as sols method but without the verticals
	public static Solution[] solsXV(int col, char p, char[][]brd)
	{
		ArrayList<Integer>rows=new ArrayList<Integer>();
		ArrayList<Integer>cols=new ArrayList<Integer>();
		p=opp(p);
		int row=lowest(col,brd);
	//horizontals
		for(int x=0;x<4;x++)
			if(isInSol(row,col,new Solution(
				new int[]{row,row,row,row},
				new int[]{x,x+1,x+2,x+3})) &&
			brd[row][x]!=p && brd[row][x+1]!=p &&
			brd[row][x+2]!=p && brd[row][x+3]!=p)
			{
				for(int i=0;i<4;i++)
				{
					rows.add(row);
					cols.add(x+i);
				}
			}
	//growth diag
		int[]c=gDiag(row,col);
		while(c.length==2 && isValid(c[0]-3,c[1]+3))
		{
			if(isInSol(row,col,new Solution(
					new int[]{c[0],c[0]-1,c[0]-2,c[0]-3},
					new int[]{c[1],c[1]+1,c[1]+2,c[1]+3})) &&
				brd[c[0]][c[1]]!=p && brd[c[0]-1][c[1]+1]!=p &&
				brd[c[0]-2][c[1]+2]!=p && brd[c[0]-3][c[1]+3]!=p)
			{
				for(int i=0;i<4;i++)
				{
					rows.add(c[0]-i);
					cols.add(c[1]+i);
				}
			}
			c[0]--;
			c[1]++;
		}
	//decay diag
		c=dDiag(row,col);
		while(c.length==2 && isValid(c[0]-3,c[1]-3))
		{
			if(isInSol(row,col,new Solution(
					new int[]{c[0],c[0]-1,c[0]-2,c[0]-3},
					new int[]{c[1],c[1]-1,c[1]-2,c[1]-3})) &&
				brd[c[0]][c[1]]!=p && brd[c[0]-1][c[1]-1]!=p &&
				brd[c[0]-2][c[1]-2]!=p && brd[c[0]-3][c[1]-3]!=p)
			{
				for(int i=0;i<4;i++)
				{
					rows.add(c[0]-i);
					cols.add(c[1]-i);
				}
			}
			c[0]--;
			c[1]--;
		}
		Solution[]sols=new Solution[rows.size()/4];
		int i=0;
		for(int n=0;n<sols.length;n++)
		{
			sols[n]=new Solution(
				new int[]{rows.get(i),rows.get(i+1),
					rows.get(i+2),rows.get(i+3)},
				new int[]{cols.get(i),cols.get(i+1),
					cols.get(i+2),cols.get(i+3)});
			i+=4;
		}
		return sols;
	}

	//returns starting growth diagonal coordinate
	 //from bottom-most corner
	public static int[] gDiag(int row, int col)
	{
		int x=col;
		int y=row;
		while(isValid(y,x))
		{
			x--;
			y++;
		}
		if(y<3)
			return new int[]{0};
		return new int[]{y-1,x+1};		
	}
	
	//returns starting decay diagonal coordinate
	 //from bottom-most corner
	public static int[] dDiag(int row, int col)
	{
		int x=col;
		int y=row;
		while(isValid(y,x))
		{
			x++;
			y++;
		}
		if(y<3)
			return new int[]{0};
		return new int[]{y-1,x-1};
	}
	
	//determines if a point is part of a solution
	public static boolean isInSol(int row,int col,Solution s)
	{
		if((row==s.rows[0] && col==s.cols[0]) ||
		(row==s.rows[1] && col==s.cols[1]) ||
		(row==s.rows[2] && col==s.cols[2]) ||
		(row==s.rows[3] && col==s.cols[3]))
			return true;
		return false;
	}
	
	//returns lowest deathzone point in a column
	//-1 is for none
	public static int deathzone(int col,char p,char[][]bd)
	{
		char[][]brd=new char[6][7];
		for(int y=0;y<6;y++)
			for(int x=0;x<7;x++)
				brd[y][x]=bd[y][x];
		for(int y=lowest(col,bd);y>0;y--)
		{
			int a=trap(col,3,p,brd);
			brd[y][col]=opp(p);
			int b=trap(col,3,p,brd);
			if(b==a-1)
				return a;
		}
		return -1;
	}
	
	//returns char of other player
	public static char opp(char c)
	{
		if(c=='p')
			return 'c';
		return 'p';
	}
	
	//determines if a trap of n size is in play
	 //in a column
	//returns -1 if none
	 //or else it returns the row of the trap
	public static int trap(int col,int num,char p,char[][]brd)
	{
		for(int y=lowest(col,brd);y>-1;y--)
		{
			Solution[]s=solsXV(col,p,brd);
			for(int i=0;i<s.length;i++)
			{
				if(ptsInSol(s[i],p)==num && 
					ptsInSol(s[i],opp(p))==0)
					return y;
			}
		}
		return -1;
	}
	
	//returns how many points in a potential
	 //solution are already present
	public static int ptsInSol(Solution s,char p)
	{
		int n=0;
		for(int i=0;i<4;i++)
			if(board[s.rows[i]][s.cols[i]]==p)
				n++;
		return n;
	}
	
	//returns if a position is in bounds
	public static boolean isValid(int row,int col)
	{
		return row>-1 && row<6 && col>-1 && col<7;
	}

	//determines if anybody has won
		//0 - none
		//1 - player
		//2 - computer
	public static int winner(char[][]brd)
	{
	//horizontals
		for(int y=0;y<6;y++)
			for(int x=0;x<4;x++)
				if(brd[y][x]=='p' && brd[y][x+1]=='p' &&
					brd[y][x+2]=='p' && brd[y][x+3]=='p')
						return 1;
		for(int y=0;y<6;y++)
			for(int x=0;x<4;x++)
				if(brd[y][x]=='c' && brd[y][x+1]=='c' &&
					brd[y][x+2]=='c' && brd[y][x+3]=='c')
						return 2;
	//verticals
		for(int x=0;x<7;x++)
			for(int y=5;y>2;y--)
				if(brd[y][x]=='p' && brd[y-1][x]=='p' &&
					brd[y-2][x]=='p' && brd[y-3][x]=='p')
						return 1;
		for(int x=0;x<7;x++)
			for(int y=5;y>2;y--)
				if(brd[y][x]=='c' && brd[y-1][x]=='c' &&
					brd[y-2][x]=='c' && brd[y-3][x]=='c')
						return 2;
	//growth diag
		for(int x=0;x<4;x++)
			for(int y=5;y>2;y--)
				if(brd[y][x]=='p' && brd[y-1][x+1]=='p' &&
					brd[y-2][x+2]=='p' && brd[y-3][x+3]=='p')
						return 1;
		for(int x=0;x<4;x++)
			for(int y=5;y>2;y--)
				if(brd[y][x]=='c' && brd[y-1][x+1]=='c' &&
					brd[y-2][x+2]=='c' && brd[y-3][x+3]=='c')
						return 2;
	//decay diag
		for(int x=6;x>2;x--)
			for(int y=5;y>2;y--)
				if(brd[y][x]=='p' && brd[y-1][x-1]=='p' &&
					brd[y-2][x-2]=='p' && brd[y-3][x-3]=='p')
						return 1;
		for(int x=6;x>2;x--)
			for(int y=5;y>2;y--)
				if(brd[y][x]=='c' && brd[y-1][x-1]=='c' &&
					brd[y-2][x-2]=='c' && brd[y-3][x-3]=='c')
						return 2;
		return 0;
	}

}

/* 

	 | - | - | - | - | - | - | - |
	 | - | - | - | - | - | - | - |
	 | - | - | - | O | - | - | - |
	 | - | - | - | # | X | - | - |
	 | - | - | - | O | X | O | O |
	 | - | - | - | # | # | # | O |
	===============================
		deathzone shown here 
		(X is the deathzone and 
		the rest is the setup)
		
	Win Combos: 
	yes, 2, 4, 4, 4, 7, 7, 2, 6, 6, 5
	yes, 4, 4, 4, 2, 3, 5, 1, 3, 5, 5
*/


























