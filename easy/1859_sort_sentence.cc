#include <iostream>
#include <map>
#include <string>

using namespace std;

class Solution {
public:
    string sortSentence(string s) {
        map<int, string> container;
        string temp = "";
        s += " ";
        int mxNum=0;
        for (size_t i = 0; i < s.length(); i++){
            if(!isspace(s.at(i))) {
                temp += s.at(i);
                continue;
            }
            int val = getIndex(s.at(i-1));
            container[val] = temp.substr(0, temp.length()-1);
            if(val > mxNum){
                mxNum = val;
            }
            temp="";
        }
        string ret = "";
        for(map<int,string>::iterator ii=container.begin(); ii != container.end(); ++ii) {  
            if(ii->first == mxNum){
                ret += ii->second;
                return ret;
            }
            ret += ii->second + " ";
        }
    };
    int getIndex(char c){
        string str = "";
        str += c;
        return stoi(str);
    }
};

int main(){
    string s = "hello2 world3 buy1";
    Solution *ls = new Solution();
    cout << ls->sortSentence(s) << endl;
}
