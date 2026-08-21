package domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Banco {
    private final String nome;
    private final String codigo;
    private final List<Conta> contas;

    public Banco(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.contas = new ArrayList<>();
    }

    public boolean adicionarConta(Conta conta) {
        if (conta == null || buscarConta(conta.getNumeroConta()) != null) {
            return false;
        }

        contas.add(conta);
        return true;
    }

    public Conta buscarConta(String numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                return conta;
            }
        }

        return null;
    }

    public boolean transferir(String contaOrigem, String contaDestino, BigDecimal valor) {
        Conta origem = buscarConta(contaOrigem);
        Conta destino = buscarConta(contaDestino);

        if (origem == null || destino == null) {
            return false;
        }

        return origem.transferir(destino, valor);
    }

    public boolean encerrarConta(String numeroConta) {
        Conta conta = buscarConta(numeroConta);
        return conta != null && conta.encerrar();
    }

    public void processarFechamentoMensal() {
        for (Conta conta : contas) {
            conta.processarFechamentoMensal();
        }
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public List<Conta> getContas() {
        return Collections.unmodifiableList(new ArrayList<>(contas));
    }
}
