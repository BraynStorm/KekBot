package braynstorm.manualinject;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

import braynstorm.kekbot.net.packets.Packet;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelPacketMove extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelPacketMove() {
		
		
		setLayout(null);
		
		this.setBounds(0, 0, 256, 100);
		
		JLabel lblX = new JLabel("X: ");
		lblX.setBounds(10, 12, 22, 14);
		add(lblX);
		
		JLabel lblY = new JLabel("Y: ");
		lblY.setBounds(10, 37, 22, 14);
		add(lblY);
		
		JFormattedTextField boxMoveToX = new JFormattedTextField();
		boxMoveToX.setBounds(31, 11, 67, 20);
		add(boxMoveToX);
		
		JFormattedTextField boxMoveToY = new JFormattedTextField();
		boxMoveToY.setBounds(31, 34, 67, 20);
		add(boxMoveToY);
		
		JButton butGetCurX = new JButton("Get Current X");
		butGetCurX.setBounds(108, 8, 138, 23);
		add(butGetCurX);
		
		JButton butGetCurY = new JButton("Get Current Y");
		butGetCurY.setBounds(108, 33, 138, 23);
		add(butGetCurY);
		
		JButton butInject = new JButton("Go");
		butInject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ManualInject.getInstance().proxy.sendPacket(Packet.clientMovementPacket(Integer.parseInt(boxMoveToX.getText()), Integer.parseInt(boxMoveToY.getText())));
				
			}
		});
		butInject.setBounds(10, 62, 236, 23);
		add(butInject);

	}
}
