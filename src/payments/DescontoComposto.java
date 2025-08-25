package payments;

import java.util.List;

public class DescontoComposto implements DescontoStrategy {
    private List<DescontoStrategy> descontos;

    public DescontoComposto(List<DescontoStrategy> descontos) {
        this.descontos = descontos;
    }

    @Override
    public double aplicarDesconto(double valor) {
        double valorFinal = valor;
        for (DescontoStrategy desconto : descontos) {
            valorFinal = desconto.aplicarDesconto(valorFinal);
        }
        return valorFinal;
    }
}
