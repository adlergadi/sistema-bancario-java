package domain;

public enum TipoTransacao {
    DEPOSITO(true),
    SAQUE(false),
    TRANSFERENCIA_ENVIADA(false),
    TRANSFERENCIA_RECEBIDA(true),
    RENDIMENTO(true),
    TARIFA(false);

    private final boolean credito;

    TipoTransacao(boolean credito) {
        this.credito = credito;
    }

    public String getSinal() {
        return credito ? "+" : "-";
    }
}
