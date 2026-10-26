class Dice():

    def __init__(self, dice_list):
        self.state = [0 for _ in range(6)]
        self.state[0] = dice_list[0]
        self.state[1] = dice_list[1]
        self.state[2] = dice_list[2]
        self.state[3] = dice_list[3]
        self.state[4] = dice_list[4]
        self.state[5] = dice_list[5]

    def north(self):
        tmp = self.state[0]
        self.state[0] = self.state[1]
        self.state[1] = self.state[5]
        self.state[5] = self.state[4]
        self.state[4] = tmp

    def west(self):
        tmp = self.state[0]
        self.state[0] = self.state[2]
        self.state[2] = self.state[5]
        self.state[5] = self.state[3]
        self.state[3] = tmp

    def south(self):
        tmp = self.state[0]
        self.state[0] = self.state[4]
        self.state[4] = self.state[5]
        self.state[5] = self.state[1]
        self.state[1] = tmp


    def east(self):
        tmp = self.state[0]
        self.state[0] = self.state[3]
        self.state[3] = self.state[5]
        self.state[5] = self.state[2]
        self.state[2] = tmp

    
if __name__ == '__main__':
    def is_same(dice_num1, dice_num2):
        dice1 = Dice(dice_num1)

        flag = False
        dice2 = Dice(dice_num2)
        for i in range(4):
            for j in range(4):
                if dice1.state[1] == dice2.state[1]:
                    flag = True
                    break
                dice2.east()

            if dice1.state[1] == dice2.state[1]:
                flag = True
                break

            dice2.north()

        if flag == False:
            dice2 = Dice(dice_num2)
            dice2.west()
            dice2.south()
            for i in range(4):
                if dice1.state[1] == dice2.state[1]:
                    flag = True
                    break
                dice2.west()

        if flag == False:
            dice2 = Dice(dice_num2)
            dice2.east()
            dice2.south()
            for i in range(4):
                if dice1.state[1] == dice2.state[1]:
                    break
                dice2.west()

        ans = 'No'
        for i in range(4):
            if dice1.state == dice2.state:
                ans = 'Yes'
                break
            dice2.west()

        if ans == 'Yes':
            return True
        else:
            return False

    N = int(input())
    L = [list(map(int, input().split())) for _ in range(N)]

    answer = 'Yes'
    for i in range(N):
        for j in range(N):
            if i != j:
                if is_same(L[i], L[j]):
                    answer = 'No'
        
        
    print(answer)
