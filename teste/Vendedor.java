package teste;

import java.util.Scanner;

public class Vendedor extends Cadastro {
    String nomeDoProduto;
    double valorUnitarioDoProduto;
    String codigoDoProduto;
    Scanner digite;

    public Vendedor(String nome, String cpf, String email, String nomeDoProduto, double valorUnitarioDoProduto, String codigo, Tipo tipo) {
        super(nome, cpf, email,tipo);
        this.nomeDoProduto = nomeDoProduto;
        this.valorUnitarioDoProduto = valorUnitarioDoProduto;
        this.codigoDoProduto = codigo;
    }

    public Vendedor() {
        super();
        this.digite = new Scanner(System.in);
    }


}