---
config:
  theme: neo-dark
---
classDiagram
    %% =========================
    %% SISTEMA PRINCIPAL
    %% =========================
    class SistemaDiagnosticos {
        +main(args: String[]): void
    }

    class SistemaFacade {
      -carga: CargaDadosFacade
      -exameFacade: ExameFacade
      -procFacade: ProcessamentoFacade
      -notificadores: NotificadorFacade
      -laudoFacade: LaudoFacade
      +executarFluxo(caminhoCsv: String): void
      -processarExame(exameProcessado: Exame): void
    }
    %% =========================
    %% FACHADAS ESPECIALIZADAS
    %% =========================

    class CargaDadosFacade {
      -exameFacade: ExameFacade
      +carregarDados(caminhoCsv: String): List~Exame~
    }
    class ExameFacade {
        +agendarExame(tipoExame: String, codigo: String, valorBase: double, dataSolicitacao: Date, prioridade: Prioridade, paciente: Paciente, medico: Medico): Exame
        +pagarExame(exame: Exame): void
    }
    class LaudoFacade {
        -notificadores: NotificadorFacade
        +gerarLaudo(exame: Exame, formato: String, printConsole: boolean): String
    }
    class NotificadorFacade {
        -notificadores: List~NotificadorObserver~
        +notificarPaciente(exame: Exame, caminhoLaudo: String): void
        +adicionarNotificador(notificador: NotificadorObserver): void
    }
    class ProcessamentoFacade {
        -gerenciador: GerenciadorDeProcessamentoDeExames
        +enfileirarExame(exame: Exame): void
        +adicionarNotificadorGenerico(): void
        +processarExames(callback: Consumer~Exame~): void
        +getGerenciador(): GerenciadorDeProcessamentoDeExames
    }

    %% =========================
    %% ENTIDADES
    %% =========================
    class Paciente {
      -nome: String
      -cpf: String
      -dataNascimento: Date
      -email: String
      -sexo: Sexo
      -faixaEtaria: FaixaEtaria
      -temConvenio: boolean
      -exames: List~Exame~
      +getIdade(): int
      +adicionarExame(exame: Exame): void
      +getConvenio(): boolean
    }

    class Medico {
      -nome: String
      -CRM: String
      +solicitarExame(paciente: Paciente, tipoExame: String, fabrica: FabricaExame): Exame
    }


    %% =========================
    %% EXAMES E ESTADOS
    %% =========================
    class Exame {
      <<abstract>>
      -codigo: String
      -valorBase: double
      -dataSolicitacao: Date
      -prioridade: Prioridade
      -paciente: Paciente
      -medico: Medico
      -estado: StatusExameState
      -caminhoLaudo: String
      +getCodigo(): String
      +getValorBase(): double
      +getDataSolicitacao(): Date
      +getPrioridade(): Prioridade
      +getPaciente(): Paciente
      +getMedico(): Medico
      +getCaminhoLaudo(): String
      +getEstado(): StatusExameState
      +setEstado(estado: StatusExameState): void
      +avancarEstado(): void
      +cancelarExame(): void
    }
    %% =========================
    %% ENUMERAÇÕES
    %% =========================
  
    class Prioridade {
      <<enumeration>>
      +ALTA
      +MEDIA
      +BAIXA
    }

    class Sexo {
      <<enumeration>>
      +MASCULINO
      +FEMININO
    }
    class FaixaEtaria {
      <<enumeration>>
      +CRIANCA
      +ADULTO
      +IDOSO
    }

    class StatusExameState {
      <<interface>>
      +mudarEstadoExame(exame: Exame): void
      +cancelarExame(exame: Exame): void
    }

    class ExameSolicitado { 
      +mudarEstadoExame(exame: Exame): void
      +cancelarExame(exame: Exame): void 
      }
    class ExameProcessando { 
      +mudarEstadoExame(exame: Exame): void
      +cancelarExame(exame: Exame): void
      }
    class ExameConcluido { 
      +mudarEstadoExame(exame: Exame): void
      +cancelarExame(exame: Exame): void
      }
    class ExameCancelado { 
      +mudarEstadoExame(exame: Exame): void
      +cancelarExame(exame: Exame): void
      }

    class Hemograma {
      -hemoglobina: Double
      -leucocitos: Double
      -hematocrito: Double
      -plaquetas: Double
      +getHemoglobina(): Double
      +setHemoglobina(hemoglobina: Double): void
      +getLeucocitos(): Double
      +setLeucocitos(leucocitos: Double): void
      +getHematocrito(): Double
      +setHematocrito(hematocrito: Double): void
      +getPlaquetas(): Double
      +setPlaquetas(plaquetas: Double): void
    }

    class Ressonancia {
      -areaCorpo: String
      -comContraste: boolean
      +getAreaCorpo(): String
      +setAreaCorpo(areaCorpo: String): void
      +getComContraste(): boolean
      +setComContraste(comContraste: boolean): void
    }

    %% =========================
    %% FACTORY
    %% =========================
    class FabricaExame {
      <<interface>>
      +criarExame(codigo: String, valorBase: double, dataSolicitacao: Date, prioridade: Prioridade, paciente: Paciente, medico: Medico): Exame
    }

    class FabricaHemograma { 
      +criarExame(codigo: String, valorBase: double, dataSolicitacao: Date, prioridade: Prioridade, paciente: Paciente, medico: Medico): Exame 
    }
    class FabricaRessonancia { 
      +criarExame(codigo: String, valorBase: double, dataSolicitacao: Date, prioridade: Prioridade, paciente: Paciente, medico: Medico): Exame
    }

    class ExameFactoryRegistry {
      -registry: Map~String, FabricaExame~
      +registerFactory(key: String, factory: FabricaExame): void
      +getFactory(key: String): FabricaExame
    }

    %% =========================
    %% VALIDADORES
    %% =========================
    class ValidadorExame {
        <<interface>>
        +validar(exame: Exame): List~String~
    }
    class ValidadorBase {
        <<abstract>>
        +validarExameBase(exame: Exame): List~String~
    }
    class ValidadorHemograma {
        +validar(exame: Exame): List~String~
        +getStatusHemoglobina(valor: double, sexo: Sexo): String
        +getStatusLeucocitos(valor: double): String
        +getStatusHematocrito(valor: double, sexo: Sexo): String
        +getStatusPlaquetas(valor: double): String
    }
    class ValidadorRessonancia {
        -areasValidas: List~String~
        +validar(exame: Exame): List~String~
    }
    class ValidadorFactory {
       +criarValidador(exame: Exame): ValidadorExame 
    }

    %% =========================
    %% PAGAMENTO (STRATEGY)
    %% =========================
    class ProcessadorPagamento {
      -exame: Exame
      -descontoStrategy: DescontoStrategy
      -custoFinal: double
      +calcularCusto(): double
      +processarPagamento(): void
      +getCustoFinal(): double
      +setDescontoStrategy(estrategia: DescontoStrategy): void
    }

    class DescontoStrategy {
        <<interface>>
        +aplicarDesconto(valor: double): double
    }

    class DescontoConvenio {
        -PORCENTAGEM: double = 0.15
        +aplicarDesconto(valor: double): double
    }

    class DescontoIdoso {
        -PORCENTAGEM: double = 0.08
        +aplicarDesconto(valor: double): double
    }
    class DescontoComposto {
      -descontos: List~DescontoStrategy~
      +aplicarDesconto(valor: double): double
    }

    %% =========================
    %% LAUDOS (TEMPLATE METHOD)
    %% =========================
    class GeradorLaudo {
     -template: LaudoTemplate
      +gerar(cabecalho: String, corpo: String, rodape: String, nomeArquivo: String, printConsole: boolean): String
    }

    class LaudoTemplate {
      <<interface>>
      +gerarConteudo(cabecalho: String, corpo: String, rodape: String): String
      +salvarEmArquivo(conteudo: String, nomeArquivo: String): String
      +gerar(cabecalho: String, corpo: String, rodape: String, nomeArquivo: String, printConsole: boolean): String
    }

    class LaudoTexto {
      +gerarConteudo(cabecalho: String, corpo: String, rodape: String): String
      +salvarEmArquivo(conteudo: String, nomeArquivo: String): String
    }
    class LaudoHtml {
      +gerarConteudo(cabecalho: String, corpo: String, rodape: String): String
      +salvarEmArquivo(conteudo: String, nomeArquivo: String): String 
    }
    class LaudoPdf {
      +gerarConteudo(cabecalho: String, corpo: String, rodape: String): String
      +salvarEmArquivo(conteudo: String, nomeArquivo: String): String
    }
    class LaudoFactoryRegistry {
      -registry: Map~String, LaudoTemplate~
      +getTemplate(key: String): LaudoTemplate
      +registerTemplate(key: String, template: LaudoTemplate): void
    }
    %% =========================
    %% LAUDOS ESPECÍFICOS
    %% =========================
    class Laudo {
        <<interface>>
        +gerarCorpo(exame: Exame): String
    }
    class LaudoHemograma {
        -template: LaudoTemplate
        +gerarCorpo(exame: Exame): String
    }
    class LaudoRessonancia {
        -template: LaudoTemplate
        +gerarCorpo(exame: Exame): String
    }
    class LaudoFactory {
        +criarLaudo(exame: Exame, template: LaudoTemplate): Laudo
    }

    %% =========================
    %% DECORATOR DE LAUDOS
    %% =========================
    class DecoradorLaudo {
        <<abstract>>
      +DecoradorLaudo(laudo: LaudoTemplate)
      +gerarConteudo(cabecalho: String, corpo: String, rodape: String): String
      +salvarEmArquivo(conteudo: String, nomeArquivo: String): String
    }

    class DecoradorCarimbo {
      +gerarConteudo(cabecalho: String, corpo: String, rodape: String): String
    }
    class DecoradorRodapeConfidencial {
      +gerarConteudo(cabecalho: String, corpo: String, rodape: String): String
    }

    %% =========================
    %% OBSERVER (NOTIFICAÇÕES)
    %% =========================
    class NotificadorObserver {
        <<interface>>
        +atualizar(exame: Exame, caminhoLaudo: String): void
    }

    class NotificadorEmail {
        +atualizar(exame: Exame, caminhoLaudo: String): void
        -enviarEmail(destinatario: String, assunto: String, corpo: String, caminhoAnexo: String): void
    }
    class NotificadorTelegram {
        -chatId: String
        +atualizar(exame: Exame, caminhoLaudo: String): void
        -enviarMensagem(chatId: String, mensagem: String): void
    }

    %% =========================
    %% GERENCIADOR DE EXAMES
    %% =========================
    class GerenciadorDeProcessamentoDeExames {
        -filaExames: PriorityQueue~Exame~
        -notificadores: List~NotificadorObserver~
        +adicionarExame(exame: Exame): void
        +processarProximoExame(): Exame
        +notificarLaudoPronto(exame: Exame, caminhoLaudo: String): void
        +adicionarNotificador(notificador: NotificadorObserver): void
        +getNotificadores(): List~NotificadorObserver~
    }

    %% =========================
    %% RELACIONAMENTOS
    %% =========================
    SistemaDiagnosticos --> SistemaFacade
    SistemaFacade --> CargaDadosFacade
    SistemaFacade --> ExameFacade
    SistemaFacade --> ProcessamentoFacade
    SistemaFacade --> NotificadorFacade
    SistemaFacade --> LaudoFacade

    GerenciadorDeProcessamentoDeExames --> Exame
    GerenciadorDeProcessamentoDeExames --> NotificadorObserver
    ProcessamentoFacade --> GerenciadorDeProcessamentoDeExames

    ValidadorFactory --> ValidadorExame
    ValidadorExame <|.. ValidadorHemograma
    ValidadorExame <|.. ValidadorRessonancia
    ValidadorBase <|-- ValidadorHemograma
    ValidadorBase <|-- ValidadorRessonancia
    ProcessamentoFacade --> ValidadorFactory
    ValidadorHemograma ..> Hemograma
    ValidadorRessonancia ..> Ressonancia
    ValidadorRessonancia --> FaixaEtaria
    ValidadorHemograma --> Sexo

    ProcessadorPagamento --> Exame
    ProcessadorPagamento --> DescontoStrategy

    Laudo <|.. LaudoHemograma
    Laudo <|.. LaudoRessonancia
    LaudoFactory --> Laudo
    LaudoFactory --> LaudoTemplate
    LaudoHemograma --> LaudoTemplate
    LaudoRessonancia --> LaudoTemplate
    LaudoFacade --> LaudoFactory
    LaudoFacade --> Laudo

    GeradorLaudo --> LaudoTemplate
    LaudoTemplate <|-- LaudoTexto
    LaudoTemplate <|-- LaudoHtml
    LaudoTemplate <|-- LaudoPdf
    LaudoFacade --> LaudoFactoryRegistry
    LaudoFactoryRegistry --> LaudoTemplate
    LaudoFacade --> GeradorLaudo
    LaudoTemplate <|-- DecoradorLaudo

    DecoradorLaudo <|-- DecoradorCarimbo
    DecoradorLaudo o-- LaudoTemplate
    DecoradorLaudo <|-- DecoradorRodapeConfidencial
    LaudoFacade --> DecoradorCarimbo

    Exame --> StatusExameState
    Exame <|-- Hemograma
    Exame <|-- Ressonancia
    Exame --> Prioridade
    Exame --> LaudoTemplate

    Paciente --> Sexo
    Paciente --> FaixaEtaria

    StatusExameState <|.. ExameSolicitado
    StatusExameState <|.. ExameProcessando
    StatusExameState <|.. ExameConcluido
    StatusExameState <|.. ExameCancelado

    Paciente "1" *-- "*" Exame
    Medico "1" -- "*" Exame

    FabricaExame <|.. FabricaHemograma
    FabricaExame <|.. FabricaRessonancia
    ExameFacade --> ExameFactoryRegistry
    ExameFactoryRegistry --> FabricaExame

    DescontoStrategy <|.. DescontoConvenio
    DescontoStrategy <|.. DescontoIdoso
    DescontoStrategy <|.. DescontoComposto
    ExameFacade --> ProcessadorPagamento
    ProcessadorPagamento --> Exame
    ProcessadorPagamento --> DescontoStrategy

    NotificadorObserver <|.. NotificadorEmail
    NotificadorObserver <|.. NotificadorTelegram
    NotificadorFacade --> NotificadorObserver
    NotificadorObserver --> Exame
