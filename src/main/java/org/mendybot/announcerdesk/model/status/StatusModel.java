package org.mendybot.announcerdesk.model.status;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.mendybot.announcer.common.engine.EngineClient;
import org.mendybot.announcer.common.model.dto.Archive;
import org.mendybot.announcer.common.model.dto.ArchiveResource;
import org.mendybot.announcer.common.model.dto.Cube;
import org.mendybot.announcer.common.model.dto.StatusResponse;
import org.mendybot.announcer.fault.ExecuteException;

public class StatusModel extends TimerTask
{
  private EngineClient client;
  private ImageModel iModel;
  private SoundModel sModel;
  private CubeModel cModel;

  public StatusModel()
  {
    UUID uuid = UUID.fromString("ddc9f13a-fe3c-4fe9-8382-7ec1b523228f");
    client = new EngineClient();

    cModel = new CubeModel();
    iModel = new ImageModel(cModel);
    sModel = new SoundModel(cModel);
    
    Timer timer = new Timer(true);
    timer.scheduleAtFixedRate(this, 0, 10*1000);    
  }

  public ImageModel getImageModel()
  {
    return iModel;
  }
  
  public SoundModel getSoundModel()
  {
    return sModel;
  }
  
  public CubeModel getCubeModel()
  {
    return cModel;
  }
  
  @Override
  public void run()
  {
    try
    {
      StatusResponse r = client.sendStatus();
      List<Cube> cubes = r.getCubes();
//    System.out.println(cubes);
      Archive a = r.getArchive();
      List<ArchiveResource> images = a.getImageFiles();
//      System.out.println(images);
      List<ArchiveResource> sounds = a.getSoundFiles();
//      System.out.println(sounds);
      iModel.init(images);
      sModel.init(sounds);
      cModel.init(cubes);
      
    }
    catch (ExecuteException e)
    {
      //TODO: msg
      System.out.println(e.getMessage());
    }
  }
  
}
