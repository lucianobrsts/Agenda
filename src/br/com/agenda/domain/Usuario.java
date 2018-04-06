package br.com.agenda.domain;

public class Usuario {

	private Long idUsuario;
	private String nome;
	private String senha;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long codigo) {
		this.idUsuario = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "Código" + idUsuario + "\nNome: " + nome + "\nSenha: " + senha ;
	}
	
}
