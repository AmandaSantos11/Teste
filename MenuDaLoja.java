import java.util.Scanner;
public class MenuDaLoja {
    public static Scanner digite = new Scanner(System.in);
    static Vendedor vendedor = new Vendedor();
    static Cliente cliente = new Cliente();
    public static void menuPrincipal() throws UnsupportedOperationException{
        String escolhaDoUsuario;
        int escolhaConvertida=0;
        boolean continuar=true;
        do{
            System.out.println("=====Bem-Vindo a nossa loja=====\n             MENU                ");
            System.out.println("Você deseja:\n1-Fazer Login | 2-Cadastrar-se | 3-Sair");
            escolhaDoUsuario = digite.nextLine();
            try{
                if(usuarioDigitouUmVazio(escolhaDoUsuario) || !(usuarioDigitouLetraAoInvesDeNumero(escolhaDoUsuario))){
                    continuar=false;
                    throw new UnsupportedOperationException();
                }
                escolhaConvertida = Integer.parseInt(escolhaDoUsuario);
                switch (escolhaConvertida){
                    case 1:
                        opcaoLogin();
                        break;

                    case 2:
                        opcaoCadastro();
                        break;
                    case 3:

                        System.out.println("Obrigado por comprar conosco!!");
                        System.exit(0);

                    default:
                        continuar=false;
                        throw new UnsupportedOperationException();
                }
            }
            catch (UnsupportedOperationException error) {
                System.err.println("Opção inválida, tente novamente");
            }
        }
        while (!(escolhaConvertida==1 || escolhaConvertida==2 || escolhaConvertida==3 || continuar));
    }
    public static void opcaoCadastro() throws UnsupportedOperationException{
        vendedor.adicionarVendedorNaLista();
        cliente.adicionarClienteNaLista();
        vendedor.mapDoVendedorComProduto();
        String escolhaDoUsuario;
        int escolhaConvertida=0;
        boolean continuar=true;
        do{
            System.out.println("---------------------\nDeseja se cadastrar como:\n1-Cliente | 2-Vendedor");
            escolhaDoUsuario = digite.nextLine();
            try {
                if(usuarioDigitouUmVazio(escolhaDoUsuario) || !(usuarioDigitouLetraAoInvesDeNumero(escolhaDoUsuario))){
                    continuar=false;
                    throw new UnsupportedOperationException();
                }
                escolhaConvertida = Integer.parseInt(escolhaDoUsuario);
                switch (escolhaConvertida) {
                    case 1:
                        cliente.cadastrarNoSistema();
                        menuPrincipal();
                        break;

                    case 2:
                        vendedor.cadastrarNoSistema();
                        vendedor.mapDoVendedorComProduto();
                        menuPrincipal();
                        break;

                    default:
                        continuar=false;
                        throw new UnsupportedOperationException();
                }
            }
            catch (UnsupportedOperationException error){
                System.err.println("Opção inválida, digite novamente");
            }
        } while(!(escolhaConvertida==1 || escolhaConvertida==2 || continuar));
    }
    public static void opcaoLogin() throws UnsupportedOperationException{
        vendedor.adicionarVendedorNaLista();
        cliente.adicionarClienteNaLista();
        String escolhaDoUsuario;
        int escolhaConvertida=0;
        boolean continuar=true;
        do{
            System.out.println("Deseja logar como:\n1-Cliente | 2-Vendedor");
            escolhaDoUsuario=digite.nextLine();
            try {
                if(usuarioDigitouUmVazio(escolhaDoUsuario) || !(usuarioDigitouLetraAoInvesDeNumero(escolhaDoUsuario))){
                    continuar=false;
                    throw new UnsupportedOperationException();
                }
                escolhaConvertida = Integer.parseInt(escolhaDoUsuario);
                switch (escolhaConvertida) {
                    case 1:
                        cliente.login();
                        menuDoCliente();
                        break;

                    case 2:
                        vendedor.login();
                        vendedor.historicoDeVendasDoVendedor();
                        break;

                    default:
                        continuar=false;
                        throw new UnsupportedOperationException();
                }
            }
            catch (UnsupportedOperationException error){
                System.err.println("Opção inválida, digite novamente");
            }
        } while(!(escolhaConvertida==1 || escolhaConvertida==2 || continuar));
    }
    public static void menuDoCliente() throws UnsupportedOperationException{
        vendedor.adicionarVendedorNaLista();
        cliente.adicionarClienteNaLista();
        vendedor.mapDoVendedorComProduto();
        vendedor.mapEmail();
        String escolhaDoUsuario;
        int escolhaConvertida=0;
        boolean continuar=true;
        do{
            System.out.println("Você deseja:\n1-Comprar | 2-Histórico de compras | 3-Sair");
            escolhaDoUsuario=digite.nextLine();
            try {
                if(usuarioDigitouUmVazio(escolhaDoUsuario) || !(usuarioDigitouLetraAoInvesDeNumero(escolhaDoUsuario))){
                    continuar=false;
                    throw new UnsupportedOperationException();
                }
                escolhaConvertida = Integer.parseInt(escolhaDoUsuario);
                switch (escolhaConvertida) {
                    case 1:
                        System.out.println("=====NOSSOS PRODUTOS=====");
                        vendedor.mostrarVendedorComProduto();
                        cliente.comprar();
                        break;

                    case 2:
                        cliente.historicoDeCompraDoCliente();
                        break;

                    case 3:
                        System.out.println("Obrigada por comprar em nossa loja!!");
                        System.exit(0);

                    default:
                        continuar=false;
                        throw new UnsupportedOperationException();
                }
            }
            catch (UnsupportedOperationException error){
                System.err.println("Opção inválida, digite novamente");
            }
        } while(!(escolhaConvertida==1 || escolhaConvertida==2 || continuar));
    }
    public static boolean usuarioDigitouLetraAoInvesDeNumero(String escolhaDoUsuario) {
        return escolhaDoUsuario.matches("[0-9]*");
    }
    public static boolean usuarioDigitouUmVazio(String escolhaDoUsuario){
        return escolhaDoUsuario.isBlank();
    }
}