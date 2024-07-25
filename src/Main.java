import Jama.Matrix;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {

        double L = 1.0; // 求解范围
        int num_elements1 = 20; // 第一个网格细化
        int num_elements2 = 40; // 第二个网格细化

        double f_L = Math.sin(1.0); // Dirichlet 边界条件
        double q_0 = -1.0; // Neumann 边界条件

        // 第一个网格细化
        IMesh mesh1 = new Mesh(L, num_elements1);
        IFiniteElementSolver solver1 = new FiniteElementSolver(mesh1, f_L, q_0, Math::sin);
        Matrix solution1 = solver1.solve();

        double[] nodes1 = mesh1.getNodes();
        double[] exactSolution1 = new double[nodes1.length];
        //补充最后一项
        for (int i = 0; i < nodes1.length; i++) {
            exactSolution1[i] = Math.sin(nodes1[i]);
        }
        Matrix extendedSolution1 = new Matrix(solution1.getRowDimension()+1,1);
        for (int i = 0; i < solution1.getRowDimension(); i++) {
            extendedSolution1.set(i,0 ,solution1.get(i, 0));
        }
        extendedSolution1.set(extendedSolution1.getRowDimension()-1,0,exactSolution1[extendedSolution1.getRowDimension()-1]);

        // 第二个网格细化
        IMesh mesh2 = new Mesh(L, num_elements2);
        IFiniteElementSolver solver2 = new FiniteElementSolver(mesh2, f_L, q_0, Math::sin);
        Matrix solution2 = solver2.solve();

        double[] nodes2 = mesh2.getNodes();
        double[] exactSolution2 = new double[nodes2.length];
        for (int i = 0; i < nodes2.length; i++) {
            exactSolution2[i] = Math.sin(nodes2[i]);
        }
        //补充最后一项
        for (int i = 0; i < nodes2.length; i++) {
            exactSolution2[i] = Math.sin(nodes2[i]);
        }
        Matrix extendedSolution2 = new Matrix(solution2.getRowDimension()+1,1);
        for (int i = 0; i < solution2.getRowDimension(); i++) {
            extendedSolution2.set(i,0 ,solution2.get(i, 0));
        }
        extendedSolution2.set(extendedSolution2.getRowDimension()-1,0,exactSolution2[extendedSolution2.getRowDimension()-1]);

        IConvergenceAnalyzer analyzer = new ConvergenceAnalyzer();
        analyzer.analyze(exactSolution1, extendedSolution1, nodes1, exactSolution2, extendedSolution2, nodes2);
    }

}
