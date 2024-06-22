package entidades;

import bancoDeDados.PacienteDAO;
import bancoDeDados.Registro;
import bancoDeDados.MarcacaoConsultaDAO;
import java.util.List;
import java.util.Scanner;

public class Paciente  extends Registro {
    private String nome;
    private long telefone;

    private static PacienteDAO pacienteDAO = new PacienteDAO();
    private static MarcacaoConsultaDAO consultaDAO = new MarcacaoConsultaDAO();

    public Paciente(String nome, long telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public static boolean cadastrarPaciente(Scanner scanner) {
        System.out.print("Nome: ");
        scanner.nextLine(); 
        String nome = scanner.nextLine();
        if (nome.equals("000")) return false;
        if (!nome.matches("[\\p{L} ]+")) { 
            System.out.println("Nome inválido. Não são permitidos números.");
            return true;
        }

        System.out.print("Telefone (somente números): ");
        String telefoneStr = scanner.next();
        if (telefoneStr.equals("000")) return false;
        if (telefoneStr.length() < 8 || !telefoneStr.matches("\\d+")) {
            System.out.println("Telefone inválido. Deve conter pelo menos 8 dígitos e apenas números.");
            return true;
        }

        long telefone = Long.parseLong(telefoneStr);
        scanner.nextLine(); 

        if (pacienteDAO.listar().stream().anyMatch(p -> p.getTelefone() == telefone)) {
            System.out.println("Telefone já cadastrado. Tente novamente.");
            return true;
        }

        Paciente paciente = new Paciente(nome, telefone);
        pacienteDAO.adicionar(paciente);

        System.out.println("Paciente cadastrado com sucesso.");
        return true;
    }

    public static void listarPacientes() {
        List<Paciente> pacientes = pacienteDAO.listar();
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }

        System.out.println("Pacientes cadastrados:");
        int i = 1;
        for (Paciente paciente : pacientes) {
            System.out.println(i++ + ". Nome: " + paciente.getNome() + ", Telefone: " + paciente.getTelefone());
        }
    }

    public static boolean removerPaciente(Scanner scanner) {
        System.out.print("Escolha o telefone do paciente a ser removido: ");
        String telefoneInput = scanner.next();
        if (telefoneInput.equals("000")) return false;

        long telefonePaciente = Long.parseLong(telefoneInput);
        scanner.nextLine(); 

        Paciente paciente = pacienteDAO.listar().stream()
                .filter(p -> p.getTelefone() == telefonePaciente)
                .findFirst()
                .orElse(null);

        if (paciente == null) {
            System.out.println("Telefone de paciente inválido.");
            return true;
        }

        System.out.println("Paciente selecionado para remoção: " + paciente);
        System.out.print("Deseja realmente remover esse paciente e todas as suas consultas? (s/n): ");
        String confirmacao = scanner.nextLine().toLowerCase();

        switch (confirmacao) {
            case "s":
                List<MarcacaoConsulta> consultasParaRemover = consultaDAO.listar().stream()
                        .filter(c -> c.getPaciente().getTelefone() == telefonePaciente)
                        .toList();

                for (MarcacaoConsulta consulta : consultasParaRemover) {
                    consultaDAO.remover(consulta);
                }
                pacienteDAO.remover(paciente);
                System.out.println("Paciente e suas consultas removidos com sucesso.");
                break;
            case "n":
                System.out.println("Remoção cancelada.");
                break;
            default:
                System.out.println("Opção inválida. Remoção cancelada.");
                break;
        }
        return true;
    }

    public static boolean consultarStatusPaciente(Scanner scanner) {
        System.out.print("Escolha o telefone do paciente: ");
        String telefoneInput = scanner.next();
        if (telefoneInput.equals("000")) return false;

        long telefonePaciente;
        try {
            telefonePaciente = Long.parseLong(telefoneInput);
        } catch (NumberFormatException e) {
            System.out.println("Telefone inválido. Deve conter apenas números.");
            return true;
        }
        scanner.nextLine();

        Paciente paciente = pacienteDAO.listar().stream()
                .filter(p -> p.getTelefone() == telefonePaciente)
                .findFirst()
                .orElse(null);

        if (paciente == null) {
            System.out.println("Telefone de paciente inválido.");
            return true;
        }

        System.out.println("Paciente encontrado: " + paciente);

        
        consultaDAO = new MarcacaoConsultaDAO();
        List<MarcacaoConsulta> consultasPaciente = consultaDAO.listar().stream()
                .filter(c -> c.getPaciente().getTelefone() == telefonePaciente)
                .toList();

        if (consultasPaciente.isEmpty()) {
            System.out.println("Não há consultas marcadas para este paciente.");
        } else {
            System.out.println("Consultas marcadas para este paciente:");
            for (MarcacaoConsulta consulta : consultasPaciente) {
                System.out.println(consulta);
            }
        }
        return true;
    }
    
    @Override
    public String getIdUnico() {
        return String.valueOf(telefone);
    }
    
    @Override
    public String toString() {
        return "Nome: " + nome + ", Telefone: " + telefone;
    }
}
