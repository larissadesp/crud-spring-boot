package br.com.springboot.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.crud.model.Usuario;
import br.com.springboot.crud.repository.UsuarioRepository;

@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
    	Usuario novoUsuario = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(novoUsuario, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "deletar")
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam Long idUsuario) {
    	usuarioRepository.deleteById(idUsuario);
    	return new ResponseEntity<String>("Usuário deletado com sucesso!", HttpStatus.OK);
    }
    
    @GetMapping(value = "consultar")
    @ResponseBody
    public ResponseEntity<Usuario> consultar(@RequestParam(name = "idUsuario") Long idUsuario) {
    	Usuario usuario = usuarioRepository.findById(idUsuario).get();
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    @GetMapping(value = "listarTodos")
    @ResponseBody
	public ResponseEntity<List<Usuario>> listarTodos() {
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
    
    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {
    	if (usuario.getId() == null) {
    		return new ResponseEntity<String>("É necessário informar o id do usuário para atualizar.", HttpStatus.OK);
    	}
    	
    	Usuario usuarioAtualizado = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(usuarioAtualizado, HttpStatus.OK);
    }
    
    @GetMapping(value = "pesquisarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> pesquisarPorNome(@RequestParam(name = "nomeUsuario") String nomeUsuario) {
    	List<Usuario> usuario = usuarioRepository.pesquisarPorNome(nomeUsuario.trim().toUpperCase());
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
    
}
