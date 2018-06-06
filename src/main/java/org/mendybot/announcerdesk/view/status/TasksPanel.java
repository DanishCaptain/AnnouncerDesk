package org.mendybot.announcerdesk.view.status;

import org.mendybot.announcer.common.model.dto.Announcement;
import org.mendybot.announcer.common.model.dto.ArchiveResource;
import org.mendybot.announcer.common.model.dto.Cube;
import org.mendybot.announcerdesk.model.status.ArchiveResourceChangeListener;
import org.mendybot.announcerdesk.model.status.SoundModel;
import org.mendybot.announcerdesk.model.status.TasksModel;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TasksPanel extends ArchiveResourcesPanel implements ArchiveResourceChangeListener
{
  private static final long serialVersionUID = 5515087620533027430L;
  private TasksModel model;

  public TasksPanel(TasksModel model)
  {
    this.model = model;
    model.addArchiveResourceChangeListener(this);
  }

  @Override
  public void archiveResourcesUpdated()
  {
    StringBuilder sb = new StringBuilder("<table width='100%' border='1' color='black' bgcolor='#C0C0C0'>");
    List<Announcement> list = model.getAnnouncements();
    List<Cube> cubes = model.getCubes();

    sb.append("<tr>");
    sb.append("<td bgcolor='#FFFACD'>");
    sb.append("<B>TASKS</B>");
    sb.append("</td>");
    for (Cube cube : cubes)
    {
      sb.append("<td bgcolor='#FFFACD'>");
      sb.append("<b>" + cube.getName() + "</b>");
      sb.append("<BR>" + cube.getUuid() + "");
      sb.append("</td>");
    }
    sb.append("</tr>");
    for (Announcement ar : list)
    {
      UUID uuid = ar.getUuid();
      sb.append("<tr>");
      sb.append("<td>");
      sb.append("" + uuid + "");
      sb.append("<br>" + ar.getDisplayText() + "");
      sb.append("<br>" + ar.getSayText() + "");
      sb.append("</td>");

      for (Cube cube : cubes)
      {
        sb.append("<td>");
        List<Announcement> aList = cube.getAnnouncements();
        for (Announcement a : aList) {
          sb.append("<B>*</B>");
          if (a.getPlayed()) {
            sb.append("<br><font color='green'>played</font>");
          } else {
            sb.append("<br><font color='red'>queued</font>");
          }
        }
        sb.append("</td>");
      }

      sb.append("</tr>");
    }
    sb.append("</table>");
    setText(sb.toString());
  }

}
