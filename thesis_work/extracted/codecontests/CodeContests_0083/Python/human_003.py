from decimal import Decimal
x, y, z = map(Decimal, input().split())
print(sorted(((y ** z * x.ln(), 8, 'x^y^z'),
              (z ** y * x.ln(), 7, 'x^z^y'),
              (y * z * x.ln(), 6, '(x^y)^z'),
              (x ** z * y.ln(), 5, 'y^x^z'),
              (z ** x * y.ln(), 4, 'y^z^x'),
              (x * z * y.ln(), 3, '(y^x)^z'),
              (x ** y * z.ln(), 2, 'z^x^y'),
              (y ** x * z.ln(), 1, 'z^y^x'),
              (x * y * z.ln(), 0, '(z^x)^y')))[-1][2])