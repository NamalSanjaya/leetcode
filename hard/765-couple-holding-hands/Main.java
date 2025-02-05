import java.util.*;

public class CouplesHoldingHands {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        Map<Integer, Integer> position = new HashMap<>();
        for (int i = 0; i < n; i++) {
            position.put(row[i], i);
        }

        int swaps = 0;
        for (int i = 0; i < n; i += 2) {
            int firstPerson = row[i];
            int partner = firstPerson ^ 1;

            if (row[i + 1] != partner) {
                swaps++;
                int partnerIndex = position.get(partner);

                row[partnerIndex] = row[i + 1];
                position.put(row[i + 1], partnerIndex);

                row[i + 1] = partner;
                position.put(partner, i + 1);
            }
        }
        return swaps;
    }

    public static void main(String[] args) {
        CouplesHoldingHands solution = new CouplesHoldingHands();
        System.out.println(solution.minSwapsCouples(new int[]{0, 2, 1, 3}));
        System.out.println(solution.minSwapsCouples(new int[]{3, 2, 0, 1}));
    }
}
