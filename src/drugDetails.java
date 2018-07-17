package insight;

import java.util.Comparator;

public class drugDetails {
    private  String dName;
    private int num;
    private float cost;

    public drugDetails(String dName, int num, float cost) {
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
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
            return a.getdName().compareTo(b.getdName());
        }
    }
}
