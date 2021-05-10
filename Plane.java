
/**
 * A class that represents a plane.
 *
 * @author Deborah Maidoh
 * @version 1.0
 */
public class Plane implements Comparable<Plane>
{
  private int arrivalTime;
  private int planeNumber;
  private boolean isTakingOff;
  private int fuelAmount;

  public Plane(int arrivalTime, int planeNumber,
                boolean isTakingOff, int fuelAmount)
  {
    this.arrivalTime = arrivalTime;
    this.planeNumber = planeNumber;
    this.isTakingOff = isTakingOff;
    this.fuelAmount = fuelAmount;
  }//end constructor

  public int compareTo(Plane otherPlane)
  {
    if(fuelAmount < otherPlane.fuelAmount)
      return -1;
    else if(fuelAmount == otherPlane.fuelAmount)
      return 0;
    else
      return 1;
  }


  public int getArrivalTime()
  {
    return arrivalTime;
  }//end getArrivalTime

  public int getPlaneNumber()
  {
    return planeNumber;
  }//end getPlaneNumber

  public boolean getIsTakingOff()
  {
    return isTakingOff;
  }

  public int getFuelAmount()
  {
    return fuelAmount;
  }
}//end plane class
