
package aim4.sim;

import java.util.List;

import aim4.config.Debug;
import aim4.driver.ShoutAheadDriverAgent;
import aim4.map.BasicMap;
import aim4.map.GridMap;
import aim4.map.Road;
import aim4.map.SpawnPoint;
import aim4.map.SpawnPoint.SpawnSpec;
import aim4.map.lane.Lane;
import aim4.sim.AutoDriverOnlySimulator.AutoDriverOnlySimStepResult;
import aim4.vehicle.AutoVehicleSimView;
import aim4.vehicle.VehicleSimView;
import aim4.vehicle.VehicleSpec;
import aim4.vehicle.VehicleSpecDatabase;
import aim4.vehicle.VinRegistry;
import aim4.vehicle.BasicVehicle;
/**
 * This class represents a driving simulation where every car is autonomous and operating according to the
 * rule-based system outlined by the Shout-Ahead architecture. Reinforcement learning takes place during a 
 * run of this simulation. Evolutionary learning can be carried out by running this simulation repeatedly.
 *  
 *  
 *  
 * @author christopher.hawk
 */
public class ShoutAheadSimulator extends AutoDriverOnlySimulator implements Simulator {

	public ShoutAheadSimulator(BasicMap basicMap) {
		super(basicMap);
		//Spawn 4 vehicles at the end of each road. Each should be randomly assigned a destination (North, South, East, West)
		//not equal to it's origin. 
			
			//Spawn Northbound car
		   GridMap map = (GridMap) basicMap;
		   Road verticalRoad = map.getVerticalRoads().get(0);
		   Lane northBoundLane = verticalRoad.getLanes().get(0);

		   SpawnPoint spawnPoint = map.makeSpawnPoint(currentTime, northBoundLane);
		   VehicleSpec vehicleSpec = VehicleSpecDatabase.getVehicleSpecByName("COUPE");
		   SpawnSpec spawnSpec = new SpawnSpec(currentTime, vehicleSpec, verticalRoad);//hopefully destination is North
		   AutoVehicleSimView vehicle = (AutoVehicleSimView) makeVehicle(spawnPoint, spawnSpec);
		   ShoutAheadDriverAgent driverAgent = new ShoutAheadDriverAgent(vehicle, basicMap);
		   driverAgent.setSpawnPoint(spawnPoint);
		   driverAgent.setDestination(verticalRoad);
		   vehicle.setDriver(driverAgent);
		  
           VinRegistry.registerVehicle(vehicle); // Get vehicle a VIN number
           vinToVehicles.put(vehicle.getVIN(), vehicle);
        //should be using driver view?
           vehicle.setTargetVelocityWithMaxAccel(.5);
           vehicle.turnLeft(0.175);
         //  BasicVehicle downcast = (BasicVehicle)vehicle;
          
           
				//set dest 
				//set rules
			//Spawn East bound car
				//set dest
				//set rules
			//Spawn South bound car
				//set dest !=South
				//set rules
			//Spawn West bound car
				//set dest 
				//set rules
		}

	  // the main loop

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public synchronized AutoDriverOnlySimStepResult step(double timeStep) {
//	    if (Debug.PRINT_SIMULATOR_STAGE) {
//	      System.err.printf("--------------------------------------\n");
//	      System.err.printf("------SIM:spawnVehicles---------------\n");
//	    }
//	    spawnVehicles(timeStep);
//	    if (Debug.PRINT_SIMULATOR_STAGE) {
//	      System.err.printf("------SIM:provideSensorInput---------------\n");
//	    }
//	    provideSensorInput();
	    if (Debug.PRINT_SIMULATOR_STAGE) {
	      System.err.printf("------SIM:letDriversAct---------------\n");
	    }
	   //letDriversAct();//TODO: write SAVehicalSimView.act or SADriver.act???
//	    if (Debug.PRINT_SIMULATOR_STAGE) {
//	      System.err.printf("------SIM:letIntersectionManagersAct--------------\n");
//	    }
//	    letIntersectionManagersAct(timeStep);
	    if (Debug.PRINT_SIMULATOR_STAGE) {
	      System.err.printf("------SIM:communication---------------\n");
	    }
	    communication();//TODO: remove?
	    if (Debug.PRINT_SIMULATOR_STAGE) {
	      System.err.printf("------SIM:moveVehicles---------------\n");
	    }
	    moveVehicles(timeStep);//TODO:write SAVehicalSimView.move()
	    if (Debug.PRINT_SIMULATOR_STAGE) {
	      System.err.printf("------SIM:cleanUpCompletedVehicles---------------\n");
	    }
	    List<Integer> completedVINs = cleanUpCompletedVehicles();
	    currentTime += timeStep;
	    // debug
	    checkClocks();

	    return new AutoDriverOnlySimStepResult(completedVINs);
	  }
	
	  /**
	   * If this simulation includes communication among agents, allow agents to broadcast their intended actions to others. 
	   */
	  @Override
	  protected void communication() {
		 
	  }
	  
	//WHILE there is at least one car that has not reached its destination 
	
		//For each car
			
			//Get the set of rules that are for which the condition is true
			//Get the set of applicable rules that has the maximum weight
			//Randomly choose from among these rules
			//Perform the corresponding action (breaking and steering actions)
	
}
