package org.sindifisco.portal.api.resource;


import javax.validation.Valid;

import org.sindifisco.portal.api.entity.contabil.ModoPagamento;
import org.sindifisco.portal.api.repository.contabil.ModoPagamentoRepository;
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
@RequestMapping("/api/parametros/modo-pagamento")
public class ModoPagamentoController {

	@Autowired
	private ModoPagamentoRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ModoPagamento salvar(@RequestBody @Valid ModoPagamento modoPagamento) {
		return repository.save(modoPagamento);
	}

	@GetMapping("{id}")
	public ModoPagamento buscarPorId(@PathVariable Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modo Pagamento não encontrado"));
	}

	@GetMapping
	public Page<ModoPagamento> listarTodos(Pageable pageable){
		return repository.findAll(pageable);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		repository.findById(id).map(modopagamento -> {
			repository.delete(modopagamento);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody ModoPagamento modoPagamentoAtualizado) {
		repository.findById(id).map(modoPagamento -> {
			modoPagamento.setDescricao(modoPagamentoAtualizado.getDescricao());
			return repository.save(modoPagamentoAtualizado);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modo Pagamento não encontrado"));
	}

}
