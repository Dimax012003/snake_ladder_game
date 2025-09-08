import java.util.ArrayList;
import java.util.HashMap;
public class Game {
   int round;
   Game(){
	   round=-1;
   }
   Game(int round){
	   this.round=round;
   }
   Game(Game g){
	   round=g.round;
   }
   int getround() {
	    return round;
   }
   void setround(int a) {
	   round=a;
   }
   //σε αυτην την συναρτηση τα τμηματα κωδικα που ειναι σε σχολια μπορειτε να τα βγαλετε για να ελεγξετε τον αλγοριθμο
   HashMap<Integer,Integer> setTurns(ArrayList<Player> players){
	   HashMap<Integer,Integer> a;
	   a=new HashMap<Integer,Integer>();
       int[] x;
       x=new int[players.size()];
       int temp=0;
       //ορισμος ζαριου για καθε παιχτη
       for(int i=0;i<players.size();i++) {
    	   x[i]=(int)(1+Math.random()*6);
       }
      /* for(int i=0;i<players.size();i++) {
    	   System.out.print(" "+x[i]);
    	   System.out.print(" "+players.get(i).getId());
       }*/
       System.out.println("");
       //ταξινομησηση των παιχτων με βαση το ζαρι
       for(int i=0;i<players.size()-1;i++) {
    	   for(int j=0;j<players.size();j++) {
    		   if(x[i]>x[j]) {
    			   temp=x[i];
    			   x[i]=x[j];
    			   x[j]=temp;
    			   Player temp1;
    			   temp1=new Player();
    			   temp1=players.get(i);
    			   players.set(i, players.get(j));
    			   players.set(j, temp1);
    		   }
    	   }
       }
       for(int i=0;i<players.size();i++) {
    	//   System.out.print(" "+x[i]);
    	  // System.out.print(" "+players.get(i).getId());
    	   a.put(players.get(i).getId(), x[i]);
       }
       System.out.println("");
      return a;
   }
   public static void main(String[] args) {
	   Game game=new Game(0);//δημιουργια παιχνιδιου
	   Board board=new Board(10,20,3,3,6);//δημιουργια board
	   Player player1=new Player(0,1,board,"James");//οι παιχτες
	   HeuristicPlayer player2=new HeuristicPlayer(0,2,board,"George");
	   ArrayList<Player> players=new ArrayList<Player>(2);//καθορισμος του ποιος παιζει πρωτος
	   players.add(player1);
	   players.add(player2);
	   HashMap<Integer,Integer> a;
	   a=game.setTurns(players);
	   int k=0;//o μετρητης που οριζει την σειρα των παιχτων
	   if(a.get(player1.getId())<a.get(player2.getId())) {
		   k=1;
	   }
	   else if(a.get(player1.getId())>a.get(player2.getId())) {
		   k=2;
	   }
	   else if(a.get(player1.getId())==a.get(player2.getId())) {
		   k=1;
	   }
	 
	   int pos1[],pos2=0;//οι αρχικες θεσεις των παιχτων αντιστοιχα για player και HeuristicPlayer
	   pos1=new int[4];
	   //το pos1 το εκανα σε μορφη πινακα ωστε να παιρνει ολα τα απολεσματα της move
	   pos1[0]=0;
	  //στο pos1[0] αντιστοιχει στην θεση του παιχτη μετα την εκτελεση της move 
	   board.createBoard();
	   board.createElementBoard();//Εαν θελετε να εμφανιστουν οι πινακες των presents,snakes,και ladders ανατρεξτε board και βγαλτε τις γραμμες που ειναι σε σχολια
	   //δημιουργια παιχνιδιου 
	   do {
		   if(k==1) {
			   int die=0;
			   die=(int)(1+Math.random()*6);
			  
               
			  
			  pos1=player1.move(pos1[0], die);
			   k++;
			  // System.out.print(" "+pos1[0]+" "+die);
			   
		   }
		   if(k==2) {
				   pos2=player2.getNextMove(pos2);
				   
			   
			   k--;
			  // System.out.println(" "+pos2+" ");
			   game.setround(game.getround()+1);
		   }
		   if(pos1[0]==board.getX()*board.getY()-1 || pos2==board.getX()*board.getY()-1) {
			   break;
		   }
	   }while((pos1[0]<board.getX()*board.getY() || pos2<board.getX()*board.getY()) && game.getround()<25);
	   //εκτυπωση στατιστικων score και τελικου νικητη
	   player2.statistics();
	   System.out.println("");
	   double score1,score2;
	   //το σκορ καθε παιχτη καθοριζεται απο την τελικη του θεση και τους ποντους του
	   score1=pos1[0]*0.65+player1.getscore()*0.35;
	   score2=pos2*0.65+player2.getscore()*0.35;
	   if(score1>score2) {
		   System.out.println("Player1 won number of rounds:"+game.getround());
		   System.out.println("Player1 reached:"+pos1[0]+" Score from presents:"+player1.getscore()+" Total score:"+score1);
		   System.out.println("Player2 reached:"+pos2+" Score from presents:"+player2.getscore()+" Total score:"+score2);
	   }
	   else if(score1<score2) {
		   System.out.println("Player2 won number of rounds:"+game.getround());
		   System.out.println("Player1 reached:"+pos1[0]+" Score from presents:"+player1.getscore()+" Total score:"+score1);
		   System.out.println("Player2 reached:"+pos2+" Score from presents:"+player2.getscore()+" Total score:"+score2);
	   }
	   else if(score1==score2) {
		   if(pos1[0]>pos2) {
			   System.out.println("Player1 won number of rounds:"+game.getround());
			   System.out.println("Player1 reached:"+pos1[0]+" Score from presents:"+player1.getscore()+" Total score:"+score1);
			   System.out.println("Player2 reached:"+pos2+" Score from presents:"+player2.getscore()+" Total score:"+score2);
		   }
		   if(pos1[0]<pos2) {
			   System.out.println("Player2 won number of rounds:"+game.getround());
			   System.out.println("Player1 reached:"+pos1[0]+" Score from presents:"+player1.getscore()+" Total score:"+score1);
			   System.out.println("Player2 reached:"+pos2+" Score from presents:"+player2.getscore()+" Total score:"+score2);
		   }
	   }
   }
}
