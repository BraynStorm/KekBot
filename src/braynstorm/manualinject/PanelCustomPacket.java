package braynstorm.manualinject;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import braynstorm.kekbot.net.packets.Packet;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelCustomPacket extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HexTextField boxOpCode;
	private HexTextField boxData;
	private JButton btnInject;

	/**
	 * Create the panel.
	 */
	public PanelCustomPacket() {
		setLayout(null);
		
		boxOpCode = new HexTextField();
		boxOpCode.setHorizontalAlignment(SwingConstants.CENTER);
		boxOpCode.setBounds(10, 11, 86, 20);
		add(boxOpCode);
		boxOpCode.setColumns(2);
		
		boxData = new HexTextField();
		boxData.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					btnInject.doClick();
				}
			}
		});
		boxData.setHorizontalAlignment(SwingConstants.CENTER);
		boxData.setColumns(2);
		boxData.setBounds(10, 42, 254, 66);
		add(boxData);
		
		btnInject = new JButton("Inject");
		btnInject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ManualInject.getInstance().proxy.sendPacket(new Packet(Integer.parseInt(boxOpCode.getText().replace(" ", ""), 16), boxData.getBytes(), false));
			}
		});
		btnInject.setBounds(101, 11, 163, 20);
		add(btnInject);

	}
	public HexTextField getBoxOpCode() {
		return boxOpCode;
	}
	public HexTextField getBoxData() {
		return boxData;
	}
	public JButton getBtnInject() {
		return btnInject;
	}
}
