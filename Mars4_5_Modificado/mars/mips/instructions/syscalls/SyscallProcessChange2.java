package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Escalonador;

public class SyscallProcessChange2 extends AbstractSyscall{

	public SyscallProcessChange2() {
		super(37, "ProcessChange2");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
        
		Escalonador.escalonarPorPrioridadeFixa();
	}
}

