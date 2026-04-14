import java.util.ArrayList;

public class ValidadorCredenciales {

    public Usuario autenticar(String username, String password, ArrayList<Usuario> usuarios) {
        Usuario usuarioEncontrado = null;
        int i = 0;

        while (i < usuarios.size() && usuarioEncontrado == null) {
            Usuario actual = usuarios.get(i);

            if (actual.getUsername().equals(username) && actual.getPassword().equals(password)) {
                usuarioEncontrado = actual;
            }

            i = i + 1;
        }

        return usuarioEncontrado;
    }
}