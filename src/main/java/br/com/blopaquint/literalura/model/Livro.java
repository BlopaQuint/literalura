package br.com.blopaquint.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Autor autor;

    private String idioma;

    private Integer totalDeDownloads;

    public Livro() {}

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();

        if (dadosLivro.autores() != null && !dadosLivro.autores().isEmpty()) {
            this.autor = dadosLivro.autores().get(0);
        } else {
            this.autor = new Autor();
            this.autor.setNome("Autor desconhecido");
        }

        if (dadosLivro.idiomas() != null && !dadosLivro.idiomas().isEmpty()) {
            this.idioma = dadosLivro.idiomas().get(0);
        } else {
            this.idioma = "desconhecido";
        }

        this.totalDeDownloads = dadosLivro.totalDeDownloads();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public Integer getTotalDeDownloads() { return totalDeDownloads; }
    public void setTotalDeDownloads(Integer totalDeDownloads) { this.totalDeDownloads = totalDeDownloads; }

    @Override
    public String toString() {
        return "\n* * * * * LIVRO * * * * *\n" +
                "\nTítulo: " + titulo +
                "\nAutor: " + (autor != null ? autor.getNome() : "Desconhecido") +
                "\nIdioma: " + idioma +
                "\nTotal de downloads: " + totalDeDownloads + "\n" +
                "\n* * * * * * * * * * * * *";
    }
}