package domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta implements Autenticavel {
    private final TitularConta titular;
    private final String numeroConta;
    private final String agencia;
    private final LocalDate dataCriacao;
    private String senha;
    private BigDecimal saldo;
    private StatusConta status;
    private final List<Transacao> extrato;

    public Conta(TitularConta titular, String numeroConta, String agencia, String senha) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.senha = senha;
        this.dataCriacao = LocalDate.now();
        this.status = StatusConta.ATIVA;
        this.saldo = BigDecimal.ZERO;
        this.extrato = new ArrayList<>();
    }

    @Override
    public boolean autenticar(String senhaInformada) {
        return senha != null && senha.equals(senhaInformada);
    }

    public boolean alterarSenha(String senhaAtual, String novaSenha) {
        if (!autenticar(senhaAtual) || novaSenha == null || novaSenha.isBlank()) {
            return false;
        }

        senha = novaSenha;
        return true;
    }

    public boolean depositar(BigDecimal valor) {
        return depositar(valor, "Depósito em conta");
    }

    public boolean depositar(BigDecimal valor, String descricao) {
        if (!podeMovimentar() || valorInvalido(valor)) {
            return false;
        }

        creditar(valor, TipoTransacao.DEPOSITO, descricao);
        return true;
    }

    public boolean sacar(BigDecimal valor) {
        if (!podeMovimentar() || valorInvalido(valor) || !possuiSaldoDisponivel(valor)) {
            return false;
        }

        debitar(valor, TipoTransacao.SAQUE, "Saque em conta");
        return true;
    }

    public boolean transferir(Conta contaDestino, BigDecimal valor) {
        if (contaDestino == null || contaDestino == this) {
            return false;
        }

        if (!podeMovimentar() || !contaDestino.podeMovimentar()) {
            return false;
        }

        if (valorInvalido(valor) || !possuiSaldoDisponivel(valor)) {
            return false;
        }

        saldo = saldo.subtract(valor);
        contaDestino.saldo = contaDestino.saldo.add(valor);

        registrarTransacao(
                TipoTransacao.TRANSFERENCIA_ENVIADA,
                valor,
                "Transferência para " + contaDestino.getTitular().getNome()
        );

        contaDestino.registrarTransacao(
                TipoTransacao.TRANSFERENCIA_RECEBIDA,
                valor,
                "Transferência recebida de " + titular.getNome()
        );

        return true;
    }

    public boolean bloquear() {
        if (status != StatusConta.ATIVA) {
            return false;
        }

        status = StatusConta.BLOQUEADA;
        return true;
    }

    public boolean ativar() {
        if (status != StatusConta.BLOQUEADA) {
            return false;
        }

        status = StatusConta.ATIVA;
        return true;
    }

    public boolean encerrar() {
        if (status == StatusConta.ENCERRADA || saldo.compareTo(BigDecimal.ZERO) != 0) {
            return false;
        }

        status = StatusConta.ENCERRADA;
        return true;
    }

    public void imprimirExtrato() {
        System.out.println("--------------------------------------------");
        System.out.println("Conta: " + numeroConta + " | " + getTipoConta());
        System.out.println("Titular: " + titular.getNome());

        for (Transacao transacao : extrato) {
            System.out.println(transacao);
        }

        System.out.printf("Saldo: R$ %.2f%n", saldo);
        System.out.printf("Saldo disponível: R$ %.2f%n", getSaldoDisponivel());
    }

    protected final void creditar(BigDecimal valor, TipoTransacao tipo, String descricao) {
        saldo = saldo.add(valor);
        registrarTransacao(tipo, valor, descricao);
    }

    protected final boolean debitar(BigDecimal valor, TipoTransacao tipo, String descricao) {
        if (valorInvalido(valor) || !possuiSaldoDisponivel(valor)) {
            return false;
        }

        saldo = saldo.subtract(valor);
        registrarTransacao(tipo, valor, descricao);
        return true;
    }

    protected BigDecimal getLimiteAdicional() {
        return BigDecimal.ZERO;
    }

    protected boolean podeMovimentar() {
        return status == StatusConta.ATIVA;
    }

    private boolean possuiSaldoDisponivel(BigDecimal valor) {
        return getSaldoDisponivel().compareTo(valor) >= 0;
    }

    private boolean valorInvalido(BigDecimal valor) {
        return valor == null || valor.compareTo(BigDecimal.ZERO) <= 0;
    }

    private void registrarTransacao(TipoTransacao tipo, BigDecimal valor, String descricao) {
        extrato.add(new Transacao(tipo, valor, descricao));
    }

    public abstract void processarFechamentoMensal();

    public abstract String getTipoConta();

    public TitularConta getTitular() {
        return titular;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal getSaldoDisponivel() {
        return saldo.add(getLimiteAdicional());
    }

    public StatusConta getStatus() {
        return status;
    }

    public List<Transacao> getExtrato() {
        return Collections.unmodifiableList(new ArrayList<>(extrato));
    }

}
