/* 
  Check the new incoming messages, and print via serialin 115200 baud rate.
  
  by Aaron.Lee from HelTec AutoMation, ChengDu, China
  成都惠利特自动化科技有限公司
  www.heltec.cn
  
  this project also realess in GitHub:
  https://github.com/Heltec-Aaron-Lee/WiFi_Kit_series
*/

//server

#include "heltec.h"
#include <WiFi.h>
#include <HTTPClient.h>
#include <IOXhop_FirebaseESP32.h>

#define FIREBASE_HOST ".firebaseio.com/"
#define FIREBASE_AUTH ""
#define WIFI_SSID ""
#define WIFI_PASSWORD ""

#define BAND    433E6  //you can set band here directly,e.g. 868E6,915E6

WiFiServer server(80);
WiFiClient client2;


String host = "http://";

String receivePacket = "";
String sendPacket = "";

int buzzer = 0;
String data1 = "";
int isFirst = 0;

String _log[5];

void displayReceivePacket();
void displaySendPacket();
void displayWifiDisconnected();

void setup() {
    //WIFI Kit series V1 not support Vext control
  Heltec.begin(true /*DisplayEnable Enable*/, true /*Heltec.LoRa Disable*/, true /*Serial Enable*/, true /*PABOOST Enable*/, BAND /*long BAND*/);

  //connect to WiFi
  Serial.printf("Connecting to %s ", WIFI_SSID);
  Heltec.display -> clear();
  Heltec.display -> drawString(0, 0, "Connecting to" );
  Heltec.display -> drawString(70, 0, WIFI_SSID);
  Heltec.display -> display();
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  while (WiFi.status() != WL_CONNECTED) {
      delay(500);
      Serial.print(".");
      Heltec.display -> drawString(0, 15, ".");
      Heltec.display -> display();
  }
  Serial.println(" CONNECTED");
  Heltec.display -> drawString(0, 30, "CONNECTED");
  Heltec.display -> display();
  Serial.println(WiFi.localIP());
  server.begin();
  Heltec.display -> clear();
  Heltec.display -> drawString(0, 0, "SERVER");
  Heltec.display -> display();

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
}

void loop() {
  if(WiFi.status() != WL_CONNECTED){
    Serial.println("Wifi Disconnected");
    displayWifiDisconnected();
  }

  // Receive packet From LoRa
  int packetSize = LoRa.parsePacket();
  if (packetSize) {
    // read packet
    receivePacket = "";
    while (LoRa.available()) {
      receivePacket += (char)LoRa.read();
    }
    Serial.println(receivePacket);

    _log[0] = receivePacket.substring(0,21);
    _log[1] = receivePacket.substring(21,26);
    _log[2] = receivePacket.substring(28,33);

    // append a new value to /logs
    String name = Firebase.pushInt("logs/"+_log[1]+"/"+_log[2], 1);
    // handle error
    if (Firebase.failed()) {
        Serial.print("pushing /logs failed:");
        Serial.println(Firebase.error());  
        return;
    }
    Serial.print("pushed: /logs/");
    Serial.println(_log[1]+" "+_log[2]);

    HTTPClient http;
    String phpUrl = host+"?date="+_log[1]+"%20"+_log[2];
    Serial.println(phpUrl);
    http.begin(phpUrl);
    http.setTimeout(500);
    int httpCode = http.GET();
    if(httpCode > 0){
      if(httpCode == HTTP_CODE_OK){
        String payload = http.getString();
        Serial.println(payload);}
    }else{
      http.end();
    }
    displayReceivePacket();
    packetSize = 0;
  }
//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////
  sendPacket = String(Firebase.getInt("buzzerTest"));
  if(sendPacket == "1" || (sendPacket == "0" && isFirst == 1)){
    //send Packet to raspi 패킷 전송
    LoRa.beginPacket();
    LoRa.setTxPower(14,RF_PACONFIG_PASELECT_PABOOST);
//    LoRa.print("From server\n");
    LoRa.println(sendPacket);
    LoRa.endPacket();
    
  if(sendPacket == "1"){
    isFirst = 1;
  }else{
    isFirst = 0;
  }
    //display print
    displaySendPacket();
    sendPacket = "";
  }
}
void displayReceivePacket()
{
    Heltec.display -> setColor(BLACK);
    Heltec.display -> clear();
    Heltec.display -> setColor(WHITE);
    Heltec.display -> drawString(0, 0, "Received packet ");
    Heltec.display -> drawString(0, 10, receivePacket);
    Heltec.display -> display();
    delay(100);
}

void displaySendPacket()
{
    Heltec.display -> setColor(BLACK);
    Heltec.display -> clear();
    Heltec.display -> setColor(WHITE);
    Heltec.display -> drawString(0, 0, "send to raspi ");
    Heltec.display -> drawString(0, 10, sendPacket);
    Heltec.display -> display();
}
void displayWifiDisconnected()
{
    Heltec.display -> setColor(BLACK);
    Heltec.display -> clear();
    Heltec.display -> setColor(WHITE);
    Heltec.display -> drawString(0, 0, "Wifi Disconnected");
    Heltec.display -> display();
    delay(100);
}
