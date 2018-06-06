package org.mendybot.announcerdesk;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.mendybot.announcerdesk.controller.announcement.AnnouncementController;
import org.mendybot.announcerdesk.controller.request.RequestController;
import org.mendybot.announcerdesk.controller.status.StatusController;
import org.mendybot.announcerdesk.model.announcement.AnnouncementModel;
import org.mendybot.announcerdesk.model.request.RequestModel;
import org.mendybot.announcerdesk.model.status.StatusModel;
import org.mendybot.announcerdesk.view.announcement.AnnouncementsPanel;
import org.mendybot.announcerdesk.view.request.RequestPanel;
import org.mendybot.announcerdesk.view.status.StatusPanel;

public class AnnouncerDesk
{
  private JFrame frame;

  public AnnouncerDesk()
  {
    frame = new JFrame();
    
    JTabbedPane tabs = new JTabbedPane();
    frame.add(tabs, BorderLayout.CENTER);

    StatusModel sModel = new StatusModel();
    StatusController sController = new StatusController(sModel);
    AnnouncementModel aModel = new AnnouncementModel(sModel.getSoundModel());
    AnnouncementController aController = new AnnouncementController(aModel);
    RequestModel rModel = new RequestModel();
    RequestController rController = new RequestController(rModel);
    
    
    tabs.add("status", new StatusPanel(sModel, sController));
    tabs.add("announcements", new AnnouncementsPanel(aModel, aController));
    tabs.add("requests", new RequestPanel(rModel, rController));

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
