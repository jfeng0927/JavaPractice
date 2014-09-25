package leetCode;

public class Candy {
    public int candy(int[] ratings) {
        int size = ratings.length;
        int sum = size;
        
        int candy = 0;
        int[] candyArr = new int[size];
        candyArr[0] = 0;
        for(int i=1; i < size; i++) {
        	if(ratings[i] > ratings[i-1]) {
        		candy++;
        	} else {
        		candy=0;
        	}
        	candyArr[i]=candy;
        }
        candy=0;
        for(int i=size-2; i>=0; i--) {
        	if(ratings[i] > ratings[i+1]) {
        		candy++;
        	} else {
        		candy=0;
        	}
        	sum+=Math.max(candy, candyArr[i]); // pick the maximum required candies
        }
        sum+=candyArr[size-1]; // handle the last one element
        return sum;
    }
    
    public static void main(String[] argv){
    	Candy test = new Candy();
    	int[] ratings = {1,2};
    	int res = test.candy(ratings);
    	System.out.println(res);
    }
}
