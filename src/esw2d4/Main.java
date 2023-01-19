package esw2d4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {

		Prodotto p1 = new Prodotto(1, "Libro 1", "Libri", 12.00);	
		Prodotto p2 = new Prodotto(2, "Libro 2", "Libri", 150.00);
		Prodotto p3 = new Prodotto(3, "Libro 3", "Libri", 22.00);
		Prodotto p4 = new Prodotto(4, "Gioco 1", "Baby", 8.00);
		Prodotto p5 = new Prodotto(5, "Gioco 2", "Baby", 14.00);	
		Prodotto p6 = new Prodotto(6, "Gioco 3", "Baby", 23.00);
		Prodotto p7 = new Prodotto(7, "Art 1", "Boys", 53.00);
		Prodotto p8 = new Prodotto(8, "Art 2", "Boys", 29.00);
		Prodotto p9 = new Prodotto(8, "Art 3", "Boys", 61.00);
		
		ArrayList<Prodotto> prodotti = new ArrayList<>() {{
			add(p1);
			add(p2);
			add(p3);
			add(p4);
			add(p5);
			add(p6);
			add(p7);
			add(p8);
			add(p9);

		}};
		System.out.println("----------------------------------");
	    System.out.println("----------------------------------");
		
		Stream<Prodotto> bookStream = prodotti.stream()
				.filter((prodotto) -> prodotto.getCategoria() == "Libri" && prodotto.getPrezzo() > 100) ;
			
		bookStream.forEach(prodotto -> {
			System.out.println("Libro con prezzo maggiore di 100 euro è: " + prodotto);
		});
		
		System.out.println("----------------------------------");
	    System.out.println("----------------------------------");
		
		ArrayList<Prodotto> ordine1 = new ArrayList<Prodotto>() {{
			add(p1);
			add(p7);
			add(p6);
			
		}};
		
		ArrayList<Prodotto> ordine2 = new ArrayList<Prodotto>() {{
			add(p9);
			add(p3);
			add(p1);
		
		}};
		
		ArrayList<Prodotto> ordine3 = new ArrayList<Prodotto>() {{
			add(p2);
			add(p8);
			add(p4);
			
		}};
		
		
		Cliente c1 =  new Cliente(1, "Luca Bianchi", 1 );
		Cliente c2 =  new Cliente(2, "Marco Rossi", 3 );
		Cliente c3 =  new Cliente(3, "Andrea Neri", 1 );
		
		Ordine o1 = new Ordine (1, "in consegna", LocalDate.now(),LocalDate.of(2023, 1, 23), ordine1, c1);
		Ordine o2 = new Ordine (2, "in consegna", LocalDate.now(),LocalDate.of(2023, 1, 25), ordine2, c2);
		Ordine o3 = new Ordine (3, "in consegna", LocalDate.now(),LocalDate.of(2023, 1, 26), ordine3, c3);
		
		ArrayList<Ordine> listaOrdini = new ArrayList<Ordine>() {{
			add(o1);
			add(o2);
			add(o3);
		}};
		
		ArrayList<Ordine> listaFiltrata = new ArrayList<>();
        listaOrdini.stream()
                    .filter((ordine) -> ordine.getProdotti().stream()
                                    .filter((categoriaProdotto) -> categoriaProdotto.getCategoria() == "Baby")
                                    .count() > 0)
                    .forEach((ordine) -> listaFiltrata.add(ordine));

          listaFiltrata.forEach(ordine -> {
              System.out.println(ordine);
            });
          
          System.out.println("----------------------------------");
          System.out.println("----------------------------------");
		  System.out.println("Stampo una lista di prodotti appartenenti alla categoria BOYS e applico sconto 10% ");
		  ArrayList<Prodotto> listaFiltrata2 = new ArrayList<>();
		    prodotti.stream()
		            .filter((prodotto) -> prodotto.getCategoria() == "Boys")
		            .peek(prezzo -> prezzo.setPrezzo(prezzo.getPrezzo() - (prezzo.getPrezzo() * 10) / 100)).
		            forEach((prodotto) -> System.out.println(prodotto));


              
		
		}

}

class Prodotto {
	
	long id;
	String nome;
	String categoria;
	double prezzo;
	
	public Prodotto(long id, String nome, String categoria, double prezzo) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.prezzo = prezzo;
	}
	
	public Object setPrezzo(double prezzo) {
        return this.prezzo = prezzo;
    }

	public double getPrezzo() {
		return this.prezzo;
	}

	public String getCategoria() {
		return this.categoria;
	}
	
	@Override public String toString() {
		return "Nome: " + this.nome +  " Categoria: " + this.categoria + " Prezzo: " + this.prezzo;
	}
}

class Ordine {
	
	long id;
	String stato;
	LocalDate dataOrdine;
	LocalDate dataConsegna;
	List<Prodotto> prodotti;
	Cliente cliente;

	public Ordine(long id, String stato, LocalDate dataOrdine, LocalDate dataConsegna, List<Prodotto> prodotti, Cliente    	cliente) {
		
		this.id = id;
		this.stato = stato;
		this.dataOrdine = dataOrdine;
		this.dataConsegna = dataConsegna;
		this.prodotti = prodotti;
		this.cliente = cliente;
	}
	
	public List<Prodotto> getProdotti() {
		return this.prodotti;
	}

	@Override
	public String toString() {
		return "ID - " + this.id + " stato ordine: " + this.stato + " data ordine: " + this.dataOrdine + " data consegna: " + this.dataConsegna + " prodotti: " + this.prodotti + " Cliente: " + this.cliente;
	}
}

class Cliente {
	
	long id;
	String nome;
	int livello;
	
	public Cliente(long id, String nome, int livello) {
		
		this.id = id;
		this.nome = nome;
		this.livello = livello;
	}
	
	@Override
	public String toString() {
        return this.nome + " Livello " + this.livello;
    }
}
