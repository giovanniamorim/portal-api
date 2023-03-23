package org.sindifisco.resource.user;

import org.sindifisco.model.Usuario;
import org.sindifisco.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.sindifisco.security.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

import static java.lang.Void.TYPE;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UserResource {

	@Autowired
	private final UsuarioRepository userRepository;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private final AppUserDetailsService appUserDetailsService;


	@GetMapping()
	@PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
	public Page<Usuario> listAllUsers(@PageableDefault(size = 5, sort = "codigo", direction = Sort.Direction.DESC) Pageable pageable){
		return userRepository.findAll(pageable);
	}


	@PostMapping
	@ResponseStatus(CREATED)
	@PreAuthorize("hasAnyAuthority('ROLE_CREATE') and #oauth2.hasScope('write')")
	public Usuario addUsuario(@RequestBody @Valid Usuario user)  {
		user.setSenha(encoder.encode(user.getSenha()));
		return userRepository.save(user);
	}

	@PutMapping("{codigo}")
	@PreAuthorize("hasAuthority('ROLE_UPDATE') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> editUser(@PathVariable Long codigo, @Valid @RequestBody Usuario userUpdated){

		return userRepository.findById(codigo)
				.map(usuario -> {
					usuario.setNome(userUpdated.getNome());
					usuario.setEmail(userUpdated.getEmail());
					usuario.setSenha(encoder.encode(userUpdated.getSenha()));
					usuario.setCelular(userUpdated.getCelular());
					usuario.setCpf(userUpdated.getCpf());
					usuario.setRg(userUpdated.getRg());
					usuario.setRgOrgaoExp(userUpdated.getRgOrgaoExp());
					usuario.setMatricula(userUpdated.getMatricula());
					usuario.setSituacao(userUpdated.getSituacao());
					usuario.setPermissoes(userUpdated.getPermissoes());

					Usuario putUsuario = userRepository.save(usuario);

					return ResponseEntity.ok().body(putUsuario);
				}).orElseThrow(() -> new ResponseStatusException(
						NOT_FOUND, "Usuário não encontrado"));

	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
	public Usuario findUserById(@PathVariable Long codigo) {
		return userRepository.findById(codigo)
				.orElseThrow(() -> new ResponseStatusException(
						NOT_FOUND, "Usuário não encontrado"));
	}

	@GetMapping("/perfil")
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
	public Optional<Usuario> findByEmail(@RequestParam("email") String email) {
		Optional<Usuario> usuario = userRepository.findByEmail(email);

		return usuario;
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_DELETE') and #oauth2.hasScope('write')")
	public void deleteUser(@PathVariable Long codigo) {
		userRepository.findById(codigo).map(user -> {
			userRepository.delete(user);
			return TYPE;
		}).orElseThrow(() -> new ResponseStatusException(
				NOT_FOUND, "Usuário não encontrado"));
	}

}
