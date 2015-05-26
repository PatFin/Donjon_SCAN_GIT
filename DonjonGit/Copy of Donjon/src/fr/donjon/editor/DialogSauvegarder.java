/**
 * 
 */
package fr.donjon.editor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author Baptiste
 *
 */
public class DialogSauvegarder extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Box content;

	JButton jbCancel;
	JButton jbSauvegarder;

	JLabel jlIndex;
	JLabel jlName;

	DialogListener listener;
	JTextField tfIndex;

	JTextField tfName;	

	/**
	 * 
	 */
	public DialogSauvegarder(JFrame parent, String title, boolean modal, DialogListener l){
		super(parent,title,modal);

		//Centrage
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		listener = l;

		//Composants

		jlIndex = new JLabel("Index de la carte: ");
		jlName = new JLabel("Nom de la carte: ");
		jlIndex.setPreferredSize(new Dimension(100, 10));
		jlName.setPreferredSize(new Dimension(100, 10));
		
		tfIndex = new JTextField("", 5);
		tfName = new JTextField("", 5);

		jbCancel = new JButton("Annuler");
		jbSauvegarder = new JButton("Sauvegarder");

		//Listeners

		jbCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelBox();
			}
		});
		jbSauvegarder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				validateBox();
			}
		});

		//Layout

		Box box1 = Box.createHorizontalBox();
		box1.add(Box.createHorizontalStrut(5));
		box1.add(jlName);
		box1.add(tfName);
		box1.add(Box.createHorizontalStrut(5));
		
		Box box2 = Box.createHorizontalBox();
		box2.add(Box.createHorizontalStrut(5));
		box2.add(jlIndex);
		box2.add(tfIndex);
		box2.add(Box.createHorizontalStrut(5));

		Box box3 = Box.createHorizontalBox();
		box3.add(Box.createHorizontalStrut(5));
		box3.add(jbCancel);
		box3.add(Box.createGlue());
		box3.add(jbSauvegarder);
		box3.add(Box.createHorizontalStrut(5));

		content = Box.createVerticalBox();
		content.add(Box.createVerticalStrut(5));
		content.add(box1);
		content.add(Box.createVerticalStrut(5));
		content.add(box2);
		content.add(Box.createVerticalStrut(5));
		content.add(box3);
		content.add(Box.createVerticalStrut(5));
		content.setBorder(BorderFactory.createTitledBorder("Entrez les données de la carte: "));

		//Finalisation

		this.setContentPane(content);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);

	}

	private void cancelBox(){
		listener.onCancel();
		this.setVisible(false);
	}

	private void validateBox(){

		String name = tfName.getText();
		int index = -1;
		
		try{
			index = Integer.parseInt(tfIndex.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "L'index doit etre un nombre entier !", "Erreur", JOptionPane.WARNING_MESSAGE);
			tfIndex.setText("0");
			return;
		}
		
		
		
		listener.onValidate(name,index);
		this.setVisible(false);
	}

}
