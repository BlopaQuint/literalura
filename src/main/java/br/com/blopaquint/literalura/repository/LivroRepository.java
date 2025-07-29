package br.com.blopaquint.literalura.repository;

import br.com.blopaquint.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.idioma = LOWER(:idioma) ORDER BY l.idioma ASC, l.totalDeDownloads DESC")
    List<Livro> buscarLivrosPorIdioma(String idioma);
}
