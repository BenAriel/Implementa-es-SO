package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Semaphore;
import mars.mips.SO.ProcessManager.SemaphoreList;
import mars.mips.hardware.RegisterFile;

public class SyscallDownSemaphore extends AbstractSyscall{

	public SyscallDownSemaphore() {
		super(24, "DownSemaphore");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		int enderecoVariavel = RegisterFile.getValue(4);
		
		Semaphore semaforo = SemaphoreList.getSemaphore(enderecoVariavel);
		semaforo.SemaphoreDown();
	}
}
