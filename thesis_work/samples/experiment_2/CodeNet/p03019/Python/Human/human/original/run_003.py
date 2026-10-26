# 写経
# にぶたん不要の解法
# https://atcoder.jp/contests/agc034/submissions/5768266
# generatorを作って
# 外でminする


def accumulate(a):
    """初項0"""
    su = 0
    yield su

    for x in a:
        su += x
        yield su


# class TestCaseReader:
#     def __init__(self):
#         self._gen = self.gen()
#
#     def gen(self):
#         import os
#         folder = r'XXX'
#         with open(os.path.join(folder, 'in.txt'), mode='r') as f:
#             for row in f:
#                 row = row.rstrip()
#                 yield row
#
#     def __call__(self):
#         return next(self._gen)


def solve_agc034c():
    from bisect import bisect_left
    from collections import namedtuple
    import sys
    input = sys.stdin.readline
    # input = TestCaseReader()

    Test = namedtuple('Test', 'Aoki_score lower_bound upper_bound')
    Test.Merit = lambda self, Takahashi_score: \
        self.lower_bound * Takahashi_score if Takahashi_score <= self.Aoki_score \
            else self.upper_bound * Takahashi_score - (self.upper_bound - self.lower_bound) * self.Aoki_score

    N, X = map(int, input().split())
    tests = []
    for _ in range(N):
        row = map(int, input().split())
        tests.append(Test(*row))
    tests.sort(key=lambda test: test.Merit(Takahashi_score=X), reverse=True)
    # 勝つ科目は重要度upper_bound,負ける科目は重要度lower_bound

    need = sum(test.lower_bound * test.Aoki_score for test in tests)

    *acc, = accumulate(test.Merit(Takahashi_score=X) for test in tests)

    bi = bisect_left(acc, need)  # bi問目まで完答でneed以上を達成(bi:1-indexed)=bi問解けばよい
    if bi == 0:
        print(0)  # 追加した
        return

    ge_need = acc[bi]  # need以上を達成するのに要した点数
    lt_need = acc[bi - 1]  # need未満の最大値を達成するのに要した点数

    def generate_candidates():
        INF = 10 ** 20
        for i, p in enumerate(tests, start=1):
            # p: partially_solved_test
            if i < bi:
                rest = need - (ge_need - p.Merit(Takahashi_score=X))
            else:
                rest = need - lt_need

            if rest <= p.Merit(Takahashi_score=p.Aoki_score):
                # 科目p単体の所要点数はAoki君以下でよく,lower_boundで計算
                add_ = (rest + p.lower_bound - 1) // p.lower_bound
            else:
                # 科目p単体の所要点数はAoki君を超える必要があり,upper_boundで計算
                add_ = p.Aoki_score
                add_ += (rest - p.Merit(Takahashi_score=p.Aoki_score) + p.upper_bound - 1) // p.upper_bound

            if add_ > X:
                yield INF
            else:
                ret = X * (bi - 1) + add_
                yield ret

    gen = generate_candidates()
    res = min(gen)
    print(res)
    return


if __name__ == '__main__':
    solve_agc034c()
