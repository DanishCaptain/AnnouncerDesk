package org.mendybot.announcerdesk.view.announcement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.mendybot.announcer.fault.ExecuteException;
import org.mendybot.announcerdesk.controller.announcement.AnnouncementController;
import org.mendybot.announcerdesk.model.announcement.AnnouncementModel;

public class QuickPanel extends JPanel implements ActionListener
{
  private static final long serialVersionUID = -2269691964676219777L;
  private JTextField tfMessage = new JTextField(60);
  private JButton bSend = new JButton("send");
  private AnnouncementController controller;

  public QuickPanel(AnnouncementModel model, AnnouncementController controller)
  {
    this.controller = controller;
    this.setBorder(new TitledBorder("Quick Announcement"));
    add(tfMessage);
    add(bSend);
    bSend.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    try
    {
      controller.announceQuick(tfMessage.getText());
    }
    catch (ExecuteException e1)
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }
}
