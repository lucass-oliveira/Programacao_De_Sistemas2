# lab05 — Contas (DAO + Menu)

## Estrutura
- `lab05/src/Main.java` — Menu e fluxo da aplicação.
- `lab05/src/ConnectionFactory.java` — Fabrica conexões JDBC.
- `lab05/src/Conta.java` — Modelo da conta (`numero`, `saldo`).
- `lab05/src/ContaDao.java` — CRUD usando `PreparedStatement` na tabela `contas (nro_conta, saldo)`.
- `lab05/lib/postgresql-42.7.5.jar` — Driver JDBC do PostgreSQL (adicione aqui).

## Pré‑requisitos
- JDK instalado (javac/java no PATH).
- Driver JDBC: `postgresql-42.7.5.jar` salvo em `lab05/lib/`.

## Configuração do banco
No `Main.java` já está configurado com:
- `url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres"`
- `user = "postgres.wdkkakpbcxfzqhjalxse"`
- `password = "nX1D07utAev1mVx3"`

## Compilar e Executar (Terminal)
Windows (CMD/PowerShell):
- `mkdir lab05\\bin`
- `javac -cp lab05\\lib\\postgresql-42.7.5.jar -d lab05\\bin lab05\\src\\*.java`
- `java -cp "lab05\\bin;lab05\\lib\\postgresql-42.7.5.jar" Main`

Linux/macOS/WSL:
- `mkdir -p lab05/bin`
- `javac -cp lab05/lib/postgresql-42.7.5.jar -d lab05/bin lab05/src/*.java`
- `java -cp "lab05/bin:lab05/lib/postgresql-42.7.5.jar" Main`

## Testes manuais (menu)
- (1) Listar todas as contas — deve listar vazio inicialmente.
- (3) Criar conta — ex.: número `1001`, saldo `500.75`.
- (2) Buscar conta pelo número — ex.: `1001`.
- (4) Alterar saldo — ex.: `1001` → `600.00`.
- (5) Apagar conta — ex.: `1001` e depois listar.
- (0) Sair — encerra o programa.

