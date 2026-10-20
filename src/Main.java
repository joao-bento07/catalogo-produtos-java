import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Produto {
    private final String nome;
    private final Double preco;

    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + String.format("%.2f", preco);
    }
}

public class Main {

    public static void main(String[] args) {
        List<Produto> produtos = List.of(
                new Produto("Teclado", 150.00),
                new Produto("Mouse", 80.00),
                new Produto("Monitor", 900.00),
                new Produto("Cabo HDMI", 35.50),
                new Produto("Notebook", 3500.00),
                new Produto("Headset", 100.00),
                new Produto("Webcam", 220.00));

        // 1. Produtos com preco maior que 100
        List<Produto> acimaDe100 = produtos.stream()
                .filter(p -> p.getPreco() > 100)
                .collect(Collectors.toList());

        System.out.println("== Produtos com preco acima de R$ 100,00 ==");
        acimaDe100.forEach(System.out::println);

        // 2. Produtos ordenados por nome (ordem alfabetica)
        List<Produto> ordenados = produtos.stream()
                .sorted(Comparator.comparing(Produto::getNome))
                .collect(Collectors.toList());

        System.out.println("\n== Produtos ordenados por nome ==");
        ordenados.forEach(System.out::println);

        // 3. Busca segura com Optional
        System.out.println("\n== Busca por nome ==");
        buscarEExibir(produtos, "Monitor");
        buscarEExibir(produtos, "Impressora");
    }

    private static Optional<Produto> buscarPorNome(List<Produto> produtos, String nome) {
        return produtos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    private static void buscarEExibir(List<Produto> produtos, String nome) {
        System.out.print("Buscando \"" + nome + "\": ");
        buscarPorNome(produtos, nome).ifPresentOrElse(
                p -> System.out.println(p.getNome() + " - R$ " + String.format("%.2f", p.getPreco())),
                () -> System.out.println("Produto não encontrado."));
    }
}
