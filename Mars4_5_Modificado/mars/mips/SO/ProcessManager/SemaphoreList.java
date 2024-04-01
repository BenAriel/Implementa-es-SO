package mars.mips.SO.ProcessManager;

import java.util.ArrayList;
import java.util.List;


public abstract class SemaphoreList {

	public static List<Semaphore> semaforos = new ArrayList<>();
	
	public static void adicionarSemaforo(int enderecoVariavel, int valorVariavel) {
		Semaphore semaforo = new Semaphore(enderecoVariavel, valorVariavel);
		semaforos.add(semaforo);
	}
	
	public static Semaphore getSemaphore(int endereco) {
		
		for(int i = 0; i < semaforos.size(); i++) {
			if (semaforos.get(i).getEndereco() == endereco) {
				return semaforos.get(i);
			}
		}
		
		return null;
	}
}
