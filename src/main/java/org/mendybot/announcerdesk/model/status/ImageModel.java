package org.mendybot.announcerdesk.model.status;

import java.util.ArrayList;
import java.util.List;

import org.mendybot.announcer.common.model.dto.ArchiveResource;
import org.mendybot.announcer.common.model.dto.Cube;

public class ImageModel extends ArchiveResourceModel
{
  private ArrayList<ArchiveResource> images = new ArrayList<>();
  private CubeModel cModel;
  
  public ImageModel(CubeModel cModel) {
    this.cModel = cModel;
  }

  public void init(List<ArchiveResource> images)
  {
    this.images.clear();
    this.images.addAll(images);
    notifyUpdate();
  }

  public List<ArchiveResource> getImages()
  {
    return images;
  }

  public List<Cube> getCubes()
  {
    return cModel.getCubes();
  }
}
