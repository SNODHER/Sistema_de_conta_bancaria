import java.util.Scanner;

public class Sistema_de_conta_bacaria {
    public static void main(String[] args) {
        String[] nomes = {"Jessica", "Pedro", "Amanda", "João"}; // Nomes dos usuários
        String[] tiposConta = {"Corrente", "Corrente", "Corrente", "Corrente"}; // Tipos de conta dos usuários
        double[] saldos = {2500, 1500, 3000, 2000}; // Saldo inicial de cada usuário
        int[] ids = {2321, 2322, 2323, 2324}; // IDs dos usuários

        Scanner inserir = new Scanner(System.in);

        int opcao = 0;

        System.out.println("Digite seu ID:");
        int idDigitado = inserir.nextInt();

        int indiceUsuario = -1;

        // Procurar o ID digitado
        for (int i = 0; i < ids.length; i++) {
            if (idDigitado == ids[i]) {
                indiceUsuario = i;
                break;
            }
        }

        if (indiceUsuario == -1) {
            System.out.println("Usuário não encontrado.");
        } else {
            System.out.println("***********************");
            System.out.println("\nNome do cliente: " + nomes[indiceUsuario]);
            System.out.println("Tipo conta: " + tiposConta[indiceUsuario]);
            System.out.println("Saldo atual: " + saldos[indiceUsuario]);
            System.out.println("\n***********************");

            while (opcao != 4) {
                System.out.println("""
                        Operações
                        
                        1- Consultar saldo
                        2- Receber valor
                        3- Transferir valor
                        4- Sair
                        """);
                opcao = inserir.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println("Saldo do(a) " + nomes[indiceUsuario] + ": " + saldos[indiceUsuario]);
                        break;
                    case 2:
                        System.out.println("Qual o valor a receber?");
                        double valorReceber = inserir.nextDouble();
                        saldos[indiceUsuario] += valorReceber;
                        System.out.println("Saldo atual do(a) " + nomes[indiceUsuario] + ": " + saldos[indiceUsuario]);
                        break;
                    case 3:
                        System.out.println("Quanto você quer transferir?");
                        double valorTransferir = inserir.nextDouble();
                        saldos[indiceUsuario] -= valorTransferir;
                        System.out.println("Saldo atual do(a) " + nomes[indiceUsuario] + ": " + saldos[indiceUsuario]);
                        break;
                    case 4:
                        System.out.println("Obrigado por usar nosso sistema");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }
    }
}