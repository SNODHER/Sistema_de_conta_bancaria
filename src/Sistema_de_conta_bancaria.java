import java.util.ArrayList;
import java.util.Scanner;

public class Sistema_de_conta_bancaria {

    static class Transacao {
        String descricao;
        double valor;

        Transacao(String descricao, double valor) {
            this.descricao = descricao;
            this.valor = valor;
        }
    }

    public static void main(String[] args) {
        String[] nomes = {"Jessica", "Pedro", "Amanda", "João"};
        String[] tiposConta = {"Corrente", "Corrente", "Corrente", "Corrente"};
        double[] saldos = {2500, 1500, 3000, 2000};
        int[] ids = {2321, 2322, 2323, 2324};
        String[] senhas = {"senha1", "senha2", "senha3", "senha4"};

        Scanner inserir = new Scanner(System.in);

        int opcao = 0;

        ArrayList<Transacao>[] historico = new ArrayList[4]; // Uma lista para cada usuário
        ArrayList<Transacao> historicoGeral = new ArrayList<>(); // Lista geral de transações

        for (int i = 0; i < historico.length; i++) {
            historico[i] = new ArrayList<>();
        }

        int indiceUsuario = -1;

        do {
            System.out.println("Digite seu ID:");
            int idDigitado = inserir.nextInt();

            indiceUsuario = -1;

            for (int i = 0; i < ids.length; i++) {
                if (idDigitado == ids[i]) {
                    indiceUsuario = i;
                    break;
                }
            }

            if (indiceUsuario == -1) {
                System.out.println("Usuário não encontrado.");
            } else {
                System.out.println("Digite sua senha:");
                String senhaDigitada = inserir.next();

                if (!senhaDigitada.equals(senhas[indiceUsuario])) {
                    System.out.println("Senha incorreta. Acesso negado.");
                    continue; // Volta ao início do loop
                }

                System.out.println("***********************");
                System.out.println("\nNome do cliente: " + nomes[indiceUsuario]);
                System.out.println("Tipo conta: " + tiposConta[indiceUsuario]);
                System.out.println("Saldo atual: " + saldos[indiceUsuario]);
                System.out.println("\n***********************");

                while (opcao != 8) {
                    System.out.println("""
                            Operações
                            
                            1- Consultar saldo
                            2- Receber valor
                            3- Histórico de Transações
                            4- Depósito de Cheques
                            5- Transferir dinheiro por ID
                            7- Trocar de usuário
                            8- Sair
                            """);
                    opcao = inserir.nextInt();

                    switch (opcao) {
                        case 1:
                            System.out.println("Saldo do(a) " + nomes[indiceUsuario] + ": " + saldos[indiceUsuario]);
                            break;
                        case 2:
                            System.out.println("Qual o valor a receber?");
                            double valorReceber = inserir.nextDouble();
                            if (valorReceber < 0) {
                                System.out.println("Valor inválido. Não é permitido receber valores negativos.");
                            } else {
                                saldos[indiceUsuario] += valorReceber;
                                historico[indiceUsuario].add(new Transacao("Recebimento", valorReceber));
                                historicoGeral.add(new Transacao(nomes[indiceUsuario] + " recebeu " + valorReceber, valorReceber));
                                System.out.println("Saldo atual do(a) " + nomes[indiceUsuario] + ": " + saldos[indiceUsuario]);
                            }
                            break;
                        case 3:
                            System.out.println("Histórico de Transações:");
                            for (Transacao transacao : historico[indiceUsuario]) {
                                System.out.println(transacao.descricao + ": " + transacao.valor);
                            }
                            break;
                        case 4:
                            System.out.println("Qual o valor do cheque a depositar?");
                            double valorCheque = inserir.nextDouble();
                            saldos[indiceUsuario] += valorCheque;
                            historico[indiceUsuario].add(new Transacao("Depósito de Cheque", valorCheque));
                            historicoGeral.add(new Transacao(nomes[indiceUsuario] + " depositou um cheque de " + valorCheque, valorCheque));
                            System.out.println("Saldo atual do(a) " + nomes[indiceUsuario] + ": " + saldos[indiceUsuario]);
                            break;
                        case 5:
                            System.out.println("Digite o ID do destinatário:");
                            int idDestinatario = inserir.nextInt();
                            int indiceDestinatario = -1;

                            for (int i = 0; i < ids.length; i++) {
                                if (idDestinatario == ids[i] && i != indiceUsuario) {
                                    indiceDestinatario = i;
                                    break;
                                }
                            }

                            if (indiceDestinatario == -1) {
                                System.out.println("Destinatário não encontrado ou ID inválido para transferência.");
                            } else {
                                System.out.println("Digite o valor a ser transferido:");
                                double valorTransferencia = inserir.nextDouble();

                                if (valorTransferencia > saldos[indiceUsuario]) {
                                    System.out.println("Saldo insuficiente. Transferência não realizada.");
                                } else {
                                    saldos[indiceUsuario] -= valorTransferencia;
                                    saldos[indiceDestinatario] += valorTransferencia;

                                    historico[indiceUsuario].add(new Transacao("Transferência para " + nomes[indiceDestinatario], -valorTransferencia));
                                    historico[indiceDestinatario].add(new Transacao("Recebimento de " + nomes[indiceUsuario], valorTransferencia));

                                    historicoGeral.add(new Transacao(nomes[indiceUsuario] + " transferiu " + valorTransferencia + " para " + nomes[indiceDestinatario], -valorTransferencia));
                                    historicoGeral.add(new Transacao(nomes[indiceDestinatario] + " recebeu " + valorTransferencia + " de " + nomes[indiceUsuario], valorTransferencia));

                                    System.out.println("Transferência de " + valorTransferencia + " para " + nomes[indiceDestinatario] + " realizada com sucesso.");
                                }
                            }
                            break;
                        case 7:
                            System.out.println("Digite outro ID:");
                            idDigitado = inserir.nextInt();
                            for (int i = 0; i < ids.length; i++) {
                                if (idDigitado == ids[i]) {
                                    indiceUsuario = i;
                                    System.out.println("Digite sua senha:");
                                    senhaDigitada = inserir.next();

                                    if (!senhaDigitada.equals(senhas[indiceUsuario])) {
                                        System.out.println("Senha incorreta. Acesso negado.");
                                        opcao = 8; // Encerra o loop
                                    } else {
                                        System.out.println("***********************");
                                        System.out.println("\nNome do cliente: " + nomes[indiceUsuario]);
                                        System.out.println("Tipo conta: " + tiposConta[indiceUsuario]);
                                        System.out.println("Saldo atual: " + saldos[indiceUsuario]);
                                        System.out.println("\n***********************");
                                    }
                                    break;
                                }
                            }
                            if (indiceUsuario == -1) {
                                System.out.println("Usuário não encontrado.");
                            }
                            break;
                        case 8:
                            System.out.println("Obrigado por usar nosso sistema");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                }
            }
        } while (opcao != 8);
    }
}
