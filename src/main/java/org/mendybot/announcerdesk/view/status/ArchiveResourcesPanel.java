package org.mendybot.announcerdesk.view.status;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public abstract class ArchiveResourcesPanel extends JPanel
{
  private static final long serialVersionUID = -7312975461519263145L;
  private JEditorPane editor;
  
  public ArchiveResourcesPanel()
  {
    setLayout(new BorderLayout());
    editor = new JEditorPane();
    add(editor, BorderLayout.CENTER);
    editor.setEditable(false);
    HTMLEditorKit kit = new HTMLEditorKit();
    editor.setEditorKit(kit);
    
    // add some styles to the html
//    StyleSheet styleSheet = kit.getStyleSheet();
//    styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
//    styleSheet.addRule("h1 {color: blue;}");
//    styleSheet.addRule("h2 {color: #ff0000;}");
//    styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");

    // create a document, set it on the jeditorpane, then add the html
    Document doc = kit.createDefaultDocument();
    editor.setDocument(doc);
    editor.setText("<html bgcolor='#FAF0E6'></html>");
  }

  protected void setText(String html)
  {
    editor.setText(html);
  }
}
