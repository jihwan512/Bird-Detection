//raspi

#include "heltec.h"

#define BAND    433E6  //you can set band here directly,e.g. 868E6,915E6
String receivePacket = "";
String sendPacket = "";

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  Heltec.begin(true /*DisplayEnable Enable*/, true /*Heltec.LoRa Disable*/, true /*Serial Enable*/, true /*PABOOST Enable*/, BAND /*long BAND*/);
  Heltec.display -> clear();
  Heltec.display -> drawString(0, 0, "RASPI");
  Heltec.display -> display();
}

void loop() {
  // put your main code here, to run repeatedly:


  //Receive From raspi
  while(Serial.available()){
    sendPacket += (char)Serial.read();
  }

  if( sendPacket != 0){
    //send Packet to server
    LoRa.beginPacket();
    LoRa.setTxPower(14,RF_PACONFIG_PASELECT_PABOOST);
    LoRa.print("From raspi\n");
    LoRa.print(sendPacket);
    LoRa.endPacket();

    //display print
    displaySendPacket();
    sendPacket = "";
  }

  // Receive packet From server
  int packetSize = LoRa.parsePacket();
  if (packetSize) {
    // received a packet
//    Serial.print("Received packet '");
    // read packet
    receivePacket = "";
    while (LoRa.available()) {
      receivePacket += (char)LoRa.read();
    }
    Serial.print(receivePacket);
    // print RSSI of packet
//    Serial.print("' with RSSI ");
//    Serial.println(LoRa.packetRssi());
    displayReceivePacket();
    packetSize = false;
  }
}
void displayReceivePacket()
{
    Heltec.display -> setColor(BLACK);
    Heltec.display -> clear();
    Heltec.display -> setColor(WHITE);
    Heltec.display -> clear();
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
    Heltec.display -> drawString(0, 0, "send to server ");
    Heltec.display -> drawString(0, 10, sendPacket);
    Heltec.display -> display();
}
