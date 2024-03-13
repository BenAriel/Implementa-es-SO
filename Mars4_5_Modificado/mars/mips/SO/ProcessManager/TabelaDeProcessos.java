package mars.mips.SO.ProcessManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class TabelaDeProcessos {
    private static Map<Integer, Queue<PCB>> processosPorPrioridade = new HashMap<>();//fila de prioridades
    private static Queue<PCB> processosProntos = new LinkedList<>();
    private static PCB processoEmExecucao = null;
    private static final int MAX_PRIORIDADE = 9;

    public static void adicionarProcessoProntoPrioridade(PCB processo) {
        int prioridade = processo.getPrioridade(); //pega a prioridade do processo
        Queue<PCB> fila = processosPorPrioridade.getOrDefault(prioridade, new LinkedList<>());
        fila.add(processo);//se uma fila com aquela fila não existir, cria uma nova fila. Se existir, recupera a fila adiciona o processo na fila
        processosPorPrioridade.put(prioridade, fila); //adiciona a fila na tabela de processos por prioridade
    }

    public static PCB obterProximoProcessoProntoPorPrioridade() {
        for (int prioridade = MAX_PRIORIDADE; prioridade >= 0; prioridade--) {//loop da maior prioridade para a menor
            Queue<PCB> fila = processosPorPrioridade.getOrDefault(prioridade, new LinkedList<>());// Se a fila daquela prioridade não existir, cria uma nova fila. Se existir, recupera a fila.
            if (!fila.isEmpty()) { //verifica se a fila não está vazia(Se tiver sido acabada de ser criada, estará vazia)
                return fila.poll();
            }
        }
        return null;
    }

    public static PCB obterProximoProcessoPronto() { // utilizar para escalonamento FIFO
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
    
    
}


