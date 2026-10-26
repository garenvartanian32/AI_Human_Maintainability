#include <iostream>

using namespace std;

int width,height;
int map[21][21];
int answer;

int dx[] = {0,0,1,-1};
int dy[] = {-1,1,0,0};

void check(int x, int y, int count){
	int cx,cy;

	if(count>=answer) return;//èª10ð´¦Ä¢½çI¹

	for(int i=0 ; i<4 ; i++){//ãº¶ESûüÉ²×é
		cx = x+dx[i];
		cy = y+dy[i];
		
		if(cx<0 || cy<0 || cx>=width || cy>=height || map[cy][cx]==1){
			continue;//áQ¨ÉÔÂ©é©æÊOÉo½çcontinue
		}

		while(1){//[vÅêûüÉTõ·é
			if(map[cy][cx]==3){//S[É½çI¹
				if(answer>count)answer = count;
				return;
			}

			cx += dx[i];
			cy += dy[i];

			if(cx<0 || cy<0 || cx>=width || cy>=height){
				break;//æÊOÉo½çbreak
			}
			
			if(map[cy][cx]==1){//áQ¨ÉÔÂ©Á½çÄA
				map[cy][cx] = 0;
				check( cx-dx[i] , cy-dy[i] , count+1 );
				map[cy][cx] = 1;
				break;
			}
		}
	}
}


int main(){
	int y,x,sx,sy;

	while(1){
		//E³ÌüÍ
		cin >> width >> height;
		if(width==0 && height==0) break;//0 0¾Á½çI¹

		//zñmap[][]Élðãü
		for(y=0 ; y<height ; y++){
			for(x=0 ; x<width ; x++){
				cin >> map[y][x];
				if(map[y][x]==2){//2¾Á½çX^[gn_
					sx = x; sy = y;
				}
			}
		}
		answer = 11;
		check( sx , sy , 1 );//±ÌÖÅ²×é

		//¦ÌoÍ(answer>10¾Á½ç-1ðoÍ)
		if(answer>10) answer = -1;
		cout << answer << endl;
	}

	return 0;
}