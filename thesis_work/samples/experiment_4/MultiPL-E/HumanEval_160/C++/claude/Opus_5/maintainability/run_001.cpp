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

/// Applies one operator to its two operands.
static long applyOperator(const std::string& symbol, long left, long right) {
    if (symbol == "**") {
        long result = 1;
        for (long step = 0; step < right; ++step) {
            result *= left;
        }
        return result;
    }
    if (symbol == "*") {
        return left * right;
    }
    if (symbol == "//") {
        long quotient = left / right;
        if ((left % right != 0) && ((left < 0) != (right < 0))) {
            quotient--;  // C++ truncates towards zero; Python floors.
        }
        return quotient;
    }
    if (symbol == "+") {
        return left + right;
    }
    return left - right;
}

/// Applies every operator belonging to `level`, left to right.
static void collapse(std::vector<long>& values, std::vector<std::string>& symbols,
                     const std::set<std::string>& level) {
    std::size_t position = 0;
    while (position < symbols.size()) {
        if (level.count(symbols[position]) == 0) {
            ++position;
            continue;
        }

        values[position] = applyOperator(symbols[position], values[position], values[position + 1]);
        values.erase(values.begin() + position + 1);
        symbols.erase(symbols.begin() + position);
    }
}

long do_algebra(std::vector<std::string> op, std::vector<long> operand) {
    std::vector<long> values = operand;
    std::vector<std::string> symbols = op;

    // Operators are collapsed in precedence order: exponentiation binds
    // tightest, then multiplication and floor division, then the additive pair.
    const std::vector<std::set<std::string>> precedenceLevels = {
        {"**"}, {"*", "//"}, {"+", "-"}};

    for (const auto& level : precedenceLevels) {
        collapse(values, symbols, level);
    }

    return values[0];
}