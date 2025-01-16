class Main {
    public static int xorAllNums(int[] nums1, int[] nums2) {
        int xor1 = 0;
        int xor2 = 0;

        for (int num : nums1) {
            xor1 ^= num;
        }

        for (int num : nums2) {
            xor2 ^= num;
        }

        int result = 0;
        if (nums2.length % 2 != 0) {
            result ^= xor1;
        }
        if (nums1.length % 2 != 0) {
            result ^= xor2;
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums1 = {2, 1, 3};
        int[] nums2 = {10, 2, 5, 0};
        System.out.println(xorAllNums(nums1, nums2) );

        nums1 = new int[]{1, 2};
        nums2 = new int[]{3, 4};
        System.out.println(xorAllNums(nums1, nums2));
    }
}
