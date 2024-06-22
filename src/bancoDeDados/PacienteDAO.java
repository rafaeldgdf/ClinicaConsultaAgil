package bancoDeDados;

import entidades.Paciente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class PacienteDAO extends DAOGenerico<Paciente> {
    private static final String FILE_NAME = "pacientes.txt";

    public PacienteDAO() {
        carregar();
    }

    @Override
    public void salvar() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Paciente paciente : lista) {
                writer.write(paciente.toString());
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
                Paciente paciente = converterLinhaParaPaciente(linha);
                if (paciente != null) {
                    lista.add(paciente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Paciente converterLinhaParaPaciente(String linha) {
        if (linha.startsWith("Nome: ") && linha.contains(", Telefone: ")) {
            String[] partes = linha.split(", Telefone: ");
            if (partes.length == 2) {
                String nome = partes[0].substring(6); 
                long telefone = Long.parseLong(partes[1]);
                return new Paciente(nome, telefone);
            }
        }
        return null;
    }
}
