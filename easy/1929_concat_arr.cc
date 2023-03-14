#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    vector<int> getConcatenation(vector<int>& nums) {
        vector<int> res = nums;
        for(const int &i: nums) {
            res.push_back(i);
        }
        return res;
    }
};

int main(){
    Solution *sl = new Solution();
    vector<int> arr = {1,3,2,1};
    for(const int &i: sl->getConcatenation(arr) ){
        cout << i << endl;
    }
}

