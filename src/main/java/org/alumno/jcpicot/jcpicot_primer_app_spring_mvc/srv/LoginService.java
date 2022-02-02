package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.srv;

import java.util.ArrayList;
import java.util.List;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Usuario;
import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.util.Ts;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
private static List<Usuario> usuarios= new ArrayList<Usuario>();
	
	static {
		usuarios.add(new Usuario("picot","nesu","JcPicot123.","picot.jpg"));
		usuarios.add(new Usuario("juan","sardo","Jcpicot_123",""));
	}
	
	public boolean usuarioValido(Usuario usuarioLogin) {
		for (Usuario usuario2 : usuarios) {
            if (usuario2.equals(usuarioLogin)) {
                return usuario2.getPassword().equals(usuarioLogin.getPassword());
            }
        }

        return false;
	}
	public Usuario encontrarUsuarioPorNickname(String nickname) {
		for (Usuario usuario : usuarios) {
			
			if (usuario.getNickname().equals(nickname))

				return usuario;
		}
		return null;
	}
	
	public void modificaUsuario(Usuario usuarioModificado, String usuarioModificacion){
		Usuario usuario = encontrarUsuarioPorNickname(usuarioModificacion);
		if(usuario != null) {
			for(int i = 0; i < usuarios.size(); i++) {
				if(usuarios.get(i).getNickname().contentEquals(usuarioModificado.getNickname())) {
					usuarioModificado.setTs(Ts.today());
					usuarioModificado.setUser(usuarioModificacion);
					usuarios.set(i, usuarioModificado);
					break;
				}
			}
		}
	}
}
