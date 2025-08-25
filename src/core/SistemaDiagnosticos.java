package core;

public class SistemaDiagnosticos {
    public static void main(String[] args) {
     SistemaFacade sistema = new SistemaFacade();
     sistema.executarFluxo("src/resources/dados.csv");
    }
}
