# MRT3216-2018-PowerUp

#### **NetworkTable Key Values**
These values need to only be **read** into the dashboard:  
- **elevatorHeight**: 
  - current height of the elevator
- **popper**: EXTENDED or RETRACTED
  - current status of the popper
- **pincher**: OPEN or CLOSED
  - current status of the pincher
- **gear**: HIGH or LOW
  - current transmission gear
- **rightDriveEncoderDistance**: inches
  - distance traveled by the right encoder
- **leftDriveEncoderDistance**: inches
  - distance traveled by the left encoder
- **rangeFinderDistance**: inches
  - distance to rangefinder without smoothing
- **rangeFinderAverageDistance**: inches
  - distance to rangefinder with smoothing
- **drive/navx/yaw**: Gyro
  - Assumes that you already have a / after SmartDashboard
- **timer**: timer
  - Assumes that you already have a / after SmartDashboard

These values need to be **read and set** in the dashboard:<br>

**ALL MODES**
- **bot**: TESTBOARD, BLUEFISH, MAINBOT
  - which bot is the code on  
- **kp**: 0.00-0.1 in 0.005 increments
  - correction factor for drive straight code
  
**Autonomous**
- **Autonomous**: CROSS_LINE or ???  
  - autonomous mode
- **autonomousRangefinderDistance**: 0-100 cm  
  - point at which robot stops due to rangefinder distance
- **medianSmoothingReadings**: 1-21 in increments of 2  
  - readings that will be used to smooth rangefinder distance 
  
**Teleop**
- **deadzone**: 0-1 in 0.5 increment  
  - deadzone in joysticks     
- **climbArmSpeed**: 0.00-0.1 in 0.02 increments
  - speed at which the robot climbs
- **speed**: 0-2
  - speed multiplier  
- **sensitivity**  
  - speed scale coefficent  
- **slow**: 0-75%  
  - speed multiplier in slow mode
  



Ip's and passwords: https://docs.google.com/document/d/1gnbhW8H-TXnGVFWnfqmTPNoPVJzINSkYyQ2_zQQK6Qc/edit?usp=sharing
