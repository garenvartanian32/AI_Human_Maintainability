# -*- coding: utf-8 -*-

answer = 0
def up(board, pos, count):
    global answer
    # print 'UP:'
    # print 'pos: ' + str(pos)
    # print 'count: ' + str(count)
    # for row in board:
    #     print row
    # print 

    y = pos[0]
    while y > 0:
        y -= 1
        if board[y][pos[1]] == 3:
            if count < answer:
                answer = count
            return count
        if board[y][pos[1]] == 1:
            board[y][pos[1]] = 0
            new_pos = [y+1, pos[1]]
            dfs(board, new_pos, count+1)
            board[y][pos[1]] = 1
            return



def down(board, pos, count):
    global answer
    # print 'DOWN:'
    # print 'pos: ' + str(pos)
    # print 'count: ' + str(count)
    # for row in board:
    #     print row
    # print 

    y = pos[0]
    while y < len(board) - 1:
        y += 1
        if board[y][pos[1]] == 3:
            if count < answer:
                answer = count
            return
        if board[y][pos[1]] == 1:
            board[y][pos[1]] = 0
            new_pos = [y-1, pos[1]]
            dfs(board, new_pos, count+1)
            board[y][pos[1]] = 1
            return

        

def left(board, pos, count):
    global answer
    # print 'LEFT:'
    # print 'pos: ' + str(pos)
    # print 'count: ' + str(count)
    # for row in board:
    #     print row
    # print 

    x = pos[1]
    while x > 0:
        x -= 1
        if board[pos[0]][x] == 3:
            if count < answer:
                answer = count
            return
        if board[pos[0]][x] == 1:
            board[pos[0]][x] = 0
            new_pos = [pos[0], x+1]
            dfs(board, new_pos, count+1)
            board[pos[0]][x] = 1
            return

def right(board, pos, count):
    global answer 
    # print 'RIGHT:'
    # print 'pos: ' + str(pos)
    # print 'count: ' + str(count)
    # for row in board:
    #     print row
    # print 

    x = pos[1]
    while x < len(board[0]) - 1:
        x += 1
        if board[pos[0]][x] == 3:
            if count < answer:
                answer = count
            return
        if board[pos[0]][x] == 1:
            board[pos[0]][x] = 0
            new_pos = [pos[0], x-1]
            dfs(board, new_pos, count+1)
            board[pos[0]][x] = 1
            return
    

def dfs(board, pos, count):    
    global answer
    if count > 10:
        return 11
    
    # print 'DFS:'
    # print 'pos: ' + str(pos)
    # print 'count: ' + str(count)

    # for row in board:
    #     print row
    # print 
            
    h = len(board)
    w = len(board[0])

    if count > answer:
        return

    if pos[0] - 1 >= 0 and board[pos[0]-1][pos[1]] != 1:
        up(board, pos, count)
    if pos[1] - 1 >= 0 and board[pos[0]][pos[1]-1] != 1:
        left(board, pos, count)
    if pos[0] + 1 < h and board[pos[0]+1][pos[1]] != 1:
        down(board, pos, count)
    if pos[1] + 1 < w and board[pos[0]][pos[1]+1] != 1:
        right(board, pos, count)

    return
                 
def func():
    '''

    '''
    global answer

    while True:
        w, h = map(int, raw_input().split())
        if w == 0 and h == 0:
            break
        board = []
        for i in range(h):
            row = map(int, raw_input().split())
            board.append(row)

        s = [0, 0]
        for i in range(h):
            for j in range(w):
                if board[i][j] == 2:
                    s[0] = i
                    s[1] = j
        answer = 11
        dfs(board, s, 1)
        if answer == 11:
            answer = -1
        print answer
    return None

if __name__ == '__main__':
    func()
    