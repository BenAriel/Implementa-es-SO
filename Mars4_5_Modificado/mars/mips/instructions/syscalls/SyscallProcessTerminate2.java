package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Escalonador;
import mars.mips.SO.ProcessManager.TabelaDeProcessos;
import mars.mips.hardware.RegisterFile;

public class SyscallProcessTerminate2 extends AbstractSyscall{

	public SyscallProcessTerminate2() {
		super(38, "ProcessTerminate2");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		TabelaDeProcessos.setProcessoEmExecucao(null);
		Escalonador.escalonarPorPrioridadeFixa();
		
	}

}

