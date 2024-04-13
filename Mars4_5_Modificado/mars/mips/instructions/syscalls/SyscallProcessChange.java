package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;

import mars.mips.SO.ProcessManager.Escalonador;

public class SyscallProcessChange extends AbstractSyscall{

	public SyscallProcessChange() {
		super(19, "ProcessChange");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		
		Escalonador.escalonarPorFIFO();
	}
	
}
