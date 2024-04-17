package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.TabelaDeProcessos;
import mars.mips.hardware.Memory;
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
			ensureLimit(processoAtual);
		} else {
			processoAtual.setLowerLim(Memory.textLimitAddress);
		}

		lastPcb = processoAtual;
		
		TabelaDeProcessos.adicionarProcessoProntoPrioridade(processoAtual);
	}

	public void ensureLimit(PCB processoAtual) {
		if (processoAtual.getUpperLim() > lastPcb.getLowerLim()) {
			lastPcb.setLowerLim(processoAtual.getUpperLim() - 4);
			processoAtual.setLowerLim(Memory.textLimitAddress);

		} else if (processoAtual.getUpperLim() < lastPcb.getUpperLim()) {
			processoAtual.setLowerLim(lastPcb.getUpperLim() - 4);
			lastPcb.setLowerLim(Memory.textLimitAddress);

		} else {
			processoAtual.setLowerLim(Memory.textLimitAddress);
		}
	}
}