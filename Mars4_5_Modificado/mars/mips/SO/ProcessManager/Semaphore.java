package mars.mips.SO.ProcessManager;

import java.util.LinkedList;
import java.util.Queue;

import mars.tools.ScheduleTimer;

public class Semaphore {
	
	private int endereco;
	private int valor;
	
	private Queue<PCB> processosBloqueados = new LinkedList<>();
	
	Semaphore(int endereco, int valor) {
		setEndereco(endereco);
		setValor(valor);
	}
	
	public void SemaphoreDown() {
		if (this.valor > 0) {
			valor--;
			System.out.println(valor);
		} else if (valor == 0) {
			System.out.println(valor);
			PCB processoEmExecucao = TabelaDeProcessos.getProcessoEmExecucao();
			TabelaDeProcessos.setProcessoEmExecucao(null);
			processoEmExecucao.blockState();
			
			processosBloqueados.add(processoEmExecucao);
			
			if (!ScheduleTimer.isEscalonando()) {
				switch (ScheduleTimer.scheduleType()) {
				case "Line Scheduler":
					Escalonador.escalonarPorFIFO();
					
					break;
				case "Priority Scheduler":
					Escalonador.escalonarPorPrioridadeFixa();
					
					break;
				case "Lottery Scheduler":
					Escalonador.escalonarPorLoteria();
					
					break;
				default:
					System.out.println("ERRO: Tipo inválio de escalonador selecionado!");
				}
			}
		}
	}
	
	public void SemaphoreUp() {
		if (processosBloqueados.isEmpty()) {
			valor++;
			System.out.println(valor);
		} else {
			System.out.println(valor);
			switch (ScheduleTimer.scheduleType()) {
			case "Line Scheduler":
				TabelaDeProcessos.adicionarProcessoPronto(processosBloqueados.poll());
				
				break;
			case "Priority Scheduler":
				TabelaDeProcessos.adicionarProcessoProntoPrioridade(processosBloqueados.poll());
				
				break;
			case "Lottery Scheduler":
				TabelaDeProcessos.adicionarProcessoPronto(processosBloqueados.poll());
				
				break;
			default:
				System.out.println("ERRO: Tipo inválio de escalonador selecionado!");
			}
		}
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

	public Queue<PCB> getProcessosBloqueados() {
		return processosBloqueados;
	}

	public void setProcessosBloqueados(Queue<PCB> processosBloqueados) {
		this.processosBloqueados = processosBloqueados;
	}
	
	
}
