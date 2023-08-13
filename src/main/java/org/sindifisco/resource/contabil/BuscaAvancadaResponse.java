package org.sindifisco.resource.contabil;

import lombok.Getter;
import lombok.Setter;
import org.sindifisco.model.Lancamento;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class BuscaAvancadaResponse {
    private Page<Lancamento> lancamentos;
    private Double totalValor;

    public BuscaAvancadaResponse(Page<Lancamento> lancamentos, Double totalValor) {
        this.lancamentos = lancamentos;
        this.totalValor = totalValor;
    }


}

