package mars.mips.SO.ProcessManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mars.mips.hardware.RegisterFile;
import mars.util.SystemIO;

public abstract class GerenciadorDeMemoria {
    private static int tamanhoPg = 3;
    private static int quantMax = 15;
    private static Map <Integer, List<Pagina>> mapa = new HashMap<>();
    private static String algoritmo;

    public static void ensurePage() {
        PCB processoAtual = TabelaDeProcessos.getProcessoEmExecucao();
        int pc = RegisterFile.getProgramCounter();
        boolean loaded = false;

        if(processoAtual.adressinRange(pc)) {
            for (Pagina e : mapa.get(processoAtual.getPid())) {
                for (int i = 0; i < tamanhoPg || loaded; i++) {
                    if (e.getAdress()[i] == RegisterFile.getProgramCounter()) {
                        loaded = true;
                    }
                }
            }

            if (!loaded) {
                pageFault();
            }
        } else {
            SystemIO.printString("Erro encontrado, acesso de página fora dos limites do processo" +
                                "\nLimites de endereço do processo: [" + processoAtual.getUpperLim() + ", " + processoAtual.getLowerLim() + "]" +
                                "\nEndereço acessado: " + pc);
        }
    }

    public static Pagina pageFault() {
        PCB processoAtual = TabelaDeProcessos.getProcessoEmExecucao();
        int pc = RegisterFile.getProgramCounter();
        Pagina pg = new Pagina(tamanhoPg);

        for (int i = 0; i < tamanhoPg; i++) {
            if (processoAtual.adressinRange(pc + (i * 4))) {
                pg.add(pc + (i * 4));
            } else {
                pg.add(-1);
            }
        }
        
        if (!mapa.containsKey(processoAtual.getPid())) {
            mapa.put(processoAtual.getPid(), new ArrayList<>());
            mapa.get(processoAtual.getPid()).add(pg);
        } else {
            mapa.get(processoAtual.getPid()).add(pg);
        }

        return pg;
    }
} 
