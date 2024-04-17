package mars.tools;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mars.mips.SO.ProcessManager.GerenciadorDeMemoria;
import mars.mips.SO.ProcessManager.TabelaDeProcessos;
import mars.mips.hardware.AccessNotice;
import mars.mips.hardware.Memory;

import java.awt.GridBagLayout;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class MemoryManager extends AbstractMarsToolAndApplication{
	private static String name   = "Memory Manager";
	private static String version = "Version 1.0 (Artur S. Guedes, Ben Ariel França Martins, Caio Anderson Martins Moura, João Pedro Pereira Frutoso, Lucas Gabriel Oliveira da Silva, Ricardo Viana Marinho)";
	private static String heading = "Makes the Memory Manager class work";

    private JTextField counterField;

    protected MemoryManager(String title, String heading) {
        super(title, heading);
    }

    public MemoryManager() {
		super(name + ", " + version, heading);
	}

    @Override
    public String getName() {
        return name;
    }

    @Override
    protected JComponent buildMainDisplayArea() {
        JPanel panel = new JPanel(new GridBagLayout());

        counterField = new JTextField("0", 5);
		counterField.setEditable(false);

		panel.add(counterField);

        return panel;
    }
    
    @Override
	protected void addAsObserver() {
		addAsObserver(Memory.textBaseAddress, Memory.textLimitAddress);
	}
	
	@Override
	protected void processMIPSUpdate(Observable resource, AccessNotice notice) {
		if (!notice.accessIsFromMIPS()) return;
		if (notice.getAccessType() != AccessNotice.READ) return;
		if (TabelaDeProcessos.getProcessoEmExecucao() != null) {
			GerenciadorDeMemoria.ensurePage();
		}
		updateDisplay();
	}

    public void updateDisplay() {
        counterField.setText(String.valueOf(GerenciadorDeMemoria.getMapa().size()));
    }
}
