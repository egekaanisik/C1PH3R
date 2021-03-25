package dev.mrpanda.C1PH3R;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class Cipher {
	public static JFrame f = new JFrame("C1PH3R // Created by MrPandaDev");
	public static Image frameIcon = Toolkit.getDefaultToolkit().getImage(Cipher.class.getResource("resources/c1ph3r.png"));
	
	public static void menu() {
		JPanel p = new JPanel();
		p.setLayout(null);
		
		JLabel intro = new JLabel("C1PH3R", SwingConstants.CENTER);
		intro.setBounds(20,20,100,100);
		intro.setFont(new Font("Impact", Font.PLAIN, 32));
		intro.setForeground(Color.WHITE);
		
		JLabel creator = new JLabel("Created by MrPandaDev");
		creator.setBounds(10,120,150,15);
		creator.setFont(new Font("Arial", Font.PLAIN,10));
		creator.setForeground(Color.GRAY);
		creator.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://mrpanda.dev"));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton en = new JButton("Encrypt");
		en.setBounds(140,20,100,100);
		en.setBackground(Color.DARK_GRAY);
		en.setForeground(Color.WHITE);
		en.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
				    	encrypt();
				    }
				});
			}
		});
		
		JButton de = new JButton("Decrypt");
		de.setBounds(260,20,100,100);
		de.setBackground(Color.DARK_GRAY);
		de.setForeground(Color.WHITE);
		de.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
				    	decrypt();
				    }
				});
			}
		});
		
		p.add(intro);
		p.add(creator);
		p.add(en);
		p.add(de);
		p.setBackground(Color.BLACK);
		
		f.pack();
		f.setSize(395, 180);
		f.setIconImage(frameIcon);
		f.getContentPane().removeAll();
		f.getContentPane().add(p);
		f.revalidate();
		f.repaint();
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static String key() {
		Character[] key = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		
		List<Character> list = Arrays.asList(key);
		Collections.shuffle(list);
		list.toArray(key);
		
		String k = "";
		for(int i = 0; i < 26; i++) {
			k = k + key[i];
		}
		
		return k;
	}
	
	public static void encrypt() {
		JPanel p = new JPanel();
		p.setLayout(null);
	    
		Border border = BorderFactory.createLineBorder(Color.WHITE);
		
		JLabel keyAsk = new JLabel("Key:");
		keyAsk.setBounds(25,20,50,20);
		keyAsk.setForeground(Color.WHITE);
		
		JTextField key = new JTextField();
		key.setBounds(25,50,430,20);
		key.setHorizontalAlignment(JTextField.CENTER);
		key.setBackground(Color.LIGHT_GRAY);
		key.setForeground(Color.BLACK);
		key.setDocument(new JTextFieldLimit(26));
		
		JLabel plainAsk = new JLabel("Plaintext:");
		plainAsk.setBounds(25,90,80,20);
		plainAsk.setForeground(Color.WHITE);
		
		JTextArea plain = new JTextArea();
		plain.setBackground(Color.LIGHT_GRAY);
		plain.setForeground(Color.BLACK);
		plain.setLineWrap(true);
		plain.setWrapStyleWord(true);
		plain.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		
		JScrollPane plainScr = new JScrollPane(plain);
		plainScr.setBounds(25,120,430,200);
		plainScr.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.GRAY;
				this.trackColor = Color.DARK_GRAY;
			}
			
			@Override
			protected JButton createDecreaseButton(int orientation) {
				JButton button = super.createDecreaseButton(orientation);
				button.setBackground(Color.DARK_GRAY);
				return button;
			}
					
			@Override
			protected JButton createIncreaseButton(int orientation) {
				JButton button = super.createIncreaseButton(orientation);
				button.setBackground(Color.DARK_GRAY);
				return button;
			}
		});
		plainScr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JLabel c = new JLabel("Ciphertext:");
		c.setBounds(25,340,80,20);
		c.setForeground(Color.WHITE);

		JTextArea cipher = new JTextArea();
		cipher.setBackground(Color.LIGHT_GRAY);
		cipher.setForeground(Color.BLACK);
		cipher.setLineWrap(true);
		cipher.setEditable(false);
		cipher.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		
		JScrollPane cipherScr = new JScrollPane(cipher);
		cipherScr.setBounds(25,370,430,200);
		cipherScr.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.GRAY;
				this.trackColor = Color.DARK_GRAY;
			}
			
			@Override
			protected JButton createDecreaseButton(int orientation) {
				JButton button = super.createDecreaseButton(orientation);
				button.setBackground(Color.DARK_GRAY);
				return button;
			}
					
			@Override
			protected JButton createIncreaseButton(int orientation) {
				JButton button = super.createIncreaseButton(orientation);
				button.setBackground(Color.DARK_GRAY);
				return button;
			}
		});
		cipherScr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JLabel invalid = new JLabel();
		invalid.setBounds(25,590,200,40);
		invalid.setForeground(Color.RED);
		
		JButton keygen = new JButton("Generate");
		keygen.setBounds(265,20,90,20);
		keygen.setBackground(Color.DARK_GRAY);
		keygen.setForeground(Color.WHITE);
		keygen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				key.setText(key());
			}
		});
		
		JButton copyKey = new JButton("Copy");
		copyKey.setBounds(365,20,90,20);
		copyKey.setBackground(Color.DARK_GRAY);
		copyKey.setForeground(Color.WHITE);
		copyKey.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String keyStr = key.getText();
				
				if(keyStr.length() != 26 || !keyStr.matches("\\A\\p{ASCII}*\\z")) {
					return;
				}
				
				StringSelection stringSelection = new StringSelection(keyStr);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				
				invalid.setText("Copied Key!");
				invalid.setForeground(Color.GREEN);
				
				ActionListener listener = new ActionListener(){
			        public void actionPerformed(ActionEvent event){
			            invalid.setText("");
			            invalid.setForeground(Color.RED);
			        }
			    };
			    
				Timer timer = new Timer(1000, listener);
			    timer.setRepeats(false);
			    timer.start();
			}
		});
		
		JButton copyPlain = new JButton("Copy");
		copyPlain.setBounds(365,90,90,20);
		copyPlain.setBackground(Color.DARK_GRAY);
		copyPlain.setForeground(Color.WHITE);
		copyPlain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String plainStr = plain.getText();
				
				if(plainStr.length() == 0 || !plainStr.matches("\\A\\p{ASCII}*\\z")) {
					return;
				}
				
				StringSelection stringSelection = new StringSelection(plainStr);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				
				invalid.setText("Copied Plaintext!");
				invalid.setForeground(Color.GREEN);
				
				ActionListener listener = new ActionListener(){
			        public void actionPerformed(ActionEvent event){
			            invalid.setText("");
			            invalid.setForeground(Color.RED);
			        }
			    };
			    
				Timer timer = new Timer(1000, listener);
			    timer.setRepeats(false);
			    timer.start();
			}
		});
		
		JButton copyCipher = new JButton("Copy");
		copyCipher.setBounds(365,340,90,20);
		copyCipher.setBackground(Color.DARK_GRAY);
		copyCipher.setForeground(Color.WHITE);
		copyCipher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cipherStr = cipher.getText();
				
				if(cipherStr.length() == 0) {
					return;
				}
				
				StringSelection stringSelection = new StringSelection(cipherStr);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				
				invalid.setText("Copied Ciphertext!");
				invalid.setForeground(Color.GREEN);
				
				ActionListener listener = new ActionListener(){
			        public void actionPerformed(ActionEvent event){
			            invalid.setText("");
			            invalid.setForeground(Color.RED);
			        }
			    };
			    
				Timer timer = new Timer(1000, listener);
			    timer.setRepeats(false);
			    timer.start();
			}
		});
		
		JButton back = new JButton("Go Back");
		back.setBounds(265,590,90,40);
		back.setBackground(Color.DARK_GRAY);
		back.setForeground(Color.WHITE);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
				    	menu();
				    }
				});
			}
		});
		
		JButton enc = new JButton("Encrypt");
		enc.setBounds(365,590,90,40);
		enc.setBackground(Color.DARK_GRAY);
		enc.setForeground(Color.WHITE);
		enc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String keyS = key.getText();
				
				boolean v = true;
				for(int i = 0; i < keyS.length(); i++) {
					if(!Character.isLetter(keyS.charAt(i))) {
						v = false;
						break;
					}
					
					if(i == 0)
						continue;
					
					for(int j = 0; j < i; j++) {
						if(keyS.charAt(j) == keyS.charAt(i)) {
							v = false;
							break;
						}
					}
					
					if(!v) {
						break;
					}
				}
				
				if(!v || keyS.length() != 26 || !keyS.matches("\\A\\p{ASCII}*\\z")) {
					invalid.setText("Invalid key.");
					
					ActionListener listener = new ActionListener(){
				        public void actionPerformed(ActionEvent event){
				            invalid.setText("");
				        }
				    };
				    
					Timer timer = new Timer(1000, listener);
				    timer.setRepeats(false);
				    timer.start();
					return;
				} else {
					invalid.setText("");
				}
				
				String p = plain.getText();
				
				if(p.length() == 0) {
					invalid.setText("Invalid plaintext.");
					
					ActionListener listener = new ActionListener(){
				        public void actionPerformed(ActionEvent event){
				            invalid.setText("");
				        }
				    };
				    
					Timer timer = new Timer(1000, listener);
				    timer.setRepeats(false);
				    timer.start();
					return;
				}
				
				boolean result = p.matches("\\A\\p{ASCII}*\\z");
				
				if(!result) {
					invalid.setText("Invalid plaintext.");
					
					ActionListener listener = new ActionListener(){
				        public void actionPerformed(ActionEvent event){
				            invalid.setText("");
				        }
				    };
				    
					Timer timer = new Timer(1000, listener);
				    timer.setRepeats(false);
				    timer.start();
					return;
				} else {
					invalid.setText("");
				}
				
				cipher.setText(replace(p, keyS, getIndex(p, keyS, 'e'), 'e'));	
			}
		});
		
		p.add(keygen);
		p.add(copyKey);
		p.add(copyPlain);
		p.add(copyCipher);
		p.add(back);
		p.add(keyAsk);
		p.add(key);
		p.add(plainAsk);
		p.add(plainScr);
		p.add(c);
		p.add(cipherScr);
		p.add(invalid);
		p.add(enc);
		p.setBackground(Color.BLACK);

		f.pack();
		f.setSize(495, 690);
		f.setIconImage(frameIcon);
		f.getContentPane().removeAll();
		f.getContentPane().add(p);
		f.revalidate();
		f.repaint();
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public static void decrypt() {
		JPanel p = new JPanel();
		p.setLayout(null);
		
		Border border = BorderFactory.createLineBorder(Color.WHITE);
	    
		JLabel keyAsk = new JLabel("Key:");
		keyAsk.setBounds(25,20,50,20);
		keyAsk.setForeground(Color.WHITE);
		
		JTextField key = new JTextField();
		key.setBounds(25,50,430,20);
		key.setHorizontalAlignment(JTextField.CENTER);
		key.setBackground(Color.LIGHT_GRAY);
		key.setForeground(Color.BLACK);
		key.setDocument(new JTextFieldLimit(26));
		
		JLabel cipherAsk = new JLabel("Ciphertext:");
		cipherAsk.setBounds(25,90,80,20);
		cipherAsk.setForeground(Color.WHITE);
		
		JTextArea cipher = new JTextArea();
		cipher.setBackground(Color.LIGHT_GRAY);
		cipher.setForeground(Color.BLACK);
		cipher.setLineWrap(true);
		cipher.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		
		JScrollPane cipherScr = new JScrollPane(cipher);
		cipherScr.setBounds(25,120,430,200);
		cipherScr.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.GRAY;
				this.trackColor = Color.DARK_GRAY;
			}
			
			@Override
			protected JButton createDecreaseButton(int orientation) {
				JButton button = super.createDecreaseButton(orientation);
				button.setBackground(Color.DARK_GRAY);
				return button;
			}
					
			@Override
			protected JButton createIncreaseButton(int orientation) {
				JButton button = super.createIncreaseButton(orientation);
				button.setBackground(Color.DARK_GRAY);
				return button;
			}
		});
		cipherScr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JLabel pl = new JLabel("Plaintext:");
		pl.setBounds(25,340,80,20);
		pl.setForeground(Color.WHITE);

		JTextArea plain = new JTextArea();
		plain.setBackground(Color.LIGHT_GRAY);
		plain.setForeground(Color.BLACK);
		plain.setEditable(false);
		plain.setLineWrap(true);
		plain.setWrapStyleWord(true);
		plain.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		
		JScrollPane plainScr = new JScrollPane(plain);
		plainScr.setBounds(25,370,430,200);
		plainScr.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.GRAY;
				this.trackColor = Color.DARK_GRAY;
			}
			
			@Override
			protected JButton createDecreaseButton(int orientation) {
				JButton button = super.createDecreaseButton(orientation);
				button.setBackground(Color.DARK_GRAY);
				return button;
			}
					
			@Override
			protected JButton createIncreaseButton(int orientation) {
				JButton button = super.createIncreaseButton(orientation);
				button.setBackground(Color.DARK_GRAY);
				return button;
			}
		});
		plainScr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JLabel invalid = new JLabel();
		invalid.setBounds(25,590,200,40);
		invalid.setForeground(Color.RED);
		
		JButton copyKey = new JButton("Copy");
		copyKey.setBounds(365,20,90,20);
		copyKey.setBackground(Color.DARK_GRAY);
		copyKey.setForeground(Color.WHITE);
		copyKey.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String keyStr = key.getText();
				
				if(keyStr.length() != 26 || !keyStr.matches("\\A\\p{ASCII}*\\z")) {
					return;
				}
				
				StringSelection stringSelection = new StringSelection(keyStr);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				
				invalid.setText("Copied Key!");
				invalid.setForeground(Color.GREEN);
				
				ActionListener listener = new ActionListener(){
			        public void actionPerformed(ActionEvent event){
			            invalid.setText("");
			            invalid.setForeground(Color.RED);
			        }
			    };
			    
				Timer timer = new Timer(1000, listener);
			    timer.setRepeats(false);
			    timer.start();
			}
		});
		
		JButton copyCipher = new JButton("Copy");
		copyCipher.setBounds(365,90,90,20);
		copyCipher.setBackground(Color.DARK_GRAY);
		copyCipher.setForeground(Color.WHITE);
		copyCipher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cipherStr = cipher.getText();
				
				if(!(cipherStr.length() > 4)) {
					return;
				}
				
				StringSelection stringSelection = new StringSelection(cipherStr);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				
				invalid.setText("Copied Ciphertext!");
				invalid.setForeground(Color.GREEN);
				
				ActionListener listener = new ActionListener(){
			        public void actionPerformed(ActionEvent event){
			            invalid.setText("");
			            invalid.setForeground(Color.RED);
			        }
			    };
			    
				Timer timer = new Timer(1000, listener);
			    timer.setRepeats(false);
			    timer.start();
			}
		});
		
		JButton copyPlain = new JButton("Copy");
		copyPlain.setBounds(365,340,90,20);
		copyPlain.setBackground(Color.DARK_GRAY);
		copyPlain.setForeground(Color.WHITE);
		copyPlain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String plainStr = plain.getText();
				
				if(plainStr.length() == 0) {
					return;
				}
				
				StringSelection stringSelection = new StringSelection(plainStr);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				
				invalid.setText("Copied Plaintext!");
				invalid.setForeground(Color.GREEN);
				
				ActionListener listener = new ActionListener(){
			        public void actionPerformed(ActionEvent event){
			            invalid.setText("");
			            invalid.setForeground(Color.RED);
			        }
			    };
			    
				Timer timer = new Timer(1000, listener);
			    timer.setRepeats(false);
			    timer.start();
			}
		});
		
		JButton back = new JButton("Go Back");
		back.setBounds(265,590,90,40);
		back.setBackground(Color.DARK_GRAY);
		back.setForeground(Color.WHITE);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
				    @Override
				    public void run() {
				    	menu();
				    }
				});
			}
		});
		
		JButton de = new JButton("Decrypt");
		de.setBounds(365,590,90,40);
		de.setBackground(Color.DARK_GRAY);
		de.setForeground(Color.WHITE);
		de.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String keyS = key.getText();
				
				boolean v = true;
				for(int i = 0; i < keyS.length(); i++) {
					if(!Character.isLetter(keyS.charAt(i))) {
						v = false;
						break;
					}
					
					if(i == 0)
						continue;
					
					for(int j = 0; j < i; j++) {
						if(keyS.charAt(j) == keyS.charAt(i)) {
							v = false;
							break;
						}
					}
					
					if(!v) {
						break;
					}
				}
				
				if(!v || keyS.length() != 26 || !keyS.matches("\\A\\p{ASCII}*\\z")) {
					invalid.setText("Invalid key.");
					
					ActionListener listener = new ActionListener(){
				        public void actionPerformed(ActionEvent event){
				            invalid.setText("");
				        }
				    };
				    
					Timer timer = new Timer(1000, listener);
				    timer.setRepeats(false);
				    timer.start();
					return;
				} else {
					invalid.setText("");
				}
				
				String c = cipher.getText();
				if(!(c.length() > 4)) {
					invalid.setText("Invalid ciphertext.");
					
					ActionListener listener = new ActionListener(){
				        public void actionPerformed(ActionEvent event){
				            invalid.setText("");
				        }
				    };
				    
					Timer timer = new Timer(1000, listener);
				    timer.setRepeats(false);
				    timer.start();
					return;
				} else if(!(c.charAt(0) == '#' && c.charAt(c.length() - 1) == '#' && (c.charAt(c.length() - 2) == '%' || c.charAt(c.length() - 2) == '$'))) {
					invalid.setText("Invalid ciphertext.");
					
					ActionListener listener = new ActionListener(){
				        public void actionPerformed(ActionEvent event){
				            invalid.setText("");
				        }
				    };
				    
					Timer timer = new Timer(1000, listener);
				    timer.setRepeats(false);
				    timer.start();
					return;
				} else {
					invalid.setText("");
				}
				
				boolean isValid = true;
				for (int i = 0; i < c.length(); i++) {
					if((int)c.charAt(i) == 32) {
						invalid.setText("Invalid ciphertext.");
						
						ActionListener listener = new ActionListener(){
					        public void actionPerformed(ActionEvent event){
					            invalid.setText("");
					        }
					    };
					    
						Timer timer = new Timer(1000, listener);
					    timer.setRepeats(false);
					    timer.start();
						isValid = false;
						break;
					}
				}
				
				if(!isValid) {
					return;
				} else {
					invalid.setText("");
				}
				
				plain.setText(replace(c, keyS, getIndex(c, keyS, 'd'), 'd'));	
			}
		});
		
		p.add(copyKey);
		p.add(copyPlain);
		p.add(copyCipher);
		p.add(back);
		p.add(keyAsk);
		p.add(key);
		p.add(cipherAsk);
		p.add(cipherScr);
		p.add(pl);
		p.add(plainScr);
		p.add(invalid);
		p.add(de);
		p.setBackground(Color.BLACK);
		
		f.pack();
		f.setSize(495, 690);
		f.setIconImage(frameIcon);
		f.getContentPane().removeAll();
		f.getContentPane().add(p);
		f.revalidate();
		f.repaint();
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public static int[] getIndex(String txt, String key, char type) {
	    int n = txt.length();
	    int[] index;
	    
	    if (type == 'e') {
	    	index = new int[n];
	    	for (int i = 0; i < n; i++) {
	    		if (Character.isLetter(txt.charAt(i)))
	    			index[i] = Character.toLowerCase(txt.charAt(i)) - 97;
	    		else
	    			index[i] = 0;
	    	}
	    } else {
	    	index = new int[(n-3)/2];
	    	
	    	if(txt.charAt(txt.length() - 2) == '$') {
	            for (int i = 1, r = 0; i < txt.length() - 2; i++) {
	                if (i % 2 != 0) {
	                    if (Character.isLetter(txt.charAt(i))) {
	                        char c = Character.toLowerCase(txt.charAt(i));

	                        if(r % 2 == 0){
	                           if((int)c == 97)
	                               c = (char)122;
	                           else
	                               c = (char)((int)c - 1);
	                        }

	                        if ((int)c - 2 == 95) {
	                            c = (char)120;
	                        } else if ((int)c - 2 == 96) {
	                            c = (char)121;
	                        } else if ((int)c - 2 == 97) {
	                            c = (char)122;
	                        } else {
	                            c = (char)((int)c - 3);
	                        }

	                        int j;
	                        for(j = 0; c != Character.toLowerCase(key.charAt(j)); j++);
	                        index[r] = 97 + j;
	                    } else
	                        index[r] = 0;

	                    r++;
	                } else {
	                    continue;
	                }
	            }
	        } else {
	            for (int i = 1, r = 0; i < txt.length() - 2; i++) {
	                if (i % 2 == 0) {
	                    if (Character.isLetter(txt.charAt(i))) {
	                        char c = Character.toLowerCase(txt.charAt(i));

	                        if(r % 2 == 0){
	                            if((int)c == 97)
	                                c = (char)122;
	                            else
	                                c = (char)((int)c - 1);
	                        }

	                        if ((int)c - 2 == 95) {
	                            c = (char)121;
	                        } else if ((int)c - 2 == 96) {
	                            c = (char)122;
	                        } else {
	                            c = (char)((int)c - 2);
	                        }

	                        int j;
	                        for(j = 0; c != Character.toLowerCase(key.charAt(j)); j++);
	                        index[r] = 97 + j;
	                    } else
	                        index[r] = 0;

	                        r++;
	                } else {
	                    continue;
	                }
	            }
	        }
	    }
	    
		return index;
	}
	
	public static String replace(String txt, String key, int index[], char type) {
		int n = txt.length();
		char[] temp;
		String str = "";
		
		if(type == 'e') {
			temp = new char[n];
			int eType = (int)(Math.random() * 2);
			
			for (int i = 0; i < n; i++) {
	            if (Character.isLetter(txt.charAt(i))) {
	                if ((int)txt.charAt(i) % 2 == 0) {
	                    if (Character.isUpperCase(txt.charAt(i)))
	                        temp[i] = Character.toLowerCase(key.charAt(index[i]));
	                    else
	                        temp[i] = Character.toUpperCase(key.charAt(index[i]));
	                } else {
	                    if (Character.isUpperCase(txt.charAt(i)))
	                        temp[i] = Character.toUpperCase(key.charAt(index[i]));
	                    else
	                        temp[i] = Character.toLowerCase(key.charAt(index[i]));
	                }

	                if(Character.isLowerCase(temp[i])) {
	                    if (eType == 0) {
	                        if ((int)(temp[i]) == 120) {
	                            temp[i] = (char)97;
	                        } else if ((int)(temp[i]) == 121) {
	                            temp[i] = (char)98;
	                        } else if ((int)(temp[i]) == 122) {
	                            temp[i] = (char)99;
	                        } else {
	                            temp[i] = (char)((int)temp[i] + 3);
	                        }
	                    } else {
	                        if ((int)(temp[i]) == 121) {
	                            temp[i] = (char)97;
	                        } else if ((int)(temp[i]) == 122) {
	                            temp[i] = (char)98;
	                        } else {
	                            temp[i] = (char)((int)temp[i] + 2);
	                        }
	                    }

	                    if(i % 2 == 0) {
	                        if((int)temp[i] == 122)
	                            temp[i] = (char)97;
	                        else
	                            temp[i] = (char) (((int) temp[i]) + 1);
	                    }
	                } else {
	                    if (eType == 0) {
	                        if ((int)(temp[i]) == 88) {
	                            temp[i] = (char)65;
	                        } else if ((int)(temp[i]) == 89) {
	                            temp[i] = (char)66;
	                        } else if ((int)(temp[i]) == 90) {
	                            temp[i] = (char)67;
	                        } else {
	                            temp[i] = (char)((int)temp[i] + 3);
	                        }
	                    } else {
	                        if ((int)(temp[i]) == 89) {
	                            temp[i] = (char)65;
	                        } else if ((int)(temp[i]) == 90) {
	                            temp[i] = (char)66;
	                        } else {
	                            temp[i] = (char)((int)temp[i] + 2);
	                        }
	                    }

	                    if(i % 2 == 0) {
	                        if((int)temp[i] == 90)
	                            temp[i] = (char)65;
	                        else
	                            temp[i] = (char) (((int) temp[i]) + 1);
	                    }
	                }

	            } else if ((int)txt.charAt(i) >= 33 && (int)txt.charAt(i) <= 64) {
	                if (eType == 0) {
	                    if ((int) txt.charAt(i) == 63) {
	                        temp[i] = (char) 33;
	                    } else if ((int) txt.charAt(i) == 64) {
	                        temp[i] = (char) 34;
	                    } else {
	                        temp[i] = (char) ((int) txt.charAt(i) + 2);
	                    }
	                } else {
	                    if ((int) txt.charAt(i) == 64) {
	                        temp[i] = (char) 33;
	                    } else {
	                        temp[i] = (char) ((int) txt.charAt(i) + 1);
	                    }
	                }

	                if(i % 2 == 0) {
	                    if((int)temp[i] == 64)
	                        temp[i] = (char)33;
	                    else
	                        temp[i] = (char) (((int) temp[i]) + 1);
	                }
	            } else if ((int)txt.charAt(i) >= 91 && (int)txt.charAt(i) <= 96) {
	                if (eType == 0) {
	                    if ((int) txt.charAt(i) == 95) {
	                        temp[i] = (char) 91;
	                    } else if ((int) txt.charAt(i) == 96) {
	                        temp[i] = (char) 92;
	                    } else {
	                        temp[i] = (char) ((int) txt.charAt(i) + 2);
	                    }
	                } else {
	                    if ((int) txt.charAt(i) == 96) {
	                        temp[i] = (char) 91;
	                    } else {
	                        temp[i] = (char) ((int) txt.charAt(i) + 1);
	                    }
	                }

	                if(i % 2 == 0) {
	                    if((int)temp[i] == 96)
	                        temp[i] = (char)91;
	                    else
	                        temp[i] = (char) (((int) temp[i]) + 1);
	                }
	            } else if ((int)txt.charAt(i) >= 123 && (int)txt.charAt(i) <= 126) {
	                if (eType == 0) {
	                    if ((int) txt.charAt(i) == 125) {
	                        temp[i] = (char) 123;
	                    } else if ((int) txt.charAt(i) == 126) {
	                        temp[i] = (char) 124;
	                    } else {
	                        temp[i] = (char) ((int) txt.charAt(i) + 2);
	                    }
	                } else {
	                    if ((int) txt.charAt(i) == 126) {
	                        temp[i] = (char) 123;
	                    } else {
	                        temp[i] = (char) ((int) txt.charAt(i) + 1);
	                    }
	                }

	                if(i % 2 == 0) {
	                    if((int)temp[i] == 126)
	                        temp[i] = (char)123;
	                    else
	                        temp[i] = (char) (((int) temp[i]) + 1);
	                }
	            } else {
	                temp[i] = '\u00BF';
	            }
	        }

	        str += "#";
	        char[] random = new char[temp.length];

	        for(int i = 0; i < random.length; i++) {
	            random[i] = (char)((int)(Math.random() * 94) + 33);
	        }

	        for(int i = 0, j = 0, r = 0; i < temp.length * 2; i++) {
	            if(eType == 0) {
	                if (i % 2 == 0) {
	                    str += temp[j];
	                    j++;
	                } else {
	                    str += random[r];
	                    r++;
	                }
	            } else {
	                if (i % 2 == 0) {
	                    str += random[r];
	                    r++;
	                } else {
	                    str += temp[j];
	                    j++;
	                }
	            }
	        }

	        if(eType == 0) {
	            str += "$";
	        } else {
	            str += "%";
	        }

	        str += "#";
			
		} else {
			temp = new char[(n-3)/2];
			
			if(txt.charAt(txt.length() - 2) == '$') {
	            for (int i = 1, r = 0; i < txt.length() - 2; i++) {
	                if (i % 2 != 0) {
	                    if (Character.isLetter(txt.charAt(i))) {
	                        temp[r] = (char)index[r];

	                        if((int)temp[r] % 2 == 0) {
	                            if (Character.isUpperCase(txt.charAt(i)))
	                                temp[r] = Character.toLowerCase(temp[r]);
	                            else
	                                temp[r] = Character.toUpperCase(temp[r]);
	                        } else {
	                            if (Character.isUpperCase(txt.charAt(i)))
	                                temp[r] = Character.toUpperCase(temp[r]);
	                            else
	                                temp[r] = Character.toLowerCase(temp[r]);
	                        }

	                    } else if ((int)txt.charAt(i) >= 33 && (int)txt.charAt(i) <= 64) {
	                        char c = txt.charAt(i);

	                        if(r % 2 == 0){
	                            if((int)c == 33)
	                                c = (char)64;
	                            else
	                                c = (char)((int)c - 1);
	                        }

	                        if ((int)c == 33) {
	                            temp[r] = (char)63;
	                        } else if ((int)c == 34) {
	                            temp[r] = (char)64;
	                        } else {
	                            temp[r] = (char) ((int)c - 2);
	                        }
	                    } else if ((int)txt.charAt(i) >= 91 && (int)txt.charAt(i) <= 96){
	                        char c = txt.charAt(i);

	                        if(r % 2 == 0){
	                            if((int)c == 91)
	                                c = (char)96;
	                            else
	                                c = (char)((int)c - 1);
	                        }

	                        if ((int)c == 91) {
	                            temp[r] = (char)95;
	                        } else if ((int)c == 92) {
	                            temp[r] = (char)96;
	                        } else {
	                            temp[r] = (char) ((int)c - 2);
	                        }
	                    } else if ((int)txt.charAt(i) >= 123 && (int)txt.charAt(i) <= 126){
	                        char c = txt.charAt(i);

	                        if(r % 2 == 0){
	                            if((int)c == 123)
	                                c = (char)126;
	                            else
	                                c = (char)((int)c - 1);
	                        }

	                        if ((int)c == 123) {
	                            temp[r] = (char)125;
	                        } else if ((int)c == 124) {
	                            temp[r] = (char)126;
	                        } else {
	                            temp[r] = (char) ((int)c - 2);
	                        }
	                    } else {
	                        temp[r] = (char)32;
	                    }

	                    r++;
	                } else {
	                    continue;
	                }
	            }
	        } else {
	            for (int i = 1, r = 0; i < txt.length() - 2; i++) {
	                if (i % 2 == 0) {
	                    if (Character.isLetter(txt.charAt(i))) {
	                        temp[r] = (char)index[r];

	                        if((int)temp[r] % 2 == 0) {
	                            if (Character.isUpperCase(txt.charAt(i)))
	                                temp[r] = Character.toLowerCase(temp[r]);
	                            else
	                                temp[r] = Character.toUpperCase(temp[r]);
	                        } else {
	                            if (Character.isUpperCase(txt.charAt(i)))
	                                temp[r] = Character.toUpperCase(temp[r]);
	                            else
	                                temp[r] = Character.toLowerCase(temp[r]);
	                        }

	                    } else if ((int)txt.charAt(i) >= 33 && (int)txt.charAt(i) <= 64) {
	                        char c = txt.charAt(i);

	                        if(r % 2 == 0){
	                            if((int)c == 33)
	                                c = (char)64;
	                            else
	                                c = (char)((int)c - 1);
	                        }

	                        if ((int)c == 33) {
	                            temp[r] = (char)64;
	                        } else {
	                            temp[r] = (char) ((int)c - 1);
	                        }
	                    } else if ((int)txt.charAt(i) >= 91 && (int)txt.charAt(i) <= 96){
	                        char c = txt.charAt(i);

	                        if(r % 2 == 0){
	                            if((int)c == 91)
	                                c = (char)96;
	                            else
	                                c = (char)((int)c - 1);
	                        }

	                        if ((int)c == 91) {
	                            temp[r] = (char)96;
	                        } else {
	                            temp[r] = (char) ((int)c - 1);
	                        }
	                    } else if ((int)txt.charAt(i) >= 123 && (int)txt.charAt(i) <= 126){
	                        char c = txt.charAt(i);

	                        if(r % 2 == 0){
	                            if((int)c == 123)
	                                c = (char)126;
	                            else
	                                c = (char)((int)c - 1);
	                        }

	                        if ((int)c == 123) {
	                            temp[r] = (char)126;
	                        } else {
	                            temp[r] = (char) ((int)c - 1);
	                        }
	                    } else {
	                        temp[r] = (char)32;
	                    }

	                    r++;
	                } else {
	                    continue;
	                }
	            }
	        }

	        for(int i = 0; i < temp.length; i++) {
	            str += temp[i];
	        }
		}
		
		return str;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    @Override
		    public void run() {
		    	menu();
		    }
		});
	}
}
