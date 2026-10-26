class Book:
    def __init__(self, time, for_alice, for_bob):
        self.time = time
        self.for_alice = bool(for_alice)
        self.for_bob = bool(for_bob)
        self.status = ('A' if self.for_alice else '') + ('B' if self.for_bob else '')


def get(li, i):
    try:
        return li[i].time
    except IndexError:
        return 10 ** 10


n, k = map(int, input().split())
books = [Book(*map(int, input().split())) for _ in range(n)]
alice_rest, bob_rest = k, k

for_alice_books = list(filter(lambda x: x.status == 'A', books))
for_bob_books = list(filter(lambda x: x.status == 'B', books))
for_both_books = list(filter(lambda x: x.status == 'AB', books))

for_alice_books = sorted(for_alice_books, key=lambda x: x.time)
for_bob_books = sorted(for_bob_books, key=lambda x: x.time)
for_both_books = sorted(for_both_books, key=lambda x: x.time)

for_alice_books_index, for_bob_books_index, for_both_books_index = 0, 0, 0

total = 0
while alice_rest > 0 or bob_rest > 0:
    alice_var = get(for_alice_books, for_alice_books_index)
    bob_var = get(for_bob_books, for_bob_books_index)
    both_var = get(for_both_books, for_both_books_index)

    if all(i == 10 ** 10 for i in [alice_var, bob_var, both_var]):
        total = -1
        break

    if alice_rest > 0 and bob_rest > 0:

        if alice_var + bob_var > both_var:
            for_both_books_index += 1
            total += both_var
        else:
            for_alice_books_index += 1
            for_bob_books_index += 1
            total += alice_var + bob_var

        alice_rest -= 1
        bob_rest -= 1

    elif alice_rest == 0:
        if bob_var > both_var:
            for_both_books_index += 1
            total += both_var
        else:
            for_bob_books_index += 1
            total += bob_var

        bob_rest -= 1

    else:
        if alice_var > both_var:
            for_both_books_index += 1
            total += both_var
        else:
            for_alice_books_index += 1
            total += alice_var

        alice_rest -= 1

print(total if total < 10 ** 10 else -1)





