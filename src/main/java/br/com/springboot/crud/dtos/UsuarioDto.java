package br.com.springboot.crud.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UsuarioDto {
	
	@NotBlank
	@Size(max = 100)
	private String nome;
	
	@NotNull
	private Integer idade;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
}
