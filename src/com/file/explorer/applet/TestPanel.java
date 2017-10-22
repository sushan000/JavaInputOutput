package com.file.explorer.applet;


	import java.awt.BorderLayout;
	import java.awt.Container;
	import java.awt.Dimension;
	import java.io.File;
	import java.util.Collections;
	import java.util.Vector;
	/* w w w . jav  a  2s .  c o m*/
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTree;
	import javax.swing.tree.DefaultMutableTreeNode;
	import javax.swing.tree.DefaultTreeCellRenderer;

	public class TestPanel {
	  public static void main(final String[] av) {
	    JFrame frame = new JFrame();
	    Container cp = frame.getContentPane();
	    cp.add(new FileTree(new File("..")));
	    frame.pack();
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  }
	}

	class FileTree extends JPanel {
	  public FileTree(File dir) {
	    setLayout(new BorderLayout());
	    JTree tree = new JTree(addNodes(null, dir));
//	    tree.addTreeSelectionListener(e -> {
//	      DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath()
//	          .getLastPathComponent();
//	      System.out.println("You selected " + node);
//	    });
	    DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree
	        .getCellRenderer();
	    JScrollPane scrollpane = new JScrollPane();
	    scrollpane.getViewport().add(tree);
	    add(BorderLayout.CENTER, scrollpane);
	  }

	  private DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop,
	      File dir) {
	    String curPath = dir.getPath();
	    DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
	    if (curTop != null) {
	      curTop.add(curDir);
	    }
	    Vector<String> ol = new Vector<String>();
	    String[] tmp = dir.list();
	    for (int i = 0; i < tmp.length; i++) {
	      ol.addElement(tmp[i]);
	    }
	    Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
	    File f;
	    Vector<Object> files = new Vector<Object>();
	    for (int i = 0; i < ol.size(); i++) {
	      String thisObject = ol.elementAt(i);
	      String newPath;
	      if (curPath.equals(".")) {
	        newPath = thisObject;
	      } else {
	        newPath = curPath + File.separator + thisObject;
	      }
	      if ((f = new File(newPath)).isDirectory()) {
	        addNodes(curDir, f);
	      } else {
	        files.addElement(thisObject);
	      }
	    }
	    for (int fnum = 0; fnum < files.size(); fnum++) {
	      curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
	    }
	    return curDir;
	  }

	  @Override
	  public Dimension getMinimumSize() {
	    return new Dimension(200, 400);
	  }

	  @Override
	  public Dimension getPreferredSize() {
	    return new Dimension(200, 400);
	  }
	}

	

