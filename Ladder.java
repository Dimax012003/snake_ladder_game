
public class Ladder {
  int ladderId;
  int topSquareId;
  boolean broken;
  int bottomSquareId;
  Ladder(){
	  ladderId=-1;
	  topSquareId=-1;
	  broken=false;
	  bottomSquareId=-1;
  }
  Ladder(int ladderId,int topSquareId,boolean broken,int bottomSquareId){
	  this.ladderId=ladderId;
	  this.topSquareId=topSquareId;
	  this.broken=broken;
	  this.bottomSquareId=bottomSquareId;
  }
  Ladder(Ladder p){
	  ladderId=p.ladderId;
	  topSquareId=p.topSquareId;
	  broken=p.broken;
	  bottomSquareId=p.bottomSquareId;
  }
  int getladderId() {
	  return ladderId;
  }
  int gettopSquareId() {
	  return topSquareId;
  }
  int getbottomSquareId() {
	  return bottomSquareId;
  }
  boolean getbroken() {
	  return broken;
  }
  void setladderId(int ladderId) {
	  this.ladderId=ladderId;
  }
  void settopSquareId(int a) {
	  topSquareId=a;
  }
  void setbottomSquareId(int a) {
	  bottomSquareId=a;
  }
  void setbroken(boolean a) {
	  broken=a;
  }
}
