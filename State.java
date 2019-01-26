import java.util.*;

public class State {
    protected static int NUMBER = 3;
    protected static int BOAT_CAPACITY = 2;

    protected int ml; // Missionaries on left shore
    protected int mr; // Missionaries on right shore
    protected int mb; // Missionaries on boat
    protected int cl; // Cannibals on left shore
    protected int cr; // Cannibals on right shore
    protected int cb; // Cannibals on boat
    protected String boat; // Which side boat is on: "L" or "R"
    
    // Default constructor
    public State () {
        this(NUMBER, NUMBER, 0, 0, 0, 0, "L");
    }
    
    // Constructs new State
    public State (int ml, int cl, int mb, int cb, int mr, int cr, String boat) {
        this.ml = ml;
        this.cl = cl;
        this.mb = mb;
        this.cb = cb;
        this.mr = mr;
        this.cr = cr;
        this.boat = boat;
    }
    
    // Checks if State is valid
    public boolean isValidState() {
        if ((ml < 0 || mr < 0 || mb < 0 || cl < 0 || cr < 0 || cb < 0) || 
            (mb + cb > BOAT_CAPACITY) || (ml < cl && ml > 0) || (mr < cr && mr > 0)) {
            return false;
        } else {
            return true;
        }
    }
    
    // Returns a list of all possible moves IF valid
    public ArrayList<State> getMoves() {
        ArrayList<State> list = new ArrayList<State>(); 
        if (boat.equals("L")) {
            this.addValid(list, new State(ml-1, cl, 1, 0, mr+1, cr, "R")); 
            this.addValid(list, new State(ml, cl-1, 0, 1, mr, cr+1, "R"));
            this.addValid(list, new State(ml-2, cl, 2, 0, mr+2, cr, "R"));
            this.addValid(list, new State(ml, cl-2, 0, 2, mr, cr+2, "R"));
            this.addValid(list, new State(ml-1, cl-1, 1, 1, mr+1, cr+1, "R"));
        } else {
            this.addValid(list, new State(ml+1, cl, 1, 0, mr-1, cr, "L")); 
            this.addValid(list, new State(ml, cl+1, 0, 1, mr, cr-1, "L"));
            this.addValid(list, new State(ml+2, cl, 2, 0, mr-2, cr, "L"));
            this.addValid(list, new State(ml, cl+2, 0, 2, mr, cr-2, "L"));
            this.addValid(list, new State(ml+1, cl+1, 1, 1, mr-1, cr-1, "L"));
        } 
        return list;
    }
    
    // Adds state to list only if it is a valid state
    private void addValid(ArrayList<State> list, State s) {
        if (s.isValidState()) {
            list.add(s);
        }
    }
    
    // Returns true if goal State is reached (000033R)
    public boolean isGoalState() {
        return (ml == 0 && cl == 0 && cr == NUMBER && mr == NUMBER);
    }
    
    /*public String toString() {
        return String.format("(%dM%dC-%d%d-%dM%dC,%s)", ml, cl, mb, cb, mr, cr, boat);
    }*/
    
    // Prints representation of movement from shore to shore
    public String toString() {
        if (mb == 0 && cb == 0) {
            return String.format("%-10s               %10s\n", picture(ml, cl), picture(mr, cr));
        } else if (boat.equals("L")) {
            return String.format("%-10s <--- %3s <--- %10s\n", picture(ml, cl), picture(mb, cb), picture(mr, cr));            
        } else {
            return String.format("%-10s ---> %3s ---> %10s\n", picture(ml, cl), picture(mb, cb), picture(mr, cr));
        }
    }
    
    // Helper method returns picture representation of missionaries & cannibals (MMCC for example)
    private String picture(int m, int c) {
        char[] picture = new char[m+c];
        for (int i = 0; i < m; i++) {
            picture[i] = 'M';
        }
        for(int i = m; i < m+c; i++) {
            picture[i] = 'C';
        }
        return new String(picture);
    }
    
    // Compares this State to another object
    public boolean equals(Object o) {
		if (o instanceof State) {
			State other = (State) o;
            return (this.ml == other.ml && this.cl == other.cl &&
                    //this.mb == other.mb && this.cb == other.cb &&
                    this.mr == other.mr && this.cr == other.cr &&
                    this.boat.equals(other.boat));
		} else {
		    return false;
	    }
    }
}
