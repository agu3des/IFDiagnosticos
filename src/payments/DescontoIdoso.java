package payments;

public class DescontoIdoso implements DescontoStrategy {

    private final double PORCENTAGEM = 0.08;

    public double aplicarDesconto(double valor) {
        return valor * (1 - PORCENTAGEM);
    }
}
