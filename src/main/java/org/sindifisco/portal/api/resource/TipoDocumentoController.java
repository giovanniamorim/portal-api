package org.sindifisco.portal.api.resource;

import javax.validation.Valid;

import org.sindifisco.portal.api.entity.contabil.TipoDocumento;
import org.sindifisco.portal.api.repository.contabil.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/api/parametros/tipo-documento")
public class TipoDocumentoController {

	@Autowired
	private TipoDocumentoRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TipoDocumento salvar(@RequestBody @Valid TipoDocumento tipoDocumento) {
		return repository.save(tipoDocumento);
	}

	@GetMapping("{id}")
	public TipoDocumento buscarPorId(@PathVariable Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de Documento não encontrado"));
	}

	@GetMapping
	public Page<TipoDocumento> listarTodos( Pageable pageable) {
		return repository.findAll(pageable);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		repository.findById(id).map(tipodocumento -> {
			repository.delete(tipodocumento);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody TipoDocumento tipoDocumentoAtualizado) {
		repository.findById(id).map(tipoDocumento -> {
			tipoDocumento.setDescricao(tipoDocumentoAtualizado.getDescricao());
			return repository.save(tipoDocumentoAtualizado);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de Documento não encontrado"));
	}

}
