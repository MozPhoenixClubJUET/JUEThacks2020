# Hello  , This is Our Mask Detector Model We Call It Crafter's Mask Detector, Its a combined effort of team code crafters

from tensorflow.keras.applications.mobilenet_v2 import preprocess_input
from tensorflow.keras.preprocessing.image import img_to_array
from tensorflow.keras.models import load_model
from imutils.video import VideoStream
from playsound import playsound
from gtts import gTTS
import numpy as np
import argparse
import imutils
import time
import cv2
import os
import datetime as dt
import random
import pyttsx3
import winsound
import pygame
import urllib


proto_txt_path = os.path.sep.join([r"C:\Users\Ishaan\Desktop\Juet Hacks",
 "deploy.prototxt.txt"])
starter = os.path.sep.join([r"C:\Users\Ishaan\Desktop\Juet Hacks",
"starter.mp3"])
proceed = os.path.sep.join([r"C:\Users\Ishaan\Desktop\Juet Hacks",
"proceed.wav"])
sorry = os.path.sep.join([r"C:\Users\Ishaan\Desktop\Juet Hacks",
"sorry.wav"])
url='http://192.168.43.1:8080/shot.jpg'


pygame.mixer.init()
pygame.mixer.set_num_channels(8)
voice =pygame.mixer.Channel(2)
pygame.mixer.music.load(sorry)
sound =pygame.mixer.Sound(sorry)
sound1=pygame.mixer.Sound(proceed)


model_path = os.path.sep.join([r"C:\Users\Ishaan\Desktop\Juet Hacks",
"res10_300x300_ssd_iter_140000.caffemodel"])
model=os.path.sep.join([r"C:\Users\Ishaan\Desktop\Juet Hacks",
"mask_detector.model"])
face_detector = cv2.dnn.readNetFromCaffe(proto_txt_path, model_path)
mask_detector = load_model(model)


class VideoCamera(object):
    def __init__(self):
        self.cap = cv2.VideoCapture(0)
        self.lastTime=dt.datetime.now()
        self.currentTime=dt.datetime.now()


    def __del__(self):
        self.cap.release()

    def get_frame(self):
        # playsound(starter)
        while True :
            ret, frame = self.cap.read()
            # this code is for ip camera , if we want to integrate camera which is a seperate device from webcam
            # imgResp=urllib.request.urlopen(url)
            # imgNp=np.array(bytearray(imgResp.read()),dtype=np.uint8)
            # frame=cv2.imdecode(imgNp,-1)

            frame = imutils.resize(frame, width=500)
            (h, w) = frame.shape[:2]
            blob = cv2.dnn.blobFromImage(frame, 1.0, (300, 300), (104, 177, 123))

            face_detector.setInput(blob)
            detections = face_detector.forward()

            faces = []
            bbox = []
            results = []

            for i in range(0, detections.shape[2]):
                confidence = detections[0, 0, i, 2]

                if confidence > 0.5:
                    box = detections[0, 0, i, 3:7] * np.array([w, h, w, h])
                    (startX, startY, endX, endY) = box.astype("int")

                    face = frame[startY:endY, startX:endX]
                    face = cv2.cvtColor(face, cv2.COLOR_BGR2RGB)
                    face = cv2.resize(face, (224, 224))
                    face = img_to_array(face)
                    face = preprocess_input(face)
                    face = np.expand_dims(face, axis=0)

                    faces.append(face)
                    bbox.append((startX, startY, endX, endY))

            if len(faces) > 0:
                results = mask_detector.predict(faces)

            for (face_box, result) in zip(bbox, results):
                (startX, startY, endX, endY) = face_box
                (mask, withoutMask) = result

                label = ""

                if mask > withoutMask:

                    label = "Mask"
                    color = (0, 255, 0)
                    if voice.get_busy() == False:
                            voice.play(sound1)


                else:
                    label = "No Mask"
                    color = (0, 0, 255)
                    if voice.get_busy() == False:
                                 voice.play(sound)


                cv2.putText(frame, label, (startX, startY-10), cv2.FONT_HERSHEY_SIMPLEX, 0.45, color, 2)
                cv2.rectangle(frame, (startX, startY), (endX, endY), color, 2)
                self.cap.release;
                # cv2.imshow("Frame", frame)
                # key = cv2.waitKey(1) & 0xFF



            return frame
