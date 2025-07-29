# 📚 Literatura API

Este projeto foi desenvolvido como parte da **minha especialização Back-End TECH FOUNDATION** do programa **ONE - Oracle Next Education**, uma iniciativa educacional em parceria com a Alura e a Oracle que visa formar desenvolvedores completos e preparados para o mercado.

---

## 🧠 Sobre o Projeto

A aplicação consiste em um sistema de catálogo literário que consome dados da **API Gutendex**, uma interface moderna que facilita o acesso ao acervo de livros do **Projeto Gutenberg** — a mais antiga e vasta biblioteca digital de obras em domínio público.

### 🔍 O que é a API Gutendex?

A **Gutendex** é uma API RESTful que fornece metadados de livros do Projeto Gutenberg em formato JSON. Ela permite realizar buscas por título, autor, idioma, popularidade e outros filtros, tornando o acesso ao conteúdo literário muito mais prático para aplicações modernas.

- Site oficial: [gutendex.com](https://gutendex.com)
- Repositório: [GitHub - garethbjohnson/gutendex](https://github.com/garethbjohnson/gutendex)

### 📖 O que é o Projeto Gutenberg?

O **Projeto Gutenberg**, fundado em 1971 por Michael S. Hart, é considerado a **primeira biblioteca digital do mundo**. Seu objetivo é disponibilizar gratuitamente livros em domínio público, digitalizados por voluntários. Atualmente, o acervo conta com mais de **75.000 obras clássicas** em diversos idiomas e formatos.

- Site oficial: [gutenberg.org](https://www.gutenberg.org)
- Principais características:
    - Acesso livre e universal
    - Obras literárias clássicas e educacionais
    - Compatível com eReaders, navegadores e dispositivos móveis

---

## 🛠️ Tecnologias Utilizadas

- **Java 21+**
- **Spring Boot**
- **Maven**
- **JPA / Hibernate + JPQL**
- **PostgreSQL**

---

## 📌 Funcionalidades

- Buscar livros por título via menu interativo
- Consumir dados da API Gutendex
- Converter resposta JSON em objetos Java
- Persistir autores e livros no PostgreSQL
- Exibir informações detalhadas dos livros
- Consultar dados utilizando JPQL

---

## 🚀 Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/literatura-api.git
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd literatura-api
   ```

3. Certifique-se de ter o **PostgreSQL** instalado e em execução na máquina.


4. Configure as **variáveis de ambiente** utilizadas no `application.properties`:

    - `DB_HOST`
    - `DB_NAME`
    - `DB_USERNAME`
    - `DB_PASSWORD`
   

5. Execute o projeto com Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

6. Use o menu interativo no terminal para buscar livros e explorar o catálogo.

---

## 📄 Licença

Este projeto é de uso educacional e está sob a licença MIT.

---

## ✍️ Autor

Desenvolvido por [Pablo Quintiliano](https://github.com/BlopaQuint).
