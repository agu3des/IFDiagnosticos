package payments;

import model.exame.Exame;

public class ProcessadorPagamento {

    private Exame exame;
    private DescontoStrategy descontoStrategy;
    private double custoFinal;

    public ProcessadorPagamento(Exame exame, DescontoStrategy estrategia) {
        this.exame = exame;
    }

    public void setDescontoStrategy(DescontoStrategy estrategia) {
        this.descontoStrategy = estrategia;
    }

    public double calcularCusto() {
        if (descontoStrategy != null) custoFinal = descontoStrategy.aplicarDesconto(exame.getValorBase());
        else custoFinal = exame.getValorBase();
        return custoFinal;
    }

    public void processarPagamento() {
        calcularCusto();
        System.out.println("Pagamento processado. Valor final: " + custoFinal);
        // opcional: marcar exame como pago
    }

    public double getCustoFinal() { return custoFinal; }
}
