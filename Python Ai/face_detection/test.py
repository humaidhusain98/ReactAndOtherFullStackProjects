import cv2
camera = cv2.VideoCapture(0)
print(camera.isOpened()) # False
isread,frame= camera.read() # (False, None)
cv2.imshow("Face Detector",frame)
cv2.waitKey()
