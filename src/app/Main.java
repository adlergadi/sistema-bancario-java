package app;

import domain.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco UnDF", "001");

        Endereco enderecoAdler = new Endereco("DF", "Brasília", "70000-000");
        enderecoAdler.setBairro("Jardim Botânico");
        enderecoAdler.setRua("14");
        enderecoAdler.setNumero("10");

        TitularConta adler = new TitularConta("Adler Gadioli", "065.708.345-12");
        adler.setDataNascimento(LocalDate.of(2006, 11, 16));
        adler.setEmail("adler@gmail.com");
        adler.setTelefone("(61) 99999-9999");
        adler.setEndereco(enderecoAdler);

        TitularConta eduarda = new TitularConta("Eduarda Costa", "22.656.777-89");

        Conta contaCorrenteAdler = new ContaCorrente(
                adler,
                "1001",
                "0001",
                "1234",
                500.00,
                25.00
        );

        Conta contaPoupancaEduarda = new ContaPoupanca(
                eduarda,
                "1002",
                "0001",
                "4321",
                0.60
        );

        banco.adicionarConta(contaCorrenteAdler);
        banco.adicionarConta(contaPoupancaEduarda);

        Autenticavel acesso = contaCorrenteAdler;
        System.out.println("Autenticação válida: " + acesso.autenticar("1234"));

        contaCorrenteAdler.depositar(1000.00);
        contaCorrenteAdler.depositar(300.00, "Depósito identificado: salário");
        contaPoupancaEduarda.depositar(800.00);

        contaCorrenteAdler.transferir( contaPoupancaEduarda, 250.00);
        contaPoupancaEduarda.sacar(100.00);


        for (Conta conta : banco.getContas()) {
            conta.imprimirExtrato();
        }
    }
}
