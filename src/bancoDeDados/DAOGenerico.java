package bancoDeDados;

import java.util.ArrayList;
import java.util.List;

public abstract class DAOGenerico<T extends Registro> {

    protected List<T> lista = new ArrayList<>();

    public void adicionar(T t) {
        lista.add(t);
        salvar();
    }

    public void remover(T t) {
        lista.remove(t);
        salvar();
    }

    public List<T> listar() {
        return lista;
    }

    public T buscar(int id) {
        for (T t : lista) {
            if (t.getIdUnico().equals(String.valueOf(id))) {
                return t;
            }
        }
        return null;
    }

    public abstract void salvar();
}
