#!/usr/bin/env python
# jump.py - Codeforces <!!! NNNA !!!> quiz
#
# Copyright (C) 2015 Sergey
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#       http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

"""
Let's call a level to be good if you can perform a sequence of four jumps of
the same length or in the other words there must be a sequence consisting of
five platforms so that the intervals between consecutive platforms are of the
same length. Given the scheme of the level, check if it is good.

Input

The first line contains integer n (1<=n<=100) - the number of segments on
the level.  Next line contains the scheme of the level represented as a string
of n characters '*' and '.'.

Output

If the level is good, print the word "yes" (without the quotes), otherwise
print the word "no" (without the quotes).

"""

# Standard libraries
import unittest
import sys
import re
import random

# Additional libraries


###############################################################################
# Jump Class
###############################################################################


class Jump:
    """ Jump representation """

    def __init__(self, args):
        """ Default constructor """

        self.args = args

        self.size = args[0]
        self.maze = args[1]
        self.dist = []
        self.cur_dist = []

        self.calc_dist()

    def calc_dist(self):
        distance = 0
        start = 0
        for m in self.maze:
            if not start:
                if m:
                    start = 1
                continue
            distance += 1
            if m:
                self.dist.append(distance)
                distance = 0

    def next(self):
        self.cur_dist[1] = self.cur_dist[0] + self.cur_dist[1]
        del(self.cur_dist[0])

    def iterate(self):
        len = self.cur_dist[0]
        hop_len = 0
        hops = 0
        for m in self.cur_dist:
            hop_len += m
            if hop_len == len:
                hop_len = 0
                hops += 1
            if hops == 4:
                return 1
            if hop_len > len:
                return 0
        return 0

    def iterate_all(self):
        while len(self.cur_dist) > 1:
            if self.iterate():
                return 1
            self.next()
        return 0

    def calculate(self):
        """ Main calcualtion function of the class """

        for i in range(len(self.dist)):
            self.cur_dist = list(self.dist[i:])
            if self.iterate_all():
                return "yes"
        return "no"

###############################################################################
# Executable code
###############################################################################


def decode_inputs(inputs):
    """ Decoding input string tuple into base class args tuple """

    # Decoding first input into an integer
    size = int(inputs[0])

    # Decoding second input into a list of ints
    maze = [0 if i == "." else 1 for i in inputs[1]]
    args = (size, maze)

    return args


def calculate(inputs):
    """ Base class calculate method wrapper """
    return Jump(decode_inputs(inputs)).calculate()


def main():
    """ Main function. Not called by unit tests """

    # Read test input string tuple
    inputs = (input(), input())

    # Print the result
    print(calculate(inputs))

###############################################################################
# Unit Tests
###############################################################################


class unitTests(unittest.TestCase):

    def test_decode_inputs(self):
        """ Input string decoding testing """
        self.assertEqual(decode_inputs(("4", ".*.*")), (4, [0, 1, 0, 1]))

    def test_Jump_class__basic_functions(self):
        """ Jump class basic functions testing """
        d = Jump((7, [0, 1, 0, 1, 1, 0, 1]))
        self.assertEqual(d.size, 7)

        # Distance listb
        self.assertEqual(d.dist, [2, 1, 2])

        # Next dist merge with the next one
        d.cur_dist = [2, 1, 2]
        d.next()
        self.assertEqual(d.cur_dist, [3, 2])

        # Iterate function, 1 - success
        d.cur_dist = [2, 2, 2, 2]
        self.assertEqual(d.iterate(), 1)

        d.cur_dist = [2, 1, 1, 2, 2]
        self.assertEqual(d.iterate(), 1)

        d.cur_dist = [2, 3]
        self.assertEqual(d.iterate(), 0)

        # Iterate all possible cur_dist
        d.cur_dist = [2, 1, 3, 3, 3]
        self.assertEqual(d.iterate_all(), 1)
        d.cur_dist = [2, 1, 3, 3, 2]
        self.assertEqual(d.iterate_all(), 0)

        # Calculate
        d.dist = [20, 2, 2, 2, 2]
        # self.assertEqual(d.calculate(), "yes")

    def test_calculate(self):
        """ Main calculation function """

        # Sample test 1
        self.assertEqual(calculate(("16", ".**.*..*.***.**.")), "yes")

        # Sample test2
        self.assertEqual(calculate(("11", ".*.*...*.*.")), "no")

if __name__ == "__main__":
    if sys.argv[-1] == "-ut":
        unittest.main(argv=[" "])
    main()
