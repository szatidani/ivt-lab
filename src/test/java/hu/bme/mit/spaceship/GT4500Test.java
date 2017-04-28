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
      when(mockPrimary.isEmpty()).thenReturn(false);
      when(mockSecondary.isEmpty()).thenReturn(false);
    // Act
    //boolean result = ship.fireTorpedos(FiringMode.ALL);
    ship.fireTorpedos(FiringMode.ALL);

    // Assert
    //assertEquals(true, result);
    verify(mockPrimary,times(1)).fire(1);
    verify(mockSecondary,times(1)).fire(1);
  }

  @Test
    public void fireTorpedos_Primary_first_then_secondary(){
      when(mockPrimary.fire(1)).thenReturn(true);
      when(mockSecondary.fire(1)).thenReturn(true);
      when(mockSecondary.isEmpty()).thenReturn(false);
      when(mockPrimary.isEmpty()).thenReturn(false);

      ship.fireTorpedos(FiringMode.SINGLE);
      ship.fireTorpedos(FiringMode.SINGLE);

      verify(mockPrimary,times(1)).fire(1);
      verify(mockSecondary,times(1)).fire(1);
  }

    @Test
    public void fireTorpedos_Primary_first_then_Primary_again(){
        when(mockPrimary.fire(1)).thenReturn(true);
        when(mockSecondary.isEmpty()).thenReturn(true);
        when(mockPrimary.isEmpty()).thenReturn(false);

        ship.fireTorpedos(FiringMode.SINGLE);
        ship.fireTorpedos(FiringMode.SINGLE);

        verify(mockPrimary,times(2)).fire(1);
        verify(mockSecondary,times(0)).fire(1);
    }

    @Test
    public void fireTorpedos_Primary_isEmpty_Secondary_twice(){
        when(mockSecondary.fire(1)).thenReturn(true);
        when(mockPrimary.isEmpty()).thenReturn(true);
        when(mockSecondary.isEmpty()).thenReturn(false);

        ship.fireTorpedos(FiringMode.SINGLE);
        ship.fireTorpedos(FiringMode.SINGLE);

        verify(mockPrimary,times(0)).fire(1);
        verify(mockSecondary,times(2)).fire(1);
    }

    @Test
    public void fireLasers_Fail(){
        when(mockPrimary.isEmpty()).thenReturn(true);
        when(mockSecondary.isEmpty()).thenReturn(true);

        ship.fireLasers(FiringMode.SINGLE);

        verify(mockPrimary, times(0)).fire(1);
        verify(mockSecondary,times(0)).fire(1);

    }

}

