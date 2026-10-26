#include <iostream>
#include <string>
using namespace std;

class Dice {
public:
  int label[100][6] = {};
  string rotation = "EEENEEENEEESEEESEEENEEEN";

  void E(int label[]) {
    int rotate = label[0];
    label[0] = label[3];
    label[3] = label[5];
    label[5] = label[2];
    label[2] = rotate;
  }
  void N(int label[]) {
    int rotate = label[0];
    label[0] = label[1];
    label[1] = label[5];
    label[5] = label[4];
    label[4] = rotate;
  }
  void S(int label[]) {
    int rotate = label[0];
    label[0] = label[4];
    label[4] = label[5];
    label[5] = label[1];
    label[1] = rotate;
  }
  void W(int label[]) {
    int rotate = label[0];
    label[0] = label[2];
    label[2] = label[5];
    label[5] = label[3];
    label[3] = rotate;
  }

  bool comp(int label1[], int label2[]) {
    if (label1[0] == label2[0] &&
        label1[1] == label2[1] &&
        label1[2] == label2[2] &&
        label1[3] == label2[3] &&
        label1[4] == label2[4] &&
        label1[5] == label2[5]) return true;
    else return false;
  }
};

int main() {
  Dice dice;
  int n;
  {
    cin >> n;
    for (int i = 0; i < n; i++) for (int j = 0; j < 6; j++) cin >> dice.label[i][j];
  }

  bool is_different = true;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (i == j) break;

      if (dice.comp(dice.label[i], dice.label[j])) {
        cout << "No" << endl;
        return 0;
      }

      for (int k = 0; k < dice.rotation.size(); k++) {
        if (dice.rotation[k] == 'E') dice.E(dice.label[j]);
        else if (dice.rotation[k] == 'N') dice.N(dice.label[j]);
        else if (dice.rotation[k] == 'S') dice.S(dice.label[j]);
        else if (dice.rotation[k] == 'W') dice.W(dice.label[j]);

        if (dice.comp(dice.label[i], dice.label[j])) {
          is_different = false;
          break;
        } else is_different = true;
      }

      if (!is_different) {
        cout << "No" << endl;
        return 0;
      }
    }
  }

  cout << "Yes" << endl;

  return 0;
}
