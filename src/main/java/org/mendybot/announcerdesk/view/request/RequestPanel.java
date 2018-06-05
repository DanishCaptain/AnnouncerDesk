package org.mendybot.announcerdesk.view.request;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.mendybot.announcerdesk.controller.request.RequestController;
import org.mendybot.announcerdesk.model.request.RequestModel;
import org.mendybot.announcerdesk.model.request.RequestStatusListener;
import org.mendybot.announcerdesk.model.request.Server;
import org.mendybot.announcerdesk.model.request.ServersListener;

public class RequestPanel extends JPanel implements ServersListener, ActionListener, RequestStatusListener
{
  private static final long serialVersionUID = -4098871157346617896L;
  private RequestModel model;
  private RequestController controller;
  private DefaultListModel<Server> mServers = new DefaultListModel<>();
  private JCheckBox cbAlarm = new JCheckBox();
  private JList<Server> lServers = new JList<>(mServers);
  private JTextField tfSoundLevel = new JTextField(Integer.toString(RequestController.DEFAULT_SOUND_LEVEL));
  private JTextField tfSayText = new JTextField(60);
  private JComboBox cbPlay = new JComboBox();
  private JTextField tfDisplayText = new JTextField(60);
  private JComboBox<Integer> cbDisplayRepeat = new JComboBox<>();
  private JComboBox<DisplayColor> cbDisplayColors = new JComboBox<>();
  private JComboBox<String> cbDisplayFonts = new JComboBox<>();

  private DefaultListModel<String> mStatus = new DefaultListModel<>();
  private JList<String> lStatus = new JList<>(mStatus);
  
  private JButton bSend = new JButton("Send Announcement");

  public RequestPanel(RequestModel model, RequestController controller)
  {
    this.model = model;
    this.controller = controller;
    
    cbAlarm.setSelected(true);
    
    for (int i=1; i<=10; i++)
    {
      cbDisplayRepeat.addItem(i);
    }
    cbDisplayFonts.addItem("10x20");
    cbDisplayFonts.addItem("4x6");
    cbDisplayFonts.addItem("7x13");
    cbDisplayFonts.setSelectedIndex(0);
//    10x20.bdf  6x12.bdf   7x13B.bdf  8x13B.bdf  9x18B.bdf    README
//    4x6.bdf    6x13B.bdf  7x13.bdf   8x13.bdf   9x18.bdf     README.md
//    5x7.bdf    6x13.bdf   7x13O.bdf  8x13O.bdf  AUTHORS      tom-thumb.bdf
//    5x8.bdf    6x13O.bdf  7x14B.bdf  9x15B.bdf  clR6x12.bdf
//    6x10.bdf   6x9.bdf    7x14.bdf   9x15.bdf   helvR12.bdf
        
    cbDisplayColors.addItem(new DisplayColor("White", Color.WHITE));
    cbDisplayColors.addItem(new DisplayColor("Red", Color.RED));
    cbDisplayColors.addItem(new DisplayColor("Blue", Color.BLUE));
    cbDisplayColors.addItem(new DisplayColor("Yellow", Color.YELLOW));
    cbDisplayColors.addItem(new DisplayColor("Green", Color.GREEN));
    cbDisplayColors.addItem(new DisplayColor("Yellow", Color.YELLOW));
    cbDisplayColors.addItem(new DisplayColor("Cyan", Color.CYAN));
    cbDisplayColors.addItem(new DisplayColor("Gray", Color.GRAY));
    cbDisplayColors.addItem(new DisplayColor("Dark Gray", Color.DARK_GRAY));
    cbDisplayColors.addItem(new DisplayColor("Magenta", Color.MAGENTA));
    cbDisplayColors.addItem(new DisplayColor("Orange", Color.ORANGE));
    cbDisplayColors.setSelectedIndex(0);
    
    
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
    JPanel pServers = new JPanel();
    pServers.setBorder(new TitledBorder("Announcement Pods:"));
    add(pServers);
    pServers.add(lServers);
    pServers.add(new JLabel("Is Alarm: "));
    pServers.add(cbAlarm);

    JPanel pSound = new JPanel();
    pSound.setBorder(new TitledBorder("Sound:"));
    add(pSound);
    pSound.add(new JLabel("level %: "));
    pSound.add(tfSoundLevel);
    pSound.add(new JLabel("say: "));
    pSound.add(tfSayText);
    pSound.add(new JLabel("play: "));
    pSound.add(cbPlay);

    JPanel pDisplay = new JPanel();
    pDisplay.setBorder(new TitledBorder("Display:"));
    pDisplay.setLayout(new BoxLayout(pDisplay, BoxLayout.Y_AXIS));
    add(pDisplay);
    register(pDisplay, "Font:", cbDisplayFonts);
    register(pDisplay, "Font Color:", cbDisplayColors);
    register(pDisplay, "Repeat:", cbDisplayRepeat);

    JPanel pText = new JPanel();
    pText.setBorder(new TitledBorder("Text:"));
    pDisplay.add(pText);
    pText.add(new JLabel("display: "));
    pText.add(tfDisplayText);

    JPanel pImage = new JPanel();
    pImage.setBorder(new TitledBorder("Image:"));
    pDisplay.add(pImage);
    
    JPanel pButtons = new JPanel();
    pButtons.setBorder(new TitledBorder("Commands:"));
    add(pButtons);
    pButtons.add(bSend);
    bSend.addActionListener(this);
    
    add(lStatus);
    
    model.addServersListener(this);
    model.addRequestStatusListener(this);
}

  private void register(JPanel panel, String lbl, JComponent component)
  {
    JPanel p = new JPanel();
    JPanel pp = new JPanel();
    p.add(pp);
    pp.setLayout(new BoxLayout(pp, BoxLayout.X_AXIS));
    pp.add(new JLabel(lbl));
    pp.add(component);
    panel.add(p);    
  }

  @Override
  public void addNew(Server s)
  {
    mServers.addElement(s);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    List<Server> servers = lServers.getSelectedValuesList();
    boolean isAlarm = cbAlarm.isSelected();
    int soundLevel =  Integer.parseInt(tfSoundLevel.getText());
    String sayText = tfSayText.getText();
    String displayText = tfDisplayText.getText();
    String displayFont = (String) cbDisplayFonts.getSelectedItem();
    DisplayColor displayColor = (DisplayColor) cbDisplayColors.getSelectedItem();
    Integer displayRepeat = (Integer) cbDisplayRepeat.getSelectedItem();
    controller.announce(servers, isAlarm, soundLevel, sayText, displayText, displayFont, displayColor.getColor(), displayRepeat);
  }

  @Override
  public void newRequestStatus(List<String> status)
  {
    mStatus.clear();
    for (String s : status)
    {
      mStatus.addElement(s);
    }
    repaint();
  }

}
