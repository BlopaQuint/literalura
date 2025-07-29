package br.com.blopaquint.literalura.main;

import br.com.blopaquint.literalura.model.Autor;
import br.com.blopaquint.literalura.model.DadosLivro;
import br.com.blopaquint.literalura.model.Livro;
import br.com.blopaquint.literalura.repository.AutorRepository;
import br.com.blopaquint.literalura.repository.LivroRepository;
import br.com.blopaquint.literalura.service.ConsumoApi;
import br.com.blopaquint.literalura.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books?search=";
    private LivroRepository livroRepositorio;
    private AutorRepository autorRepositorio;
    private List<Livro> livros;
    private List<Autor> autores;

    public Main(LivroRepository livroRepositorio, AutorRepository autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void exibeMenu() {

        int option = -1;
        while (option != 0) {

            System.out.println("""
                    \n1 - Buscar livro pelo título
                    2 - Listar livros catalogados
                    3 - Listar autores catalogados
                    4 - Listar autores vivos por ano
                    5 - Listar livros por idioma
                    0 - Sair
                    \nDigite a opção desejada:
                    """);

            option = leitura.nextInt();
            leitura.nextLine();

            switch (option) {

                case 1:
                    buscarLivroNaWeb();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    buscarAutoresPorAno();
                    break;
                case 5:
                    buscarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");

            }

        }

    }

    private <T, U extends Comparable<U>> void listarOrdenado(
            List<T> lista,
            Function<T, U> chaveOrdenacao,
            boolean reverso
    ) {
        Stream<T> streamOrdenado = lista.stream()
                .sorted(reverso
                        ? Comparator.comparing(chaveOrdenacao).reversed()
                        : Comparator.comparing(chaveOrdenacao)
                );

        streamOrdenado.forEach(System.out::println);
    }

    private DadosLivro getDadosLivro() {

        System.out.println("\nDigite o nome do livro para busca:");
        String nomeLivro = leitura.nextLine();
        String nomeFormatado = nomeLivro.toLowerCase().replace(" ", "+");
        String json = consumo.obterDados(ENDERECO + nomeFormatado);

        if (json == null || json.isEmpty() || json.contains("\"results\":[]")) {
            System.out.println("\nNão foi possível encontrar o livro pesquisado ou o título foi digitado incorretamente.");
            return null;
        }
        return conversor.obterDados(json, DadosLivro.class);

    }

    private void buscarLivroNaWeb() {

        var novoCadastro = "S";
        while (novoCadastro.equalsIgnoreCase("s")) {

            DadosLivro dados = getDadosLivro();
            if (dados == null) return;
            Livro livro = new Livro(dados);
            livroRepositorio.save(livro);
            System.out.println(livro.toString());
            System.out.println("\nO Livro foi registrado com sucesso!");
            System.out.println("\nDeseja realizar uma nova busca de livro para registrar? (S/N)");
            novoCadastro = leitura.nextLine().toLowerCase();

        }

    }

    private void listarLivros() {

        livros = livroRepositorio.findAll();
        listarOrdenado(livros, Livro::getTotalDeDownloads, true);

    }

    private void listarAutores() {

        autores = autorRepositorio.findAll();
        listarOrdenado(autores, Autor::getNome, false);

    }

    private void buscarAutoresPorAno() {

        System.out.println("\nDigite um ano para listar os autores vivos na época:");
        var ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autores = autorRepositorio.buscarAutoresPorAno(ano);
        if(autores.isEmpty()){
            System.out.println("\nAinda não há autores vivos catalogados no ano " + ano + ".");
        } else {
            autores.forEach(System.out::println);
        }

    }

    private void buscarLivrosPorIdioma() {

        System.out.println("""
                Opções de idiomas:
                \npt - Português
                en - Inglês
                es - Espanhol
                fr - Francês
                \nDigite o idioma desejado para listar (exemplo -> pt):
                """);

        var idioma = leitura.nextLine();
        livros = livroRepositorio.buscarLivrosPorIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("\nIdioma não encontrado ou ainda não há livros catalogados no idioma escolhido.");
        } else {
            livros.forEach(System.out::println);
        }

    }

}