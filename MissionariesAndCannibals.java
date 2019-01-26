import java.util.*;

public class MissionariesAndCannibals {
    // Initialize array to store each state that is visited
    private static ArrayList<State> triedStates = new ArrayList<State>();
    
    // Main method
    public static void main(String[] args) {
        State initial = new State();
        solveMandC(initial);
    }
    
    // Checks if the state has been visited before
    public static boolean isRepeated(ArrayList<State> list, State s) {
        return (list.contains(s));
    }
    
    public static boolean solveMandC(State s) {
        Stack<State> solution = new Stack<State>();
        solution.push(s);
        return solveMandC(s, solution);
    }
    
    // RECURSIVE method
    private static boolean solveMandC(State s, Stack<State> solution) {
        // starting state = (3, 3, 0, 0, 0, 0, "L")
        // solution state = (0, 0, 0, 0, 3, 3, "R")
        if (s.isGoalState()) {                   // base case
            print(solution);
            return true;
        } else if (isRepeated(triedStates, s)) { // don't proceed if State has already been seen
            return false;
        } else {
            triedStates.add(s);
            ArrayList<State> moves = s.getMoves(); // list of all possible moves
            for (State nextState : moves) {
                solution.push(nextState);
                if (solveMandC(nextState, solution)) {  // recursive call
                    return true;
                }
                solution.pop();
            }
            return false;
        }    
        
    }
      
    // Prints the picture of each State in a list of States   
    public static void print(Stack<State> list) {
        for(State s: list) {
            System.out.println(s);
        }
    }   
}


