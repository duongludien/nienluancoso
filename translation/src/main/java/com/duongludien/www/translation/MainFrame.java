package com.duongludien.www.translation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea textAreaSource, textAreaTarget;
	JComboBox<LanguageItem> comboBoxSourceLang;
	private JComboBox<LanguageItem> comboBoxTargetLang;
	private JButton buttonDetect, buttonTranslate, buttonExit;

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
				// Get source text, source language and target language
				String sourceText = textAreaSource.getText();
				LanguageItem sourceLang = (LanguageItem) comboBoxSourceLang.getSelectedItem();
				LanguageItem targetLang = (LanguageItem) comboBoxTargetLang.getSelectedItem();
				String sourceLangCode = sourceLang.getCode();
				String targetLangCode = targetLang.getCode();
				
				// If source language is not set, sourceLangCode is null
				if(sourceLangCode.equals("detect"))
					sourceLangCode = null;
				
				// Translate
				String result = TranslateText.translateText(sourceText, sourceLangCode, targetLangCode);
				
				// Show translated text
				textAreaTarget.setText(result);
			}
		});
		
		buttonDetect.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String sourceText = textAreaSource.getText();
				String message = DetectLanguages.detectLanguages(sourceText);
				JOptionPane.showMessageDialog(null, message);
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
		
		ArrayList<LanguageItem> languages = SupportedLanguages.getSupportedLanguages();
		
		JLabel labelSource = new JLabel("Source Language:");
		panelSourceLanguage.add(labelSource);
		comboBoxSourceLang = new JComboBox<LanguageItem>();
		comboBoxSourceLang.addItem(new LanguageItem("detect", "Detect"));
		for(LanguageItem item : languages) {
			comboBoxSourceLang.addItem(item);
		}
		panelSourceLanguage.add(comboBoxSourceLang);
		
		JLabel labelDest = new JLabel("Target Language:");
		panelDestLanguage.add(labelDest);
		comboBoxTargetLang = new JComboBox<LanguageItem>();
		for(LanguageItem item : languages) {
			comboBoxTargetLang.addItem(item);
		}
		panelDestLanguage.add(comboBoxTargetLang);
		
		textAreaSource = new JTextArea();
		textAreaSource.setLineWrap(true);
		textAreaSource.setWrapStyleWord(true);
		textAreaSource.setFont(new Font("sans-serif", Font.PLAIN, 14));
		JScrollPane scrollPaneSourceText = new JScrollPane(textAreaSource);
		panelSourceText.add(scrollPaneSourceText, BorderLayout.CENTER);
		
		textAreaTarget = new JTextArea();
		textAreaTarget.setEditable(false);
		textAreaTarget.setLineWrap(true);
		textAreaTarget.setWrapStyleWord(true);
		textAreaTarget.setFont(new Font("sans-serif", Font.PLAIN, 14));
		JScrollPane scrollPaneDestText = new JScrollPane(textAreaTarget);
		panelDestText.add(scrollPaneDestText, BorderLayout.CENTER);
		
		buttonDetect = new JButton("Detect Languages");
		buttonTranslate = new JButton("Translate");
		buttonExit = new JButton("Exit");
		panelButton.add(buttonDetect);
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
