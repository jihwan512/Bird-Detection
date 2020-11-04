# Bird-Detection

## 개발목적
  + 현재 제주도에서 사용하는 조류 퇴치용 폭음기의 소음 공해 최소화
  + 새 객체를 탐지하였을 때만 새를 쫓을 수 있는 시스템 개발

------------

## 작품내용
  + 라즈베리파이와 카메라 및 Yolo 라이브러리로 새 탐지 
  + 탐지 내용 Firebase에 저장
  + 새 객체 탐지 시 부저 및 LED 작동
  + 안드로이드 애플리케이션 알람 및 제어 기능

## 구성도
<img src="https://user-images.githubusercontent.com/43696800/96970478-b6985100-154e-11eb-8d17-8777173cdb23.png">

------------

## 개발결과
  + 장거리에서 새 탐지 가능
  + LED 및 부저로 퇴치할 수 있는 시스템
  + 안드로이드 애플리케이션 알람 기능

------------

## 개발환경
  + S/W
    + Yolo4, Tensorflow Lite, Android Studio, Python, Firebase, PHP
  + H/W
    + Raspberry pi 4 + Pi camera
    + Arduino(LoRa + esp32) 

------------

## 데모
  + 조류 탐지 데모
  <img src="https://user-images.githubusercontent.com/43876552/98137112-fa069e00-1f04-11eb-995c-04ed75138c7a.gif">
  <video width="320" height="240" controls>
  <source src="https://youtu.be/OvVLnwNU-vY" type="video/mp4" autoplay>
  </video>
  + 사용자 제어 데모
  <img src="https://user-images.githubusercontent.com/43876552/98137377-4225c080-1f05-11eb-8aba-d87a202a609c.gif">
