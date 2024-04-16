package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.TabelaDeProcessos;
import mars.mips.hardware.RegisterFile;

public class SyscallFork2 extends AbstractSyscall{

	private static int id = 0;
	private static PCB lastPcb;

	public SyscallFork2() {
		super(21, "Fork2");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		int programLabel = RegisterFile.getValue(4);
		int prioridade = RegisterFile.getValue(5);
		id++;

		PCB processoAtual = new PCB(programLabel, id);
		processoAtual.setPrioridade(prioridade);

		if(id != 1) {
			lastPcb.setLowerLim(programLabel - 4);
		}

		lastPcb = processoAtual;
		
		TabelaDeProcessos.adicionarProcessoProntoPrioridade(processoAtual);
	}
}