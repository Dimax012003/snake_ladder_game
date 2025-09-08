
public class Player {
  int playerId;
  int score;
  Board board;
  String name;
  Player(){
	  playerId=-1;
	  score=-1;
	  name="";
	  board=null;
  }
  Player(int score,int playerId,Board board,String name){
	  this.score=score;
	  this.name=name;
	  this.board=board;
	  this.playerId=playerId;
  }
  int getscore() {
	  return score;
  }
  int getId() {
	  return playerId;
  }
  String getname() {
	  return name;
  }
  Board getboard() {
	  return board;
  }
  void setId(int a) {
	  playerId=a;
  }
  void set(int a) {
	  score=a;
  }
  void setname(String a) {
	  name=a;
  }
  void setboard(Board b) {
	  board=b;
  }
  int[] move(int id,int die) {
	  int[] x;
	  int i;
      int nextdie=0;

	 
	  x=new int[4];
	  for(i=0;i<4;i++) {
		  x[i]=0;
	  }

	  if(die+id<board.getX()*board.getY()) {
		  x[0]=die+id;
		  int s=0,l=0,p=0;
		   for(i=0;i<board.getSnakes().length;i++) {
			   if(board.getSnakes()[i].getheadId()==id+die) {
				  
			       s++;
			
			       x[0]=board.getSnakes()[i].getTailId();
			       
			   }
			   if(board.getSnakes()[i].getTailId()==id) {
				   s++;
				   break;
			   }
		   }
		   for(i=0;i<board.getLadders().length;i++) {
			   if(board.getLadders()[i].getbottomSquareId()==id+die && board.getLadders()[i].getbroken()==false) {
				  
				   l++;
			   
				  x[0]=board.getLadders()[i].gettopSquareId();
				  
				  nextdie=i; 
				  board.getLadders()[nextdie].setbroken(true);
				  //nextdie=i;  
				  break;
			   }

		   }
		   for(int k=0;k<board.getPresents().length;k++) {
			   if(board.getPresents()[k].getpresentSquareId()==id+die) {
				   score=score+board.getPresents()[k].getpoints();
				   board.getPresents()[k].setpresentSquareId(-1);
				   board.getPresents()[k].setpoints(0);
				   p++;
				   break;
			   }
			   
		   }
		 
		  x[1]=s;
		  x[2]=l;
		  x[3]=p;
		  return x;
	  }
	  else   return x;
  }

}
