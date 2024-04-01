package mars.mips.SO.ProcessManager;

import mars.mips.hardware.RegisterFile;

public class PCB {
    private int[] registers = new int[RegisterFile.NUM_REGISTERS];
    private int programLabel;
    private int pid;
    private String state = "pronto";
    private int prioridade;

    public PCB(int programLabel, int pid, String state, int prioridade) {
        // inicialize outras propriedades
        this.programLabel = programLabel;
        this.pid = pid;
        this.state = state;
        this.prioridade = prioridade;
    }
    public PCB(){};

    // Métodos para copiar o conteúdo dos registradores físicos do hardware para a PCB
    public void copyFromRegisters() {
        for (int i = 0; i < RegisterFile.NUM_REGISTERS; i++) {
        	if (i != 32) {
                this.registers[i] = RegisterFile.getValue(i);
        	} 
        	else {
                this.registers[32] = RegisterFile.getProgramCounter();
        	}
        }
    }

    // Método para copiar da PCB para os registradores físicos do hardware
    public void copyToRegisters() {
        for (int i = 0; i < RegisterFile.NUM_REGISTERS; i++) {
            RegisterFile.updateRegister(i, this.registers[i]);
        }
    }
    
    // Funções para manipular o estado atual
    public void blockState() {
    	this.state = "bloqueado";
    }
    public boolean isBlocked() {
    	return this.state == "bloqueado";
    }
    
    public void readyState() {
    	this.state = "pronto";
    }
    public boolean isReady() {
    	return this.state == "pronto";
    }
    
    public void runningState() {
    	this.state = "executando";
    }
    public boolean isRunning() {
    	return this.state == "executando";
    }
    
    // Métodos getters e setters para informações lógicas
    public int getProgramLabel() {
        return programLabel;
    }
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    public int getPrioridade() {
        return prioridade;
    }

    public void setProgramLabel(int programLabel) {
        this.programLabel = programLabel;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int[] getRegisters() {
        return registers;
    }

    public void setRegisters(int[] registers) {
        this.registers = registers;
    }
}
