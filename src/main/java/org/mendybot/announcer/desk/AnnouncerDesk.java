package org.mendybot.announcer.desk;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.mendybot.announcer.desk.controller.request.RequestController;
import org.mendybot.announcer.desk.model.request.RequestModel;
import org.mendybot.announcer.desk.view.request.RequestPanel;

public class AnnouncerDesk
{
  private JFrame frame;

  public AnnouncerDesk()
  {
    frame = new JFrame();
    
    JTabbedPane tabs = new JTabbedPane();
    frame.add(tabs, BorderLayout.CENTER);

    RequestModel aModel = new RequestModel();
    RequestController aController = new RequestController(aModel);
    
    
    tabs.add("requests", new RequestPanel(aModel, aController));

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setSize(d.width, d.height-40);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public static void main(String[] args)
  {
    new AnnouncerDesk();
  }
}
