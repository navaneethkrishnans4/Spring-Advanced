public class Recursion {
    public static void main(String []args)
    {
        int[] arr ={1,2,3,4,5,6};
        Recursion recursion = new Recursion();

        System.out.println(recursion.findSum(arr,arr.length));
    }

    public int findSum(int[] arr,int length)
    {

        if(length<=0)
            return 0;
        return (findSum(arr,length-1)+arr[length-1]);
    }
}
