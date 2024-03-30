package br.gov.sp.fatec.apipixel.core.domain.repository;

import br.gov.sp.fatec.apipixel.core.domain.dto.ColaboradorDto;

import java.util.List;

public interface ColaboradorRepository {

    List<String> carregar(Long empresaId);
}
