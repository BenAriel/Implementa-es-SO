package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Semaphore;
import mars.mips.SO.ProcessManager.SemaphoreList;
import mars.mips.hardware.RegisterFile;

public class SyscallUpSemaphore extends AbstractSyscall{

	public SyscallUpSemaphore() {
		super(25, "UpSemaphore");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		int enderecoVariavel = RegisterFile.getValue(4);
		
		Semaphore semaforo = SemaphoreList.getSemaphore(enderecoVariavel);		
		semaforo.SemaphoreUp();
	}
}
