
import QuadTree.Quadtree;
import QuadTree.Quadtree.Boundary;
public class main {
    public static void main(String args[]) {
        

        System.out.println("Quadtree:");

        Quadtree.QuadTree anySpace = new Quadtree.QuadTree(1, new Boundary(0, 0, 100, 100));
        anySpace.insert(30, 30, 1);
        Quadtree.QuadTree.dfs(anySpace);
        anySpace.insert(20, 15, 1);
        Quadtree.QuadTree.dfs(anySpace);
        anySpace.insert(50, 40, 1);
        Quadtree.QuadTree.dfs(anySpace);
        anySpace.insert(10, 12, 1);
        Quadtree.QuadTree.dfs(anySpace);
        anySpace.insert(40, 20, 1);
        Quadtree.QuadTree.dfs(anySpace);
        anySpace.insert(25, 60, 1);
        Quadtree.QuadTree.dfs(anySpace);
        anySpace.insert(15, 25, 1);
        System.out.println();

        Quadtree.QuadTree.dfs(anySpace);
        System.out.println();


    }
}
