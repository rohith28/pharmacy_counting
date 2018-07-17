import java.util.Comparator;

public class drugDetails {
    private  String dName;
    private int num;
    private double cost;

    public drugDetails(String dName, int num, double cost) {
        this.dName = dName;
        this.num = num;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "print{" +
                "dName='" + dName + '\'' +
                ", num=" + num +
                ", cost=" + cost +
                '}';
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
class SortBycost implements Comparator<drugDetails> {
    public int compare(drugDetails a,drugDetails b){
        if(a.getCost() > b.getCost())
            return -1;
        else if(b.getCost() > a.getCost()){
            return 1;
        }else{
            return b.getdName().compareTo(a.getdName());
        }
    }
}
