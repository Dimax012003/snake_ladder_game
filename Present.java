
public class Present {
	int presentId;
	 int presentSquareId;
	 int points;
	 Present() {
		 presentId=-1;
		 presentSquareId=-1;
		 points=0;
	 }
	 Present(int presentId,int presentSquareId,int points){
		 this.presentId=presentId;
		 this.presentSquareId=presentSquareId;
		 this.points=points;
	 }
	 Present(Present p){
		 presentId=p.presentId;
		 presentSquareId=p.presentSquareId;
		 points=p.points;
	 }
	 int getpresentId() {
		 return presentId;
	 }
	 int getpresentSquareId() {
		 return presentSquareId;
	 }
	 int getpoints() {
		 return points;
	 }
	 void setpresentId(int a) {
		 presentId=a;
	 }
	 void setpresentSquareId(int a) {
		 presentSquareId=a;
	 }
	 void setpoints(int a) {
		 points=a;
	 }
}
