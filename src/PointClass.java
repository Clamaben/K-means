public class PointClass {
    public Point[] points;
    public double X_mean,Y_mean;
    public Point MeanPoint;
    public PointClass(){
        this.points=null;
        this.MeanPoint=new Point();
    }
    public PointClass(Point[] points){
        this.points=points;
        setX_mean();
        setY_mean();
        MeanPoint=new Point(X_mean,Y_mean);
    }
    public PointClass(PointClass pointClass){
        this.points=pointClass.points;
        setX_mean();
        setY_mean();
        MeanPoint=new Point(X_mean,Y_mean);
    }
    public void AddPoint(Point point){
        if (this.points==null){
            this.points=new Point[1];
            this.points[0]=point;
            return;
        }
        Point[] points=new Point[this.points.length+1];
        for (int i=0;i<this.points.length;i++){
            points[i]=new Point(this.points[i]);
        }
        points[this.points.length]=new Point(point);
        this.points=points;
        Refresh();
    }
    public void Refresh(){
        setX_mean();
        setY_mean();
        MeanPoint=new Point(X_mean,Y_mean);
    }
    public void setX_mean(){
        if (this.points!=null){
            double x=0;
            for (int i=0;i<this.points.length;i++){
                x=x+this.points[i].x;
            }
            X_mean=x/this.points.length;
        }
    }
    public void setY_mean(){
        if (this.points!=null){
            double y=0;
            for (int i=0;i<this.points.length;i++){
                y=y+this.points[i].y;
            }
            Y_mean=y/this.points.length;
        }
    }

}
