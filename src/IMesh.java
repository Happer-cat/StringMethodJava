import java.util.List;
import java.util.function.Function;/**
 * @Author：Happer
 * @Package：PACKAGE_NAME
 * @Project：FiniteMethod
 * @name：IMesh
 * @Date：2024/7/24 20:10
 * @Filename：IMesh
 */

public interface IMesh {
    int getNumNodes();
    double[] getNodes();
    List<IElement> getElements(Function<Double, Double> s_func);
}



