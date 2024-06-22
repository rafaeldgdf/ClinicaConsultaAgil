package bancoDeDados;

import entidades.MarcacaoConsulta;
import entidades.Paciente;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

public class MarcacaoConsultaDAO extends DAOGenerico<MarcacaoConsulta> {
    private static final String FILE_NAME = "consultas.txt";

    public MarcacaoConsultaDAO() {
        carregar();
    }

    @Override
    public void salvar() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (MarcacaoConsulta consulta : lista) {
                writer.write(consulta.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregar() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                MarcacaoConsulta consulta = converterLinhaParaConsulta(linha);
                if (consulta != null) {
                    lista.add(consulta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MarcacaoConsulta converterLinhaParaConsulta(String linha) {
        if (linha.startsWith("MarcacaoConsulta{") && linha.endsWith("}")) {
            try {
                String conteudo = linha.substring(17, linha.length() - 1); 
                String[] partes = conteudo.split(", ");

                if (partes.length == 6) {
                    int idMarcacao = Integer.parseInt(partes[0].split("=")[1]);
                    String nome = partes[1].split("'")[1];
                    long telefone = Long.parseLong(partes[2].split("'")[1]);
                    String especialidade = partes[3].split("'")[1];
                    LocalDate dataMarcacao = LocalDate.parse(partes[4].split("=")[1].trim());
                    LocalTime horarioMarcacao = LocalTime.parse(partes[5].split("=")[1].trim());

                    Paciente paciente = new Paciente(nome, telefone);
                    return new MarcacaoConsulta(idMarcacao, paciente, dataMarcacao, horarioMarcacao, especialidade);
                } else {
                    System.out.println("Linha inválida (partes.length != 6): " + linha);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Erro ao processar linha (Índice fora do limite): " + linha);
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Erro ao processar linha: " + linha);
                e.printStackTrace();
            }
        } else {
            System.out.println("Linha inválida (formato incorreto): " + linha);
        }
        return null; 
    }

}
