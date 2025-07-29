package br.com.blopaquint.literalura.repository;

import br.com.blopaquint.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);

    @Query("SELECT a FROM Autor a WHERE :ano >= a.anoDeNascimento AND :ano <= a.anoDeMorte ORDER BY a.anoDeNascimento")
    List<Autor> buscarAutoresPorAno(Integer ano);
}