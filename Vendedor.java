import java.util.*;

public class Vendedor extends Cadastro {
    String nomeDoProduto;
    double valorUnitarioDoProduto;
    String codigoDoProduto;
    Scanner digite;

    public Vendedor(String nome, String cpf, String email, String nomeDoProduto, double valorUnitarioDoProduto, String codigo) {
        super(nome, cpf, email);
        this.nomeDoProduto = nomeDoProduto;
        this.valorUnitarioDoProduto = valorUnitarioDoProduto;
        this.codigoDoProduto = codigo;
    }

    public Vendedor() {
        super();
        this.digite = new Scanner(System.in);
    }

    List<Vendedor> vendedoresDaLojaLista = new ArrayList<>();
    Map<String, String> mapDoVendedorNoCadastro = new HashMap<>();
    public static Map<String, List<String>> mapProdutoComVendedor = new HashMap<>();

    public static Map<String, List<String>> mapEmail = new HashMap<>();

    public void adicionarVendedorNaLista() {
        vendedoresDaLojaLista.add(new Vendedor("João", "49123457", "joaocardoso@gmail.com", "Maça", 3.20, "000"));
        vendedoresDaLojaLista.add(new Vendedor("Nathalia", "81202351", "natluz@yahoo.com.br", "Caneta", 1.50, "001"));
        vendedoresDaLojaLista.add(new Vendedor("Paulo", "61293204", "paulonunes@outlook.com", "Travesseiro", 25, "002"));

        for (Cadastro cadastro : vendedoresDaLojaLista) {
            String emailDoMap = cadastro.getEmail();
            String cpfDoMap = cadastro.getCpf();
            mapDoVendedorNoCadastro.put(emailDoMap, cpfDoMap);
        }
    }

    public void mapDoVendedorComProduto() {
        for (Vendedor vendedor : vendedoresDaLojaLista) {
            String nome = vendedor.getNome();
            String nomeProduto = vendedor.getNomeDoProduto();
            String valorUnitario = String.valueOf(vendedor.getValorUnitarioDoProduto());
            String codigo = vendedor.getCodigoDoProduto();

            List<String> produtos = new ArrayList<>();
            produtos.add(nomeProduto);
            produtos.add(valorUnitario);
            produtos.add(codigo);

            mapProdutoComVendedor.put(nome, produtos);
        }
    }

    public void mapEmail() {
        for (Vendedor vendedor : vendedoresDaLojaLista) {
            String email = vendedor.getEmail();

            mapEmail.put(email, new ArrayList<>());
        }
    }

    public void mostrarVendedorComProduto() {
        for (Map.Entry<String, List<String>> entry : mapProdutoComVendedor.entrySet()) {
            String vendedor = entry.getKey();
            List<String> produtos = entry.getValue();

            System.out.println("Vendedor: " + vendedor);

            for (int i = 0; i < produtos.size(); i += 3) {
                String nomeProduto = produtos.get(i);
                String valorUnitario = produtos.get(i + 1);
                String codigo = produtos.get(i + 2);

                System.out.println("Produto: " + nomeProduto);
                System.out.println("Valor unitário:R$" + valorUnitario);
                System.out.println("Código:" + codigo);
                System.out.println("---------------------");
            }
        }
    }

    public void cadastrarNoSistema() {
        boolean continuarNome = false;
        boolean continuarCpf = false;
        boolean continuarEmail = false;
        System.out.println("Preencha os campos para se cadastrar:\n----------------------");
        do {
            try {
                System.out.println("Digite seu nome: ");
                nome = digite.nextLine();
                if (usuarioDigitouVazioNoNome(nome) || validarNome(nome)) {
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                } else {
                    continuarNome = true;
                }
            } catch (UnsupportedOperationException error) {
                System.err.println(error.getMessage());
            }
        } while (!continuarNome);
        do {
            try {
                System.out.println("Digite seu CPF: ");
                cpf = digite.nextLine();
                if (usuarioDigitouVazioNoCpf(cpf) || !validarCPF(cpf)) {
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if (mapDoVendedorNoCadastro.containsValue(cpf)) {
                    System.out.println("Esse CPF já está cadastrado!!");
                    MenuDaLoja.menuPrincipal();
                } else {
                    continuarCpf = true;
                }
            } catch (UnsupportedOperationException error) {
                System.err.println(error.getMessage());
            }
        } while (!continuarCpf);
        do {
            try {
                System.out.println("Digite seu email: ");
                email = digite.nextLine();
                if (usuarioDigitouVazioNoEmail(email) || !validarEmail()) {
                    continuarEmail = false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if (mapDoVendedorNoCadastro.containsKey(email)) {
                    System.out.println("Esse email já está cadastrado!!");
                    MenuDaLoja.menuPrincipal();
                }
                continuarEmail = true;
            } catch (UnsupportedOperationException error) {
                System.err.println(error.getMessage());
            }
        } while (!continuarEmail);
        boolean continuarProduto = false;
        boolean continuarValor = false;
        boolean continuarCodigo = false;
        System.out.println("Preencha os campos para cadastrar o seu produto:\n----------------------");
        do {
            try {
                System.out.println("Digite o nome do produto: ");
                nomeDoProduto = digite.nextLine();
                if (usuarioDigitouVazioNoNomeDoProduto(nomeDoProduto) || validarNomeDoProduto(nomeDoProduto)) {
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                } else {
                    continuarProduto = true;
                }
            } catch (UnsupportedOperationException error) {
                System.err.println(error.getMessage());
            }
        } while (!continuarProduto);
        do {
            try {
                System.out.println("Digite o valor unitário do produto: ");
                String usuarioDigitou = digite.nextLine();
                if (usuarioDigitouVazioNoValorUnitarioDoProduto(usuarioDigitou) || !validarUsuarioDigitou(usuarioDigitou)) {
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                } else {
                    valorUnitarioDoProduto = Double.parseDouble(usuarioDigitou);
                    continuarValor = true;
                }
            } catch (UnsupportedOperationException error) {
                System.err.println(error.getMessage());
            }
        } while (!continuarValor);
        do {
            try {
                System.out.println("Digite o código do produto: ");
                codigoDoProduto = digite.nextLine();
                if (usuarioDigitouVazioNoCodigoDoProduto(codigoDoProduto) || !validarCodigoDoProduto(codigoDoProduto)) {
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                } else {
                    continuarCodigo = true;
                    System.out.println("Seu produto foi cadastrado com sucesso!");
                }
            } catch (UnsupportedOperationException error) {
                System.err.println(error.getMessage());
            }
        } while (!continuarCodigo);
        Vendedor vendedorNovo = new Vendedor(nome, cpf, email, nomeDoProduto, valorUnitarioDoProduto, codigoDoProduto);
        vendedoresDaLojaLista.add(vendedorNovo);

    }

    @Override
    public void login() throws UnsupportedOperationException {
        Vendedor vendedor;
        boolean continuar = true;
        do {
            try {
                System.out.println("======LOGIN======");
                System.out.println("Digite seu email:");
                email = digite.nextLine();

                if (usuarioDigitouVazioNoEmail(email) || !validarEmail()) {
                    continuar = false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                for (int i = 0; i < vendedoresDaLojaLista.size(); i++) {
                    if (email.equals(vendedoresDaLojaLista.get(i).email)) {
                        vendedor = vendedoresDaLojaLista.get(i);

                    }

                }
//                if (mapDoVendedorNoCadastro.containsKey(email)) {
//                    continuar = true;
//                } else {
                System.out.println("Email não cadastrado!");
                MenuDaLoja.menuPrincipal();
//            }
        } catch(UnsupportedOperationException error){
            System.err.println(error.getMessage());
        }
    } while(!continuar);

}
//    List<String> vendas = Cliente.getVenda();

    public void historicoDeVendasDoVendedor() {
//        Cliente listaVendedor = new Cliente();
//        List<String> historicoDeVendasDoVendedor = listaVendedor.getHistoricoDeVendasDoVendedor();
        String escolhaDoUsuario;
        int escolhaConvertida = 0;
        boolean continuar = true;
        try {
            System.out.println("=====SEU HISTÓRICO DE VENDAS=====");
            if (mapEmail.values().size() == 0) {
//                vendas = new ArrayList<>();
//                throw new IndexOutOfBoundsException();
                System.out.println("não tem venda");
                MenuDaLoja.menuPrincipal();
            }

//            for (String venda : vendas) {
//                System.out.println(venda);
//            }
//            System.out.println("Vendedor: " + historicoDeVendasDoVendedor.get(0) + "\nCliente: " + historicoDeVendasDoVendedor.get(1) + "\nProduto vendido:" + historicoDeVendasDoVendedor.get(2) + "\nCódigo do produto:" + historicoDeVendasDoVendedor.get(3) + "\nValor unitário:R$" + historicoDeVendasDoVendedor.get(4) + "\nQuantidade comprada:" + historicoDeVendasDoVendedor.get(5) + "\nValor total da compra:R$" + historicoDeVendasDoVendedor.get(6) + "\n-----------");
            do {
                try {
                    System.out.println("Você deseja:\n1-Sair | 2-Voltar ao menu principal");
                    escolhaDoUsuario = digite.nextLine();
                    if (usuarioDigitouUmVazio(escolhaDoUsuario) || !(usuarioDigitouLetraAoInvesDeNumero(escolhaDoUsuario))) {
                        continuar = false;
                        throw new UnsupportedOperationException();
                    }
                    escolhaConvertida = Integer.parseInt(escolhaDoUsuario);
                    switch (escolhaConvertida) {
                        case 1:
                            System.out.println("Obrigado por utilizar nosso serviço!!");
                            System.exit(0);

                        case 2:
                            MenuDaLoja.menuPrincipal();
                            break;

                        default:
                            continuar = false;
                            throw new UnsupportedOperationException();
                    }
                } catch (UnsupportedOperationException error) {
                    System.err.println("Opção inválida, digite novamente");
                }
            }
            while (!(escolhaConvertida == 1 || escolhaConvertida == 2 || continuar));
        } catch (IndexOutOfBoundsException error) {
            System.out.println("Você não realizou nenhuma venda!!");
            MenuDaLoja.menuDoCliente();
        }
    }

    @Override
    public String toString() {
        return "Informações do Vendedor - Nome: " + nome + " Cpf: " + cpf + " Email: " + email;
    }


    //VERIFICAÇÃO VENDEDOR
    @Override
    public boolean usuarioDigitouVazioNoNome(String nome) {
        return nome.isBlank();
    }

    @Override
    public boolean usuarioDigitouVazioNoCpf(String cpf) {
        return cpf.isBlank();
    }

    @Override
    public boolean usuarioDigitouVazioNoEmail(String email) {
        return email.isBlank();
    }

    @Override
    public boolean validarEmail() {
        return (this.email.indexOf('@') > 0);
    }

    @Override
    public boolean validarCPF(String cpf) {
        return cpf.matches("[0-9]*");
    }

    @Override
    public boolean validarNome(String nome) {
        return nome.matches(".*\\d.*");
    }

    //VERIFICAÇÃO PRODUTO
    public boolean usuarioDigitouVazioNoNomeDoProduto(String nomeDoProduto) {
        return nomeDoProduto.isBlank();
    }

    public boolean usuarioDigitouVazioNoValorUnitarioDoProduto(String usuarioDigitou) {
        return usuarioDigitou.isBlank();
    }

    public boolean usuarioDigitouVazioNoCodigoDoProduto(String codigoDoProduto) {
        return codigoDoProduto.isBlank();
    }

    public boolean validarNomeDoProduto(String nomeDoProduto) {
        return nomeDoProduto.matches(".*\\d.*");
    }

    public boolean validarUsuarioDigitou(String usuarioDigitou) {
        try {
            Double.parseDouble(usuarioDigitou);
            return true;
        } catch (NumberFormatException error) {
            return false;
        }
    }

    public boolean validarCodigoDoProduto(String codigoDoProduto) {
        return codigoDoProduto.matches("[0-9]*");
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public double getValorUnitarioDoProduto() {
        return valorUnitarioDoProduto;
    }

    public String getCodigoDoProduto() {
        return codigoDoProduto;
    }

    public static Map<String, List<String>> getMapProdutoComVendedor() {
        return mapProdutoComVendedor;
    }

    public static Map<String, List<String>> getMapEmail() {
        return mapEmail;
    }

    public static boolean usuarioDigitouLetraAoInvesDeNumero(String escolhaDoUsuario) {
        return escolhaDoUsuario.matches("[0-9]*");
    }

    public static boolean usuarioDigitouUmVazio(String escolhaDoUsuario) {
        return escolhaDoUsuario.isBlank();
    }
}