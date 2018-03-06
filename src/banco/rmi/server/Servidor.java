package banco.rmi.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import banco.rmi.remotebase.IRemoteBanco;

public class Servidor implements IRemoteBanco {
	private List<User> lista = userCadas();

	public static void main(String[] args) {
		try {

			System.out.println("Construindo servidor remoto");
			Servidor servidor = new Servidor();

			IRemoteBanco stub = (IRemoteBanco) UnicastRemoteObject.exportObject(servidor, 0);

			Registry registry = LocateRegistry.getRegistry(9876);

			registry.bind("servidor_banco", stub);

			System.out.println("A batata ta assando");
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public double deposito(int id, Double vlrDepo) {
		User usuaSecao = null;
		if (checaUser(id) != null) {

			usuaSecao = checaUser(id);
			usuaSecao.setSaldo(vlrDepo + usuaSecao.getSaldo());

		} else {
			System.out.println("Usuario nao encontrado");
		}

		return usuaSecao.getSaldo();
	}

	@Override
	public double saldo(int id) {
		User usuaSecao = null;
		double saldo = 0.0;

		if (checaUser(id) != null) {
			usuaSecao = checaUser(id);
			saldo = usuaSecao.getSaldo();
		} else {
			System.out.println("Usuario nao encontrado");
		}
		return saldo;
	}

	@Override
	public double saque(int id, Double vlrSaque) {
		User usuaSecao = null;
		if (checaUser(id) != null) {
			usuaSecao = checaUser(id);
			if (usuaSecao.getSaldo() < vlrSaque) {
				return 0;
			} else {
				usuaSecao.setSaldo(vlrSaque - usuaSecao.getSaldo());
			}

		} else {
			System.out.println("Usuario nao encontrado");
		}

		return usuaSecao.getSaldo();
	}

	public User checaUser(int id) {

		User usuario = null;

		for (User user : lista) {
			if (user.getId() == id) {
				usuario = user;
			}
		}
		return usuario;
	}

	public List<User> userCadas() {
		User uno = new User(1, "batata", 0.0);
		User dos = new User(2, "pirulito", 0.0);
		User tes = new User(3, "lasanha", 0.0);
		User qts = new User(4, "marmmita", 0.0);

		List<User> usuarios = new ArrayList<>();
		usuarios.add(uno);
		usuarios.add(dos);
		usuarios.add(tes);
		usuarios.add(qts);

		return usuarios;
	}

}
