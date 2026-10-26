N = int(input())
A = [int(input()) for _ in range(N)]
total = sum(A)
A = A + A

def check(next_piece, piece_size):
    for i in range(N):
        ni = next_piece[i]
        if ni == -1: continue
        if piece_size[i] >= total: continue

        ni %= N
        nni = next_piece[ni]
        if nni == -1: continue
        if total - piece_size[i] - piece_size[ni] >= mid:
            return True
    return False

def tow_pointers(mid):
    # しゃくとり法でインデックスの左側を全探索しつつ、合計がmid以上になる最小区間がどこまでかを求める
    # next_piece[i] := iから計算した合計がmid以上になる最小区間の終端インデックス
    # piece_size[i] := iから計算した合計がmid以上になる最小区間の終端インデックスまでの合計値
    next_piece = [-1] * N
    piece_size = [-1] * N
    right, amount = 0, 0
    for left in range(N):
        while right < N * 2 and amount < mid:
            amount += A[right]
            right += 1
        if amount >= mid:
            # amoutがmid以上の場合、それぞれの記録用配列に値を格納する
            next_piece[left] = right
            piece_size[left] = amount
        # ここの役割がわかんね、両方共なんだか必要に見えなくもない
        amount -= A[left]
    return next_piece, piece_size

# 二部探索
low, high = 0, 1 << 60
while high - low > 1:
    mid = low + (high - low) // 2
    next_piece, piece_size = tow_pointers(mid)
    if check(next_piece, piece_size):
        low = mid
    else:
        high = mid

print(low)


