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

## Casos de Uso para Software de Controle de Varejo

O sistema de gerenciamento de produtos foi projetado para ser utilizado em um software de controle de varejo do tipo supermercado. Abaixo estão alguns casos de uso relevantes, ilustrando como os métodos implementados podem ser aplicados em situações do dia a dia:

### 1. Cadastro de Produto
**Descrição:** O administrador cadastra um novo produto no sistema.  
**Métodos Utilizados:** `save(Produto produto)`  
**Validações:**  
- Verificar se todos os campos obrigatórios (nome, preço, quantidade) estão preenchidos.
- Garantir que o preço do produto seja um valor positivo.
- Garantir que a quantidade em estoque seja um número não negativo.

### 2. Atualização de Produto
**Descrição:** O administrador atualiza as informações de um produto existente.  
**Métodos Utilizados:** `update(Produto produto)`  
**Validações:**  
- Verificar se o produto existe no sistema antes da atualização.
- Validar se os novos valores de preço e quantidade são válidos.

### 3. Exclusão de Produto
**Descrição:** O administrador remove um produto do sistema.  
**Métodos Utilizados:** `delete(Long id)`  
**Validações:**  
- Verificar se o produto existe antes de tentar deletá-lo.
- Garantir que o produto não esteja associado a vendas pendentes.

### 4. Consulta de Produto por ID
**Descrição:** O usuário consulta as informações de um produto específico.  
**Métodos Utilizados:** `findById(Long id)`  
**Validações:**  
- Retornar uma mensagem apropriada se o produto não for encontrado.

### 5. Listagem de Todos os Produtos
**Descrição:** O usuário consulta a lista de todos os produtos disponíveis.  
**Métodos Utilizados:** `findAll()`  
**Validações:**  
- Retornar uma lista vazia se não houver produtos cadastrados.

### 6. Controle de Estoque
**Descrição:** O sistema monitora a quantidade de produtos em estoque e alerta quando a quantidade estiver abaixo de um limite mínimo.  
**Métodos Utilizados:** `findAll()`  
**Validações:**  
- Comparar a quantidade disponível com um valor mínimo definido para cada produto.
- Gerar alertas ou relatórios quando o estoque estiver baixo.

### 7. Registro de Venda
**Descrição:** O operador registra a venda de um ou mais produtos.  
**Métodos Utilizados:** `findById(Long id)`, atualização da quantidade do produto.  
**Validações:**  
- Verificar se o produto existe e se há estoque suficiente para a venda.
- Atualizar a quantidade do produto após a venda.

### 8. Relatório de Vendas
**Descrição:** O administrador consulta um relatório das vendas realizadas em um determinado período.  
**Métodos Utilizados:** Métodos auxiliares para filtrar e agregar dados de vendas.  
**Validações:**  
- Garantir que o período informado seja válido.
- Retornar resultados apropriados se não houver vendas no período.

### 9. Consulta de Produtos com Desconto
**Descrição:** O administrador aplica um desconto em um ou mais produtos e consulta os produtos em promoção.  
**Métodos Utilizados:** `update(Produto produto)` para aplicar o desconto.  
**Validações:**  
- Validar se o desconto é um valor válido (não negativo e menor que o preço original).
- Retornar a lista de produtos que estão em promoção.

### Observação Sobre Versatilidade
Embora os métodos implementados sejam simples, eles podem ser utilizados de forma versátil dentro da aplicação. Essa estrutura permite uma fácil escalabilidade para níveis mais complexos, à medida que novas funcionalidades e integrações são adicionadas ao sistema. A flexibilidade do design possibilita a adaptação a diferentes necessidades de negócio, mantendo a robustez e a eficiência.

### POSTMAN
Além disso, na raiz do projeto, há uma coleção do Postman disponível, permitindo que usuários interajam com a API de forma simplificada, testando os endpoints sem complicações.