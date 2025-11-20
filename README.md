ESports Arena – Projeto Refatorado
---
Status do Projeto
Concluído / Preparado para integração Web


---
Tecnologias Utilizadas

- Java (JDK 17)

- MySQL

- JDBC (Java Database Connectivity)

- Git & GitHub (Versionamento)

- Arquitetura em camadas (Model, DAO, Service)

- Princípios SOLID

- Utilização de db.properties para credenciais

---

Desenvolvedor

- Ricardo Barreto


---

Objetivo do Software

- Refatorar o sistema desktop anterior, removendo dependências da interface Swing e reorganizando sua estrutura para:

- aplicar princípios SOLID

- separar regras de negócio da interface

- seguir boas práticas de arquitetura

- preparar o código para ser reutilizado no desenvolvimento de uma aplicação web futura

- tornar o sistema mais limpo, modular, extensível e profissional

---

Funcionalidades Implementadas

- Cadastro, edição e exclusão de usuários

- Criação e gerenciamento de torneios

- Cadastro e gerenciamento de times e jogadores

- Registro e consulta de rankings

- Agendamento e atualização de partidas

- Sistema de inscrições de jogadores em torneios

- Camada de serviços (Service Layer) aplicando regras de negócio

- Padrão DAO com implementações MySQL

- Conexão estruturada via db.properties

- Testes completos via método main() garantindo o funcionamento de ponta a ponta

---
Destaques da Refatoração:

- Separação completa entre interface gráfica e lógica de negócio

- Criação de uma camada Service para cada entidade

- DAO interfaces padronizadas e suas implementações MySQL

- Classes model reorganizadas

- Correções de inconsistências de tipos (LocalDate / LocalDateTime)

- Remoção de duplicações e code smells

- Estrutura de projeto preparada para uma futura API REST em Java ou Spring Boot
