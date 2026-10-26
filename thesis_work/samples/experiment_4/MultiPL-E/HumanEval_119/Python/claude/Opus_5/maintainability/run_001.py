def match_parens(lst):
    '''
    You are given a list of two strings, both strings consist of open
    parentheses '(' or close parentheses ')' only.
    Your job is to check if it is possible to concatenate the two strings in
    some order, that the resulting string will be good.
    A string S is considered to be good if and only if all parentheses in S
    are balanced. For example: the string '(())()' is good, while the string
    '())' is not.
    Return 'Yes' if there's a way to make a good string, and return 'No' otherwise.

    Examples:
    match_parens(['()(', ')']) == 'Yes'
    match_parens([')', ')']) == 'No'
    '''
    def is_balanced(text: str) -> bool:
        """True when every closing bracket is matched by an earlier opening one."""
        depth = 0
        for character in text:
            depth += 1 if character == '(' else -1
            if depth < 0:
                return False
        return depth == 0

    first, second = lst

    # Only two orderings exist, so testing both settles the question.
    if is_balanced(first + second) or is_balanced(second + first):
        return 'Yes'
    return 'No'