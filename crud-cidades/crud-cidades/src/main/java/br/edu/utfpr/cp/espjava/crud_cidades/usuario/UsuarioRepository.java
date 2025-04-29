package br.edu.utfpr.cp.espjava.crud_cidades.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByNome(String nome);
    
}
