package mars.mips.SO.ProcessManager;

public class Pagina {
    private int tamanhoPg;
    private int[] enderecos;
    private int i = 0;

    public Pagina(int tamanhoPg, int[] enderecos) {
        this.tamanhoPg = tamanhoPg;
        this.enderecos = enderecos;
    }

    public Pagina(int tamanhoPg) {
        this.tamanhoPg = tamanhoPg;
        enderecos = new int[this.tamanhoPg];
    }

    public int[] getAdress() {
        return enderecos;
    }

    public boolean add(int endereco) {
        if (!(i < tamanhoPg)) {
            return false;
        } else {
            enderecos[i] = endereco;
            i++;
        }

        return true;
    }
}
