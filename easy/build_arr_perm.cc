#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    vector<int> buildArray(vector<int>& nums) {
        vector<int> res(nums.size(), 0);
        for (size_t i = 0; i < nums.size(); i++){
            res[i] = nums[nums[i]];
        }
        return res;
    }
};


int main(){
    Solution *sl = new Solution();
    vector<int> arr = {0,2,1,5,3,4};
    for(const int &i: sl -> buildArray(arr)){
        cout << i << endl;
    }
}
