import java.util.*;
public class Cliente extends Cadastro{
    public Cliente(String nome, String cpf, String email) {
        super(nome, cpf, email);
    }
    public Cliente() {
        super();
    }
    String nomeDoCliente;
    List<Cadastro> clientesDaLojaLista = new ArrayList<>();
    Map<String, String> mapDoClienteNoCadastro = new HashMap<>();
    Map<String, List<String>> mapProdutoComVendedor = Vendedor.getMapProdutoComVendedor();
    Map<String, List<String>> mapEmail = Vendedor.getMapEmail();
    Map<String, String> mapCpfNomeDoCliente = new HashMap<>();
    public static List<String> historicoDeVendasDoVendedor = new ArrayList<>();
    Map<String, List<String>> historicoCliente = new HashMap<>();
    public static List<String> compras = new ArrayList<>();

    public  List<String> venda = new ArrayList<>();
    public void adicionarClienteNaLista(){
        clientesDaLojaLista.add(new Cliente("Luana","00","luasol@gmail.com"));

        for (Cadastro cadastro : clientesDaLojaLista) {
            String emailDoMap = cadastro.getEmail();
            String cpfDoMap = cadastro.getCpf();
            mapDoClienteNoCadastro.put(emailDoMap, cpfDoMap);
        }
        for (Cadastro cadastro : clientesDaLojaLista) {
            String cpfDoMap = cadastro.getCpf();
            String nomeDoMap = cadastro.getNome();
            mapCpfNomeDoCliente.put(cpfDoMap, nomeDoMap);
        }
    }
    @Override
    public void cadastrarNoSistema(){
        adicionarClienteNaLista();
        boolean continuarNome=false;
        boolean continuarCpf=false;
        boolean continuarEmail=false;
        System.out.println("Preencha os campos para se cadastrar:\n----------------------");
        do{
            try{
                System.out.println("Digite seu nome: ");
                nome = digite.nextLine();
                if (usuarioDigitouVazioNoNome(nome) || validarNome(nome)) {
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                else{
                    continuarNome=true;
                }
            }catch (UnsupportedOperationException error) {
                System.err.println(error.getMessage());
            }
        }while (!continuarNome);
        do{
            try{
                System.out.println("Digite seu CPF: ");
                cpf = digite.nextLine();
                if (usuarioDigitouVazioNoCpf(cpf) || !validarCPF(cpf)) {
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if(mapDoClienteNoCadastro.containsValue(cpf)){
                    System.out.println("Esse CPF já está cadastrado!!");
                    MenuDaLoja.menuPrincipal();
                }
                else{
                    continuarCpf=true;
                }
            }catch (UnsupportedOperationException error) {
                System.err.println(error.getMessage());
            }
        }while (!continuarCpf);
        do{
            try{
                System.out.println("Digite seu email: ");
                email = digite.nextLine();
                if (usuarioDigitouVazioNoEmail(email) || !validarEmail()) {
                    continuarEmail=false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if(mapDoClienteNoCadastro.containsKey(email)){
                    System.out.println("Esse email já está cadastrado!!");
                    MenuDaLoja.menuPrincipal();
                }
                continuarEmail=true;
            }catch (UnsupportedOperationException error) {
                System.err.println(error.getMessage());
            }
        }while (!continuarEmail);
        Cliente clienteNovo = new Cliente(nome, cpf, email);
        clientesDaLojaLista.add(clienteNovo);
        System.out.println("Cadastro realizado com sucesso!");
    }
    @Override
    public void login() throws UnsupportedOperationException{
        boolean continuar=true;
        do{
            try{
                System.out.println("======LOGIN======");
                System.out.println("Digite seu CPF:");
                cpf = digite.nextLine();
                nomeDoCliente = mapCpfNomeDoCliente.get(cpf);
                compras = historicoCliente.get(cpf);

                venda = mapEmail.get(email);

                if (usuarioDigitouVazioNoCpf(cpf) || !validarCPF(cpf)) {
                    continuar = false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if(mapDoClienteNoCadastro.containsValue(cpf)){
                    continuar=true;
                }
                else{
                    System.out.println("CPF não cadastrado!");
                    MenuDaLoja.menuPrincipal();
                }
            }catch (UnsupportedOperationException error){
                System.err.println(error.getMessage());
            }
        }while(!continuar);
    }
    public void comprar() throws UnsupportedOperationException{
        boolean continuar=true;
        do {
            try {
                if(compras == null){
                    compras = new ArrayList<>();
                    venda = new ArrayList<>();
                    historicoCliente.put(cpf,compras);
                    mapEmail.put(email,venda);
                }
                System.out.println("Digite o nome do vendedor que deseja comprar: ");
                nome = digite.nextLine();
                if (usuarioDigitouVazioNoNome(nome) || validarNome(nome)) {
                    continuar = false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if (!(mapProdutoComVendedor.containsKey(nome))) {
                    System.err.println("Esse vendedor não existe, digite novamente");
                    continuar = false;
                }
                System.out.println("Digita a quantidade que deseja comprar: ");
                String quantidade = digite.nextLine();
                if(usuarioDigitouVazioNaQuantidade(quantidade)){
                    continuar = false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if(!(validarQuantidade(quantidade))){
                    System.err.println("Só números inteiros são permitidos, digite novamente");
                    continuar=false;
                }

                List<String> produtos = mapProdutoComVendedor.get(nome);

                if(produtos.size() != 0){
                    String nomeDoProdutoDoUsuario = produtos.get(0);
                    String valorUnitarioDoProdutoDoUsuario = produtos.get(1);
                    String codigoDoProdutoDoUsuario = produtos.get(2);
                    double valorTotalDaCompraDoUsuario = valorTotalDaCompraDoUsuario(quantidade,valorUnitarioDoProdutoDoUsuario);
                    String valorTotalConvertido = String.valueOf(valorTotalDaCompraDoUsuario);

                    System.out.println("Produto escolhido: "+nomeDoProdutoDoUsuario+"\nValor unitário:R$"+valorUnitarioDoProdutoDoUsuario+"\nQuantidade:"+quantidade+"\nValor total da compra:R$"+valorTotalDaCompraDoUsuario+"\n----------");

                    String compra = "Produto: "+nomeDoProdutoDoUsuario+"\nQuantidade: "+quantidade+"\nValor pago:R$"+valorTotalDaCompraDoUsuario+"\n----------";
                    compras.add(compra);

                    String info = "Nome: "+nome+"\nCliente: "+nomeDoCliente+
                            "\nnome: "+nomeDoProdutoDoUsuario+"\ncodigo: "+codigoDoProdutoDoUsuario+
                            "\nvalor: "+valorUnitarioDoProdutoDoUsuario+"\nquantidade: "+quantidade+
                            "\ntotal: "+valorTotalConvertido+"\n----------";
                    venda.add(info);

//                    historicoDeVendasDoVendedor.add(nome);
//                    historicoDeVendasDoVendedor.add(nomeDoCliente);
//                    historicoDeVendasDoVendedor.add(nomeDoProdutoDoUsuario);
//                    historicoDeVendasDoVendedor.add(codigoDoProdutoDoUsuario);
//                    historicoDeVendasDoVendedor.add(valorUnitarioDoProdutoDoUsuario);
//                    historicoDeVendasDoVendedor.add(quantidade);
//                    historicoDeVendasDoVendedor.add(valorTotalConvertido);
                }
                else{
                    continuar=true;
                }
            } catch (UnsupportedOperationException error) {
                System.err.println(error.getMessage());
            }
        } while(!continuar);
        MenuDaLoja.menuDoCliente();
    }
    public void historicoDeCompraDoCliente() throws UnsupportedOperationException {
        String escolhaDoUsuario;
        int escolhaConvertida=0;
        boolean continuar=true;
            System.out.println("=====SEU HISTÓRICO DE COMPRAS=====");
            if(compras == null){
                System.out.println("Você não realizou nenhuma compra!!\n----------");
                compras = new ArrayList<>();
                historicoCliente.put(cpf,compras);
                mapEmail.put(email,venda);
                MenuDaLoja.menuDoCliente();
            }
            for (String compraCliente : compras) {
                System.out.println(compraCliente);
            }
            do{
                try{
                    System.out.println("Você deseja:\n1-Sair | 2-Voltar ao menu principal");
                    escolhaDoUsuario=digite.nextLine();
                    if(usuarioDigitouUmVazio(escolhaDoUsuario) || !(usuarioDigitouLetraAoInvesDeNumero(escolhaDoUsuario))){
                        continuar=false;
                        throw new UnsupportedOperationException();
                    }
                    escolhaConvertida = Integer.parseInt(escolhaDoUsuario);
                    switch (escolhaConvertida){
                        case 1:

                            System.out.println("Obrigado por comprar conosco!!");
                            System.exit(0);

                        case 2:
                            MenuDaLoja.menuPrincipal();
                            break;

                        default:
                            continuar=false;
                            throw new UnsupportedOperationException();
                    }
                }catch (UnsupportedOperationException error) {
                    System.err.println("Opção inválida, digite novamente");
                }
            }
            while (!(escolhaConvertida==1 || escolhaConvertida==2 || continuar));
    }
    @Override
    public String toString() {
        return "Informações do Cliente - Nome: "+nome+" Cpf: "+cpf+" Email: "+email;
    }
    //VERIFICAÇÃO CLIENTE
    @Override
    public boolean usuarioDigitouVazioNoNome(String nome){
        return nome.isBlank();
    }
    @Override
    public boolean usuarioDigitouVazioNoCpf(String cpf){
        return cpf.isBlank();
    }
    @Override
    public boolean usuarioDigitouVazioNoEmail(String email){
        return email.isBlank();
    }
    @Override
    public boolean validarEmail(){
        return (this.email.indexOf('@') > 0);
    }
    @Override
    public boolean validarCPF(String cpf) {
        return cpf.matches("[0-9]*");
    }
    @Override
    public boolean validarNome(String nome){
        return nome.matches(".*\\d.*");
    }
    public boolean validarQuantidade(String quantidade) {
        return quantidade.matches("[0-9]*");
    }
    public boolean usuarioDigitouVazioNaQuantidade(String quantidade){
        return quantidade.isBlank();
    }
    public double valorTotalDaCompraDoUsuario(String quantidade, String valorUnitarioDoProdutoDoUsuario){
        int quantidadeConvertida = Integer.parseInt(quantidade);
        double valorUnitarioConvertido = Double.parseDouble(valorUnitarioDoProdutoDoUsuario);
        return quantidadeConvertida*valorUnitarioConvertido;
    }

    public static boolean usuarioDigitouLetraAoInvesDeNumero(String escolhaDoUsuario) {
        return escolhaDoUsuario.matches("[0-9]*");
    }
    public static boolean usuarioDigitouUmVazio(String escolhaDoUsuario){
        return escolhaDoUsuario.isBlank();
    }
    public static List<String> getHistoricoDeVendasDoVendedor() {
        return historicoDeVendasDoVendedor;
    }


}