package braynstorm.kekbot.navigator;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class GUINavigator extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField boxXPos;
	private JTextField boxYPos;
	private JTextField boxRadius;
	private JTextField boxCurX;
	private JTextField boxCurY;

	/**
	 * Create the panel.
	 */
	public GUINavigator() {
		setLayout(null);
		
		JLabel lblKekbotNavigator = new JLabel("KeKBot Navigator");
		lblKekbotNavigator.setBounds(10, 11, 92, 14);
		add(lblKekbotNavigator);
		
		boxXPos = new JTextField();
		boxXPos.setHorizontalAlignment(SwingConstants.CENTER);
		boxXPos.setText("0");
		boxXPos.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				HuntingArea.setX(Integer.parseInt(boxXPos.getText()));
			}
		});
		boxXPos.setBounds(54, 59, 119, 20);
		add(boxXPos);
		boxXPos.setColumns(10);
		
		JLabel lblX = new JLabel("X:");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(12, 62, 43, 14);
		add(lblX);
		
		boxYPos = new JTextField();
		boxYPos.setHorizontalAlignment(SwingConstants.CENTER);
		boxYPos.setText("0");
		boxYPos.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				HuntingArea.setY(Integer.parseInt(boxYPos.getText()));
			}
		});
		boxYPos.setColumns(10);
		boxYPos.setBounds(54, 81, 119, 20);
		add(boxYPos);
		
		JLabel lblY = new JLabel("Y:");
		lblY.setHorizontalAlignment(SwingConstants.CENTER);
		lblY.setBounds(12, 84, 43, 14);
		add(lblY);
		
		boxRadius = new JTextField();
		boxRadius.setHorizontalAlignment(SwingConstants.CENTER);
		boxRadius.setText("20");
		boxRadius.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				HuntingArea.setRadius(Integer.parseInt(boxRadius.getText()));
			}
		});
		boxRadius.setColumns(10);
		boxRadius.setBounds(54, 104, 119, 20);
		add(boxRadius);
		
		JLabel lblRadius = new JLabel("Radius:");
		lblRadius.setHorizontalAlignment(SwingConstants.CENTER);
		lblRadius.setBounds(12, 107, 43, 14);
		add(lblRadius);
		
		JButton btnGetFromCurrent = new JButton("Get From Current Posiotion");
		btnGetFromCurrent.setBounds(10, 36, 164, 23);
		add(btnGetFromCurrent);
		
		JButton btnNavigate = new JButton("Navigate");
		btnNavigate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HuntingArea.navigate();
			}
		});
		btnNavigate.setBounds(184, 36, 110, 88);
		add(btnNavigate);
		
		boxCurX = new JTextField();
		boxCurX.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				Navigator.curX = Integer.parseInt(boxCurX.getText());
			}
		});
		boxCurX.setText("0");
		boxCurX.setHorizontalAlignment(SwingConstants.CENTER);
		boxCurX.setColumns(10);
		boxCurX.setBounds(76, 281, 119, 20);
		add(boxCurX);
		
		boxCurY = new JTextField();
		boxCurY.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				Navigator.curY = Integer.parseInt(boxCurY.getText());
			}
		});
		boxCurY.setText("0");
		boxCurY.setHorizontalAlignment(SwingConstants.CENTER);
		boxCurY.setColumns(10);
		boxCurY.setBounds(76, 303, 119, 20);
		add(boxCurY);
		
		JLabel label = new JLabel("Y:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(34, 306, 43, 14);
		add(label);
		
		JLabel label_1 = new JLabel("X:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(34, 284, 43, 14);
		add(label_1);

	}
	public JTextField getBoxCurX() {
		return boxCurX;
	}
	public JTextField getBoxCurY() {
		return boxCurY;
	}
}
