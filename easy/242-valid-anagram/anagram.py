class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        s_dic = dict()
        for s1 in s:
            val = s_dic.get(s1, 0)
            s_dic[s1] =  val + 1
        
        
        for t1 in t:
            val2 = s_dic.get(t1, 0)
            val2 = val2 - 1
            if val2 < 0:
                return False
            s_dic[t1] = val2
            
        for v in s_dic.values():
            if v != 0:
                return False
        
        return True
    

s = Solution()
print( s.isAnagram("anagram", "nagaram") )
print( s.isAnagram("anagram", "nagara") )


        