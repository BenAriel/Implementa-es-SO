package mars.mips.SO.ProcessManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class TabelaDeProcessos {
    private static Map<Integer, Queue<PCB>> processosPorPrioridade;
    private static Queue<PCB> processosProntos;
    private static PCB processoEmExecucao;
    private static final int MAX_PRIORIDADE = 9;

    // Bloco estático para inicializar as estruturas de dados estáticas
    static {
        processosPorPrioridade = new HashMap<>();
        processosProntos = new LinkedList<>();
        processoEmExecucao = null;
        
        // Inicializa as filas de prioridade
        for (int prioridade = 0; prioridade <= MAX_PRIORIDADE; prioridade++) {
            processosPorPrioridade.put(prioridade, new LinkedList<>());
        }
    }

    public static void adicionarProcessoProntoPrioridade(PCB processo) {
        int prioridade = processo.getPrioridade();
        Queue<PCB> fila = processosPorPrioridade.get(prioridade);
        fila.add(processo);
    }

    public static PCB observarProximoProcessoProntoPorPrioridade() {
        for (int prioridade = MAX_PRIORIDADE; prioridade >= 0; prioridade--) {
            Queue<PCB> fila = processosPorPrioridade.get(prioridade);
            if (!fila.isEmpty()) {
                return fila.peek();
            }
        }
        return null;
    }

    public static PCB obterProximoProcessoProntoPorPrioridade() {
        for (int prioridade = MAX_PRIORIDADE; prioridade >= 0; prioridade--) {
            Queue<PCB> fila = processosPorPrioridade.get(prioridade);
            if (!fila.isEmpty()) {
                return fila.poll();
            }
        }
        return null;
    }

    public static PCB obterProximoProcessoPronto() { // utilizar para escalonamento FIFO
        System.out.println(processosProntos.isEmpty());
        if (!processosProntos.isEmpty()) {
            return processosProntos.poll();
        } else {
            return null;  // Retorna null se não houver processos prontos
        }
    }
    public static void adicionarProcessoPronto(PCB processo) {
        processosProntos.add(processo);
    }

    public static PCB obterProximoProcessoPorLoteria() {
        if (!processosProntos.isEmpty()) {
            List<PCB> listaProntos = new ArrayList<>(processosProntos);
            //cria uma lista com os processos prontos
            int indiceAleatorio = new Random().nextInt(listaProntos.size());//sorteia um indice aleatório do tamanho da lista;
            return listaProntos.get(indiceAleatorio);
        } else {
            return null;
        }
    }

    public static PCB getProcessoEmExecucao() {
        return processoEmExecucao;
    }

    public static void setProcessoEmExecucao(PCB processoEmExecucao) {
        TabelaDeProcessos.processoEmExecucao = processoEmExecucao;
    }

    public static Queue<PCB> getProcessosProntos() {
        return processosProntos;
    }
    
    public static Map<Integer, Queue<PCB>> getProcessosPorPrioridade() {
    	return processosPorPrioridade;
    }
    
    public static void reset() {
    	processosPorPrioridade = new HashMap<>();
    	processosProntos = new LinkedList<>();
    	processoEmExecucao = null;
    }
}


