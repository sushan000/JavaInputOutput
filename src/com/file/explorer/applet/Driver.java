package com.file.explorer.applet;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class Driver  extends JApplet implements TreeSelectionListener {
	private static final long serialVersionUID = 1L;

	public static Container contentPane;
	public JTree tree, tree2;
	public DefaultMutableTreeNode root, subDir;
	JPanel mainPanel, left, right;
	JLabel txtContent;
	String mainDirectory = "E:\\";

	public void init() {
		contentPane = getContentPane();
		this.setSize(600, 500);

		mainPanel = new JPanel(new BorderLayout());
		left = new JPanel();
		right = new JPanel();
		left.setPreferredSize(new Dimension(300, 250));
		right.setPreferredSize(new Dimension(300, 250));
		mainPanel.add(left, BorderLayout.WEST);
		mainPanel.add(right, BorderLayout.EAST);
		root = new DefaultMutableTreeNode("Main Directory");
		rootDir();
		tree = new JTree(root);
		tree.addTreeSelectionListener(this);
		left.add(tree);
		add(mainPanel);

	}

	private void rootDir() {
		File directory = new File(mainDirectory);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
			boolean isDirectory = file.isDirectory();
			if (isDirectory) {
				node.add(new DefaultMutableTreeNode(""));
			}
			root.add(node);
		}

	}

	private void dirLoad(Object nodeObj) {
		String nodeName = nodeObj.toString();
		File directory = new File(mainDirectory + nodeName);
		boolean isDirectory = directory.isDirectory();
		if (isDirectory) {
			subDir = new DefaultMutableTreeNode(nodeName);
			File[] fList = directory.listFiles();
			for (File file : fList) {
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
				boolean isDir = file.isDirectory();
				if (isDir) {
					node.add(new DefaultMutableTreeNode(""));
				}
				subDir.add(node);
			}
		}

		tree2 = new JTree(subDir);
		tree2.addTreeSelectionListener(this);
		right.removeAll();
		right.add(tree2);
		right.revalidate();
		right.repaint();

	}

	@Override
	public void valueChanged(TreeSelectionEvent event) {
		JTree source = (JTree) event.getSource();
		if (source.equals(tree)) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (node == null)
				return;
			Object nodeInfo = node.getUserObject();
			dirLoad(nodeInfo);
			
		} else if (source.equals(tree2)) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree2.getLastSelectedPathComponent();
			String fileName = node.getUserObject().toString();
			Boolean isTxt = fileName.toLowerCase().contains(".txt");
			if (isTxt) {
				String filePath = mainDirectory;
				Object[] paths = tree2.getSelectionPath().getPath();
				for (int i = 0; i < paths.length; i++) {
					filePath += paths[i];
					if (i + 1 < paths.length) {
						filePath += File.separator;
					}
				}
				readfile(filePath);
			}

		}

	}

	private void readfile(String filePath) {
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

		txtContent = new JLabel(content);
		right.removeAll();
		right.add(txtContent);
		right.revalidate();
		right.repaint();

	}


}
