# squadRaizes

Projeto para avaliação de capacidades técnicas para ocupação de vaga de Desenvolvedor.

## Projeto desenvolvido para o Gerenciamento de Produtos

### Descrição do Projeto
Este projeto é um sistema de gerenciamento de produtos, onde administradores podem cadastrar novos produtos em um catálogo. O sistema foi desenvolvido utilizando os princípios do Test-Driven Development (TDD), garantindo que todas as funcionalidades sejam testadas antes da implementação, seguindo uma abordagem orientada a resultados.

### Tecnologias Utilizadas
- **Java:** Linguagem de programação principal.
- **Spring Boot:** Framework utilizado para construção de APIs RESTful.
- **JPA (Java Persistence API):** Para gerenciar a persistência de dados.
- **H2 Database:** Banco de dados em memória usado durante o desenvolvimento e testes.
- **Mockito:** Biblioteca para criar objetos simulados nas classes de teste.
- **JUnit:** Framework para realizar testes unitários.
- **Maven:** Gerenciador de dependências e construção do projeto.

### Design Pattern
O projeto utiliza os padrões **MVC (Model-View-Controller)** e **Factory**, onde a lógica de negócios está separada da camada de apresentação. Assim, temos:

- **Modelo (Model):** Representa os dados da aplicação, onde os produtos são representados por classes de entidade (Entity).
- **Visão (View):** A resposta da API fornece dados no formato JSON para o cliente.
- **Controlador (Controller):** A classe `ProdutoController` gerencia as requisições e respostas, utilizando um serviço para manipulação de dados.
- **DTO (Data Transfer Object):** Utilizado para transferir dados entre a camada de apresentação e a camada de serviço.
- **DAO (Data Access Object):** Responsável pela interação com a base de dados, permitindo operações de CRUD (Create, Read, Update, Delete) nos produtos.
- **Conversores (Utils):** Implementações de utilitários, como conversores que transformam entidades em DTOs e vice-versa, facilitando a comunicação entre diferentes camadas do sistema.

### História de Usuário
**Título:** Cadastro de Produto

**Como** um administrador do sistema,  
**Quero** cadastrar novos produtos,  
**Para que** eu possa atualizar o catálogo de produtos disponíveis para venda.

#### Critérios de Aceitação:
1. O administrador deve ser capaz de enviar informações do produto (nome, descrição, preço e quantidade).
2. O sistema deve validar se todos os campos obrigatórios foram preenchidos.
3. Caso algum campo esteja inválido, o sistema deve retornar um status de erro 400 com detalhes sobre os erros.
4. Se todos os campos estiverem corretos, o produto deve ser salvo no banco de dados e retornar um status de sucesso 201.
5. O produto cadastrado deve retornar suas informações completas em formato JSON.

### Caso de Uso
**Título:** Cadastro de Produto

**Ator Primário:** Administrador

#### Pré-condições:
- O administrador deve estar autenticado no sistema.
- O sistema deve estar em funcionamento.

#### Pós-condições:
- O produto é salvo no banco de dados.

#### Fluxo Principal:
1. O administrador acessa a funcionalidade de cadastro de produtos.
2. O administrador preenche um formulário com as informações do produto (nome, descrição, preço e quantidade).
3. O administrador envia o formulário.
4. O sistema valida os dados:
   - Se os dados são válidos, o sistema salva o produto no banco de dados.
   - Se os dados são inválidos, o sistema retorna um status de erro 400, incluindo mensagens de erro.
5. O sistema retorna uma resposta:
   - Se o cadastro foi bem-sucedido, retorna um status 201 junto com os detalhes do produto cadastrado.
   - Se ocorreu um erro, retorna o status 400 com detalhes sobre quais campos estão incorretos.

#### Fluxo Alternativo - Erros de Validação:
- Se o administrador não preencher todos os campos obrigatórios ou se preencher dados inválidos (por exemplo, preço negativo), o sistema deve gerar mensagens de erro apropriadas e não salvar o produto.

### Testes
O projeto implementa uma série de testes unitários que seguem o modelo do Test-Driven Development (TDD), assegurando que as funcionalidades atendam aos requisitos especificados e funcionem conforme o esperado.

Além disso, na raiz do projeto, há uma coleção do Postman disponível, permitindo que usuários interajam com a API de forma simplificada, testando os endpoints sem complicações.