package coding.challenge.Api.service;

import java.nio.charset.Charset;
import java.util.Optional;

import coding.challenge.Api.controller.PostagemController;
import coding.challenge.Api.repository.PostagemRepository;
import org.apache.commons.codec.binary.Base64;
import coding.challenge.Api.model.UserLogin;
import coding.challenge.Api.model.Usuario;
import coding.challenge.Api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return repository.save(usuario);
	}
	
	public Optional<UserLogin> Logar(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario()); 
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodeAuth);
				
				user.get().setToken(authHeader);
				user.get().setId(usuario.get().getId());
				user.get().setNome(usuario.get().getNome());
				user.get().setFoto(usuario.get().getFoto());
				user.get().setTipo(usuario.get().getTipo());
				
				return user;
			}
		}
		return null;
	}

	public Usuario rolePontos(Usuario usuario){
		if (usuario.getPontos()< 20) {
			usuario.setRole("Leitor");

			return usuario;
		} if (usuario.getPontos() > 19 && usuario.getPontos() < 99) {
			usuario.setRole("Básico");

			return usuario;
		} if (usuario.getPontos() > 99 && usuario.getPontos() < 999) {
			usuario.setRole("Avançado");

			return usuario;
		}if (usuario.getPontos() > 999) {
			usuario.setRole("Moderador");

			return usuario;
		}

		return null;
	}
}
