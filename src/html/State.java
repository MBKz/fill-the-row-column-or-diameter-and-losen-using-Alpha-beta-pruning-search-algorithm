package html;

import java.util.LinkedList;
import java.util.List;

public class State {

     int size ;
     char[][] grid;
     int R = -1;
     int C = -1;

    public State(int size) {
        this.size = size;
        grid = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public State(State state) {
        this.size = state.size;
        grid = new char[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(state.grid[i], 0, grid[i], 0, size);
        }

    }

    public void fill(int x, int y) {
        if (grid[x][y] == ' ') {
            grid[x][y] = 'X';
        }
        this.R = x;
        this.C = y;
    }

    public List<State> allNextStates() {
        List<State> nextBoards = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == ' ') {
                    State nextBoard = new State(this);
                    nextBoard.fill(i, j);
                    nextBoards.add(nextBoard);
                }
            }
        }
        return nextBoards;
    }

    public int evaluate(String type) {

        int counter=0 , filled=0;
        // rows
        for(int i=0;i<this.size;i++){
            for(int j=0;j<this.size;j++){
                if(this.grid[i][j] == 'X') filled++ ;
            }
            if(filled == this.size-1) counter ++ ;
            filled = 0;
        }

        //colom
        for(int i=0;i<this.size;i++){
            for(int j=0;j<this.size;j++){
                if(this.grid[j][i] == 'X') filled++ ;
            }
            if(filled == this.size-1) counter ++ ;
            filled = 0;
        }

        //diam\
        for(int i=0;i<this.size;i++){
            if(this.grid[i][i] == 'X') filled++ ;
        }
        if(filled == this.size-1) counter ++ ;
        filled = 0;

        //diam /
        for(int i=0;i<this.size;i++){
            if(this.grid[i][this.size-i-1] == 'X') filled++ ;
        }
        if(filled == this.size-1) counter ++ ;

        if(type == "computer")
            return counter;
        else
            return -1*counter;

    }

    public boolean lost() {
        int row = this.R;
        int col = this.C;
        //check row
        List<Character> collected_row = new LinkedList<>();
        for (int c = 0; c < size; c++) {
            collected_row.add(grid[row][c]);
        }
        if (full(collected_row)) {
            return true;
        }
        //check row
        List<Character> collected_col = new LinkedList<>();
        for (int r = 0; r < size; r++) {
            collected_col.add(grid[r][col]);
        }
        if (full(collected_col)) {
            return true;
        }
        // check diag \
        if (row == col) {
            List<Character> collected_d1 = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                collected_d1.add(grid[i][i]);
            }
            if (full(collected_d1)) {
                return true;
            }
        }

        // check diag /
        if (row == (size - (col + 1))) {
            List<Character> collected_d1 = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                collected_d1.add(grid[i][size - (i + 1)]);
            }
            if (full(collected_d1)) {
                return true;
            }
        }
        return false;
    }

    private boolean full(List<Character> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != 'X') {
                return false;
            }
        }
        return true;
    }

    public void print() {
        for(int i=0;i<this.size;i++){
            for(int j=0;j<this.size;j++){
                if(grid[i][j] == ' ') System.out.print("_"+ " ");
                else System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }

}
