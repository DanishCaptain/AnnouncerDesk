package org.mendybot.announcerdesk.model.status;

import java.util.ArrayList;
import java.util.List;

import org.mendybot.announcer.common.model.dto.ArchiveResource;
import org.mendybot.announcer.common.model.dto.Cube;

public class SoundModel extends ArchiveResourceModel
{
  private ArrayList<ArchiveResource> sounds = new ArrayList<>();
  private CubeModel cModel;

  public SoundModel(CubeModel cModel) {
    this.cModel = cModel;
  }

  public void init(List<ArchiveResource> sounds)
  {
    this.sounds.clear();
    this.sounds.addAll(sounds);
    notifyUpdate();
  }
  
  public List<ArchiveResource> getSounds()
  {
    return sounds;
  }

  public List<Cube> getCubes()
  {
    return cModel.getCubes();
  }

}
