package ps2.titular_app;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TitularDao {

    private final TitularRepo titularRepo;

    public TitularDao(TitularRepo titularRepo) {
        this.titularRepo = titularRepo;
    }

    public Iterable<Titular> listarTodos() {
        return titularRepo.findAll();
    }

    public Optional<Titular> buscarPorId(long id) {
        return titularRepo.findById(id);
    }

    public Titular salvar(Titular titular) {
        return titularRepo.save(titular);
    }

    public boolean remover(long id) {
        if (!titularRepo.existsById(id)) {
            return false;
        }
        titularRepo.deleteById(id);
        return true;
    }
}
