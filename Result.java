
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;

public class Result {

	protected Shell window;

	public void open() {
		Display display = Display.getDefault();
		createContents();
		window.open();
		window.layout();
		while (!window.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		window = new Shell();
		window.setSize(450, 300);
		if(MedicalTracker.resultType==1){
			window.setText(MedicalTracker.sName+": Results");
			window.setBackgroundImage(SWTResourceManager.getImage(Result.class, "/org/beats.jpeg"));
			
			Label lblresult = new Label(window, SWT.NONE);
			lblresult.setBackground(SWTResourceManager.getColor(0, 0, 0));
			lblresult.setForeground(SWTResourceManager.getColor(255, 255, 0));
			lblresult.setFont(SWTResourceManager.getFont("Cambria", 14, SWT.BOLD));
			lblresult.setBounds(175, 10, 325, 25);
			if(MedicalTracker.calcResult==1){
				lblresult.setText("NO HEART DISEASE");
			}
			if(MedicalTracker.calcResult==2){
				lblresult.setText("HAS HEART DISEASE");
			}
			if(MedicalTracker.calcResult==3){
				lblresult.setText("ON THE BORDER OF HEART DISEASE");
			}
			
			Label lblrisk = new Label(window, SWT.NONE);
			lblrisk.setBackground(SWTResourceManager.getColor(0, 0, 0));
			lblrisk.setForeground(SWTResourceManager.getColor(255, 255, 0));
			lblrisk.setFont(SWTResourceManager.getFont("Cambria", 14, SWT.BOLD));
			lblrisk.setBounds(200, 50, 250, 25);
			lblrisk.setText(MedicalTracker.yesPercent+"% HEART RISK");
			
			Button btnOk = new Button(window, SWT.NONE);
			btnOk.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			btnOk.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
			btnOk.setBounds(275, 90, 94, 28);
			btnOk.setText("OK");
			btnOk.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e){
					window.dispose();
				}
			});
			window.setDefaultButton(btnOk);
		}
		
		if(MedicalTracker.resultType==2){
			window.setText(MedicalTracker.sName+": Results and Risk Factors");
			window.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));;
			
		}
	}

}
