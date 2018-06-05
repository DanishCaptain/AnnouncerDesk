package org.mendybot.announcerdesk.view.request;

import java.awt.Color;

public class DisplayColor
{
  private String name;
  private Color color;

  public DisplayColor(String name, Color color)
  {
    this.name = name;
    this.color = color;
  }

  public Color getColor()
  {
    return color;
  }
  
  @Override
  public String toString()
  {
    return name;
  }
}
