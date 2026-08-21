package domain;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta {
    private double taxaRendimento;

    public ContaPoupanca(
            TitularConta titular,
            String numeroConta,
            String agencia,
            String senha,
            double taxaRendimento
    ) {
        super(titular, numeroConta, agencia, senha);
        this.taxaRendimento = Math.max(taxaRendimento, 0);
    }

    @Override
    public void processarFechamentoMensal() {
        if (podeMovimentar()
                && getSaldo().compareTo(BigDecimal.ZERO) > 0
                && taxaRendimento > 0) {

            BigDecimal rendimento = getSaldo()
                    .multiply(BigDecimal.valueOf(taxaRendimento))
                    .divide(BigDecimal.valueOf(100));

            creditar(rendimento, TipoTransacao.RENDIMENTO, "Rendimento mensal da poupança");
        }
    }

    @Override
    public String getTipoConta() {
        return "Conta poupança";
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        if (taxaRendimento >= 0) {
            this.taxaRendimento = taxaRendimento;
        }
    }
}
