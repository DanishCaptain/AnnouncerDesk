package org.mendybot.announcerdesk.view.announcement;

import javax.swing.JPanel;

import org.mendybot.announcerdesk.controller.announcement.AnnouncementController;
import org.mendybot.announcerdesk.model.announcement.AnnouncementModel;

public class AnnouncementsPanel extends JPanel
{
  private static final long serialVersionUID = 8277182589884126043L;

  public AnnouncementsPanel(AnnouncementModel model, AnnouncementController controller)
  {
    add(new QuickPanel(model, controller));
    add(new LongPanel(model, controller));
  }

}
