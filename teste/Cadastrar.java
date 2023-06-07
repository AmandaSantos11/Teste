package teste;

import java.util.ArrayList;
import java.util.List;

public class Cadastrar {

    public List<Cadastro> cadastrar = new ArrayList<>();

    public void cadastrarPessoas(){
        cadastrar.add(new Vendedor("Paulo","1212","paulonunes@outlook.com","pera",2.20,"001",Tipo.VENDEDOR));
    }

    public List<Cadastro> getCadastrar() {
        return cadastrar;
    }
}
