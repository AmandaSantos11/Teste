package teste;
import java.util.Scanner;
public class Cadastro {
    protected Tipo tipo;
    public Scanner digite;
    protected String nome, cpf,email;
    public Cadastro(String nome, String cpf, String email,Tipo tipo) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.tipo = tipo;
        this.digite = new Scanner(System.in);
    }
    public Cadastro(){
        this.digite = new Scanner(System.in);
    }

    @Override
    public String toString() {
        return "Cadastro{" +
                "tipo=" + tipo +
                ", digite=" + digite +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    public void cadastrarNoSistema(){
    }
    public void login(){}
    public boolean usuarioDigitouVazioNoNome(String nome){
        return nome.isBlank();
    }
    public boolean usuarioDigitouVazioNoCpf(String cpf){
        return cpf.isBlank();
    }
    public boolean usuarioDigitouVazioNoEmail(String email){
        return email.isBlank();
    }
    public boolean validarEmail(){
        return (this.email.indexOf('@') > 0);
    }
    public boolean validarCPF(String cpf) {
        return cpf.matches("[0-9]*");
    }
    public boolean validarNome(String nome){
        return nome.matches(".*\\d.*");
    }
}