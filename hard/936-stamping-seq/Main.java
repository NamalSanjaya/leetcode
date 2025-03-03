import java.util.*;

public class StampingSequence {
    public int[] movesToStamp(String stamp, String target) {
        char[] stampArr = stamp.toCharArray();
        char[] targetArr = target.toCharArray();
        boolean[] visited = new boolean[targetArr.length];
        List<Integer> result = new ArrayList<>();
        int replaced = 0;

        while (replaced < targetArr.length) {
            boolean stamped = false;
            for (int i = 0; i <= targetArr.length - stampArr.length; i++) {
                if (!visited[i] && canStamp(targetArr, stampArr, i)) {
                    replaced += doStamp(targetArr, stampArr.length, i);
                    visited[i] = true;
                    stamped = true;
                    result.add(i);
                    if (replaced == targetArr.length) {
                        break;
                    }
                }
            }
            if (!stamped) {
                return new int[0];
            }
        }

        Collections.reverse(result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private boolean canStamp(char[] targetArr, char[] stampArr, int pos) {
        boolean hasNonWildcard = false;
        for (int i = 0; i < stampArr.length; i++) {
            if (targetArr[pos + i] != '?' && targetArr[pos + i] != stampArr[i]) {
                return false;
            }
            if (targetArr[pos + i] != '?') {
                hasNonWildcard = true;
            }
        }
        return hasNonWildcard;
    }

    private int doStamp(char[] targetArr, int stampLength, int pos) {
        int count = 0;
        for (int i = 0; i < stampLength; i++) {
            if (targetArr[pos + i] != '?') {
                targetArr[pos + i] = '?';
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        StampingSequence solver = new StampingSequence();
        System.out.println(Arrays.toString(solver.movesToStamp("abc", "ababc")));
        System.out.println(Arrays.toString(solver.movesToStamp("abca", "aabcaca")));
    }

}
