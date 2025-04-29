package br.edu.utfpr.cp.espjava.crud_cidades.cidade;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class CidadeController {

    private final CidadeRepository repository;

    public CidadeController(final CidadeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String listar(
        Model memoria, 
        Principal usuario, 
        HttpSession sessao,
        HttpServletResponse response) {

        //salvando dados com cookies
        response.addCookie(new Cookie("listar", LocalDateTime.now().toString()));
        memoria.addAttribute("listaCidades", this.converteCidade(repository.findAll()));

        //salvando nome do usuário na sessão
        sessao.setAttribute("usuarioAtual", usuario.getName());

        return "/crud";
    }

    private List<Cidade> converteCidade(List<CidadeEntidade> cidades) {
        return cidades.stream()
                        .map(cidade -> new Cidade(
                            cidade.getNome(), 
                            cidade.getEstado()))
                        .collect(Collectors.toList());
    }

    @PostMapping("/criar")
    public String criar(
        @Valid Cidade cidade, 
        BindingResult validacao, 
        Model memoria,
        HttpServletResponse response) {

        //salvando dados com cookies
        response.addCookie(new Cookie("criar", LocalDateTime.now().toString()));

        if (validacao.hasErrors()) {
            validacao
                .getFieldErrors()
                .forEach(error -> 
                        memoria.addAttribute(
                            error.getField(), 
                            error.getDefaultMessage())
                        );

                memoria.addAttribute("nomeInformado", cidade.getNome());
                memoria.addAttribute("estadoInformado", cidade.getEstado());
                memoria.addAttribute("listaCidades", this.converteCidade(repository.findAll()));
                
                return ("/crud");
        } else {

            repository.save(cidade.clonar());
        }

        return "redirect:/";
    }

    @GetMapping("/excluir")
    public String excluir(
            @RequestParam String nome, 
            @RequestParam String estado,
            HttpServletResponse response) {

        //salvando dados com cookies
        response.addCookie(new Cookie("excluir", LocalDateTime.now().toString()));

        var cidadeEstadoEncontrada = repository.findByNomeAndEstado(nome, estado);
        cidadeEstadoEncontrada.ifPresent(repository::delete);

        return "redirect:/";
    }

    @GetMapping("/preparaAlterar")
    public String preparaAlterar(
        @RequestParam String nome, 
        @RequestParam String estado,
        Model memoria) {

            var cidadeAtual = repository.findByNomeAndEstado(nome, estado);

            cidadeAtual.ifPresent(cidadeEncontrada -> {
                memoria.addAttribute("cidadeAtual", cidadeEncontrada);
                memoria.addAttribute("listaCidades", this.converteCidade(repository.findAll()));
            });

            return "/crud";
    }

    @PostMapping("/alterar")
    public String alterar(
        @RequestParam String nomeAtual, 
        @RequestParam String estadoAtual,
        Cidade cidade,
        BindingResult validacao,
        Model memoria,
        HttpServletResponse response) {

            //salvando dados com cookies
            response.addCookie(new Cookie("alterar", LocalDateTime.now().toString()));

            var cidadeAtual = repository.findByNomeAndEstado(nomeAtual, estadoAtual);

            if (cidadeAtual.isPresent()) {
                
                var cidadeEncontrada = cidadeAtual.get();
                cidadeEncontrada.setNome(cidade.getNome());
                cidadeEncontrada.setEstado(cidade.getEstado());

                repository.saveAndFlush(cidadeEncontrada);
            }

            return "redirect:/";
    }

    //método para exibir cookie
    @GetMapping("/mostrar")
    @ResponseBody
    public String mostraCookie(@CookieValue String listar) {
        return "Último acesso ao método listar(): " + listar;
    }
}