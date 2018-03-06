package banco.rmi.server;

public class User {
	private int id;
	private String nome;
	private Double saldo;

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public User(int id, String nome,Double saldo) {
		this.id = id;
		this.nome = nome;
		this.saldo = saldo;
	}
	
}
