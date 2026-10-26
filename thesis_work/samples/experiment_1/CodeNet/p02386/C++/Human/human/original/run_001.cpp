#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
using namespace std;

class Dice
{
private:
  int surf[6];
public:
  void set_surf(int* input);
  void roll(char orient);
  int search_id(int num);
  int get_top();
  void get_all(int *d);
  int get_right_id(int tp_id, int fw_id);
  void disp_top();
  void disp_all();
};

void Dice::set_surf(int* input)
{
  memcpy(surf, input, 6*sizeof(int));
}

void Dice::roll(char orient){
  int temp[6];
  
  switch(orient){
  case 'S':
    temp[0]=surf[4];
    temp[1]=surf[0];
    temp[2]=surf[2];
    temp[3]=surf[3];
    temp[4]=surf[5];
    temp[5]=surf[1];
    break;
  case 'N':
    temp[0]=surf[1];
    temp[1]=surf[5];
    temp[2]=surf[2];
    temp[3]=surf[3];
    temp[4]=surf[0];
    temp[5]=surf[4];
    break;
  case 'W':
    temp[0]=surf[2];
    temp[1]=surf[1];
    temp[2]=surf[5];
    temp[3]=surf[0];
    temp[4]=surf[4];
    temp[5]=surf[3];
    break;
  case 'E':
    temp[0]=surf[3];
    temp[1]=surf[1];
    temp[2]=surf[0];
    temp[3]=surf[5];
    temp[4]=surf[4];
    temp[5]=surf[2];
    break;
  }

  memcpy(surf, temp, 6*sizeof(int));
  
}

int Dice::search_id(int num){
  int ret_id;
  for (int i=0; i<6; i++){
    if(surf[i] == num){
      ret_id = i;
      break;
    }
  }

  return(ret_id);

}

int Dice::get_top(){
  return(surf[0]);
}

void Dice::get_all(int *d){
  for(int i=0; i<6; i++){
    d[i] = surf[i];
  }

}

void Dice::disp_top(){
  cout << surf[0] << endl;
}

void Dice::disp_all(){
  
  for (int i=0; i<6; i++){
    cout << surf[i];
  }
  cout << endl;
}


int Dice::get_right_id(int tp_id, int fw_id){
  int right[6][4][2] = {
    { {1, 2}, {2, 4}, {4, 3}, {3, 1} },
    { {0, 3}, {3, 5}, {5, 2}, {2, 0} },
    { {0, 1}, {1, 5}, {5, 4}, {4, 0} },
    { {0, 4}, {4, 5}, {5, 1}, {1, 0} },
    { {0, 2}, {2, 5}, {5, 3}, {3, 0} },
    { {1, 3}, {3, 4}, {4, 2}, {2, 1} }
  };

  int right_num;
  for(int i=0; i<4; i++){
    if(right[tp_id][i][0] == fw_id){
      right_num = surf[right[tp_id][i][1]];
//      cout << "  right_id=" << right[tp_id][i][1] << endl;
    }
  }
  
  return(right_num);
  
}


bool comp_dices(Dice *d1, Dice *d2){
  bool same_dice = false;
  int t_d1[6];
  int t_d2[6];
  
  d1->get_all(t_d1);
  d2->get_all(t_d2);

  for(int i=0; i<6; i++){
    bool same_num=false;
    int num = t_d1[i];
    for(int j=0; j<6; j++){
      if(t_d2[j] == num){
        same_num = true;
        break;
      }
    }
    if(!same_num){
      same_dice = false;
      return(same_dice);
    }
  }
  
//  d1->disp_top();
//  d2->disp_top();

//d1????????¢?????°????????????????????????d2????????????
  int surf =  d2->search_id(d1->get_top());
  switch(surf){
  case 1:
    d2->roll('N');
    break;
  case 2:
    d2->roll('W');
    break;
  case 3:
    d2->roll('E');
    break;
  case 4:
    d2->roll('S');
    break;
  case 5:
    d2->roll('N');
    d2->roll('N');
    break;
  case 0:
  default:
    break;
  }

  
//disp d1, d2
//  d1->disp_all();
//  d2->disp_all();
//  cout << endl;

  d1->get_all(t_d1);
  d2->get_all(t_d2);
  int d1_right,d2_right;

  int same_count = 0;
  for(int i=1; i<5; i++){
    d1_right = d1->get_right_id(0, i);
    for(int j=1; j<5; j++){
      if(t_d1[i] == t_d2[j]){
        d2_right = d2->get_right_id(0, j);
        if(d1_right == d2_right){
          same_count++;
          break;
        }
      }
    }
  }

//  cout << "count="<< same_count << endl;
  if(same_count==4){
    same_dice = true;
  }
  
  return(same_dice);
}

int main()
{
  int input[6], n;
  Dice *dice;
  string order;

  cin >> n;
  dice = new Dice [n];
  
  for(int i=0; i<n; i++){
    for(int j=0; j<6; j++){
      cin >> input[j];
    }
    dice[i].set_surf(input);
  }
  
//compare
  bool all_differ = true;
  for(int i=0; i<n-1; i++){
    for(int j=i+1; j<n; j++){
      if( comp_dices(&dice[i], &dice[j]) == true ){
        all_differ = false;
        break;
      }
    }
  }

  if(all_differ){
    cout << "Yes" << endl;
  }else{
    cout << "No" << endl;
  }
  return(0);
}