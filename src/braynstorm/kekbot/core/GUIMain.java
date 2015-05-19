package braynstorm.kekbot.core;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Dimension;
import java.awt.Component;

import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import braynstorm.kekbot.lib.IKekBotPlugin;

import javax.swing.ListSelectionModel;
import javax.swing.JPanel;

public class GUIMain {

	JFrame frmKekbot;
	private JLabel lblKexyStatus;
	private JList<String> listMenus;

	public GUIMain() {
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}
	
	private void initialize() {
		frmKekbot = new JFrame();
		frmKekbot.setTitle("KekBot");
		frmKekbot.setBounds(100, 100, 870, 512);
		frmKekbot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("Menus");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setMinimumSize(new Dimension(100, 14));
		label.setMaximumSize(new Dimension(100, 14));
		label.setPreferredSize(new Dimension(100, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new EmptyBorder(1, 1, 1, 1));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JScrollPane curMenuScrollPane = new JScrollPane();
		curMenuScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		curMenuScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel lblKexyStatusLabel = new JLabel();
		lblKexyStatusLabel.setText("Kexy status");
		
		lblKexyStatus = new JLabel();
		
		JPanel panel = new JPanel();
		
		
		GroupLayout groupLayout = new GroupLayout(frmKekbot.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblKexyStatusLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblKexyStatus, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(curMenuScrollPane, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addGap(11)))
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(4)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(curMenuScrollPane, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblKexyStatusLabel)
								.addComponent(lblKexyStatus, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
							.addGap(10))))
		);
		panel.setLayout(null);
		
		JLabel lblStatistics = new JLabel("Statistics");
		lblStatistics.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatistics.setBounds(10, 11, 118, 14);
		panel.add(lblStatistics);
		
		listMenus = new JList<String>();
		listMenus.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMenus.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(listMenus.isSelectionEmpty() || listMenus.getSelectedIndex() < 0 || arg0.getValueIsAdjusting())
					return;
				
				IKekBotPlugin plugin = Main.getPluginByName(listMenus.getSelectedValue());
				if(plugin == null)
					return;
				curMenuScrollPane.setViewportView(plugin.getJPanel());
				curMenuScrollPane.repaint();
			}
		});
		listMenus.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"General Config", "Navigator", "Skill Setup", "Manual Inject"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(listMenus);
		frmKekbot.getContentPane().setLayout(groupLayout);
	}
	public JLabel getKexyStatusLabel() {
		return lblKexyStatus;
	}
	public JList<String> getListMenus() {
		return listMenus;
	}
}
