import java.io.File;
import java.util.Scanner;

public class K_means {
    public int K;
    public PointClass[] pointClasses;
    public PointClass pointset;
    public Point[] point_Means;
    public static int u;
    public K_means(int k){
        this.K=k;
        pointClasses=new PointClass[k];
        pointset=new PointClass();
        point_Means=new Point[k];
        for (int i=0;i<K;i++){
            pointClasses[i]=new PointClass();
            point_Means[i]=new Point();
        }
        u=0;
    }
    public boolean setpointset(String url){
        try {
            Scanner in = new Scanner(new File(url));//读入文件
            while (in.hasNextLine()) {
                String str = in.nextLine();//将文件的每一行存到str的临时变量中
                String[] sourceStrArray = str.split(",", 2);
                pointset.AddPoint(new Point(Double.valueOf(sourceStrArray[0]),Double.valueOf(sourceStrArray[1])));
            }
            return true;
        } catch (Exception e) { //如果出错返回false
            e.printStackTrace();
            return false;
        }
    }
    public boolean initPoint_Means(){
    int d=pointset.points.length/this.K;
    for (int i=0;i<K;i++){
        point_Means[i]=pointset.points[i*d];
    }
        return true;
    }
    public int getMin(double[] record){
        double min=record[0];
        int id =0;
        for (int i=1;i<record.length;i++){
            if (min>record[i]){
                min=record[i];
                id=i;
            }
        }
        return id;
    }
    public void ReflushPoint_Means(){
        for (int j=0;j<K;j++){
            point_Means[j]=new Point(pointClasses[j].MeanPoint);
            pointClasses[j]=new PointClass();
        }
    }
    public void start(){
        for (int i=0;i<pointset.points.length;i++){
            double[] record=new double[K];
            for (int j=0;j<K;j++){
                record[j]=pointset.points[i].distance(point_Means[j]);
            }
            pointClasses[getMin(record)].AddPoint(pointset.points[i]);
        }
        if (judge()){
        Result();
        }else{
            ReflushPoint_Means();
            start();
        }
    }
    public boolean judge(){
        for (int j=0;j<K;j++){
            if ((point_Means[j].x<=pointClasses[j].MeanPoint.x+0.001&&point_Means[j].x>=pointClasses[j].MeanPoint.x-0.001)&&(point_Means[j].y<=pointClasses[j].MeanPoint.y+0.001&&point_Means[j].y>=pointClasses[j].MeanPoint.y-0.001)){
                continue;
            }else {
                return false;
            }
        }
        return true;
    }
    public void Result()
    {

        for (int i=0;i<pointset.points.length;i++){
            System.out.print(pointset.points[i].x+",");}
            System.out.print("\n");
        for (int i=0;i<pointset.points.length;i++){
            System.out.print(pointset.points[i].y+",");}
            System.out.print("\n");

        for (int i=0;i<K;i++){
            System.out.print("第"+(i+1)+"组点集：");
//            for (int j=0;j<pointClasses[i].points.length;j++){
//                System.out.print(pointClasses[i].points[j].x+","+pointClasses[i].points[j].y);
//                System.out.print("\t");
//            }
            System.out.println(pointClasses[i].points.length);
            for (int j=0;j<pointClasses[i].points.length;j++){
                System.out.print(pointClasses[i].points[j].x+",");
            }
            System.out.print("\n");
            for (int j=0;j<pointClasses[i].points.length;j++){
                System.out.print(pointClasses[i].points[j].y+",");
            }
            System.out.print("\n");
        }
    }
    public static void main(String args[]){
        K_means k_means=new K_means(4);
        if ( k_means.setpointset("src/data.txt")){
            System.out.println("ok");
        }else{
            System.out.println("No");
        }
        k_means.initPoint_Means();
        k_means.start();


    }
}
