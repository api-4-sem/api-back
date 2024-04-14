package br.gov.sp.fatec.apipixel.core.usecase.empresa;

import br.gov.sp.fatec.apipixel.core.domain.projection.EmpresaProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarregarEmpresaUC {

    private final EmpresaRepository empresaRepository;

    public CarregarEmpresaUC(EmpresaRepository empresaRepository){
        this.empresaRepository = empresaRepository;
    }

    public List<EmpresaProjection> executar(){
        return empresaRepository.carregar();
    }
}
