def correct_bracketing(brackets: str):
    balance = 0
    for bracket in brackets:
        balance += 1 if bracket == "(" else -1
        if balance < 0:
            return False
    return balance == 0