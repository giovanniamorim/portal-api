package org.sindifisco.portal.api.resource;


import java.util.List;

import javax.validation.Valid;

import org.sindifisco.portal.api.entity.security.Grupo;
import org.sindifisco.portal.api.repository.security.GrupoRepository;
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
@RequestMapping("/api/seguranca/grupo")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Grupo salvar(@RequestBody @Valid Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	@GetMapping("{id}")
	public Grupo buscarPorId(@PathVariable Integer id) {
		return grupoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo não encontrado"));
	}

	@GetMapping
	public List<Grupo> listarTodos() {
		return grupoRepository.findAll();
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		grupoRepository.findById(id).map(grupo -> {
			grupoRepository.delete(grupo);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody Grupo grupoAtualizado) {
		grupoRepository.findById(id).map(grupo -> {
			grupo.setDescricao(grupoAtualizado.getDescricao());
			return grupoRepository.save(grupoAtualizado);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo não encontrado"));
	}

}
