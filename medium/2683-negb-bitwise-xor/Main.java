public class NeighboringBitwiseXOR {

    public boolean doesValidArrayExist(int[] derived) {
        return  isValid(derived, 0) || isValid(derived, 1);
    }

    private boolean isValid(int[] derived, int firstValue) {
        int n = derived.length;
        int[] original = new int[n];
        original[0] = firstValue;

        for (int i = 1; i < n; i++) {
            original[i] = original[i - 1] ^ derived[i - 1];
        }

        return (original[n - 1] ^ original[0]) == derived[n - 1];
    }

    public static void main(String[] args) {
        NeighboringBitwiseXOR solution = new NeighboringBitwiseXOR();

        int[] derived1 = {1, 1, 0};
        System.out.println(solution.doesValidArrayExist(derived1));

        int[] derived2 = {1, 1};
        System.out.println(solution.doesValidArrayExist(derived2));
    }
}
