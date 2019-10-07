import java.util.*;

public class VerifyThreeSum {

    public static void main(String[] args) {

        //hand coded arrays, declare result variable
        //myArray0 - 3 triplets (3,7,-4), (-3,-1,4), (-1,-4,5)
        //myArray1 - 0 triplets
        //myArray2 - 5 triplets (-3,2,1), (-7,4,3), (4,-11,7), (4,-17,13), (-11,-22,33)
        long[] myArray0 = {-3, 7, -1, 4, -4, 5, 22, 11};
        long[] myArray1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        long[] myArray2 = {-3, 2, 1, -7, 4, 3, -11, 7, -22, 33, -17, 13};
        int result;

        //print results of all three tests to make sure they are working correctly
        System.out.println("\n" +"ThreeSum results:" + "\n");

        result = threeSum(myArray0);
        System.out.println("Number of triplets in myArray0 = " + result);

        result = threeSum(myArray1);
        System.out.println("Number of triplets in myArray1 = " + result);

        result = threeSum(myArray2);
        System.out.println("Number of triplets in myArray2 = " + result + "\n");

        System.out.println("ThreeSumFast results:" + "\n");

        result = threeSumFast(myArray0);
        System.out.println("Number of triplets in myArray0 = " + result);

        result = threeSumFast(myArray1);
        System.out.println("Number of triplets in myArray1 = " + result);

        result = threeSumFast(myArray2);
        System.out.println("Number of triplets in myArray2 = " + result + "\n");

        System.out.println("ThreeSumFastest results:" + "\n");

        result = threeSumFastest(myArray0);
        System.out.println("Number of triplets in myArray0 = " + result);

        result = threeSumFastest(myArray1);
        System.out.println("Number of triplets in myArray1 = " + result);

        result = threeSumFastest(myArray2);
        System.out.println("Number of triplets in myArray2 = " + result + "\n");
    }

    public static int threeSum(long[] list){

        //set variables
        int N = list.length;
        int count = 0;

        //test each combination of three numbers without repeats
        for (int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){
                for(int k = j + 1; k < N; k++){
                    if(list[i] + list[j] + list[k] == 0) {
                        //if the trio sums to zero, increase count
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static int threeSumFast(long[] list){

        //set variables
        int N = list.length;
        int count = 0;

        //sort the list
        Arrays.sort(list);

        //use combinations of pairs
        for (int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){
                //use binary search to see if the pairs added together's opposite is in list and is greater than j index
                if(binarySearch(-(list[i] + list[j]), list) > j){
                    //if so, increase count - has triple that sums to zero
                    count++;
                }
            }
        }
        return count;
    }

    //using as a guide: https://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/
    public static int threeSumFastest(long[] list){

        //set variables
        int N = list.length;
        int count = 0;

        //sort the list
        Arrays.sort(list);

        //use single loop to cycle thru elements
        for(int i = 0; i < N-1; i++){
            //set left and right variables at each end of the list, work in from there, set x to list[i]
            int left = i + 1;
            int right = N - 1;
            long x = list[i];
            //while left is to left of right
            while(left < right){
                //if the triplet sums to zero, increase count
                if(x + list[left] + list[right] == 0){
                    count++;
                    //increase left index and decrease right index
                    left++;
                    right--;
                }
                //if sum is less than zero, increase left
                else if(x + list[left] + list[right] < 0){
                    left++;
                }
                //else decrease right index
                else{
                    right--;
                }
            }
        }
        return count;
    }

    /* return index of the searched number if found, or -1 if not found */
    public static int binarySearch(long key, long[] list) {

        //set variables
        int i = 0;
        int j= list.length-1;

        //if key found, return index
        if (list[i] == key) return i;
        if (list[j] == key) return j;

        //split sorted list in half using k, return index if found
        int k = (i+j)/2;
        while(j-i > 1){
            if (list[k]== key) return k;
            else if (list[k] < key) i=k;
            else j=k;
            k=(i+j)/2;
        }
        //return -1 if not found
        return -1;
    }
}

