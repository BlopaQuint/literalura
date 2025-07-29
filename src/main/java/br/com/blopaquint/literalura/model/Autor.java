package br.com.blopaquint.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @JsonAlias("name")
    private String nome;

    @JsonAlias("birth_year")
    private Integer anoDeNascimento;

    @JsonAlias("death_year")
    private Integer anoDeMorte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getAnoDeNascimento() { return anoDeNascimento; }
    public void setAnoDeNascimento(Integer anoDeNascimento) { this.anoDeNascimento = anoDeNascimento; }

    public Integer getAnoDeMorte() { return anoDeMorte; }
    public void setAnoDeMorte(Integer anoDeMorte) { this.anoDeMorte = anoDeMorte; }

    public List<Livro> getLivros() { return livros; }
    public void setLivros(List<Livro> livros) {
        livros.forEach(l -> l.setAutor(this));
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "\nAutor: " + nome +
                "\nAno de nascimento: " + anoDeNascimento +
                "\nAno de falecimento: " + anoDeMorte +
                "\nLivros: " + livros.stream().map(Livro::getTitulo).toList() + "\n";
    }
}