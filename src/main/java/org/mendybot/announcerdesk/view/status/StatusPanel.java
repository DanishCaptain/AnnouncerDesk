package org.mendybot.announcerdesk.view.status;

import java.awt.Color;

import javax.swing.*;

import org.mendybot.announcerdesk.controller.status.StatusController;
import org.mendybot.announcerdesk.model.status.StatusModel;

public class StatusPanel extends JPanel
{
  private static final long serialVersionUID = -4098871157346617896L;

  public StatusPanel(StatusModel model, StatusController controller)
  {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBackground(new Color(0xFA, 0xF0, 0xE6));

    add(new JScrollPane(new ImagesPanel(model.getImageModel())));
    add(new JScrollPane(new SoundsPanel(model.getSoundModel())));
    add(new JScrollPane(new TasksPanel(model.getTasksModel())));
  }

}
