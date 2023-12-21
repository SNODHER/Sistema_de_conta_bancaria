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

        Scanner inserir = new Scanner(System.in);

        int opcao = 0;

        ArrayList<Transacao>[] historico = new ArrayList[4]; // Uma lista para cada usuário
        ArrayList<Transacao> historicoGeral = new ArrayList<>(); // Lista geral de transações

        for (int i = 0; i < historico.length; i++) {
            historico[i] = new ArrayList<>();
        }

        do {
            System.out.println("Digite seu ID:");
            int idDigitado = inserir.nextInt();

            int indiceUsuario = -1;

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

                while (opcao != 8) {
                    System.out.println("""
                            Operações
                            
                            1- Consultar saldo
                            2- Receber valor
                            3- Transferir valor
                            4- Histórico de Transações
                            5- Depósito de Cheques
                            6- Transferir dinheiro por ID
                            7- Enviar Valor para Usuário
                            8- Sair
                            9- Trocar de usuário
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
                            historico[indiceUsuario].add(new Transacao("Recebimento", valorReceber));
                            historicoGeral.add(new Transacao(nomes[indiceUsuario] + " recebeu " + valorReceber, valorReceber));
                            System.out.println("Saldo atual do(a) " + nomes[indiceUsuario] + ": " + saldos[indiceUsuario]);
                            break;
                        case 3:
                            System.out.println("Quanto você quer transferir?");
                            double valorTransferir = inserir.nextDouble();
                            saldos[indiceUsuario] -= valorTransferir;
                            historico[indiceUsuario].add(new Transacao("Transferência", -valorTransferir));
                            historicoGeral.add(new Transacao(nomes[indiceUsuario] + " transferiu " + valorTransferir, -valorTransferir));
                            System.out.println("Saldo atual do(a) " + nomes[indiceUsuario] + ": " + saldos[indiceUsuario]);
                            break;
                        case 4:
                            // Histórico de Transações
                            System.out.println("Histórico de Transações:");
                            for (Transacao transacao : historico[indiceUsuario]) {
                                System.out.println(transacao.descricao + ": " + transacao.valor);
                            }
                            break;
                        case 5:
                            // Depósito de Cheques
                            System.out.println("Qual o valor do cheque a depositar?");
                            double valorCheque = inserir.nextDouble();
                            saldos[indiceUsuario] += valorCheque;
                            historico[indiceUsuario].add(new Transacao("Depósito de Cheque", valorCheque));
                            historicoGeral.add(new Transacao(nomes[indiceUsuario] + " depositou um cheque de " + valorCheque, valorCheque));
                            System.out.println("Saldo atual do(a) " + nomes[indiceUsuario] + ": " + saldos[indiceUsuario]);
                            break;
                        case 6:
                            // Transferir dinheiro por ID
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
                            // Enviar Valor para Usuário
                            System.out.println("Digite o ID do usuário para enviar valor:");
                            int idUsuarioDestino = inserir.nextInt();
                            int indiceUsuarioDestino = -1;

                            for (int i = 0; i < ids.length; i++) {
                                if (idUsuarioDestino == ids[i]) {
                                    indiceUsuarioDestino = i;
                                    break;
                                }
                            }

                            if (indiceUsuarioDestino == -1) {
                                System.out.println("Usuário de destino não encontrado.");
                            } else {
                                System.out.println("Digite o valor a ser enviado:");
                                double valorEnviar = inserir.nextDouble();

                                if (valorEnviar > saldos[indiceUsuario]) {
                                    System.out.println("Saldo insuficiente. Transferência não realizada.");
                                } else {
                                    saldos[indiceUsuario] -= valorEnviar;
                                    saldos[indiceUsuarioDestino] += valorEnviar;

                                    historico[indiceUsuario].add(new Transacao("Envio para " + nomes[indiceUsuarioDestino], -valorEnviar));
                                    historico[indiceUsuarioDestino].add(new Transacao("Recebimento de " + nomes[indiceUsuario], valorEnviar));

                                    historicoGeral.add(new Transacao(nomes[indiceUsuario] + " enviou " + valorEnviar + " para " + nomes[indiceUsuarioDestino], -valorEnviar));
                                    historicoGeral.add(new Transacao(nomes[indiceUsuarioDestino] + " recebeu " + valorEnviar + " de " + nomes[indiceUsuario], valorEnviar));

                                    System.out.println("Transferência de " + valorEnviar + " para " + nomes[indiceUsuarioDestino] + " realizada com sucesso.");
                                }
                            }
                            break;
                        case 8:
                            System.out.println("Obrigado por usar nosso sistema");
                            break;
                        case 9:
                            System.out.println("Digite outro ID:");
                            idDigitado = inserir.nextInt();
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
                            }
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                }
            }
        } while (opcao != 8);
    }
}
