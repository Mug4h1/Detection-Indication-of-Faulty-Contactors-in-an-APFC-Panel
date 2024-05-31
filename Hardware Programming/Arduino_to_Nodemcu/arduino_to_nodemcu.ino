 #include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2);

String data, data1, data2, message1, message2;

int pc1, pc2, cc1, cc2;

static bool inputs_received;

void setup() {
  lcd.clear();
  lcd.begin();
  Serial.begin(9600);
}

void loop()
{
  pc1 = digitalRead(8);
  cc1 = digitalRead(12);
  pc2 = digitalRead(7);
  cc2 = digitalRead(4);
  
  message1 = "";
  message2 = "";
  data1 = "";
  data2 = "";
  
   if((pc1 == 0 && cc1 == 0) || (pc2 == 0 && cc2 == 0))
   {
     message1 = " PC1: " + String(pc1) + ", CC1: " + String(cc1) + ", Contactor 1: OFF";
     data1 = String(pc1) + String(cc1);

     message2 = " PC2: " + String(pc2) + ", CC2: " + String(cc2) + ", Contactor 2: OFF";
     data2 = String(pc2) + String(cc2);
   }
   else if((pc1 == 1 && cc1 == 1) || (pc2 == 1 && cc2 == 1))
   {
     message1 = " PC1: " + String(pc1) + ", CC1: " + String(cc1) + ", Contactor 1: Working";
     data1 = String(pc1) + String(cc1);

     message2 = " PC2: " + String(pc2) + ", CC2: " + String(cc2) + ", Contactor 2: Working";
     data2 = String(pc2) + String(cc2);
   }   
   if(pc1 == 1 && cc1 == 0)
   {
     message1 = " PC1: " + String(pc1) + ", CC1: " + String(cc1) + ", Contactor 1: Not Working";
     data1 = String(pc1) + String(cc1);

   }
   if(pc2 == 1 && cc2 == 0){
     message2 = " PC2: " + String(pc2) + ", CC2: " + String(cc2) + ", Contactor 2: Not Working";
     data2 = String(pc2) + String(cc2);
    }

   data = data1+data2;

   Serial.println(data);
    

   static bool printed_message = false;
   if(!printed_message) {
     lcd.clear();
     lcd.setCursor(0, 0);
     lcd.print(message1);
     lcd.setCursor(0, 1);
     lcd.print(message2);
     printed_message = true;
   }

   for (int positionCounter = 0; positionCounter < 30; positionCounter++) {
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

   delay(1000);
}
