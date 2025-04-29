package br.edu.utfpr.cp.espjava.crud_cidades.cidade;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<CidadeEntidade, Long>{

    //método de busca por nome e estado
    //usamos o Optional porque não sabemos se a cidade existe e o Optional nos permite fazer validações antes da exclusão.
    public Optional<CidadeEntidade> findByNomeAndEstado(String nome, String estado);
}
