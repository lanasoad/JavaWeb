package br.edu.utfpr.cp.espjava.crud_cidades.cidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public final class Cidade {

    @NotBlank(message = "{app.cidade.blank}")
    @Size(min = 5, max = 60, message = "{app.cidade.size}")
    private final String nome;

    @NotBlank(message = "{app.estado.blank}")
    @Size(min = 2, max = 2, message = "{app.estado.size}")
    private final String estado;

    public Cidade(final String nome, final String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public String getNome() {
        return nome;
    }

    //add DTO
    //usa os dados da instância Cidade atual e retorna um objeto do tipo CidadeEntidade
    public CidadeEntidade clonar() {
        var cidadeEntidade = new CidadeEntidade();

        cidadeEntidade.setNome(this.getNome());
        cidadeEntidade.setEstado(this.getEstado());

        return cidadeEntidade;
    }

    //recebe um objeto do tipo CidadeEntidade como parâmetro e retorna um objeto do tipo Cidade
    public Cidade clonar(CidadeEntidade cidade) {
        return new Cidade(cidade.getNome(), cidade.getEstado());
    }
}
