package com.dishbreak.cci.arrays_and_strings;

public class Point {
    public Integer x;
    public Integer y;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void translate(Integer length) {
        int temp = 0;
        temp = this.x;
        this.x = this.y;
        this.y = length - temp;

    }

    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
