package br.com.springboot.crud.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.crud.dtos.UsuarioDto;
import br.com.springboot.crud.model.Usuario;
import br.com.springboot.crud.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@PostMapping(value = "salvar")
	public ResponseEntity<Usuario> salvar(@RequestBody @Valid UsuarioDto usuarioDto) {
		Usuario usuarioModel = new Usuario();
		BeanUtils.copyProperties(usuarioDto, usuarioModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuarioModel));
	}

}
