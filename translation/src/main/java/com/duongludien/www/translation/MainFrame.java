package com.duongludien.www.translation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea textAreaSource, textAreaDest;
	private JComboBox<String> comboBoxSourceLang, comboBoxDestLang;
	private JButton buttonTranslate, buttonExit;

	public MainFrame(String title) {
		super(title);
		addControls();
		addEvents();
	}

	private void addEvents() {
		buttonExit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		buttonTranslate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void addControls() {
		Container container = getContentPane();
		
		JPanel panelMain = new JPanel();
		panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
		
		JPanel panelTop = new JPanel();
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));
		
		JPanel panelLeft = new JPanel();
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
		
		JPanel panelSourceLanguage = new JPanel(new FlowLayout());
		
		JPanel panelSourceText = new JPanel(new BorderLayout());
		panelSourceText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel panelRight = new JPanel();
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		
		JPanel panelDestLanguage = new JPanel(new FlowLayout());
		
		JPanel panelDestText = new JPanel(new BorderLayout());
		panelDestText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel panelButton = new JPanel(new FlowLayout());
		
		JLabel labelSource = new JLabel("Source Language:");
		panelSourceLanguage.add(labelSource);		
		comboBoxSourceLang = new JComboBox<String>();
		panelSourceLanguage.add(comboBoxSourceLang);
		
		JLabel labelDest = new JLabel("Destination Language:");
		panelDestLanguage.add(labelDest);
		comboBoxDestLang = new JComboBox<String>();
		panelDestLanguage.add(comboBoxDestLang);
		
		textAreaSource = new JTextArea();
		textAreaSource.setLineWrap(true);
		textAreaSource.setWrapStyleWord(true);
		textAreaSource.setFont(new Font("sans-serif", Font.PLAIN, 14));
		JScrollPane scrollPaneSourceText = new JScrollPane(textAreaSource);
		panelSourceText.add(scrollPaneSourceText, BorderLayout.CENTER);
		
		textAreaDest = new JTextArea();
		textAreaDest.setEditable(false);
		textAreaDest.setLineWrap(true);
		textAreaDest.setWrapStyleWord(true);
		textAreaDest.setFont(new Font("sans-serif", Font.PLAIN, 14));
		JScrollPane scrollPaneDestText = new JScrollPane(textAreaDest);
		panelDestText.add(scrollPaneDestText, BorderLayout.CENTER);
		
		buttonTranslate = new JButton("Translate");
		buttonExit = new JButton("Exit");
		panelButton.add(buttonTranslate);
		panelButton.add(buttonExit);
		
		panelLeft.add(panelSourceLanguage);
		panelLeft.add(panelSourceText);
		
		panelRight.add(panelDestLanguage);
		panelRight.add(panelDestText);
		
		panelTop.add(panelLeft);
		panelTop.add(panelRight);
		
		panelMain.add(panelTop);
		panelMain.add(panelButton);
		container.add(panelMain);
	}
	
	public void showWindow() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
