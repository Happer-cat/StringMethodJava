import Jama.Matrix;
import java.util.List;
import java.util.function.Function;

/**
 * @Author：Happer
 * @Package：PACKAGE_NAME
 * @Project：FiniteMethod
 * @name：FiniteElementSolver
 * @Date：2024/7/24 21:20
 * @Filename：FiniteElementSolver
 */
public class FiniteElementSolver extends AbstractFiniteElementSolver {

    public FiniteElementSolver(IMesh mesh, double f_L, double q_0, Function<Double, Double> s_func) {
        super(mesh, f_L, q_0, s_func);
    }

    @Override
    public Matrix solve() {
        int num_elements = mesh.getNumNodes() - 1;
        Matrix D = new Matrix(num_elements, num_elements);
        Matrix M = new Matrix(num_elements, num_elements + 1);
        Matrix F = new Matrix(num_elements, 1);

        List<IElement> elements = mesh.getElements(s_func);

        F.set(0, 0, -1 / 1.0);
        F.set(num_elements - 1, 0, Math.sin(1) / (1.0 / num_elements));

        for (int i = 0; i < num_elements; i++) {
            Matrix k_local = elements.get(i).getLocalStiffnessMatrix();
            Matrix m_local = elements.get(i).getLocalMassMatrix();
            Matrix f_local = elements.get(i).getLocalLoadVector();

            if (i < num_elements - 1) {
                D.setMatrix(i, i + 1, i, i + 1, D.getMatrix(i, i + 1, i, i + 1).plus(k_local));
                M.setMatrix(i, i + 1, i, i + 1, M.getMatrix(i, i + 1, i, i + 1).plus(m_local));
            }
        }

        // 修正质量矩阵的最后一列
        D.set(num_elements - 1, num_elements - 1, D.get(num_elements - 1, num_elements - 1) + elements.get(num_elements - 1).getLocalStiffnessMatrix().get(1, 1));
        M.set(num_elements - 1, num_elements - 1, M.get(num_elements - 1, num_elements - 1) + elements.get(num_elements - 1).getLocalMassMatrix().get(1, 1));
        M.set(num_elements - 1, num_elements, M.get(num_elements - 1, num_elements) + elements.get(num_elements - 1).getLocalMassMatrix().get(1, 1) / 2);

        System.out.println("全局刚度矩阵 D:\n" + D);
        System.out.println("全局质量矩阵 M:\n" + M);
        System.out.println("全局载荷向量 F:\n" + F);

        Matrix s_values = s_func_values();
        System.out.println("s:\n" + s_values);

        Matrix b = F.plus(M.times(s_values));

        System.out.println("修正后的载荷向量 b:\n" + b);

        return D.solve(b);
    }

    @Override
    protected Matrix s_func_values() {
        int num_elements = mesh.getNumNodes() - 1;
        Matrix s_values = new Matrix(num_elements + 1, 1);
        double[] nodes = mesh.getNodes();
        for (int i = 0; i <= num_elements; i++) {
            s_values.set(i, 0, s_func.apply(nodes[i]));
        }
        return s_values;
    }

    @Override
    protected void applyBoundaryConditions(Matrix D, Matrix F) {
        int num_elements = mesh.getNumNodes() - 1;
        D.set(num_elements - 1, 0, 0);
        D.set(num_elements - 1, num_elements - 1, 1.0);
        F.set(num_elements - 1, 0, f_L);

        F.set(0, 0, F.get(0, 0) - q_0);
    }
}
