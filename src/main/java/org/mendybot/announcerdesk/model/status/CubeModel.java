package org.mendybot.announcerdesk.model.status;

import java.util.ArrayList;
import java.util.List;

import org.mendybot.announcer.common.model.dto.Cube;

public class CubeModel
{
  private ArrayList<CubeChangeListener> listeners = new ArrayList<>();
  private ArrayList<Cube> cubes = new ArrayList<>();

  public void init(List<Cube> cubes)
  {
    this.cubes.clear();
    this.cubes.addAll(cubes);
    notifyUpdate();
  }

  public List<Cube> getCubes()
  {
    return cubes;
  }

  public void addCubeChangeListener(CubeChangeListener lis)
  {
    listeners.add(lis);
  }

  public void removeCubeChangeListener(CubeChangeListener lis)
  {
    listeners.remove(lis);
  }

  protected void notifyUpdate()
  {
    for (CubeChangeListener listener : listeners)
    {
      listener.cubesUpdated();
    }
  }

}
