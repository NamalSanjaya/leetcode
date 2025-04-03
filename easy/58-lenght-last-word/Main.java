class Solution {
    public int lengthOfLastWord(String s) {
        String[] words = s.split(" ");
        String lastWord = words[words.length-1];
        return lastWord.length();
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "   fly me   to   the moon  ";
        System.out.println(sol.lengthOfLastWord(s));

    }
}
