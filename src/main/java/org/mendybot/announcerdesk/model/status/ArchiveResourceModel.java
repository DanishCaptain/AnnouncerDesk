package org.mendybot.announcerdesk.model.status;

import java.util.ArrayList;

public abstract class ArchiveResourceModel
{
  private ArrayList<ArchiveResourceChangeListener> listeners = new ArrayList<>();

  public void addArchiveResourceChangeListener(ArchiveResourceChangeListener lis)
  {
    listeners.add(lis);
  }

  public void removeArchiveResourceChangeListener(ArchiveResourceChangeListener lis)
  {
    listeners.remove(lis);
  }

  protected void notifyUpdate()
  {
    for (ArchiveResourceChangeListener listener : listeners)
    {
      listener.archiveResourcesUpdated();
    }
  }

}
