import Jama.Matrix;

/**
 * @Author：Happer
 * @Package：PACKAGE_NAME
 * @Project：FiniteMethod
 * @name：IElement
 * @Date：2024/7/24 21:12
 * @Filename：IElement
 */
public interface IElement {
    Matrix getLocalStiffnessMatrix();
    Matrix getLocalMassMatrix();
    Matrix getLocalLoadVector();
}
