import Jama.Matrix;

/**
 * @Author：Happer
 * @Package：PACKAGE_NAME
 * @Project：FiniteMethod
 * @name：IConvergenceAnalyzer
 * @Date：2024/7/24 21:14
 * @Filename：IConvergenceAnalyzer
 */
public interface IConvergenceAnalyzer {
    void analyze(double[] exactSolution, Matrix numericalSolution, double[] nodes);

    void analyze(double[] exactSolution1, Matrix numericalSolution1, double[] nodes1, double[] exactSolution2, Matrix numericalSolution2, double[] nodes2);
}
