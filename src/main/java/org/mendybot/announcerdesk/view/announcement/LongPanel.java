package org.mendybot.announcerdesk.view.announcement;

import org.mendybot.announcer.common.model.dto.ArchiveResource;
import org.mendybot.announcer.fault.ExecuteException;
import org.mendybot.announcerdesk.controller.announcement.AnnouncementController;
import org.mendybot.announcerdesk.model.announcement.AnnouncementModel;
import org.mendybot.announcerdesk.model.status.ArchiveResourceChangeListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LongPanel extends JPanel implements ActionListener, ArchiveResourceChangeListener
{
  private static final long serialVersionUID = -2269691964676219777L;
  private final AnnouncementModel model;
  private JTextField tfMessage = new JTextField(60);
  private DefaultComboBoxModel<ArchiveResource> mSounds = new DefaultComboBoxModel<>();
  private JComboBox<ArchiveResource> cbSounds = new JComboBox<>(mSounds);
  private JButton bSend = new JButton("send");
  private AnnouncementController controller;

  public LongPanel(AnnouncementModel model, AnnouncementController controller)
  {
    this.model = model;
    this.controller = controller;
    this.setBorder(new TitledBorder("Long Announcement"));
    add(tfMessage);
    add(cbSounds);
    add(bSend);
    bSend.addActionListener(this);

    model.addArchiveResourceChangeListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    try
    {
      controller.announceLong(tfMessage.getText(), cbSounds.getSelectedItem());
    }
    catch (ExecuteException e1)
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  @Override
  public void archiveResourcesUpdated() {
    List<ArchiveResource> list = model.getSounds();
    mSounds.removeAllElements();
    for (ArchiveResource ar : list) {
      mSounds.addElement(ar);
    }
//    ArrayList<ArchiveResource> working = new ArrayList();
//    working.addAll(list);
  }
}
