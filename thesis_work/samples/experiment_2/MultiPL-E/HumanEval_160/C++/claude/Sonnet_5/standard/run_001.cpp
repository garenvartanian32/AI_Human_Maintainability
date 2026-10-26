#include<assert.h>
#include<bits/stdc++.h>
// Given two vectors operator, and operand. The first vector has basic algebra operations, and 
// the second vector is a vector of integers. Use the two given vectors to build the algebric 
// expression and return the evaluation of this expression.
// The basic algebra operations:
// Addition ( + ) 
// Subtraction ( - ) 
// Multiplication ( * ) 
// Floor division ( // ) 
// Exponentiation ( ** ) 
// Example:
// operator['+', '*', '-']
// vector = [2, 3, 4, 5]
// result = 2 + 3 * 4 - 5
// => result = 9
// Note:
// The length of operator vector is equal to the length of operand vector minus one.
// Operand is a vector of of non-negative integers.
// Operator vector has at least one operator, and operand vector has at least two operands.
long do_algebra(std::vector<std::string> op, std::vector<long> operand) {
    std::vector<long> values = operand;
    std::vector<std::string> ops = op;

    // Pass 1: exponentiation
    for (size_t i = 0; i < ops.size(); ) {
        if (ops[i] == "**") {
            long base = values[i], exp = values[i+1];
            long res = 1;
            for (long e = 0; e < exp; e++) res *= base;
            values[i] = res;
            values.erase(values.begin() + i + 1);
            ops.erase(ops.begin() + i);
        } else {
            i++;
        }
    }
    // Pass 2: * and //
    for (size_t i = 0; i < ops.size(); ) {
        if (ops[i] == "*" || ops[i] == "//") {
            long a = values[i], b = values[i+1];
            long res;
            if (ops[i] == "*") res = a * b;
            else {
                res = a / b;
                if ((a % b != 0) && ((a < 0) != (b < 0))) res--;
            }
            values[i] = res;
            values.erase(values.begin() + i + 1);
            ops.erase(ops.begin() + i);
        } else {
            i++;
        }
    }
    // Pass 3: + and -
    long result = values[0];
    for (size_t i = 0; i < ops.size(); i++) {
        long b = values[i+1];
        if (ops[i] == "+") result += b;
        else if (ops[i] == "-") result -= b;
    }
    return result;
}

int main() {
    std::vector<std::string> op = {"+","*","-"};
    std::vector<long> operand = {2,3,4,5};
    std::cout << do_algebra(op, operand) << std::endl;
    return 0;
}