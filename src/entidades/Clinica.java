package entidades;

import bancoDeDados.PacienteDAO;
import bancoDeDados.MarcacaoConsultaDAO;
import java.util.Scanner;

public class Clinica {
    private static PacienteDAO pacienteDAO = new PacienteDAO();
    private static MarcacaoConsultaDAO consultaDAO = new MarcacaoConsultaDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            exibirRegraDeNegocios();
            exibirMenu(scanner);
        }
    }

    private static void exibirMenu(Scanner scanner) {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar paciente");
            System.out.println("2. Marcar consulta");
            System.out.println("3. Listar consultas");
            System.out.println("4. Cancelar consulta");
            System.out.println("5. Listar pacientes");
            System.out.println("6. Remover paciente");
            System.out.println("7. Consultar status de paciente");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = validarEntrada(scanner);

            switch (opcao) {
                case 1:
                    if (!Paciente.cadastrarPaciente(scanner)) break;
                    break;
                case 2:
                    if (!MarcacaoConsulta.marcarConsulta(scanner)) break;
                    break;
                case 3:
                    MarcacaoConsulta.listarConsultas();
                    break;
                case 4:
                    if (!MarcacaoConsulta.cancelarConsulta(scanner)) break;
                    break;
                case 5:
                    Paciente.listarPacientes();
                    break;
                case 6:
                    if (!Paciente.removerPaciente(scanner)) break;
                    break;
                case 7:
                    if (!Paciente.consultarStatusPaciente(scanner)) break;
                    break;
                case 8:
                    if (confirmarSaida(scanner)) {
                        System.out.println("Saindo...");
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    promptEnterKey(scanner);
            }
        }
    }

    private static int validarEntrada(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, insira um número entre 1 e 8.");
            scanner.next();
            exibirMenu(scanner);
        }
        return scanner.nextInt();
    }

    private static void promptEnterKey(Scanner scanner) {
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
        exibirMenu(scanner);
    }
    
    private static boolean confirmarSaida(Scanner scanner) {
        System.out.print("Tem certeza que deseja sair? (s/n): ");
        String resposta = scanner.next();
        if (!resposta.equalsIgnoreCase("s")) {
            System.out.println("Resposta diferente de 's', o sistema continua.");
            return false;
        }
        return true;
    }


    private static void exibirRegraDeNegocios() {
        System.out.println("**********************************************************");
        System.out.println("*              Cliníca de Consulta Ágil                  *");
        System.out.println("**********************************************************");
        System.out.println("A clínica funciona de segunda a sexta, das 08:00 às 18:00.");
        System.out.println("Cada dia, apenas duas especialidades atendem:            *");
        System.out.println("*                                                        *");
        System.out.println("Segunda-feira: Clínica Geral e Cardiologia               *");
        System.out.println("Terça-feira: Dermatologia e Neurologia                   *");
        System.out.println("Quarta-feira: Ortopedia e Pediatria                      *");
        System.out.println("Quinta-feira: Psiquiatria e Ginecologia                  *");
        System.out.println("Sexta-feira: Psicologia e Endocrinologia                 *");
        System.out.println("**********************************************************");
        System.out.println();
    }
}
