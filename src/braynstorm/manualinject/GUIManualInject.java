package braynstorm.manualinject;

import javax.swing.JPanel;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JFormattedTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUIManualInject extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	private JScrollPane packetEditorArea;

	/**
	 * Create the panel.
	 */
	
	private boolean hexToDec = true;
	private HexTextField boxHex;
	private JFormattedTextField boxDec;
	private JComboBox<String> cmbboxPacketType;
	
	public GUIManualInject() {
		super();
		setLayout(null);
		//this.setBounds(0, 0, 300, 512);
		
		cmbboxPacketType = new JComboBox<String>();
		cmbboxPacketType.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				JPanel jp = ManualInject.packets.get(cmbboxPacketType.getSelectedItem());
				packetEditorArea.setViewportView(jp);
				packetEditorArea.repaint();
			}
		});
		cmbboxPacketType.setBounds(10, 11, 280, 20);
		add(cmbboxPacketType);
		
		boxHex = new HexTextField();
		boxHex.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(hexToDec){
					//boxDec.setText(Integer.parseInt(boxHex.getText().replace(" ", "") ,16));
				}
			}
		});
		boxHex.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				hexToDec = true;
			}
		});
		boxHex.setBounds(10, 484, 96, 20);
		add(boxHex);
		
		JLabel lblHexToDec = new JLabel("HEX <> DEC");
		lblHexToDec.setHorizontalAlignment(SwingConstants.CENTER);
		lblHexToDec.setBounds(112, 487, 72, 14);
		add(lblHexToDec);
		
		packetEditorArea = new JScrollPane();
		packetEditorArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		packetEditorArea.setBounds(0,42,300,300);
		add(packetEditorArea);
		
		boxDec = new JFormattedTextField();
		boxDec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!hexToDec){
					boxHex.setText(Integer.toHexString(Integer.parseInt(boxDec.getText())));
				}
			}
		});
		boxDec.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				hexToDec = false;
			}
		});
		boxDec.setHorizontalAlignment(SwingConstants.CENTER);
		boxDec.setBounds(194, 484, 96, 20);
		add(boxDec);

	}
	public JScrollPane getPacketEditorArea() {
		return packetEditorArea;
	}
	public HexTextField getBoxHex() {
		return boxHex;
	}
	public JFormattedTextField getBoxDec() {
		return boxDec;
	}
	public JComboBox<String> getCmbboxPacketType() {
		return cmbboxPacketType;
	}
}
