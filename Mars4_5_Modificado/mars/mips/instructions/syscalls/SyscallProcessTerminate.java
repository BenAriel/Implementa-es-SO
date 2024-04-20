package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Escalonador;
import mars.mips.SO.ProcessManager.TabelaDeProcessos;
import mars.tools.ScheduleTimer;

public class SyscallProcessTerminate extends AbstractSyscall{

	public SyscallProcessTerminate() {
		super(20, "ProcessTerminate");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		TabelaDeProcessos.setProcessoEmExecucao(null);
		
		try {
			String tipo = ScheduleTimer.scheduleType();

			switch (tipo) {
				case "Line Scheduler":
					Escalonador.escalonarPorFIFO();

					break;
				case "Priority Scheduler":
					Escalonador.escalonarPorPrioridadeFixa();

					break;
				case "Lottery Scheduler":
					Escalonador.escalonarPorLoteria();

					break;
				default:
					Escalonador.escalonarPorFIFO();
			}

		} catch (Exception e) {
			e.printStackTrace();

			if (TabelaDeProcessos.getProcessoEmExecucao().getPrioridade() != 0) {
				Escalonador.escalonarPorPrioridadeFixa();
			} else {
				Escalonador.escalonarPorFIFO();
			}
		}
	}

}
