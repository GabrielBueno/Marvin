#include <SoftwareSerial.h>

const int BT_RX = 6;
const int BT_TX = 7;

const int MOTOR_ESQ_OUT1 = 10;
const int MOTOR_ESQ_OUT2 = 11;
const int MOTOR_DIR_OUT1 = 12;
const int MOTOR_DIR_OUT2 = 13;

SoftwareSerial HC05(BT_RX, BT_TX);

void cmd(unsigned char);

void setup() {
  pinMode(MOTOR_ESQ_OUT1, OUTPUT);
  pinMode(MOTOR_ESQ_OUT2, OUTPUT);
  pinMode(MOTOR_DIR_OUT1, OUTPUT);
  pinMode(MOTOR_DIR_OUT2, OUTPUT);
  
  Serial.begin(9600);
  HC05.begin(9600);
}

void loop() {
  while (Serial.available())
    HC05.write(Serial.read());
  
  while (HC05.available())
    cmd(HC05.read());
}

void cmd(unsigned char c) {
  
    // Motor da DIREITA ligado
    if (c & 0x01u) {
      if (c & 0x08u) {
        // Invertido
        Serial.println("Motor da direita invertido");
        
        digitalWrite(MOTOR_DIR_OUT1, LOW);
        digitalWrite(MOTOR_DIR_OUT2, HIGH);
      } else {
        // Normal
        Serial.println("Motor da direita normal");

        digitalWrite(MOTOR_DIR_OUT1, HIGH);
        digitalWrite(MOTOR_DIR_OUT2, LOW);
      }
    } else {
      // Motor da DIREITA desligado
      Serial.println("Motor da direita desligado");
    
      digitalWrite(MOTOR_DIR_OUT1, LOW);
      digitalWrite(MOTOR_DIR_OUT2, LOW);
    }

    // Motor da ESQUERDA ligado
    if (c & 0x10u) {
      if (c & 0x80u) {
        //Invertido
        Serial.println("Motor da esquerda invertido");

        digitalWrite(MOTOR_ESQ_OUT1, HIGH);
        digitalWrite(MOTOR_ESQ_OUT2, LOW);
      } else {
        // Normal
        Serial.println("Motor da esquerda normal");
        
        digitalWrite(MOTOR_ESQ_OUT1, LOW);
        digitalWrite(MOTOR_ESQ_OUT2, HIGH);
      }
    } else {
      // Motor da direita desligado
      Serial.println("Motor da esquerda desligado");
      
      digitalWrite(MOTOR_ESQ_OUT1, LOW);
      digitalWrite(MOTOR_ESQ_OUT2, LOW);
    }
}
