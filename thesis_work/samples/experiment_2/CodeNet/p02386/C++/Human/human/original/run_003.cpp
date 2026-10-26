#include <iostream>
#include <cstdio>
#include <string>
#include <cmath>
#include <vector>
#include <algorithm>
#include <map>
#include <utility>

using namespace std;


class DICE
{
private:
	void swapMap(string a, string b);
public:
	map<string,int> dice;
	DICE(vector<int> &v);
	~DICE();
	void turnDice(char c);
	void rotateDice(char c);
	void setDice(string s1, string s2, int a, int b);
	void printDice(string s);
	bool judgeSame(DICE &d);
};

DICE::DICE(vector<int> &v){
	dice["top"] = v[0];
	dice["front"] = v[1];
	dice["right"] = v[2];
	dice["left"] = v[3];
	dice["back"] = v[4];
	dice["bottom"] = v[5];
}

DICE::~DICE(){
	
}

void DICE::swapMap(string a, string b){
	if(dice.end() == dice.find(a) || dice.end() == dice.find(b)) return;
	int tmp;
	tmp = dice[a];
	dice[a] = dice[b];
	dice[b] = tmp;
}

void DICE::turnDice(char c){
	switch(c){
		case 'N':
			swapMap("top","back");
			swapMap("top","bottom");
			swapMap("top","front");
			break;
		case 'E':
			swapMap("top","right");
			swapMap("top","bottom");
			swapMap("top","left");
			break;
		case 'S':
			swapMap("top","front");
			swapMap("top","bottom");
			swapMap("top","back");
			break;
		case 'W':
			swapMap("top","left");
			swapMap("top","bottom");
			swapMap("top","right");
			break;
		default :
			break;
	}
}

void DICE::rotateDice(char c){
	switch(c){
		case 'R':
			swapMap("front","right");
			swapMap("front","back");
			swapMap("front","left");
			break;
		case 'L':
			swapMap("front","left");
			swapMap("front","back");
			swapMap("front","right");
			break;
		default :
			break;
	}
}

void DICE::setDice(string s1, string s2, int a, int b){
	int cnt = 0;
	while(dice[s1] != a && cnt < 6){
		if(cnt % 2 == 0) this->turnDice('E');
		else this->turnDice('N');
		cnt++;
	}
	cnt = 0;
	while(dice[s2] != b && cnt < 4){
		this->rotateDice('R');
		cnt++;
	}
}

void DICE::printDice(string s){
	cout << dice[s] << endl;
}

bool DICE::judgeSame(DICE &d){
	DICE tmp(d);
	int cnt = 0;
	while(cnt < 6){
		if(cnt % 2 == 0) tmp.turnDice('E');
		else tmp.turnDice('N');
		if(this->dice["top"] != tmp.dice["top"]){
		}
		else{
			for(int i = 0; i < 4; i++){
				tmp.rotateDice('R');
				if(	(this->dice["right"] == tmp.dice["right"]) &&
					(this->dice["left"] == tmp.dice["left"]) &&
					(this->dice["front"] == tmp.dice["front"]) &&
					(this->dice["back"] == tmp.dice["back"]) &&
					(this->dice["bottom"] == tmp.dice["bottom"])) 
					return true;
			}
		}
		cnt++;
	}
	return false;
}

int main(){
	int n;
	vector<DICE> data;
	cin >> n;
	for(int i = 0; i < n; i++){
		vector<int> v;
		for(int j = 0; j < 6; j++){
			int tmp;
			cin >> tmp;
			v.push_back(tmp);
		}
		DICE dice(v);
		data.push_back(dice);
	}

	bool flag = true;
	for(int i = 0; i < n; i++){
		for(int j = i+1; j < n; j++){
			if(data[i].judgeSame(data[j])){
				flag = false;
				continue;
			}
		}
	}

	cout << (flag ? "Yes": "No") << endl;
}