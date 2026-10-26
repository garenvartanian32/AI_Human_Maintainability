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

    long result = operand[0];
    for (size_t i = 0; i < op.size(); i++) {
        long next = operand[i+1];
        std::string &o = op[i];
        if (o == "+") result += next;
        else if (o == "-") result -= next;
        else if (o == "*") result *= next;
        else if (o == "//") result = (long) std::floor((double)result / next);
        else if (o == "**") result = (long) std::pow(result, next);
    }
    return result;
}