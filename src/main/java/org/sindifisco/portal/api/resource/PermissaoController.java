package org.sindifisco.portal.api.resource;


import java.util.List;

import javax.validation.Valid;

import org.sindifisco.portal.api.entity.security.Permissao;
import org.sindifisco.portal.api.repository.security.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/seguranca/permissao")
public class PermissaoController {

	@Autowired
	private PermissaoRepository permissaoRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Permissao salvar(@RequestBody @Valid Permissao permissao) {
		return permissaoRepository.save(permissao);
	}

	@GetMapping("{id}")
	public Permissao buscarPorId(@PathVariable Integer id) {
		return permissaoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@GetMapping
	public List<Permissao> listarTodos() {
		return permissaoRepository.findAll();
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		permissaoRepository.findById(id).map(tipodocumento -> {
			permissaoRepository.delete(tipodocumento);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody Permissao permissaoAtualizada) {
		permissaoRepository.findById(id).map(permissao -> {
			permissao.setNome(permissaoAtualizada.getNome());
			return permissaoRepository.save(permissaoAtualizada);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Permissão não encontrada"));
	}

}
