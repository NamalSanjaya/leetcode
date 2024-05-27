#include <bits/stdc++.h>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

int main(){
    TreeNode l21(5);
    TreeNode l1(3, &l21, NULL);
    TreeNode r1(2);
    TreeNode root1(1, &l1, &r1);

    TreeNode ll22(4);
    TreeNode rr22(7);
    TreeNode ll1(1, NULL, &ll22);
    TreeNode rr1(3, NULL, &rr22);
    TreeNode root2(2, &ll1, &rr1);
    cout << endl;
}
