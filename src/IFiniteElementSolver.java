import Jama.Matrix;

/**
 * @Author：Happer
 * @Package：PACKAGE_NAME
 * @Project：FiniteMethod
 * @name：IFiniteElementSolver
 * @Date：2024/7/24 21:13
 * @Filename：IFiniteElementSolver
 */
public interface IFiniteElementSolver {
    Matrix solve();
}
