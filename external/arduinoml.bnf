State:
    'State' ID
    '{'
        'on enter'
        '{'
            Actuator ("," Actuator)*
        '}'
    '}' Transition ('or' Transition)*

Transition:
    'transit to state ' state_id 'when' Sensor 'is' Signal ('and' Sensor 'is' Signal)*

Brick:
    (Actuator|Sensor) 'on' PIN 'with' Signal

Actuator:
    (Led | Buzzer) ID

Pin:
    'pin' int

Signal:
    'signal' ID

Led:
    'Led'

Buzzer:
    'Buzzer'

Sensor:
    Button ID

Button:
    'Button'


// Scenario 1

Led led1
Buzzer buz1

Button but1

State on
{
    actuators
    {
        Led 'led1' high, Buzzer 'buz1' high
    }, transit to state of when but1 is 
}

State of
{
    sensor
    {
        Led 'led1' low, Buzzer 'buz1' low
    }
}