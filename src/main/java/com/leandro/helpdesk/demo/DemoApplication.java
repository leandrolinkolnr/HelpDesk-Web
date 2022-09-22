package com.leandro.helpdesk.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leandro.helpdesk.demo.domain.Chamado;
import com.leandro.helpdesk.demo.domain.Cliente;
import com.leandro.helpdesk.demo.domain.Tecnico;
import com.leandro.helpdesk.demo.domain.Repositories.ChamadoRepository;
import com.leandro.helpdesk.demo.domain.Repositories.ClienteRepository;
import com.leandro.helpdesk.demo.domain.Repositories.TecnicoRepository;
import com.leandro.helpdesk.demo.domain.enums.Perfil;
import com.leandro.helpdesk.demo.domain.enums.Prioridade;
import com.leandro.helpdesk.demo.domain.enums.Status;


// CommandLineRunner vai fazer o metodo run sempre ser executado quando der o run
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired   //Ingeção de dependencia
	private TecnicoRepository tecnicoRepository;

	@Autowired   //Ingeção de dependencia
	private ClienteRepository clienteRepository;

	@Autowired   //Ingeção de dependencia
	private ChamadoRepository chamadoRepository;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Valdir Costa", "760.457.770-93", "valdir@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);  // Adicionar esse perfil como adm

		Cliente cli1 = new Cliente(null, "Linus Tovalders", "10768103654", "linus@mail.com", "1234");
		//cli1.addPerfil(Perfil.CLIENTE);

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado1", "Primeiro Chamado", tec1, cli1);
		
		// Pra salvar no banco de dados
		// o Array as list vai criar uma lista com o que tem nos parametros
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}

}
