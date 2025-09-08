
public class Board {
  int M,N;
  int[][] squares;
  Snake[] snake;
  Ladder[] ladder;
  Present[] present;
  Board(){
	  M=-1;
	  N=-1;
  }
  Board(int M,int N,int s,int l,int p){
	  this.M=M;
	  this.N=N;
	  squares=new int[N][M];
	  snake=new Snake[s];
	  ladder=new Ladder[l];
	  present=new Present[p];
  }
  Board(Board board) {
	  M=board.M;
	  N=board.N;
	  squares=board.squares;
	  ladder=board.ladder;
	  ladder=new Ladder[board.ladder.length];
	  present=board.present;
	  present=new Present[board.present.length];
	  snake=board.snake;
	  snake=new Snake[board.snake.length];
	  int i=0;
	  for(i=0;i<ladder.length;i++) {
		  ladder[i]=new Ladder(board.ladder[i].ladderId,board.ladder[i].topSquareId,board.ladder[i].broken,board.ladder[i].getbottomSquareId());
	  }
	  for(i=0;i<snake.length;i++) {
		  snake[i]=new Snake(board.snake[i].getsnakeId(),board.snake[i].getheadId(),board.snake[i].getTailId());
	  }
	  for(i=0;i<present.length;i++) {
		  present[i]=new Present(board.present[i].presentId,board.present[i].presentSquareId,board.present[i].getpoints());
	  }
  }
  Snake[] getSnakes() {
	  return snake;
  }
  Present[] getPresents() {
	  return present;
  }
  Ladder[] getLadders() {
	  return ladder;
  }
  int getX() {
	  return N;
  }
  int getY() {
	  return M;
  }
  void createBoard() {
	  int i,j,k=1;
	  for(i=N-1;i>=0;i--) {
		 if(k==0) {
		  for(j=0;j<M;j++) {
			  squares[i][j]=M-1-j+10*(N-i-1);
			  
		  }
		  k++;
		}
		 else if(k==1) {
			 for(j=0;j<M;j++) {
				  squares[i][j]=j+10*(N-i-1);
				  
			  }
			 k--;
		 }
	  }
	  for(i=0;i<snake.length;i++) {
		  int x,y;
		  do {
			  x=(int)(Math.random()*(M*N-1));
			  y=(int)(Math.random()*(M*N));
		  }while(x<=y );
		  snake[i]=new Snake(i,x,y);
	  }
	  for(i=0;i<ladder.length;i++) {
		  int x,y;
		  do {
			  x=(int)(Math.random()*(M*N));
			  y=(int)(Math.random()*(M*N));
		  }while(x<=y);
		  
		  ladder[i]=new Ladder(i,x,false,y);
	  }
	  for(i=0;i<present.length;i++) {
		   int y=(int)(Math.random()*10);
		   int x=(int)(Math.random()*(M*N));
           present[i]=new Present(i,x,y);
	  }
  }
  void createElementBoard() {
	  String[][] elementBoardSnakes;
	  String[][] elementBoardLadders;
	  String[][] elementBoardPresents;
	  elementBoardSnakes=new String[N][M];
	  elementBoardLadders=new String[N][M];
	  elementBoardPresents=new String[N][M];
	  for(int i=0;i<N;i++) {
		  for(int j=0;j<M;j++) {
			 for(int k=0;k<snake.length;k++) {
				 if(snake[k].getheadId()==j+10*i) {
					 elementBoardSnakes[i][j]="SH"+snake[k].snakeId;
					 break;
				 }
				 else  if(snake[k].getTailId()==j+10*i) {
					 elementBoardSnakes[i][j]="ST"+snake[k].snakeId;
					 break;
				 }
				 else 
					 elementBoardSnakes[i][j]="___";
			 }
			 for(int g=0;g<ladder.length;g++) {
				 if(ladder[g].gettopSquareId()==j+10*i) {
					 elementBoardLadders[i][j]="LU"+ladder[g].ladderId;
					 break;
				 }
				 else if(ladder[g].getbottomSquareId()==j+10*i) {
					 elementBoardLadders[i][j]="LD"+ladder[g].ladderId;
					 break;
				 }
				 else elementBoardLadders[i][j]="___";
			 }
			 for(int h=0;h<present.length;h++) {
				 if(present[h].getpresentSquareId()==j+10*i) {
					 elementBoardPresents[i][j]="PR"+present[h].presentId;
					 break;
				 }
				 else elementBoardPresents[i][j]="___";
			 }
		  }
	  }
	  for(int n=N-1;n>=0;n--) {
		  for(int m=0;m<M;m++) {
			  //System.out.print(" "+elementBoardSnakes[n][m]);
		  }
		  //System.out.println(" ");
	  }
	 
	  System.out.println(" ");
	  System.out.println(" ");
	  for(int n=N-1;n>=0;n--) {
		  for(int m=0;m<M;m++) {
			  //System.out.print(" "+elementBoardLadders[n][m]);
		  }
		 // System.out.println(" ");
	  }
	  System.out.println(" ");
	  System.out.println(" ");
	  for(int n=N-1;n>=0;n--) {
		  for(int m=0;m<M;m++) {
			 // System.out.print(" "+elementBoardPresents[n][m]);
		  }
		  //System.out.println(" ");
	  }
  }

}
