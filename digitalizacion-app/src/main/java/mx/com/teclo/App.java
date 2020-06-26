package mx.com.teclo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import mx.com.teclo.base.context.BeanLocator;
import mx.com.teclo.escanner.Scanner;
import mx.com.teclo.omr.Omr;
import mx.com.teclo.restclient.MiInterfaz;
import mx.com.teclo.restclient.RestClient;

public class App {
	
	private JFrame frame;
	
	private RestClient restClient = (RestClient) BeanLocator.getService("restClient");
	
	private Scanner scanner = (Scanner)BeanLocator.getService("scanner");
	
	private Omr omr = (Omr) BeanLocator.getService("omr");
	
	private MiInterfaz instanciademiInterfaz  = (MiInterfaz) BeanLocator.getService("miInterfaz");
	
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					App window = new App();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
	
	public App(){
//		frame = new JFrame("Prueba de JNLP"); 
//		frame.setBounds(100, 100, 254, 152);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setResizable(false);
//		JLabel label = new JLabel();
//		Container content = frame.getContentPane();
//		content.add(label, BorderLayout.CENTER);
//		String message = "http://www.teclo.com";
//
//		label.setText(message);
//
//		JButton button = new JButton("Boton");
//
//		content.add(button, BorderLayout.SOUTH);
//		scanner.consultaTest();
//
//		omr.consultaTest();
//		
//		restClient.consultaTest();
//		
//		instanciademiInterfaz.mimetodo();		
//		
	}
}
