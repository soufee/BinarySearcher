
import javafx.scene.chart.PieChart;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>();
         for (int i = 0; i < 10; i++) {
            list.add(getInt());
        }
        int count = 1;
        int max = 1;
        for (int i=0; i<list.size()-1;i++)
        {
            if (list.get(i) == list.get(i +1))
            {
                count++;
                if (max < count)
                    max = count;
            }
            else
                count=1;
        }
        System.out.println(max);
        System.out.println(list);
    }
private static Integer getInt(){
    java.util.Scanner sc = new java.util.Scanner(System.in);
    try{
        int i = sc.nextInt();
        return i;
    } catch (InputMismatchException e){
       return getInt();
    }

}
}
