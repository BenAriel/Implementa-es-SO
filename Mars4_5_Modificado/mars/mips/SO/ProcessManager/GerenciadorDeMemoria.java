package mars.mips.SO.ProcessManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mars.mips.hardware.RegisterFile;
import mars.simulator.Simulator;
import mars.util.SystemIO;

public abstract class GerenciadorDeMemoria {
    private static int tamanhoPg = 3;
    private static int quantMax = 15;
    private static Map<Integer, Integer> quantMap = new HashMap<>();
    private static Map<Integer, List<Pagina>> mapa = new HashMap<>();
    private static String algoritmo = "Fifo";

    public static void ensurePage() {
        PCB processoAtual = TabelaDeProcessos.getProcessoEmExecucao();
        int pc = RegisterFile.getProgramCounter();
        boolean loaded = false;

        Pagina e;
        try {
            if(processoAtual.adressinRange(pc)) {
                for (int j = 0; j < mapa.get(processoAtual.getPid()).size(); j++) {
                    e = mapa.get(processoAtual.getPid()).get(j);
                    for (int i = 0; (i < tamanhoPg) && !loaded; i++) {
                        if (e.getAdress()[i] == pc) {
                            loaded = true;
                        }
                        System.out.println("comparacao: " + e.getAdress()[i] + " == " + pc);
                    }
                }

                if (!loaded) {
                    System.out.println("Page Fault loaded = false");
                    pageFault();
                }
            } else {
                SystemIO.printString("Erro encontrado, acesso de página fora dos limites do processo" +
                                    "\nLimites de endereço do processo: [" + processoAtual.getUpperLim() + ", " + processoAtual.getLowerLim() + "]" +
                                    "\nEndereço acessado: " + pc);
                                    
                Simulator.getInstance().stopExecution(null);
            }
        } catch (NullPointerException npe) {
            pageFault();
        }
        System.out.println("___________Fim ensurePage___________");
    }

    public static Pagina pageFault() {
        PCB processoAtual = TabelaDeProcessos.getProcessoEmExecucao();
        int pc = RegisterFile.getProgramCounter();
        Pagina pg = new Pagina(tamanhoPg);

        for (int i = 0; i < tamanhoPg; i++) {
            if (processoAtual.adressinRange(pc + (i * 4))) {
                System.out.println(pc + (i * 4));
                pg.add(pc + (i * 4));
            } else {
                pg.add(-1);
            }
        }

        int id = processoAtual.getPid();

        if (!mapa.containsKey(id)) {
            System.out.println("inserido");
            mapa.put(id, new ArrayList<>());
            quantMap.put(id, 1);
            mapa.get(id).add(pg);

        } else {
            System.out.println("segunda pg");
            paginacao(pg, processoAtual);
        }

        quantMap.replace(id, quantMap.get(id) % quantMax);
        return pg;
    }

    public static void paginacao(Pagina pg, PCB processoAtual) {
        int id = processoAtual.getPid();
        quantMap.replace(id, quantMap.get(id) + 1);

        switch (algoritmo) {
            case "Fifo":

                if (mapa.get(id).size() == quantMap.get(id) - 1) {
                    mapa.get(id).add(pg);
                } else {
                    mapa.get(id).set(quantMap.get(id) - 1, pg);
                }

                break;
        
            default:
                SystemIO.printString("Algoritmo de paginação não reconhecido, por favor altere-o");

                break;
        }
    }

    // Getters e Setters
    public static int getTamanhoPg() {
        return tamanhoPg;
    }

    public static void setTamanhoPg(int tamanhoPg) {
        GerenciadorDeMemoria.tamanhoPg = tamanhoPg;
    }

    public static int getQuantMax() {
        return quantMax;
    }

    public static void setQuantMax(int quantMax) {
        GerenciadorDeMemoria.quantMax = quantMax;
    }

    public static Map<Integer, Integer> getQuantMap() {
        return quantMap;
    }

    public static void setQuantMap(Map<Integer, Integer> quantMap) {
        GerenciadorDeMemoria.quantMap = quantMap;
    }

    public static Map<Integer, List<Pagina>> getMapa() {
        return mapa;
    }

    public static void setMapa(Map<Integer, List<Pagina>> mapa) {
        GerenciadorDeMemoria.mapa = mapa;
    }

    public static String getAlgoritmo() {
        return algoritmo;
    }

    public static void setAlgoritmo(String algoritmo) {
        GerenciadorDeMemoria.algoritmo = algoritmo;
    }    
} 
