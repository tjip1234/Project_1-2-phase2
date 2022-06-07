package Maze;

import java.util.LinkedList;

public class MazeCreator {
    LinkedList<Tuple> result = new LinkedList<>();
    public LinkedList<Tuple> StartMaze(){
        result.add(new Tuple<>(3.0, 3.0));
        return result;
    }
}
