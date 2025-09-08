
public class Snake {
 int snakeId;
 int headId;
 int TailId;
 Snake() {
	 snakeId=-1;
	 headId=-1;
	 TailId=0;
 }
 Snake(int snakeId,int headId,int TailId){
	 this.headId=headId;
	 this.snakeId=snakeId;
	 this.TailId=TailId;
 }
 Snake(Snake p){
	 snakeId=p.snakeId;
	 headId=p.headId;
	 TailId=p.TailId;
 }
 int getsnakeId() {
	 return snakeId;
 }
 int getheadId() {
	 return headId;
 }
 int getTailId() {
	 return TailId;
 }
 void setsnakeId(int a) {
	 snakeId=a;
 }
 void setheadId(int a) {
	 headId=a;
 }
 void setTailId(int a) {
	 TailId=a;
 }
}
