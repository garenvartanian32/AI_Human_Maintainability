import java.util.Scanner;

class Vector {
    double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return
                (Math.abs(this.x) < 0000.1 ? "0.00000000" : this.x) +
                        " " +
                        (Math.abs(this.y) < 0000.1 ? "0.00000000" : this.y);
    }

    public Vector unit(Vector that) {
        return new Vector((that.x - this.x) / 3, (that.y - this.y) / 3);
    }

    public Vector plus(Vector that) {
        return new Vector(this.x + that.x, this.y + that.y);
    }

    public Vector rotate(double radians) {
        return new Vector(
                this.x * Math.cos(radians) - this.y * Math.sin(radians),
                this.x * Math.sin(radians) + this.y * Math.cos(radians)
        );
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Vector left = new Vector(0, 0);
        Vector right = new Vector(100, 0);
        System.out.println(left.toString());
        printKochPoints(
                left,
                right,
                0,
                n
        );
        System.out.println(right.toString());
    }

    static void printKochPoints(Vector l, Vector r, int i, int n) {
        if (i >= n) {
            return;
        }

        Vector unit = l.unit(r);

        Vector p1 = l.plus(unit);
        Vector p1p2 = unit.rotate(Math.PI / 3);
        Vector p2 = p1.plus(p1p2);
        Vector lp3 = unit.rotate(Math.PI);
        Vector p3 = r.plus(lp3);

        printKochPoints(l, p1, i + 1, n);
        System.out.println(p1.toString());
        printKochPoints(p1, p2, i + 1, n);
        System.out.println(p2.toString());
        printKochPoints(p2, p3, i + 1, n);
        System.out.println(p3.toString());
        printKochPoints(p3, r, i + 1, n);
    }
}
