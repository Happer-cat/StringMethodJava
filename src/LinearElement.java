import Jama.Matrix;

import java.util.function.Function;

/**
 * @Author：Happer
 * @Package：PACKAGE_NAME
 * @Project：FiniteMethod
 * @name：LinearElement
 * @Date：2024/7/24 21:18
 * @Filename：LinearElement
 */
public class LinearElement extends AbstractElement {

    public LinearElement(double x1, double x2, Function<Double, Double> s_func) {
        super(x1, x2, s_func);
    }

    @Override
    public Matrix getLocalStiffnessMatrix() {
        Matrix k_local = new Matrix(2, 2);
        double h = x2 - x1;
        k_local.set(0, 0, 1.0 / h);
        k_local.set(0, 1, -1.0 / h);
        k_local.set(1, 0, -1.0 / h);
        k_local.set(1, 1, 1.0 / h);
        return k_local;
    }

    @Override
    public Matrix getLocalMassMatrix() {
        Matrix m_local = new Matrix(2, 2);
        double h = x2 - x1;
        m_local.set(0, 0, h / 3.0);
        m_local.set(0, 1, h / 6.0);
        m_local.set(1, 0, h / 6.0);
        m_local.set(1, 1, h / 3.0);
        return m_local;
    }
//修改为简单数值积分
    @Override
    public Matrix getLocalLoadVector() {
        Matrix f_local = new Matrix(2, 1);
        double h = x2 - x1;
        double mid_point = (x1 + x2) / 2.0;
        f_local.set(0, 0, s_func.apply(mid_point) * h / 2);
        f_local.set(1, 0, s_func.apply(mid_point) * h / 2);
        return f_local;
    }
}