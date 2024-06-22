package entidades;

import bancoDeDados.Registro;
import bancoDeDados.PacienteDAO;
import bancoDeDados.MarcacaoConsultaDAO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.Scanner;

public class MarcacaoConsulta extends Registro {
    private int idMarcacao;
    private Paciente paciente;
    private LocalDate dataMarcacao;
    private LocalTime horarioMarcacao;
    private String especialidade;

    public MarcacaoConsulta(int idMarcacao, Paciente paciente, LocalDate dataMarcacao, LocalTime horarioMarcacao, String especialidade) {
        super();
        this.idMarcacao = idMarcacao;
        this.paciente = paciente;
        this.dataMarcacao = dataMarcacao;
        this.horarioMarcacao = horarioMarcacao;
        this.especialidade = especialidade;
    }

    @Override
    public String getIdUnico() {
        return String.valueOf(idMarcacao);
    }

    public int getIdMarcacao() {
        return idMarcacao;
    }

    public void setIdMarcacao(int idMarcacao) {
        this.idMarcacao = idMarcacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getDataMarcacao() {
        return dataMarcacao;
    }

    public void setDataMarcacao(LocalDate dataMarcacao) {
        this.dataMarcacao = dataMarcacao;
    }

    public LocalTime getHorarioMarcacao() {
        return horarioMarcacao;
    }

    public void setHorarioMarcacao(LocalTime horarioMarcacao) {
        this.horarioMarcacao = horarioMarcacao;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "MarcacaoConsulta{ID_MARCACAO=" + idMarcacao + ", NOME:'" + paciente.getNome() + "', TELEFONE:'" + paciente.getTelefone() + "', ESPECIALIDADE='" + especialidade + "', DATA=" + dataMarcacao + ", HORÁRIO=" + horarioMarcacao + "}";
    }
    
    private static MarcacaoConsultaDAO consultaDAO = new MarcacaoConsultaDAO();
    private static PacienteDAO pacienteDAO = new PacienteDAO();
    private static AtomicInteger proximoIdConsulta = new AtomicInteger(1);
    
    static {
        List<MarcacaoConsulta> consultasExistentes = consultaDAO.listar();
        int maxId = consultasExistentes.stream()
                                       .mapToInt(MarcacaoConsulta::getIdMarcacao)
                                       .max()
                                       .orElse(0);
        proximoIdConsulta.set(maxId + 1);
    }

    public static boolean marcarConsulta(Scanner scanner) {
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

        boolean dataHoraValida = false;

        while (!dataHoraValida) {
            try {
                System.out.print("Dia (dd/MM/yyyy): ");
                String dataInput = scanner.nextLine();
                DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(dataInput, formatoBrasileiro);

                LocalDate dataAtual = LocalDate.now();
                LocalDate dataLimite = dataAtual.plusYears(1);

                if (data.isBefore(dataAtual)) {
                    System.out.println("A data da consulta não pode ser no passado. Tente novamente.");
                    continue;
                } else if (data.isAfter(dataLimite)) {
                    System.out.println("A data da consulta deve estar dentro do intervalo de 1 ano. Tente novamente.");
                    continue;
                } else if (data.getDayOfWeek() == DayOfWeek.SATURDAY || data.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    System.out.println("As consultas só podem ser marcadas de segunda a sexta. Tente novamente.");
                    continue;
                }

                String diaSemanaPt = data.getDayOfWeek().getDisplayName(java.time.format.TextStyle.FULL, new java.util.Locale("pt", "BR"));
                String[] especialidades = obterEspecialidades(data);
                System.out.println("Dia da semana escolhido: " + diaSemanaPt + ".");
                System.out.println("Especialidades: " + String.join(" e ", especialidades) + ".");

                boolean horarioValido = false;
                LocalTime hora = null;
                while (!horarioValido) {
                    System.out.print("Hora (HH:mm): ");
                    try {
                        hora = LocalTime.parse(scanner.nextLine());
                        if (data.equals(dataAtual) && hora.isBefore(LocalTime.now())) {
                            System.out.println("A hora da consulta não pode ser no passado. Tente novamente.");
                        } else if (hora.isBefore(LocalTime.of(8, 0)) || hora.isAfter(LocalTime.of(17, 0))) {
                            System.out.println("A clínica funciona das 08:00 às 18:00, com o último horário para agendamento às 17:00.");
                        } else if (hora.getMinute() != 0) {
                            System.out.println("A consulta só pode ser marcada em intervalos de uma hora. Ex: 08:00, 09:00, etc. Tente novamente.");
                        } else {
                            String especialidade = escolherEspecialidade(scanner, data);
                            if (especialidade.equals("000")) return false;

                            final LocalTime horaFinal = hora; 
                            boolean consultaExistente = consultaDAO.listar().stream()
                                .anyMatch(c -> c.getDataMarcacao().equals(data) 
                                               && c.getHorarioMarcacao().equals(horaFinal)
                                               && c.getEspecialidade().equals(especialidade));

                            if (consultaExistente) {
                                System.out.println("Já existe uma consulta marcada para essa data, horário e especialidade. Tente novamente.");
                                horarioValido = false; 
                            } else {
                                MarcacaoConsulta consulta = new MarcacaoConsulta(proximoIdConsulta.getAndIncrement(), paciente, data, hora, especialidade);
                                consultaDAO.adicionar(consulta);
                                consultaDAO.salvar(); 

                                System.out.println(paciente.getNome() + ", consulta agendada com sucesso!");
                                System.out.println("Você tem um horário reservado das " + hora + " às " + hora.plusHours(1) + " para especialidade " + especialidade + " em " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " (" + diaSemanaPt + ").");

                                dataHoraValida = true;
                                horarioValido = true;
                            }
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Hora inválida. Tente novamente.");
                    }
                }
            } catch (DateTimeParseException e) {
                System.out.println("Data ou hora inválida. Tente novamente.");
            }
        }
        return true;
    }

    private static String[] obterEspecialidades(LocalDate data) {
        DayOfWeek diaSemana = data.getDayOfWeek();
        String[] especialidades;

        switch (diaSemana) {
            case MONDAY:
                especialidades = new String[]{"Clínica Geral", "Cardiologia"};
                break;
            case TUESDAY:
                especialidades = new String[]{"Dermatologia", "Neurologia"};
                break;
            case WEDNESDAY:
                especialidades = new String[]{"Ortopedia", "Pediatria"};
                break;
            case THURSDAY:
                especialidades = new String[]{"Psiquiatria", "Ginecologia"};
                break;
            case FRIDAY:
                especialidades = new String[]{"Psicologia", "Endocrinologia"};
                break;
            default:
                especialidades = new String[]{};
                break;
        }

        return especialidades;
    }

    private static String escolherEspecialidade(Scanner scanner, LocalDate data) {
        DayOfWeek diaSemana = data.getDayOfWeek();
        String[] especialidades;

        switch (diaSemana) {
            case MONDAY:
                especialidades = new String[]{"Clínica Geral", "Cardiologia"};
                break;
            case TUESDAY:
                especialidades = new String[]{"Dermatologia", "Neurologia"};
                break;
            case WEDNESDAY:
                especialidades = new String[]{"Ortopedia", "Pediatria"};
                break;
            case THURSDAY:
                especialidades = new String[]{"Psiquiatria", "Ginecologia"};
                break;
            case FRIDAY:
                especialidades = new String[]{"Psicologia", "Endocrinologia"};
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("Consultas não são permitidas aos finais de semana.");
                return "000";
            default:
                throw new IllegalStateException("Dia da semana inválido: " + diaSemana);
        }

        System.out.println("Escolha a especialidade:");
        for (int i = 0; i < especialidades.length; i++) {
            System.out.println((i + 1) + ". " + especialidades[i]);
        }

        int opcaoEspecialidade;
        do {
            System.out.print("Escolha uma opção: ");
            opcaoEspecialidade = scanner.nextInt();
            scanner.nextLine(); 
        } while (opcaoEspecialidade < 1 || opcaoEspecialidade > especialidades.length);

        return especialidades[opcaoEspecialidade - 1];
    }

    public static void listarConsultas() {
        List<MarcacaoConsulta> consultas = consultaDAO.listar();
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta marcada.");
            return;
        }

        System.out.println("Consultas agendadas:");
        for (int i = 0; i < consultas.size(); i++) {
            System.out.println((i + 1) + ". " + consultas.get(i));
        }
    }

    public static boolean cancelarConsulta(Scanner scanner) {
        System.out.print("Escolha o ID da marcação para cancelar: ");
        String idConsultaInput = scanner.next();
        if (idConsultaInput.equals("000")) return false;

        int idConsulta = Integer.parseInt(idConsultaInput);
        scanner.nextLine();

        MarcacaoConsulta consultaParaRemover = consultaDAO.listar().stream()
                .filter(c -> c.getIdMarcacao() == idConsulta)
                .findFirst()
                .orElse(null);

        if (consultaParaRemover != null) {
            System.out.println("Consulta selecionada para cancelamento: " + consultaParaRemover);
            System.out.print("Deseja realmente cancelar essa consulta? (s/n): ");
            String confirmacao = scanner.nextLine().toLowerCase();

            switch (confirmacao) {
                case "s":
                    consultaDAO.remover(consultaParaRemover);
                    System.out.println("Consulta cancelada com sucesso.");
                    break;
                case "n":
                    System.out.println("Consulta mantida.");
                    break;
                default:
                    System.out.println("Opção inválida. Consulta mantida.");
                    break;
            }
        } else {
            System.out.println("Consulta não encontrada.");
        }
        return true;
    }
}
