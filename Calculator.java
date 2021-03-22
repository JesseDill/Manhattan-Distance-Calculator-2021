/**
 * Handles all information and calculations involving points a and b. 
 */
package manhattanDistance; 
public class Calculator {
    private static Point a;
    private static Point b;

    private static class Point {
        int xCoord;
        int yCoord;

        Point(int xCoord, int yCoord) {
            this.xCoord = xCoord;
            this.yCoord = yCoord;
        }

        public String toString() {
            return String.format("(%d, %d)", xCoord, yCoord);
        }

    }

    public static void setPointA(int xCoord, int yCoord) {
        a = new Point(xCoord, yCoord);
    }

    public static void setPointB(int xCoord, int yCoord) {
        b = new Point(xCoord, yCoord);
    }

    public static Point getPointA() {
        return a;
    }

    public static Point getPointB() {
        return b;
    }

    /**
     * @return the integer value of manhattan distance, or -1 if points A or B
     *         aren't valid points
     */
    public static int manhattanDistance() {
        int xDist = Math.abs(a.xCoord - b.xCoord);
        int yDist = Math.abs(a.yCoord - b.yCoord);
        return xDist + yDist;
    }

}