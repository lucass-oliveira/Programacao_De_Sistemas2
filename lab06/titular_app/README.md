# Titular App (Lab06)

Aplicação de console construída no laboratório 06 da disciplina de Programação de Sistemas 2. O projeto foi gerado via Spring Initializr com `groupId` `ps2` e `artifactId` `titular_app`, adicionando as dependências Spring Data JPA e H2. Em seguida, substituímos a configuração padrão para usar PostgreSQL (Supabase) e implementamos as classes `ES`, `Titular`, `TitularDao`, `TitularRepo` e `TitularAppApplication`, conforme o roteiro fornecido.

## Funcionalidades
- `1` Listar todos os titulares cadastrados
- `2` Buscar um titular pelo número (ID)
- `3` Criar um novo titular
- `4` Alterar dados de um titular existente
- `5` Apagar um titular
- `0` Encerrar a aplicação

Todas as operações passam por uma instância de `TitularDao`, que encapsula o acesso ao banco via Spring Data JPA.

## Como executar
No Windows PowerShell, navegue até `lab06/titular_app` e inicie a aplicação com o wrapper Maven

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

Para encerrar, escolha a opção `0` no menu ou interrompa o processo no terminal.
