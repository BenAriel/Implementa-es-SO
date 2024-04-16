package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.TabelaDeProcessos;
import mars.mips.hardware.RegisterFile;

public class SyscallFork extends AbstractSyscall{

	private static int id = 0;
	private static PCB lastPcb;

	public SyscallFork() {
		super(18, "Fork");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		int programLabel = RegisterFile.getValue(4);
		id++;
		
		PCB processoAtual = new PCB(programLabel, id);

		if(id != 1) {
			lastPcb.setLowerLim(programLabel - 4);
		}

		lastPcb = processoAtual;
		
		TabelaDeProcessos.adicionarProcessoPronto(processoAtual);
	}
}
