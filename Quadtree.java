package QuadTree;

import java.util.ArrayList;
import java.util.List;

/**
 * quadtree class
 */
public class Quadtree {
    static class Node {
        int x, y, value;

        /**
         *
         * @param x is the x coordinate
         * @param y y coordinate
         * @param value data of the node
         */
        Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public static class Boundary {
        /**
         *
         * @return the minimum x coordinate
         */
        public int getxMin() {
            return xMin;
        }
        /**
         *
         * @return the minimum y coordinate
         */
        public int getyMin() {
            return yMin;
        }
        /**
         *
         * @return the maximum x coordinate
         */
        public int getxMax() {
            return xMax;
        }
        /**
         *
         * @return the maximum y coordinate
         */
        public int getyMax() {
            return yMax;
        }

        /**
         *
         * @param xMin minimum x coordinate on the rectangular
         * @param yMin minimum y coordinate on the rectangular
         * @param xMax maximum x coordinate on the rectangular
         * @param yMax maximum x coordinate on the rectangular
         */
         public Boundary(int xMin, int yMin, int xMax, int yMax)  {
            super();
             if (xMin<0 || xMax>100 || yMin<0 || yMax>100){
                 System.exit(1);
             }
            /* storing the values */
            this.xMin = xMin;
            this.yMin = yMin;
            this.xMax = xMax;
            this.yMax = yMax;
        }

        /**
         * this function checks that whether given values are in the range or not
         * @param x
         * @param y
         * @return
         */
        public boolean inRange(int x, int y) {
            return (x >= this.getxMin() && x <= this.getxMax()
                    && y >= this.getyMin() && y <= this.getyMax());
        }

        int xMin, yMin, xMax, yMax;

    }

    /**
     * main quadtree class which has 4 data field
     */
    public static class QuadTree {
        final int MAX_CAPACITY =4;
        int level = 0;
        List<Node> nodes;
        QuadTree northWest = null;
        QuadTree northEast = null;
        QuadTree southWest = null;
        QuadTree southEast = null;
        Boundary boundary;

        public QuadTree(int level, Boundary boundry) {
            this.level = level;
            nodes = new ArrayList<Node>();
            this.boundary = boundry;
        }

        /* Traveling the quadtree using Depth First Search*/
        public static void dfs(QuadTree tree) {
            if (tree == null)
                return;

            System.out.printf("\n Level = %d X1=%d Y1=%d \t X2=%d Y2=%d ",
                    tree.level, tree.boundary.getxMin(), tree.boundary.getyMin(),
                    tree.boundary.getxMax(), tree.boundary.getyMax());

            for (Node node : tree.nodes) {
                System.out.printf(" \n\t  x=%d y=%d", node.x, node.y);
            }
            if (tree.nodes.size() == 0) {
                System.out.printf(" \n\t  Leaf Node");
            }
            dfs(tree.northWest);
            dfs(tree.northEast);
            dfs(tree.southWest);
            dfs(tree.southEast);
        }

        /**
         * splits the quadtree into the its partitions
         */
        void split() {
            int xOffset = this.boundary.getxMin()
                    + (this.boundary.getxMax() - this.boundary.getxMin()) / 2;
            int yOffset = this.boundary.getyMin()
                    + (this.boundary.getyMax() - this.boundary.getyMin()) / 2;

            northWest = new QuadTree(this.level + 1, new Boundary(
                    this.boundary.getxMin(), this.boundary.getyMin(), xOffset,
                    yOffset));
            northEast = new QuadTree(this.level + 1, new Boundary(xOffset,
                    this.boundary.getyMin(), xOffset, yOffset));
            southWest = new QuadTree(this.level + 1, new Boundary(
                    this.boundary.getxMin(), xOffset, xOffset,
                    this.boundary.getyMax()));
            southEast = new QuadTree(this.level + 1, new Boundary(xOffset, yOffset,
                    this.boundary.getxMax(), this.boundary.getyMax()));

        }

        /**
         * insert function which travels around the quadtree and finds a suitable node
         * @param x value to be added in terms of x coordinate
         * @param y value to be added in terms of y coordinate
         * @param value values data field
         */
        public void insert(int x, int y, int value) {
            if (!this.boundary.inRange(x, y)) {
                return;
            }

            Node node = new Node(x, y, value);
            if (nodes.size() < MAX_CAPACITY) {
                nodes.add(node);
                return;
            }
            if (northWest == null) {
                split();
            }

            /* checks the coordinates which partition it belongs */
            if (this.northWest.boundary.inRange(x, y))
                this.northWest.insert(x, y, value);
            else if (this.northEast.boundary.inRange(x, y))
                this.northEast.insert(x, y, value);
            else if (this.southWest.boundary.inRange(x, y))
                this.southWest.insert(x, y, value);
            else if (this.southEast.boundary.inRange(x, y))
                this.southEast.insert(x, y, value);
            else
                System.out.println("Wrong indexes ");
        }
    }

}