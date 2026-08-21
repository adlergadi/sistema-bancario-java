package domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private final LocalDateTime dataHora;
    private final TipoTransacao tipo;
    private final BigDecimal valor;
    private final String descricao;

    public Transacao(TipoTransacao tipo, BigDecimal valor, String descricao) {
        this.dataHora = LocalDateTime.now();
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format(
                "%s | %s R$ %.2f | %s",
                dataHora.format(formato),
                tipo.getSinal(),
                valor,
                descricao
        );
    }
}
