package payments;

public class DescontoConvenio implements DescontoStrategy {

    private final double PORCENTAGEM = 0.15;

    public double aplicarDesconto(double valor) {
        return valor * (1 - PORCENTAGEM);
    }
}
