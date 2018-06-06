package org.mendybot.announcerdesk.model.announcement;

import org.mendybot.announcer.common.model.dto.ArchiveResource;
import org.mendybot.announcerdesk.model.status.ArchiveResourceChangeListener;
import org.mendybot.announcerdesk.model.status.SoundModel;

import java.util.List;

public class AnnouncementModel
{
  private final SoundModel sModel;

  public AnnouncementModel(SoundModel sModel)
  {
    this.sModel = sModel;
    init();
  }

  private void init()
  {
  }

  public void addArchiveResourceChangeListener(ArchiveResourceChangeListener lis) {
    sModel.addArchiveResourceChangeListener(lis);
  }

  public void removeArchiveResourceChangeListener(ArchiveResourceChangeListener lis) {
    sModel.removeArchiveResourceChangeListener(lis);
  }

  public List<ArchiveResource> getSounds() {
    return sModel.getSounds();
  }

  /*
  public synchronized void addServersListener(ServersListener lis)
  {
    for (Server s : servers)
    {
      lis.addNew(s);
    }
    serversListeners.add(lis);
  }

  public void removeServersListener(ServersListener lis)
  {
    serversListeners.remove(lis);
  }

  public synchronized void addRequestStatusListener(RequestStatusListener lis)
  {
    requestStatusListeners.add(lis);
  }

  public void removeRequestStatusListener(RequestStatusListener lis)
  {
    requestStatusListeners.remove(lis);
  }

  public void setRequestStatus(List<String> status)
  {
    for (RequestStatusListener lis : requestStatusListeners) {
      lis.newRequestStatus(status);
    }
  }
*/
}
