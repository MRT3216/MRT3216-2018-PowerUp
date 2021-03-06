import libjevois as jevois
import cv2
import numpy as np

## Detect a cube and its edges with image processing using OpenCV in Python on JeVois
#
# @author Preston and Juice
# 
# @displayname Python OpenCV
# @videomapping GRAY 640 480 20.0 YUYV 640 480 20.0 JeVois PythonOpenCV
class PythonOpenCV:
	# ###################################################################################################vfc
	kernel = np.ones((5,5), np.uint8)
	## Constructor
	def __init__(self):
		# Instantiate a JeVois Timer to measure our processing framerate:
		self.timer = jevois.Timer("canny", 100, jevois.LOG_INFO)

	# ###################################################################################################
	## Process function with USB output
	def process(self, inframe, outframe):
		# Get the next camera image
		inimg = inframe.getCvBGR()

		# Start measuring image processing time (NOTE: does not account for input conversion time):
		self.timer.start()

		# eliminates noise from the object
		closing = cv2.morphologyEx(inimg, cv2.MORPH_CLOSE, self.kernel)

		# convert bgr to hsv
		hsv = cv2.cvtColor(closing, cv2.COLOR_RGB2HSV)

		# define range of yellow color in HSV
		lower_yellow = np.array([70, 120, 40])
		upper_yellow = np.array([110,255,255])

		# Threshold the HSV image to get only yellow colors
		mask = cv2.inRange(hsv, lower_yellow, upper_yellow)

		# Bitwise-And mask and original image 

		outimg = cv2.bitwise_and(inimg, inimg, mask= mask) 

		# Detect edges using the Canny algorithm from OpenCV:
		edges = cv2.Canny(outimg, 100, 200, apertureSize = 3)
		
		# Write frames/s info from our timer into the edge map (NOTE: does not account for output conversion time):
		fps = self.timer.stop()
		height, width = edges.shape
		
		# Convert our GRAY output image to video output format and send to host over USB:
		outframe.sendCvGRAY(edges)
