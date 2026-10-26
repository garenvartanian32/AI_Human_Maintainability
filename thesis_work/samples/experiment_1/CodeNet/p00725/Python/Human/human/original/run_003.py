def getNewBoard(board, blockRow, blockCol):
    newBoard = []
    for row in board:
        newBoard.append(row[:])
    newBoard[blockRow][blockCol] = 0
    return newBoard

def isValidCoord(row, col, ROWS, COLS):
    return row >= 0 and row < ROWS and col >= 0 and col < COLS

def throw(direction, board, ROWS, COLS, sRow, sCol, eRow, eCol):
    if direction == 'U':
        rAdj = -1
        cAdj = 0
        if not isValidCoord(sRow + rAdj, sCol + cAdj, ROWS, COLS) \
            or board[sRow + rAdj][sCol + cAdj] == 1:
            return None, None, None

    if direction == 'D':
        rAdj = 1
        cAdj = 0
        if not isValidCoord(sRow + rAdj, sCol + cAdj, ROWS, COLS) \
            or board[sRow + rAdj][sCol + cAdj] == 1:
            return None, None, None

    if direction == 'L':
        rAdj = 0
        cAdj = -1
        if not isValidCoord(sRow + rAdj, sCol + cAdj, ROWS, COLS) \
            or board[sRow + rAdj][sCol + cAdj] == 1:
            return None, None, None

    if direction == 'R':
        rAdj = 0
        cAdj = 1
        if not isValidCoord(sRow + rAdj, sCol + cAdj, ROWS, COLS) \
            or board[sRow + rAdj][sCol + cAdj] == 1:
            return None, None, None

    while True:
        sRow += rAdj
        sCol += cAdj
        if not isValidCoord(sRow, sCol, ROWS, COLS):
            return None, None, None
        if sRow == eRow and sCol == eCol:
            return None, sRow, sCol
        if isValidCoord(sRow + rAdj, sCol + cAdj, ROWS, COLS) and board[sRow + rAdj][sCol + cAdj] == 1:
            return getNewBoard(board, sRow + rAdj, sCol + cAdj), sRow, sCol

def play(board, ROWS, COLS, sRow, sCol, eRow, eCol, numThrows):
    global minThrows

    if board == None and sRow == None and sCol == None:
        return

    if minThrows != None and numThrows >= minThrows:
        return

    if numThrows > 10:
        return

    if sRow == eRow and sCol == eCol:
        minThrows = numThrows if minThrows == None else min(minThrows, numThrows)
        return

    directions = ['U', 'D', 'L', 'R']
    for d in directions:
        if numThrows < 10:
            newBoard, newRow, newCol = throw(d, board, ROWS, COLS, sRow, sCol, eRow, eCol)
            play(newBoard, ROWS, COLS, newRow, newCol, eRow, eCol, numThrows + 1)

if __name__ == '__main__':
    while True:
        COLS, ROWS = [ int(x) for x in list(filter(lambda x: x != '', \
            input().strip().split(' '))) ]
        if ROWS == 0 and COLS == 0:
            break

        sRow = None
        sCol = None
        eRow = None
        eCol = None

        board = []
        for r in range(ROWS):
            row = [ int(x) for x in list(filter(lambda x: x != '', \
                input().strip().split(' '))) ]
            board.append(row)

            if sRow == None and sCol == None and 2 in row:
                sRow = r
                sCol = row.index(2)
                board[sRow][sCol] = 0

            if eRow == None and eCol == None and 3 in row:
                eRow = r
                eCol = row.index(3)

        minThrows = None
        play(board, ROWS, COLS, sRow, sCol, eRow, eCol, 0)

        print(minThrows if minThrows != None else -1)
