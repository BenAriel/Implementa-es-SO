package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Semaphore;
import mars.mips.SO.ProcessManager.SemaphoreList;
import mars.mips.hardware.RegisterFile;

public class SyscallTerminateSemaphore extends AbstractSyscall{

	public SyscallTerminateSemaphore() {
		super(23, "TerminateSemaphore");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		int enderecoVariavel = RegisterFile.getValue(4);
		
		try {
			Semaphore semaforo = SemaphoreList.getSemaphore(enderecoVariavel);
			semaforo.getProcessosBloqueados().clear();
		} catch (Exception e) {
			System.out.println("ERRO: Nenhum semáforo associado a esse endereço");
		}
		
	}
}
