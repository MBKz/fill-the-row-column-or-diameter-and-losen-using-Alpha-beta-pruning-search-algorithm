package html;

import java.util.Scanner;

public class Game {

    State state = new State(3);
    int count = 0;

    public void play() {
        state.print();
        while (true) {
            getUserMove();
            state.print();
            if (state.lost()) {
                System.out.println("you lost");
                break;
            }
            System.out.println("computer turn :");
            ComputerStep();
//            System.out.println(count);
            state.print();
            if (state.lost()) {
                System.out.println("you Wins");
                break;
            }
        }

    }

    private void getUserMove() {
        Scanner s = new Scanner(System.in);
        int row;
        while (true) {
            System.out.print("Enter row: ");
            row = s.nextInt();
            if ((row >= 0) && (row <= state.size - 1)) {
                break;
            }
        }
        int col;
        while (true) {
            System.out.print("Enter column: ");
            col = s.nextInt();
            if ((col >= 0) && (col <= state.size - 1)) {
                break;
            }
        }
        state.fill(row, col);
    }

    private void ComputerStep() {
        Integer alpha = Integer.MIN_VALUE;
        Integer beta = Integer.MAX_VALUE;
        maxValue(state,true,alpha,beta);
    }


    int maxValue(State state,boolean stepOne,Integer alpha,Integer beta){
        State best = null;
        if(state.lost()){
            count++;
            return state.evaluate("computer");
        }
        int max = Integer.MIN_VALUE;
        for (State currentMove : state.allNextStates()){
            int NowMoveVal = minValue(currentMove,false,alpha,beta);
            if(NowMoveVal >= max){
                max = NowMoveVal;
                best = currentMove;
            }
            if(max > alpha){
                alpha = max;
            }
            if(alpha >= beta){
                break;
            }
        }
        if(stepOne){
            this.state = best;
        }
        return max;
    }

    int minValue(State state,boolean stepOne,Integer alpha,Integer beta){
        State best = null;
        if(state.lost()){
            count++;
            return state.evaluate("player");
        }
        int min = Integer.MAX_VALUE;
        for (State currentMove : state.allNextStates()){
            int NowMoveVal = maxValue(currentMove,false,alpha,beta);
            if(NowMoveVal < min){
                min = NowMoveVal;
                best = currentMove;
            }
            if(min < beta){
                beta = min;
            }
            if(alpha >= beta){
                break;
            }
        }
        if(stepOne){
            this.state = best;
        }
        return min;
    }


}
