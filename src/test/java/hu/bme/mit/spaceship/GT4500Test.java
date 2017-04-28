package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private TorpedoStore mockPrimary;
  private TorpedoStore mockSecondary;
  private GT4500 ship;

  @Before
  public void init(){
    mockPrimary = mock(TorpedoStore.class);
    mockSecondary = mock(TorpedoStore.class);
    this.ship = new GT4500(mockPrimary, mockSecondary);
  }

  @Test
  public void fireTorpedos_Single_Success(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(true);

    // Act
    //boolean result = ship.fireTorpedos(FiringMode.SINGLE);
    ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    //assertEquals(true, result);
    verify(mockPrimary,times(1)).fire(1);
  }

  @Test
  public void fireTorpedos_All_Success(){
    // Arrange
    when(mockPrimary.fire(1) && mockSecondary.fire(1)).thenReturn(true);

    // Act
    //boolean result = ship.fireTorpedos(FiringMode.ALL);
    ship.fireTorpedos(FiringMode.ALL);

    // Assert
    //assertEquals(true, result);
    verify(mockPrimary,times(1)).fire(1);
    verify(mockSecondary,times(1)).fire(1);
  }

}
