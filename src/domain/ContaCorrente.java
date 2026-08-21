package domain;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {
    private BigDecimal limite;
    private BigDecimal taxaMensal;

    public ContaCorrente(
            TitularConta titular,
            String numeroConta,
            String agencia,
            String senha,
            BigDecimal limite,
            BigDecimal taxaMensal
    ) {
        super(titular, numeroConta, agencia, senha);
        this.limite = limite != null
                && limite.compareTo(BigDecimal.ZERO) >= 0
                ? limite
                : BigDecimal.ZERO;
        this.taxaMensal = taxaMensal != null
                && taxaMensal.compareTo(BigDecimal.ZERO) >= 0
                ? taxaMensal
                : BigDecimal.ZERO;
    }

    @Override
    protected BigDecimal getLimiteAdicional() {
        return limite;
    }

    @Override
    public void processarFechamentoMensal() {
        if (podeMovimentar()
                && taxaMensal.compareTo(BigDecimal.ZERO) > 0) {
            debitar(taxaMensal, TipoTransacao.TARIFA, "Cobrança da tarifa mensal");
        }
    }

    @Override
    public String getTipoConta() {
        return "Conta corrente";
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        if (limite != null
                && limite.compareTo(BigDecimal.ZERO) >= 0) {
            this.limite = limite;
        }
    }

    public BigDecimal getTaxaMensal() {
        return taxaMensal;
    }

    public void setTaxaMensal(BigDecimal taxaMensal) {
        if (taxaMensal != null
                && taxaMensal.compareTo(BigDecimal.ZERO) >= 0) {
            this.taxaMensal = taxaMensal;
        }
    }
}
