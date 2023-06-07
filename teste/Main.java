package teste;

public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        Cadastrar cadastrar = new Cadastrar();
        cadastrar.cadastrarPessoas();

        Cadastro pessoa = login.logar(cadastrar.getCadastrar());
        System.out.println(pessoa);
    }
}
