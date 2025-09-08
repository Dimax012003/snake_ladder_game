import java.util.ArrayList;
public class HeuristicPlayer extends Player{
	
	ArrayList<Integer[]>path;
	//Οι constructors
    HeuristicPlayer(){
    	super();
    	path=new ArrayList<Integer[]>();
    }
    HeuristicPlayer(int score,int playerId,Board board,String name){
    	super(score,playerId,board,name);
    	path=new ArrayList<Integer[]>();
    }
    /* εδω βρισκεται η συναρτηση αξιολογησης με την οποια ελεγχω για το αντιστοιχο
     * ζαρι τα φιδια τα δωρα και τις αντιστοιχες σκαλες που θα συναντησει ο παιχτης*/
    double evaluate(int currentpos,int dice) {
    	int steps=0,points=0,i;
    	
    	//ελεγχω για τυχον σκαλες εφοσον δεν ειναι σπασμενες 
    	//εαν δεν υπαρχουν σκαλες τοτε τα βηματα ειναι το αντιστοιχο ζαρι
    	//εαν βρει σκαλα τοτε αυτοματος τα βηματα γινοταν τα συνολικα τετραγωνα που θα ανεβει ο παιχτης συν το ζαρι
    	for(i=0;i<board.getLadders().length;i++) {
    		if(currentpos+dice==board.getLadders()[i].getbottomSquareId() && board.getLadders()[i].getbroken()==false) {
    			steps=dice+board.getLadders()[i].gettopSquareId()-board.getLadders()[i].getbottomSquareId();
    		
    			break;
    		}
    		else steps=dice;
    	}
    	//ελεγχω για τυχον δωρα.Σε περιπτωση που βρει τα points γινονται ta points του δωρου
    	//αλλιως αν δεν βρει αντιστοιχα τα points γινονταο 0
    	for(i=0;i<board.getPresents().length;i++) {
    		if(currentpos+dice==board.getPresents()[i].getpresentSquareId()) {
    			points=board.getPresents()[i].getpoints();
    			break;
    		}
    		else points=0;
    	}
    	//αντιστοιχα και για τα φιδια
    	for(i=0;i<board.getSnakes().length;i++) {
    		if(currentpos+dice==board.getSnakes()[i].getheadId()) {
    			steps=steps-board.getSnakes()[i].getheadId()+board.getSnakes()[i].getTailId()+dice;
    			//return -1;
    		}
    	}
    	//σε αυτην την περιπτωση αν το ζαρι εχει τιμη που μπορει να τον στειλει στο τερμα η συναρτηση επιστρεφει μεγαλη αξιολογηση ωστε ο παιχτης αναγκαστικα να τερματισει
    	//πχ ο παιχτης ειναι στο square 195 θελει 4 για να φτασει στο τερμα οποτε με μια μεγαλη αξιολογηση παιχτης θα ριξει 4 αποκλειωντας τα αλλα ζαρια
    	if(currentpos+dice==board.getX()*board.getY()-1) {
    		return 1000000;
    	}
    	return 0.65*steps+0.35*points;
    }
    int getNextMove(int currentpos) {
    	Integer[][] x;
    	x=new Integer[6][7];// η δομη ως μορφη πινακα
    	//οι 6 σειρες αποτελουν τα αντιστοιχα ζαρια 1,2,3,4,5,6
    	//οι στηλες αποτελουν τα αντιστοιχα στατιστικα
    	//πρωτη στηλη το ζαρι,δευτερη τους ποντους του square
    	//τριτη την τελικη κινηση που επιλεγει και η οποια εκτελειται στο τελος
    	//4τις σκαλες,5 τα φιδακια,6 τα δωρα,7 την αξιολογηση για καθε ζαρι
    	double max;
    	//αρχικοποιηση πινακα για σκαλες,φιδακια,δωρα
    	for(int i=0;i<6;i++) {
            for(int j=3;j<=5;j++) {
            	x[i][j]=0;
            }
    	}
    	max=evaluate(currentpos,1);
    	int g=1;
        for(int i=0;i<6;i++) {
        	x[i][0]=i+1;//το ζαρι αυξημενο κατα 1 καθως ο μετρητης ξεκινα απο 0 τελειωνει σε 5
           for(int j=0;j<board.getPresents().length;j++) {
            if(currentpos+i+1==board.getPresents()[j].getpresentSquareId()) {
            	x[i][1]=board.getPresents()[j].getpoints();
            	break;
              }
            else x[i][1]=0;
        	}
           //ελεγχος για σκαλα
           for(int k=0;k<board.getLadders().length;k++) {
        	   if(currentpos+i+1==board.getLadders()[k].getbottomSquareId()) {
        		  x[i][3]=1; 
        		  break;
        	   }
           }
           //ελεγχος για φιδι
           for(int k=0;k<board.getSnakes().length;k++) {
        	   if(currentpos+i+1==board.getSnakes()[k].getheadId()) {
        		  x[i][4]=1; 
        		  break;
        	   }
           }
           //ελεγχος για δωρο
           for(int k=0;k<board.getPresents().length;k++) {
        	   if(currentpos+i+1==board.getPresents()[k].getpresentSquareId()) {
        		  x[i][5]=1; 
        		  break;
        	   }
           }
           
           //επιλογη μεγαλυτερης αξιολογησης
           if(max<evaluate(currentpos,i+1)) {
        	   max=evaluate(currentpos,i+1);
    		   g=i+1;  //εδω επιλεγεται το ζαρι με την μεγαλυτερη αξιολογηση
           }
          
           x[i][6]=(int)evaluate(currentpos,i);//προσθηκη αξιολογησης
    
           }
           
       x[g-1][2]=move(currentpos,g)[0];//εκτελεσης της κινησης με το ζαρι που εφερε την καλυτερη αξιολογηση
       path.add(x[g-1]);//προσθηκη της καλυτερης κινησης στο path
        return x[g-1][2];//επιστροφη κινησης
    

}
    void statistics() {
    	//στατιστικα καθε τελικης κινησης
    	for(int i=0;i<path.size();i++) {
            	 System.out.println("Round:"+(i+1)+" Dice:"+path.get(i)[0]+" Present:"+path.get(i)[5]+" Moved to square:"+path.get(i)[2]+" used:"+path.get(i)[3]+" ladder"+" bitten by:"+path.get(i)[4]+" snakes ");
       }
    	//συνολικα στατιστικα παιχνιδου
    	int snakes=0,ladders=0,presents=0;
    	for(int i=0;i<path.size();i++) {
    		snakes=snakes+path.get(i)[4];
    		ladders=ladders+path.get(i)[3];
    		presents=presents+path.get(i)[5];
    	}
    	System.out.println("");
    	System.out.println("Bitten by:"+snakes+" snakes Got:"+presents+" presents Used:"+ladders+" ladders");
 }
    }
