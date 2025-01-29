package com.rover.Rover.service;

import com.rover.Rover.model.Rover;
import com.rover.Rover.utils.enums.RoverDirection;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoverService {
    private final int MAX_Y = 10;
    private final int MAX_X = 10;
    private Rover rover;
    private final Rover[][] arr = new Rover[MAX_Y][MAX_X];
    private final char[] directions = {'N', 'E', 'S', 'W'};
    private int pointer;


    public void setRover(Rover rover){
        this.rover = rover;
        setBorders();
        arr[rover.getPosY()][rover.getPosX()] = rover;
        setPointerToDirection();
    }

    private void setBorders() {
        if (rover.getPosX() > MAX_X-1){
            rover.setPosX(MAX_X-1);
        }
        if (rover.getPosY()> MAX_Y-1){
            rover.setPosY(MAX_Y-1);
        }
        if (rover.getPosX() < 0){
            rover.setPosX(0);
        }
        if (rover.getPosY() < 0){
            rover.setPosY(0);
        }
    }

    private void setPointerToDirection() {
        if(RoverDirection.N.compareTo(rover.getRoverDirection())==0){
            this.pointer = 0;
        } else if (RoverDirection.E.compareTo(rover.getRoverDirection())==0){
            this.pointer = 1;
        } else if(RoverDirection.S.compareTo(rover.getRoverDirection())==0){
            this.pointer = 2;
        } else if(RoverDirection.W.compareTo(rover.getRoverDirection())==0){
            this.pointer = 3;
        }
    }

    public Rover setRoverMovement(String directions){
        char[] directionsCharList;
        directionsCharList = directions.toUpperCase().toCharArray();
        for (int i = 0; i < directionsCharList.length; i++) {
            switch (directionsCharList[i]){
                case 'F':
                    moveRoverForward();
                    break;
                case 'B':
                    moveRoverBack();
                    break;
                case 'L':
                    turnRoverLeft();
                    break;
                case 'R':
                    turnRoverRight();
                    break;
                default:
                    break;
            }
        }
        return this.rover;
    }

    private void moveRoverForward() {
        switch (rover.getRoverDirection()){
            case N: rover.setPosY(rover.getPosY() - 1); break;
            case S: rover.setPosY(rover.getPosY() + 1); break;
            case E: rover.setPosX(rover.getPosX() + 1); break;
            case W: rover.setPosX(rover.getPosX() - 1); break;
        }
        setRover(this.rover);
    }

    private void moveRoverBack() {
        switch (rover.getRoverDirection()){
            case N: rover.setPosY(rover.getPosY() + 1); break;
            case S: rover.setPosY(rover.getPosY() - 1); break;
            case E: rover.setPosX(rover.getPosX() - 1); break;
            case W: rover.setPosX(rover.getPosX() + 1); break;
        }
        setRover(this.rover);
    }

    private void turnRoverRight() {
        this.pointer +=1;
        if (this.pointer > 3){
            this.pointer = 0;
        }
        setRoverDirection();
    }

    private void turnRoverLeft() {
        this.pointer -=1;
        if (this.pointer<0){
            this.pointer = 3;
        }
        setRoverDirection();
    }

    private void deletePastPosition() {
        arr[rover.getPosY()][rover.getPosX()] = null;
    }

    private void setRoverDirection(){
        if (this.pointer == 0){
            rover.setRoverDirection(RoverDirection.N);
        }else if (this.pointer == 1){
            rover.setRoverDirection(RoverDirection.E);
        } else if (this.pointer == 2){
            rover.setRoverDirection(RoverDirection.S);
        } else if (this.pointer == 3){
            rover.setRoverDirection(RoverDirection.W);
        }
    }





}
