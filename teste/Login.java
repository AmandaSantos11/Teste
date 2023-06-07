package teste;

import java.util.List;
import java.util.Scanner;

public class Login {

    Scanner digite = new Scanner(System.in);

    public Cadastro logar(List<Cadastro> cadastrar){
        System.out.println("Deseja logar como:\n1-Vendedor | 2-Cliente");
        int escolha = digite.nextInt();
        Cadastro cadastro=null;


        switch (escolha){
            case 1:
                cadastro = loginEmail(cadastrar);
                break;

            case 2:
                cadastro = loginCpf(cadastrar);
                break;

            default:
                break;
//                throw new IllegalArgumentException("Opção inválida");
        }
    return cadastro;
    }
    public Vendedor loginEmail(List<Cadastro> cadastrar){
        String dadoDigitado;
        Vendedor vendedor=null;
        System.out.println("======LOGIN======");
        System.out.println("Digite seu email:");
        dadoDigitado = digite.next();

        for (int i = 0; i < cadastrar.size(); i++) {
            if(dadoDigitado.equals(cadastrar.get(i).email)) {
                vendedor=(Vendedor) cadastrar.get(i);
            }
        }
        return vendedor;
    }

    public Cliente loginCpf(List<Cadastro> cadastrar){
        String dadoDigitado;
        Cliente cliente=null;
        System.out.println("======LOGIN======");
        System.out.println("Digite seu CPF:");
        dadoDigitado = digite.next();

        for (int i = 0; i < cadastrar.size(); i++) {
            if(dadoDigitado.equals(cadastrar.get(i).cpf)) {
                cliente=(Cliente) cadastrar.get(i);
            }
        }
        return cliente;
    }
}
