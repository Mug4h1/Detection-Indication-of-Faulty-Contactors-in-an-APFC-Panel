#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>#include <Wire.h> 
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2);

#ifdef ESP32
#pragma message(THIS EXAMPLE IS FOR ESP8266 ONLY!)
#error Select ESP8266 board.
#endif

ESP8266WebServer server(80); // 80 is the port number

const char* ssid = <Wifi Name>;
const char* password = <Password>;

String data, message;

int pc, cc, tmp;

static bool inputs_received;

void Data(String data)
{
  server.send(200, "text/html", data);
}

void setup() {

  Serial.begin(115200);
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED)delay(500);

  Serial.print(WiFi.localIP());

  lcd.clear();
  lcd.begin();
  //pinMode(12, INPUT);
  //pinMode(13, INPUT);

  server.begin();

}

void loop()
{
  inputs_received = false;

  // Read the data received on the pins 15 & 16
  pc = digitalRead(15);
  cc = digitalRead(16);
  message = "";
  data = "";
  
   // Determine the status of both the contactors
   if(pc == 0 && cc == 0)
   {
     data = String(pc) + String(cc);
     tmp = 1;
   }
   else if(pc == 1 && cc == 1)
   {
     data = String(pc) + String(cc);
     tmp = 2;
   }   
   else if(pc == 1 && cc == 0)
   {
     data = String(pc) + String(cc);
     tmp = 3;
   }


   // Store the status of the contactors in a variable    
   switch(tmp){
      case 1:
        message = " PC: " + String(pc) + ", CC: " + String(cc) + ", Contactor 1: OFF";
        break;
      case 2:
        message = " PC: " + String(pc) + ", CC: " + String(cc) + ", Contactor 1: Working";
        break;
      case 3:
        message = " PC: " + String(pc) + ", CC: " + String(cc) + ", Contactor 1: Not Working";
        break;
    }

   // Print the variable on LCD screen
   static bool printed_message = false;
   if(!printed_message) {
     lcd.clear();
     lcd.print(message);
     printed_message = true;
   }

   // Scroll the message being displayed on the LCD with delay of 0.5 sec
   for (int positionCounter = 0; positionCounter < 20; positionCounter++) {
     // scroll one position left:
     lcd.scrollDisplayLeft();
     // wait a bit:
     delay(500);
   }

   static unsigned long start_time = 0;
   if(millis() - start_time > 2000) {
     start_time = millis();
     printed_message = false;
     inputs_received = false;
   }

 // Send data on to "/data" url
 server.on("/data", []() {
   Data(data);
 });
  
 // Collect and display data every 1 sec
 server.handleClient();
 delay(1000);
}
