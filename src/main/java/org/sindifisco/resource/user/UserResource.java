package org.sindifisco.resource.user;

import org.sindifisco.model.Usuario;
import org.sindifisco.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

import static java.lang.Void.TYPE;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UserResource {

	@Autowired
	private final UsuarioRepository userRepository;


	@GetMapping()
	@PreAuthorize("hasAuthority('ROLE_READ') and #oauth2.hasScope('read')")
	public Page<Usuario> listAllUsers(@PageableDefault(size = 5, sort = "codigo", direction = Sort.Direction.DESC) Pageable pageable){
		return userRepository.findAll(pageable);
	}


	@PostMapping()
	@PreAuthorize("hasAuthority('ROLE_CREATE') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> saveUser(@RequestBody Usuario user){
		URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/api/user/save").toString());
		return ResponseEntity.created(uri).body(userRepository.save(user));
	}

	@PutMapping("{id}")
	@PreAuthorize("hasAuthority('ROLE_UPDATE') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> editUser(@PathVariable Long codigo, @Valid @RequestBody Usuario userUpdated){

		URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/api/usuarios/{codigo}").toString());

		Usuario user = new Usuario();
		user.setNome(userUpdated.getNome());
		user.setEmail(userUpdated.getEmail());
		user.setSenha((userUpdated.getSenha()));

		return ResponseEntity.created(uri).body(userRepository.saveAndFlush(user));

	}

	@GetMapping("/{codigo}")
	public Usuario findUserById(@PathVariable Long codigo) {
		return userRepository.findById(codigo)
				.orElseThrow(() -> new ResponseStatusException(
						NOT_FOUND, "Usuário não encontrado"));
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
