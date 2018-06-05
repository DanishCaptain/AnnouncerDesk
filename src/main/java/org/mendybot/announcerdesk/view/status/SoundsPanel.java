package org.mendybot.announcerdesk.view.status;

import java.util.Date;
import java.util.List;

import org.mendybot.announcer.common.model.dto.ArchiveResource;
import org.mendybot.announcer.common.model.dto.Cube;
import org.mendybot.announcerdesk.model.status.ArchiveResourceChangeListener;
import org.mendybot.announcerdesk.model.status.SoundModel;

public class SoundsPanel extends ArchiveResourcesPanel implements ArchiveResourceChangeListener
{
  private static final long serialVersionUID = 5515087620533027430L;
  private SoundModel model;

  public SoundsPanel(SoundModel model)
  {
    this.model = model;
    model.addArchiveResourceChangeListener(this);
  }

  @Override
  public void archiveResourcesUpdated()
  {
    StringBuilder sb = new StringBuilder("<table width='100%' border='1' color='black' bgcolor='#C0C0C0'>");
    List<ArchiveResource> list = model.getSounds();
    List<Cube> cubes = model.getCubes();

    sb.append("<tr>");
    sb.append("<td bgcolor='#FFFACD'>");
    sb.append("<B>SOUNDS</B>");
    sb.append("</td>");
    for (Cube cube : cubes)
    {
      sb.append("<td bgcolor='#FFFACD'>");
      sb.append("<b>" + cube.getName() + "</b>");
      sb.append("<BR>" + cube.getUuid() + "");
      sb.append("</td>");
    }
    sb.append("</tr>");
    for (ArchiveResource ar : list)
    {
      String name = ar.getName();
      sb.append("<tr>");
      sb.append("<td>");
      sb.append("" + name + "");
      sb.append("<br>" + ar.getSize() + "");
      sb.append("<br>" + new Date(ar.getTs()) + "");
      sb.append("</td>");

      for (Cube cube : cubes)
      {
        sb.append("<td>");
        ArchiveResource cubeAr = cube.getArchive().lookupArchiveResource(name);
        if (cubeAr != null)
        {
          sb.append("<B>*</B>");
          if (ar.getSize() == cubeAr.getSize()) {
            sb.append("<br><font color='green'>" + cubeAr.getSize() + "</font>");
          } else {
            sb.append("<br><font color='red'>" + cubeAr.getSize() + "</font>");
          }
          if (ar.getTs() == cubeAr.getTs()) {
            sb.append("<br><font color='green'>" + new Date(cubeAr.getTs()) + "</font>");
          } else {
            sb.append("<br><font color='yellow'>" + new Date(cubeAr.getTs()) + "</font>");
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
