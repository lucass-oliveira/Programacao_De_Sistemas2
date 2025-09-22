package ps2.titular_app;

import static ps2.titular_app.ES.input;
import static ps2.titular_app.ES.print;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TitularAppApplication implements CommandLineRunner {

    @Autowired
    private TitularDao titularDao;

    public static void main(String[] args) {
        SpringApplication.run(TitularAppApplication.class, args);
    }

    public void criar() {
        Titular titular = new Titular();
        titular.setNome(input("Nome do novo titular: "));
        titular.setCpf(input("CPF do novo titular: "));
        Titular salvo = titularDao.salvar(titular);
        print("Titular criado com o id " + salvo.getId());
    }

    public void lerTudo() {
        Iterable<Titular> titulares = titularDao.listarTodos();
        boolean vazio = true;
        for (Titular titular : titulares) {
            vazio = false;
            print(titular.toString());
        }
        if (vazio) {
            print("Nenhum titular cadastrado.");
        }
    }

    public void buscar() {
        Long id = lerId();
        if (id == null) {
            return;
        }
        Optional<Titular> titular = titularDao.buscarPorId(id);
        if (titular.isPresent()) {
            print(titular.get().toString());
        } else {
            print("Titular não encontrado!");
        }
    }

    public void alterar() {
        Long id = lerId();
        if (id == null) {
            return;
        }
        Optional<Titular> existente = titularDao.buscarPorId(id);
        if (existente.isEmpty()) {
            print("Titular não encontrado!");
            return;
        }
        Titular titular = existente.get();
        String nome = input("Nome atual é '" + titular.getNome() + "'. Informe o novo nome (ou deixe em branco para manter): ");
        if (!nome.isBlank()) {
            titular.setNome(nome);
        }
        String cpf = input("CPF atual é '" + titular.getCpf() + "'. Informe o novo CPF (ou deixe em branco para manter): ");
        if (!cpf.isBlank()) {
            titular.setCpf(cpf);
        }
        Titular atualizado = titularDao.salvar(titular);
        print("Titular atualizado: " + atualizado);
    }

    public void apagar() {
        Long id = lerId();
        if (id == null) {
            return;
        }
        boolean removido = titularDao.remover(id);
        if (removido) {
            print("Titular removido com sucesso.");
        } else {
            print("Titular não encontrado!");
        }
    }

    private Long lerId() {
        String valor = input("Número do titular: ");
        try {
            return Long.parseLong(valor);
        } catch (NumberFormatException e) {
            print("Número inválido. Operação cancelada.");
            return null;
        }
    }

    @Override
    public void run(String... args) {
        print("# GERENCIADOR DE TITULARES!");
        boolean sair = false;
        String menu = "\n(1) Listar todos os titulares";
        menu += "\n(2) Buscar um titular específico pelo número";
        menu += "\n(3) Criar um novo titular";
        menu += "\n(4) Alterar os dados do titular";
        menu += "\n(5) Apagar um titular";
        menu += "\n(0) Sair \n";
        menu += "Escolha uma opção: ";

        while (!sair) {
            String op = input(menu);
            switch (op) {
                case "1":
                    lerTudo();
                    break;
                case "2":
                    buscar();
                    break;
                case "3":
                    criar();
                    break;
                case "4":
                    alterar();
                    break;
                case "5":
                    apagar();
                    break;
                case "0":
                    sair = true;
                    break;
                default:
                    print("Opção inválida!");
            }
        }
        print("Aplicação encerrada.");
    }
}
