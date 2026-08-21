package domain;

import java.math.BigDecimal;

public class ContaCorrente extends Conta{
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
        this.limite = Math.max(limite, 0);
        this.taxaMensal = Math.max(taxaMensal, 0);
    }

    @Override
    protected BigDecimal getLimiteAdicional() {
        return limite;
    }

    @Override
    public void processarFechamentoMensal() {
        if (podeMovimentar() && taxaMensal > 0) {
            debitar(taxaMensal, TipoTransacao.TARIFA, "Cobrança da tarifa mensal");
        }
    }

    @Override
    public String getTipoConta() {
        return "Conta corrente";
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        if (limite >= 0) {
            this.limite = limite;
        }
    }

    public double getTaxaMensal() {
        return taxaMensal;
    }

    public void setTaxaMensal(double taxaMensal) {
        if (taxaMensal >= 0) {
            this.taxaMensal = taxaMensal;
        }
    }
}
