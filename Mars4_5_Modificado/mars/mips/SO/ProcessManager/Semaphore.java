package mars.mips.SO.ProcessManager;

import java.util.ArrayList;
import java.util.List;

public class Semaphore {
	
	private int endereco;
	private int valor;
	
	private List<PCB> processosBloqueados = new ArrayList<>();
	
	Semaphore(int endereco, int valor) {
		setEndereco(endereco);
		setValor(valor);
	}
	
	

	public int getEndereco() {
		return endereco;
	}

	public void setEndereco(int endereco) {
		this.endereco = endereco;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public List<PCB> getProcessosBloqueados() {
		return processosBloqueados;
	}

	public void setProcessosBloqueados(List<PCB> processosBloqueados) {
		this.processosBloqueados = processosBloqueados;
	}
	
	
}
