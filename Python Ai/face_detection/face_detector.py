import cv2

#Load some pre-trained data on face frontals
trained_face_data=cv2.CascadeClassifier('/home/humaid/Desktop/Python Ai/face_detection/haarcascade_frontalface_default.xml')

# Choose an image to detect faces in
# img= cv2.imread('/home/humaid/Desktop/Python Ai/face_detection/RDJ.jpg')

# To capture video from webcam/ can take a video also
webcam = cv2.VideoCapture(0)

while True:
    ###Read the current frame
    successful_frame_read, frame = webcam.read()

    #     Must Convert to greyscale
    greyscaled_img= cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)

    # Detect faces
    face_coordinates = trained_face_data.detectMultiScale(greyscaled_img)

    # # Draw Rectangle
    for (x,y,w,h) in face_coordinates:
        cv2.rectangle(frame,(x,y),(x+w,y+h),(0,0,255),2)
    
    #Showing image
    cv2.imshow("Face Detector",frame)
    key=cv2.waitKey(1)

    if(key==81 or key ==113):
        break

webcam.release()


# key=cv2.waitKey(1)

# Must Convert to greyscale
# greyscaled_img= cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)


# Detect faces
# face_coordinates = trained_face_data.detectMultiScale(greyscaled_img)

# # print(face_coordinates)

# # Draw Rectangle
# for (x,y,w,h) in face_coordinates:
#     cv2.rectangle(img,(x,y),(x+w,y+h),(0,0,255),2)


# # Show image
# cv2.imshow("Face Detector",img)
# cv2.waitKey()

print("Code Completed")