package App;

import java.util.Random;

public class Generator {
    private int[][] grid;

    public int[][] generateGrid(){
        //Create the grid and fill it with basic values
        this.grid = new int[9][9];
        int k = 1;
        int n = 1;
        for(int i = 0; i<9; i++){
            k = n;
            for(int j = 0; j<9; j++){
                if(k > 9){
                    k = 1;
                }
                this.grid[i][j] = k;
                k++;
            }
            n = k+3;
            if(k == 10){
                n = 4;
            }
            if(n > 9){
                n = (n%9)+1;
            }
        }
        //Change some rows and cols in the grid to make it random
        Random rand = new Random();
        boolean z = true;
        int swaps = rand.nextInt(5) + 15;
        for(int i = 0; i < swaps; i++){
            int box = rand.nextInt(3);
            int a = rand.nextInt(3);
            int b;
            do{
                b = rand.nextInt(3);
            }while(a == b);
            //We do a swap of row, the na swap of col, etc ...
            if(z){
                this.swapRow(a + box*3, b + box*3);
                z = !z;
            }
            else{
                this.swapCol(a + box*3, b + box*3);
                z = !z;
            }
            
        }
        //Put some of the cells to 0 (45 out of 81)
        this.reduceGrid();
        return this.grid;
    }

    //Swap two rows within the same block
    private void swapRow(int a, int b){
        int temp;
        for(int i = 0; i < 9; i++){
            temp = this.grid[a][i];
            this.grid[a][i] = this.grid[b][i];
            this.grid[b][i] = temp;
        }
    }

    //Swap two cols within the same block
    private void swapCol(int a, int b){
        int temp;
        for(int i = 0; i < 9; i++){
            temp = this.grid[i][a];
            this.grid[i][a] = this.grid[i][b];
            this.grid[i][b] = temp;
        }
    }

    //Reduce the grid : put soem cells to 0
    private void reduceGrid(){
        Random rand = new Random();
        int x;
        int y;
        for(int i = 0; i < 45; i++){
            do{
                x = rand.nextInt(9);
                y = rand.nextInt(9);
            }while(this.grid[x][y] == 0);
            this.grid[x][y] = 0;
        }
    }
}