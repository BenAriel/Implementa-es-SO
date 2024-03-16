package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.TabelaDeProcessos;
import mars.mips.hardware.RegisterFile;
import mars.mips.SO.ProcessManager.Escalonador;

public class SyscallProcessChange extends AbstractSyscall{

	public SyscallProcessChange() {
		super(19, "ProcessChange");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		PCB processoEmExecucao = TabelaDeProcessos.getProcessoEmExecucao();
		
		if (processoEmExecucao != null) {
			processoEmExecucao.copyFromRegisters();
			processoEmExecucao.setProgramLabel(RegisterFile.getProgramCounter());
		}
		
		if (!TabelaDeProcessos.getProcessosPorPrioridade().isEmpty()) {			
			Escalonador.escalonarPorPrioridadeFixa();
		} else {
			System.out.println(TabelaDeProcessos.getProcessosProntos().toString());
			Escalonador.escalonarPorFIFO();
		}
	}
}
