import java.util.HashMap;
import java.util.Map;

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> countMap =  new HashMap<>();

        var majElemCountTh = nums.length / 2;

        for(var num: nums){
            var count = countMap.get(num);
            if(count == null){
                count = 1;
            } else {
                count++;
            }

            if (count > majElemCountTh){
                return num;
            }
            countMap.put(num, count);
        }

        return 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr1 =  {23, 2, 2, 2, 8, 2};
        int[] arr2 =  {14};

        System.out.println(sol.majorityElement(arr1));
        System.out.println(sol.majorityElement(arr2));
    }


}
