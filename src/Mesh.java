import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @Author：Happer
 * @Package：PACKAGE_NAME
 * @Project：FiniteMethod
 * @name：Mesh
 * @Date：2024/7/24 21:18
 * @Filename：Mesh
 */
public class Mesh implements IMesh {
    private double L;
    private int num_elements;
    private double[] nodes;

    public Mesh(double L, int num_elements) {
        this.L = L;
        this.num_elements = num_elements;
        this.nodes = new double[num_elements + 1];
        for (int i = 0; i <= num_elements; i++) {
            nodes[i] = i * (L / num_elements);
        }
    }

    @Override
    public int getNumNodes() {
        return num_elements + 1;
    }

    @Override
    public double[] getNodes() {
        return nodes;
    }

    @Override
    public List<IElement> getElements(Function<Double, Double> s_func) {
        List<IElement> elements = new ArrayList<>();
        for (int i = 0; i < num_elements; i++) {
            elements.add(new LinearElement(nodes[i], nodes[i + 1], s_func));
        }
        return elements;
    }
}
