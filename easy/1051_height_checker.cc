#include <iostream>
#include <vector>
#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int heightChecker(vector<int>& heights) {
        int count = 0;
        vector<int> actual = heights;
        size_t len = heights.size();
        sort(heights.begin(), heights.begin()+len);
        for (size_t i = 0; i < len; i++){
            if(actual[i] != heights[i]){
                count++;
            }
        }
        return count;
    }
};

int main(){
    vector<int> arr = {1,1,4,2,1,3};
    Solution sl;
    cout << sl.heightChecker(arr) << endl;
}
