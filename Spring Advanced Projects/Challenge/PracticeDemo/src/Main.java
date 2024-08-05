import javax.management.ObjectName;
import java.util.*;

public class Main {
    public static void main(String []args){
        int sequence = 2;

        List<Integer> arr = new ArrayList<>();

        for(int i=0;i<sequence;i++)
        {
            System.out.print(fib(i)+" ");
            arr.add(fib(i));


        }
        Collections.sort(arr);
        System.out.println("arrylist:"+arr);
    }

    private static int fib(int count)
    {
        if(count<=1)
            return count;
        return fib(count-1) + fib(count-2);

    }
}