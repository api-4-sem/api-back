package br.gov.sp.fatec.apipixel;

import br.gov.sp.fatec.apipixel.core.domain.command.AcompanharProgressoCommand;
import br.gov.sp.fatec.apipixel.core.usecase.progresso.AcompanharProgressoUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ApiPixelApplication {

	@Autowired
	private AcompanharProgressoUC acompanharProgressoUC;


	public static void main(String[] args) {
		SpringApplication.run(ApiPixelApplication.class, args);
	}

	@EventListener
	public void onMessage(ApplicationReadyEvent event) {
		AcompanharProgressoCommand command = new AcompanharProgressoCommand(1L);
		acompanharProgressoUC.executar(command);
	}

}
