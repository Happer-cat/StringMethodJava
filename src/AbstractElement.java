import java.util.function.Function;

/**
 * @Author：Happer
 * @Package：PACKAGE_NAME
 * @Project：FiniteMethod
 * @name：AbstractElement
 * @Date：2024/7/24 21:16
 * @Filename：AbstractElement
 */
abstract class AbstractElement implements IElement {
    protected double x1, x2;
    protected Function<Double, Double> s_func;

    public AbstractElement(double x1, double x2, Function<Double, Double> s_func) {
        this.x1 = x1;
        this.x2 = x2;
        this.s_func = s_func;
    }
}
