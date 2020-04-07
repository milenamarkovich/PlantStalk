// Define Pins!

// These are all the sensor values. 

int TEMPGRD = A0;
int PHOTO = A1;
int CONDMOIST = A2;
int CAPMOIST = A3;

// Initiate all sensor values to zero before reading. 

int TempGrdValue = 0;
int PhotoValue = 0;
int CondMoistValue = 0;
int CapMoistValue = 0;

const int PHOTO_OUT = 3;
const int TEMPGRD_OUT = 2;
const int CONDMOIST_OUT = 4;
const int CAPMOIST_OUT = 5; 

void setup() {

Serial.begin(9600); 
// Setting input pins
pinMode (TEMPGRD, INPUT);
pinMode (PHOTO, INPUT);
pinMode (CONDMOIST, INPUT);
pinMode (CAPMOIST, INPUT);

// Setting output pins
pinMode (PHOTO_OUT, OUTPUT);
pinMode (TEMPGRD_OUT, OUTPUT);
pinMode (CONDMOIST_OUT, OUTPUT);
pinMode (CAPMOIST_OUT, OUTPUT);

}

void loop() {

  //TestPhoto();
  TestTempGRD();
  //TestCondMoist();
  //TestCapMoist(); 
   
}

void TestPhoto (){//Testing the photo sensore values

  digitalWrite (PHOTO_OUT, HIGH);
  PhotoValue = analogRead(PHOTO);
  Serial.println("Photo Reading:");
  Serial.println(PhotoValue);
  digitalWrite (PHOTO_OUT, LOW);
  
  delay (500);   

}
void TestTempGRD (){//Testing the Ground Temp sensor values

  digitalWrite (TEMPGRD_OUT, HIGH);
  int reading = analogRead(TEMPGRD);
  
  //convert to from reading to voltage:
  float voltage = reading * 5.0;
  voltage /= 1024.0; 
  
  //convert from voltage to temp:
  float TempGrdValue = (voltage - 0.5) * 100;
  
  //print to serial monitor:
  Serial.println("Temp: GRD Reading:");
  Serial.println(TempGrdValue);
  digitalWrite (TEMPGRD_OUT, LOW);
  
  delay (500);   
}

//no longer using conductive sensor:
/*void TestCondMoist (){//Testing the Conductive Moisture sensor values

  digitalWrite (CONDMOIST_OUT, HIGH);
  CondMoistValue = analogRead(CONDMOIST);
  Serial.println("COND-Moisture Reading:");
  Serial.println(CondMoistValue);
  digitalWrite (CONDMOIST_OUT, LOW);
  
  delay (500);   
}*/

void TestCapMoist (){//Testing the Capacitive Moisture sensor values

  digitalWrite (CAPMOIST_OUT, HIGH);
  CapMoistValue = analogRead(CAPMOIST);
  Serial.println("CAP-Moisture Reading:");
  Serial.println(CapMoistValue);
  digitalWrite (CAPMOIST_OUT, LOW);
  
  delay (500);   
  
}
