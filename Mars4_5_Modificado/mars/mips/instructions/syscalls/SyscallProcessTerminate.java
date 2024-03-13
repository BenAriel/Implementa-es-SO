package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Escalonador;
import mars.mips.SO.ProcessManager.TabelaDeProcessos;
import mars.mips.hardware.RegisterFile;

public class SyscallProcessTerminate extends AbstractSyscall{

	public SyscallProcessTerminate() {
		super(20, "ProcessTerminate");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		TabelaDeProcessos.setProcessoEmExecucao(null);
		
		Escalonador.escalonarPorFIFO();
		
		RegisterFile.setProgramCounter(TabelaDeProcessos.getProcessoEmExecucao().getProgramLabel());
	}

}
