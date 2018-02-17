# MRT3216-2018-PowerUp

#### **NetworkTable Key Values**
These values need to only be **read** into the dashboard:  
--Robot Status--
- **pincher**: OPEN or CLOSED
  - current status of the pincher
- **popper**: EXTENDED or RETRACTED
  - current status of the popper
- **elevatorHeight**: 
  - current height of the elevator
- **gear**: HIGH or LOW
  - current transmission gear
--Encoders--
- **rightDriveEncoderDistance**: inches
  - distance traveled by the right encoder
- **leftDriveEncoderDistance**: inches
  - distance traveled by the left encoder
--Rangefinder--
- **rangeFinderDistance**: inches
  - distance to rangefinder without smoothing
- **rangeFinderAverageDistance**: inches
  - distance to rangefinder with smoothing
--Match info--
- **timer**: in min:sec
  - Current time of the match
- **color**: red or blue
  - Current alliance
- **switchScale**: left or right (LR)
  - Alliance side for switch and scale <br>
--Gyro--
- **drive/navx/yaw**: Gyro
  - Current heading

These values need to be **read and set** in the dashboard:<br>

**ALL MODES**
- **bot**: TESTBOARD, BLUEFISH, MAINBOT
  - which bot is the code on  
- **kp**: 0.00-0.1 in 0.005 increments
  - correction factor for drive straight code
  
**Teleop**
--Control Settings--
- **deadzone**: 0-1 in 0.5 increment  
  - deadzone in joysticks
- **sensitivity**  
  - joystick sensitivity 
--Robot Settings--
- **climbArmSpeed**: 0.00-0.1 in 0.02 increments
  - speed at which the robot climbs
- **elevatorThreshold**: 0.00-1 in 0.1 increments
  - +/- distance that the elevator encoder is from specified height
  
**Autonomous**
- **startingPosition**: LEFT, CENTER, RIGHT  
  - starting position of robot
- **/autonomous/modes**: CROSS_LINE or ???  
  - autonomous mode
- **autonomousRangefinderDistance**: 0-100 cm  
  - point at which robot stops due to rangefinder distance
- **medianSmoothingReadings**: 1-21 in increments of 2  
  - readings that will be used to smooth rangefinder distance 



Ip's and passwords: https://docs.google.com/document/d/1gnbhW8H-TXnGVFWnfqmTPNoPVJzINSkYyQ2_zQQK6Qc/edit?usp=sharing
