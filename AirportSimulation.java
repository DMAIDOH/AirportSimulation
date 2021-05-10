/**
    A class containing a queue that simulates a waiting line of airplanes
    waiting to land on or take off a single runway

    @author Deborah Maidoh
    @version 1.0
*/
import java.io.*;
public class AirportSimulation
{
  private QueueInterface<Plane> line;
  private PriorityQueueInterface<Plane> landingLine;
  private int totalLandings;
  private int totalTakeOffs;
  private int totalTimeWaitedLanding;
  private int totalTimeWaitedTakeOff;
  int duration;
  double takeOffProbability;
  double landingProbability;
  int maxFuel;

  public AirportSimulation()
  {
    line  = new LinkedQueue<Plane>();
    landingLine = new LinkedPriorityQueue<Plane>();
  }//end default constructor

  /** Task: Simulates a waiting line of planes(taking-off or landing) with one runway.
   *  @param duration  the number of minutes to simulate
   *  @param takeOffProbability  a real number between 0 and 1, and the
   *                             probability that a new plane is ready for takeoff at
   *                             a given time
   *  @param landingProbability  a real number between 0 and 1, and the
   *                             probability that a new plane lands at
   *                             a given time
   *  @param maxFuel  the maximum amount of fuel a plane can be given for
   *                             initialization */
  public void simulate(int duration, double takeOffProbability,
                        double landingProbability, int maxFuel)
  {
    //int fuelAmount= 0; //amount of fuel remaining

    for(int clock = 0; clock < duration; clock++)
    {
      if(Math.random() < landingProbability)
      {
        totalLandings++;
        int fuel = (int)(Math.random() * maxFuel + 1);
        Plane nextLanding = new Plane(clock, totalLandings, false, fuel);
        landingLine.add(nextLanding);
        System.out.println("Flight " + nextLanding.getPlaneNumber()
                         + " lands at " + clock
                         + ". Amount of fuel is " + fuel);
      }//end if

      if(Math.random() < takeOffProbability)
      {
        totalTakeOffs++;
        int fuel = (int)(Math.random() * maxFuel + 1);
        Plane nextTakeOff = new Plane(clock, totalTakeOffs, true, fuel);
        line.enqueue(nextTakeOff);
        System.out.println("Flight " + nextTakeOff.getPlaneNumber()
                         + " takes off at " + clock
                         + ". Amount of fuel is " + fuel);
      }//end if

      //if(fuelAmount > 0)
        //fuelAmount--;


      if(!landingLine.isEmpty())
      {
        Plane nextPlane = landingLine.remove();
        int fuelAmount = nextPlane.getFuelAmount() - 1;
        int timeWaitedLanding = clock - nextPlane.getArrivalTime();
        totalTimeWaitedLanding = totalTimeWaitedLanding + timeWaitedLanding;
        System.out.println("Flight " + nextPlane.getPlaneNumber() +
                            " landed at " + clock +
                              ", with " + nextPlane.getFuelAmount() + " fuel left");
      System.out.println("time waited to land " + timeWaitedLanding);                        
      System.out.println("total time waited to land " + totalTimeWaitedLanding);
      }

      else if(!line.isEmpty())
      {
        Plane nextPlane = line.dequeue();
        int fuelAmount = nextPlane.getFuelAmount() - 1;
        int timeWaitedToTakeOff = clock - nextPlane.getArrivalTime();
        totalTimeWaitedTakeOff = totalTimeWaitedTakeOff + timeWaitedToTakeOff;
        System.out.println("Flight " + nextPlane.getPlaneNumber() +
                            " waiting  " + clock +
                              ", with " + nextPlane.getFuelAmount() + " left");
        System.out.println("time waited take off " + timeWaitedToTakeOff);
        System.out.println("total time waited take off " + totalTimeWaitedTakeOff);
      }

    }//end for
  }//end simulate method

  public void displayResults()
  {
    System.out.println();
    System.out.println("Total number of takeoffs = " + totalTakeOffs);
    double averageTimeWaitedTakeOff = ((double)totalTimeWaitedTakeOff) / totalTakeOffs;
    System.out.println("Average waiting time = " + averageTimeWaitedTakeOff);

    System.out.println("Total number of landings = " + totalLandings);
    double averageTimeWaitedLand = ((double)totalTimeWaitedLanding) / totalLandings;
    System.out.println("Average waiting time = " + averageTimeWaitedLand);

    System.out.println("total time waited take off " + totalTimeWaitedTakeOff);
    System.out.println("total time waited to land " + totalTimeWaitedLanding);
    System.out.println("total landings " + totalLandings);
    System.out.println("total take offs " + totalTakeOffs);


  }//end displayResults method

  public static void main(String [] args)
  {
    try
    {
      AirportSimulation a = new AirportSimulation();
      a.duration = Integer.parseInt(args[0]);
      a.takeOffProbability = Double.parseDouble(args[1]);
      a.landingProbability = Double.parseDouble(args[2]);
      a.maxFuel = Integer.parseInt(args[3]);
      a.simulate(a.duration, a.takeOffProbability, a.landingProbability, a.maxFuel);
      a.displayResults();
    }
    catch(Exception e)
    {
      System.out.println("Index out of bounds");
    }
  }//end main
}//end AirportSimulation
