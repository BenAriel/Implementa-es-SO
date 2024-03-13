package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.TabelaDeProcessos;
import mars.mips.hardware.RegisterFile;

public class SyscallFork extends AbstractSyscall{

	public SyscallFork() {
		super(18, "Fork");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		int programLabel = RegisterFile.getValue(4);
		
		PCB processoAtual = new PCB();
		processoAtual.setProgramLabel(programLabel);
		
		TabelaDeProcessos.adicionarProcessoPronto(processoAtual);
	}

}
