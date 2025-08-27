class Solution:
    def singleNumber(self, nums):
        mp = Map(nums)
        return mp.getOneExistElem()


class Map:
    def __init__(self, arr):
        self.storeMap = dict()
        for elem in arr:
            self.storeMap[elem] = self.storeMap.get(elem, 0) + 1
        
    def getOneExistElem(self):
        for key, val in self.storeMap.items():
            if val == 1:
                return key

        
sol = Solution()
res = sol.singleNumber([4,1,2,1,2])

print(res)
