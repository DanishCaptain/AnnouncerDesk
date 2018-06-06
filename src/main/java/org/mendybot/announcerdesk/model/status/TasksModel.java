package org.mendybot.announcerdesk.model.status;

import org.mendybot.announcer.common.model.dto.Announcement;
import org.mendybot.announcer.common.model.dto.Cube;

import java.util.ArrayList;
import java.util.List;

public class TasksModel extends ArchiveResourceModel
{
  private ArrayList<Announcement> announcements = new ArrayList<>();
  private CubeModel cModel;

  public TasksModel(CubeModel cModel)
  {
    this.cModel = cModel;
  }

  public void init(List<Announcement> a)
  {
    this.announcements.clear();
    this.announcements.addAll(a);
    notifyUpdate();
  }
  
  public List<Announcement> getAnnouncements()
  {
    return announcements;
  }

  public List<Cube> getCubes()
  {
    return cModel.getCubes();
  }

}
