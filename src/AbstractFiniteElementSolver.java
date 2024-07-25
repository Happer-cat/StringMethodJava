import Jama.Matrix;

import java.util.function.Function;

/**
 * @Author：Happer
 * @Package：PACKAGE_NAME
 * @Project：FiniteMethod
 * @name：AbstractFiniteElementSolver
 * @Date：2024/7/24 21:17
 * @Filename：AbstractFiniteElementSolver
 */
abstract class AbstractFiniteElementSolver implements IFiniteElementSolver {
    protected IMesh mesh;
    protected double f_L;
    protected double q_0;
    protected Function<Double, Double> s_func;

    public AbstractFiniteElementSolver(IMesh mesh, double f_L, double q_0, Function<Double, Double> s_func) {
        this.mesh = mesh;
        this.f_L = f_L;
        this.q_0 = q_0;
        this.s_func = s_func;
    }

    protected abstract Matrix s_func_values();

    protected abstract void applyBoundaryConditions(Matrix D, Matrix F);
}