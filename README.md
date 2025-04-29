# JavaWeb
Projeto desenvolvido nos estudos da disciplina JavaWeb do curso de pós-graduação em Tecnologia Java - UTFPR.

Processo de construção:

*Semana/Etapa 1*: Início a um projeto do tipo CRUD. Como referência, usamos os dados de cidades. Uma cidade está associada a um único estado. Portanto, para criar uma cidade precisamos informar o nome da cidade e o estado onde ela se encontra.

*Semana/Etapa 2*: Transformação da primeira versão da página construída de estática para uma página dinâmica. Isso é necessário porque queremos que a tabela de cidades seja atualizada à medida que novas cidades são inseridas. Para isso, vamos precisar de mais uma tecnologia - o Freemarker. Em seguida, mudamos a página existente para uma nova pasta. Assim, o Spring Boot reconhece a página como uma página dinâmica. Também alteramos a extensão da página. O próximo passo é colocar o código dinâmico na página, usando a sintaxe do Freemarker. Também fizemos os ajustes necessários para implementar o padrão MVC no projeto.

*Semana/Etapa 3*: Finalização da implementação das quatro operações CRUD. Isso significa que nosso usuário será capaz de criar, alterar, excluir e listar as cidades em uma base de dados. Obs: ainda estamos usando uma base local, baseada em uma lista em memória. O projeto evoluirá para ter integrado sua base com um banco de dados.

*Semana/Etapa 4*: Adição da validação da entrada de dados com o Bean Validation Framework, um recurso fundamental para garantir que as entradas de dados estejam de acordo com o esperado. Vimos como usar recursos do Bean Validation Framework juntamente com o Spring Boot, Freemarker e Bootstrap para garantir que o usuário consiga visualizar os erros e corrigir os dados sempre que necessário.

*Semana/Etapa 5*: Integração da aplicação aplicação com o banco de dados usando Spring Data para persistência dos dados para além da memória.

*Semana/Etapa 6*: - Outro projeto - Construção de API com Spring WebFlux.

*Semana/Etapa 7*: A maioria das aplicações Web têm algum tipo de segurança. Segurança é um termo amplo que abrange vários aspectos, como conexão segura, cifragem de dados entre outros. Nesta seção, focamos em dois dos mecanismos mais comuns de segurança: autenticação e autorização. Esse tópico é tão grande que o Spring tem um projeto inteiro só pra cuidar disso: o Spring Security. Essa seção se concentra naquilo que é fundamental para os mecanismos de autenticação e autorização, incluindo a definição de usuários em um banco de dados e suas permissões.

*Semana/Etapa 8*: Aborda três tópicos complementares no contexto do aplicativo que estamos usando como exemplo, mas importantes no desenvolvimento de aplicações Web. Listeners permitem empregar o padrão publisher-subscriber (observer) de uma forma muito simples com o Spring Boot. Sessões e cookies permitem armazenar dados temporariamente. Enquanto o primeiro guarda dados em um espaço na memória do servidor, o outro guarda dados diretamente no navegador.