package br.gov.sp.fatec.apipixel.core.usecase.empresa;

import br.gov.sp.fatec.apipixel.core.domain.entity.Empresa;
import br.gov.sp.fatec.apipixel.core.domain.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarEmpresaUC {

    private final EmpresaRepository empresaRepository;

    public CriarEmpresaUC(EmpresaRepository empresaRepository){
        this.empresaRepository = empresaRepository;
    }

    public Empresa executar(Empresa novaEmpresa){
        return empresaRepository.save(novaEmpresa);
    }
}
