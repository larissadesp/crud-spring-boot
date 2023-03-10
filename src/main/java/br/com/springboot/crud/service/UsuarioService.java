package br.com.springboot.crud.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.springboot.crud.model.Usuario;
import br.com.springboot.crud.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public Optional<Usuario> consultar(Long idUsuario) {
		return usuarioRepository.findById(idUsuario);
	}
	
	public Page<Usuario> listarTodos(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Transactional
	public void deletar(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	public List<Usuario> pesquisarPorNome(String nomeUsuario) {
		return usuarioRepository.pesquisarPorNome(nomeUsuario);
	}
	
}
