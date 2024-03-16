package mars.mips.SO.ProcessManager;

import mars.mips.hardware.RegisterFile;

public abstract class Escalonador {
	
    public static void escalonarPorPrioridadeFixa() {
        PCB ultimoProcessoEx = TabelaDeProcessos.getProcessoEmExecucao();
        PCB proximoProcessoEx = TabelaDeProcessos.obterProximoProcessoProntoPorPrioridade();
        
		if (ultimoProcessoEx != null) {
			ultimoProcessoEx.copyFromRegisters();
			ultimoProcessoEx.setProgramLabel(RegisterFile.getProgramCounter());
		}
        
        executarProximoProcesso(proximoProcessoEx);

        if (ultimoProcessoEx != null)
        {
            TabelaDeProcessos.adicionarProcessoProntoPrioridade(proximoProcessoEx);
        }
    }

    public static void escalonarPorLoteria() {
        PCB ultimoProcessoEx = TabelaDeProcessos.getProcessoEmExecucao();
        PCB proximoProcessoEx = TabelaDeProcessos.obterProximoProcessoPorLoteria();
        
		if (ultimoProcessoEx != null) {
			ultimoProcessoEx.copyFromRegisters();
			ultimoProcessoEx.setProgramLabel(RegisterFile.getProgramCounter());
		}
        
        executarProximoProcesso(proximoProcessoEx);
            
            if (ultimoProcessoEx != null) {
            	TabelaDeProcessos.adicionarProcessoPronto(ultimoProcessoEx);
            }
        }
    
    public static void escalonarPorFIFO() {
        PCB ultimoProcessoEx = TabelaDeProcessos.getProcessoEmExecucao();
        PCB proximoProcessoEx = TabelaDeProcessos.obterProximoProcessoPronto();
        
		if (ultimoProcessoEx != null) {
			ultimoProcessoEx.copyFromRegisters();
			ultimoProcessoEx.setProgramLabel(RegisterFile.getProgramCounter());
		}
        
        executarProximoProcesso(proximoProcessoEx);
        
        if (ultimoProcessoEx != null) {
        	TabelaDeProcessos.adicionarProcessoPronto(ultimoProcessoEx);
        }
    }

    private static void executarProximoProcesso(PCB proximoProcessoEx) {
        if (proximoProcessoEx != null) {
            TabelaDeProcessos.setProcessoEmExecucao(proximoProcessoEx);
            proximoProcessoEx.copyToRegisters();
            RegisterFile.setProgramCounter(TabelaDeProcessos.getProcessoEmExecucao().getProgramLabel());
        }
    }


    // Retorna true se houver processos prontos na fila da TabelaDeProcessos
    public static boolean hasProcessosProntos() {
        return TabelaDeProcessos.getProcessosProntos().size() > 0;
    }
}

