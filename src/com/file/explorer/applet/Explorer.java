package com.file.explorer.applet;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Explorer extends JApplet {

	
	private JPanel leftPanel,rightPanel,panel;
	private JTree tree;
	private JFrame frame;
	private File file=new File("E:\\");
	private DefaultMutableTreeNode root;
	private  DefaultTreeModel model;
	public Explorer() {
		init();
		this.getContentPane().add(panel);
		
		
	}
	
	public List<File> ListDataFromDrive() {
		File[] fArray = file.listFiles();
		
		List<File> fileList=new ArrayList<>();
		fileList = Arrays.asList(fArray);
//		for(File file:fileList) {
//			System.out.println(file);
//		}
		return fileList;
	}
	
	public void populateDataInTree() {
		root= new DefaultMutableTreeNode("F://");
		//TreeModel model = new FileTreeModel(new File(System.getProperty("user.dir")));
		model = new DefaultTreeModel(root);
		
		 tree = new JTree();
		 tree.setModel(model);
		 tree.setBounds(15, 5, 349, 579);
			leftPanel.add(tree);
		 frame.pack();
	}

	public void init() {
		getContentPane().setLayout(null);
		setSize(new Dimension(800,600));
		panel = new JPanel();
		panel.setBounds(0, 0, 750, 564);
		getContentPane().add(panel);
	
		panel.setLayout(null);
		
		leftPanel = new JPanel();
		leftPanel.setBounds(0, 0, 379, 564);
		panel.add(leftPanel);
		leftPanel.setLayout(null);
		
	//	tree = new JTree();
//		tree.setModel(new DefaultTreeModel(
//			new DefaultMutableTreeNode("JTree") {
//				{
//					DefaultMutableTreeNode node_1;
//					node_1 = new DefaultMutableTreeNode("colors");
//						node_1.add(new DefaultMutableTreeNode("blue"));
//						node_1.add(new DefaultMutableTreeNode("violet"));
//						node_1.add(new DefaultMutableTreeNode("red"));
//						node_1.add(new DefaultMutableTreeNode("yellow"));
//					add(node_1);
//					node_1 = new DefaultMutableTreeNode("sports");
//						node_1.add(new DefaultMutableTreeNode("basketball"));
//						node_1.add(new DefaultMutableTreeNode("soccer"));
//						node_1.add(new DefaultMutableTreeNode("football"));
//						node_1.add(new DefaultMutableTreeNode("hockey"));
//					add(node_1);
//					node_1 = new DefaultMutableTreeNode("food");
//						node_1.add(new DefaultMutableTreeNode("hot dogs"));
//						node_1.add(new DefaultMutableTreeNode("pizza"));
//						node_1.add(new DefaultMutableTreeNode("ravioli"));
//						node_1.add(new DefaultMutableTreeNode("bananas"));
//					add(node_1);
//				}
//			}
//		));
		
		
		rightPanel = new JPanel();
		rightPanel.setBounds(369, 0, 381, 564);
		panel.add(rightPanel);
		
	}
}
