public class Point {
    public double x,y;
    public Point(){
        this.x=0;
        this.y=0;
    }
    public Point(double x,double y){
        this.x=x;
        this.y=y;
    }
    public Point(Point point){
        this.x=point.x;
        this.y=point.y;
    }
    public double distance(Point point){
        return Math.pow((this.x-point.x),2)+Math.pow((this.y-point.y),2);
    }

}
