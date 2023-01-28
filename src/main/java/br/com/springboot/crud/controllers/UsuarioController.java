package br.com.springboot.crud.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping(value = "consultar/{idUsuario}")
	public ResponseEntity<Object> consultar(@PathVariable(value = "idUsuario") Long idUsuario) {
		Optional<Usuario> usuario = usuarioService.consultar(idUsuario);
		
		if (usuario.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
		} 
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado na base de dados.");
	}
	
	@GetMapping(value = "listar-todos")
	public ResponseEntity<Page<Usuario>> listarTodos(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarTodos(pageable));
	}
	
	@PutMapping(value = "atualizar/{idUsuario}")
	public ResponseEntity<Object> atualizar(@PathVariable(value = "idUsuario") Long idUsuario,
											@RequestBody @Valid UsuarioDto usuarioDto) {
		Optional<Usuario> usuario = usuarioService.consultar(idUsuario);

		if (!usuario.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
		}

		Usuario usuarioModel = new Usuario();
		BeanUtils.copyProperties(usuarioDto, usuarioModel);
		usuarioModel.setId(usuario.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.salvar(usuarioModel));
	}

	@DeleteMapping(value = "deletar/{idUsuario}")
	public ResponseEntity<Object> deletar(@PathVariable(value = "idUsuario") Long idUsuario) {
		Optional<Usuario> usuario = usuarioService.consultar(idUsuario);

		if (!usuario.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
		}
		usuarioService.deletar(usuario.get());
		return ResponseEntity.status(HttpStatus.OK).body("O usuário " + usuario.get().getNome() + " foi deletado com sucesso!");
	}
	
	@GetMapping(value = "pesquisar-por-nome")
	@ResponseBody
	public ResponseEntity<List<Usuario>> pesquisarPorNome(@RequestParam(name = "nomeUsuario") String nomeUsuario) {
		List<Usuario> usuario = usuarioService.pesquisarPorNome(nomeUsuario.trim().toUpperCase());
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
	}
	
}
