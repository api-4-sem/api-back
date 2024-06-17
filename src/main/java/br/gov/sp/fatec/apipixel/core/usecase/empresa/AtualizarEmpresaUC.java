package br.gov.sp.fatec.apipixel.core.usecase.empresa;

import br.gov.sp.fatec.apipixel.core.domain.command.AtualizarEmpresaCommand;
import br.gov.sp.fatec.apipixel.core.domain.entity.Empresa;
import br.gov.sp.fatec.apipixel.core.domain.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtualizarEmpresaUC {

    private final EmpresaRepository empresaRepository;

    public AtualizarEmpresaUC(EmpresaRepository empresaRepository){
        this.empresaRepository = empresaRepository;
    }

    public Empresa executar(Long id, AtualizarEmpresaCommand empresaAtualizada){
        Optional<Empresa> resultado = empresaRepository.carregarEmpresa(id);
        if (resultado.isPresent()) {
            Empresa empresa = resultado.get();
            empresa.setCodigo(empresaAtualizada.getCodigo());
            empresa.setNome(empresaAtualizada.getNome());
            empresa.setCidade(empresaAtualizada.getCidade());
            empresa.setEstado(empresaAtualizada.getEstado());
            empresa.setPais(empresaAtualizada.getPais());
            empresa.setAdminNome(empresaAtualizada.getAdminNome());
            empresa.setAdminEmail(empresaAtualizada.getAdminEmail());
            return empresaRepository.save(empresa);
        } else {
            throw new RuntimeException("Empresa não encontrada com ID: " + id);
        }
    }
}
