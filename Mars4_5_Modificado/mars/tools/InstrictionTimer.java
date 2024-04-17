package mars.tools;

import javax.swing.JComponent;

public class InstrictionTimer extends AbstractMarsToolAndApplication{
	
	private String title;

	private static String name = "InstrictionTimer";
	private static String heading = "heading";

	protected InstrictionTimer(String title, String heading) {
		super(title, heading);
		// TODO Auto-generated constructor stub
	}

	public InstrictionTimer() {
		super(name, heading);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JComponent buildMainDisplayArea() {
		// TODO Auto-generated method stub
		return null;
	}

}
