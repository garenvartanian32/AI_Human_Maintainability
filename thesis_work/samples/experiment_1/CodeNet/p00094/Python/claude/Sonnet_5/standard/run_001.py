import struct

def f32(x):
    return struct.unpack('f', struct.pack('f', x))[0]

def main():
    a, b = map(int, input().split())
    s = f32(f32(a * b) / f32(3.305785))
    print(f"{s:.6f}")

main()