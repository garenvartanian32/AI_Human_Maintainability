import sys
sys.setrecursionlimit(4100000)

import math

import logging

logging.basicConfig(level=logging.DEBUG)

logger = logging.getLogger(__name__)


def dfs(set_list, n):
    global N
    for i, s in enumerate(set_list):
        new_set = s.copy()
        new_set.add(n)
        new_set_list = set_list.copy()
        new_set_list[i] = new_set
        if n == N - 1:
            min_string(new_set_list)
        else:
            dfs(new_set_list, n + 1)
    set_list.append(set([n]))
    if n == N - 1:
        min_string(set_list)
    else:
        dfs(set_list, n + 1)


def min_string(set_list):
    global N

    j = 0
    ret_list = [0 for _ in range(N)]
    for s in set_list:
        for i in s:
            ret_list[i] = j

        j += 1

    print(''.join([chr(j + ord("a")) for j in ret_list]))


def resolve():
    global N
    # S = [x for x in sys.stdin.readline().split()][0]  # 文字列 一つ
    N = [int(x) for x in sys.stdin.readline().split()][0]  # int 一つ
    # N, D = [int(x) for x in sys.stdin.readline().split()]  # 複数int
    # h_list = [int(x) for x in sys.stdin.readline().split()]  # 複数int

    # grid = [list(sys.stdin.readline().split()[0]) for _ in range(N)]  # 文字列grid
    # v_list = [int(sys.stdin.readline().split()[0]) for _ in range(N)]
    # grid = [[int(x) for x in sys.stdin.readline().split()]
    #         for _ in range(N)]  # int grid

    logger.debug('{}'.format([]))

    dfs([], 0)


if __name__ == "__main__":
    resolve()

# AtCoder Unit Test で自動生成できる, 最後のunittest.main は消す
# python -m unittest template/template.py で実行できる
# pypy3 -m unittest template/template.py で実行できる

import sys
from io import StringIO
import unittest

import sys
from io import StringIO
import unittest


class TestClass(unittest.TestCase):
    def assertIO(self, input, output):
        stdout, stdin = sys.stdout, sys.stdin
        sys.stdout, sys.stdin = StringIO(), StringIO(input)
        resolve()
        sys.stdout.seek(0)
        out = sys.stdout.read()[:-1]
        sys.stdout, sys.stdin = stdout, stdin
        self.assertEqual(out, output)

    def test_入力例_1(self):
        input = """1"""
        output = """a"""
        self.assertIO(input, output)

    def test_入力例_2(self):
        input = """2"""
        output = """aa
ab"""
        self.assertIO(input, output)
