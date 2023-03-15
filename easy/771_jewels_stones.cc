#include <iostream>
#include <unordered_map>

using namespace std;

class Solution {
public:
    int numJewelsInStones(string jewels, string stones) {
        unordered_map<char, bool> jewelsType;
        int count = 0;
        for(const char &c: jewels){
            jewelsType[c] = true;
        }
        for(const char &t: stones){
            if(jewelsType[t]){
                count++;
            }
        }
        return count;
    }
};

int main(){
}
