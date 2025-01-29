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
    private final Rover rover;
    private final Rover[][] arr = new Rover[MAX_Y][MAX_X];
    private final char[] directions = {'N', 'E', 'S', 'W'};
    private int pointer;


    public void setRover(){
        setBorders();
        arr[rover.getPosY()][rover.getPosY()] = rover;
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
        for (int i = 0; i < directionsCharList.length-1; i++) {
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
            case N:
                deletePastPosition();
                rover.setPosX(rover.getPosX()-1);
                setRover();
                break;
            case S:
                deletePastPosition();
                rover.setPosX(rover.getPosX()+1);
                setRover();
                break;
            case E:
                deletePastPosition();
                rover.setPosY(rover.getPosY()+1);
                setRover();
                break;
            case W:
                deletePastPosition();
                rover.setPosY(rover.getPosY()-1);
                setRover();
                break;
        }
    }

    private void moveRoverBack() {
        switch (rover.getRoverDirection()){
            case N:
                deletePastPosition();
                rover.setPosX(rover.getPosX()+1);
                setRover();
                break;
            case S:
                deletePastPosition();
                rover.setPosX(rover.getPosX()-1);
                setRover();
                break;
            case E:
                deletePastPosition();
                rover.setPosY(rover.getPosY()-1);
                setRover();
                break;
            case W:
                deletePastPosition();
                rover.setPosY(rover.getPosY()+1);
                setRover();
                break;
        }
    }

    private void turnRoverRight() {
        pointer +=1;
        if (pointer > 3){
            pointer = 0;
        }
    }

    private void turnRoverLeft() {
        pointer -=1;
        if (pointer<0){
            pointer = 3;
        }
    }

    private void deletePastPosition() {
        arr[rover.getPosY()][rover.getPosX()] = null;
    }






}
