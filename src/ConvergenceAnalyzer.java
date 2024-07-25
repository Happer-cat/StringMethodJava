import Jama.Matrix;
import java.util.Arrays;

public class ConvergenceAnalyzer implements IConvergenceAnalyzer {

    @Override
    public void analyze(double[] exactSolution, Matrix numericalSolution, double[] nodes) {
        int numNodes = exactSolution.length;

        double[] numericalArray = new double[numNodes];
        for (int i = 0; i < numNodes; i++) {
            numericalArray[i] = numericalSolution.get(i, 0);
        }

        double errorNorm = computeErrorNorm(exactSolution, numericalArray);
        double h = nodes[1] - nodes[0]; // Assuming uniform grid spacing

        printOutput(nodes, exactSolution, numericalArray, errorNorm, h, Double.NaN);
    }

    @Override
    public void analyze(double[] exactSolution1, Matrix numericalSolution1, double[] nodes1, double[] exactSolution2, Matrix numericalSolution2, double[] nodes2) {
        int numNodes1 = exactSolution1.length;
        int numNodes2 = exactSolution2.length;

        double[] numericalArray1 = new double[numNodes1];
        for (int i = 0; i < numNodes1; i++) {
            numericalArray1[i] = numericalSolution1.get(i, 0);
        }

        double[] numericalArray2 = new double[numNodes2];
        for (int i = 0; i < numNodes2; i++) {
            numericalArray2[i] = numericalSolution2.get(i, 0);
        }

        double errorNorm1 = computeErrorNorm(exactSolution1, numericalArray1);
        double errorNorm2 = computeErrorNorm(exactSolution2, numericalArray2);

        double h1 = nodes1[1] - nodes1[0]; // Assuming uniform grid spacing
        double h2 = nodes2[1] - nodes2[0]; // Assuming uniform grid spacing

        printOutput(nodes1, exactSolution1, numericalArray1, errorNorm1, h1, Double.NaN);
        printOutput(nodes2, exactSolution2, numericalArray2, errorNorm2, h2, Double.NaN);

        double p = (Math.log(errorNorm2 / errorNorm1) / Math.log(h2 / h1));
        System.out.println("收敛阶: " + p);
    }

    private double computeErrorNorm(double[] exactSolution, double[] numericalSolution) {
        double sum = 0.0;
        for (int i = 0; i < exactSolution.length; i++) {
            sum += Math.pow(exactSolution[i] - numericalSolution[i], 2);
        }
        return Math.sqrt(sum);
    }

    private void printOutput(double[] nodes, double[] exactSolution, double[] numericalArray, double errorNorm, double h, double convergenceOrder) {
        System.out.println("节点坐标: " + Arrays.toString(nodes));
        System.out.println("精确解: " + Arrays.toString(exactSolution));
        System.out.println("数值解: " + Arrays.toString(numericalArray));
        System.out.println("误差范数: " + errorNorm);
        System.out.println("网格间距 h: " + h);
        if (!Double.isNaN(convergenceOrder)) {
            System.out.println("收敛阶: " + convergenceOrder);
        }
    }
}
