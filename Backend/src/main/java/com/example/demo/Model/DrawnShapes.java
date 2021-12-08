import java.util.Stack;
import com.example.demo.shapes
public class DrawnShapes implements DrawnShapesI {
    Stack<ShapeI> drawnShapes;
    Stack<ShapeI> undoneShapes;

    DrawnShapes() {
        this.drawnshapes = new Stack<ShapeI>();
        this.undoneShapes = new Stack<ShapeI>();
    }

    void undoShapes() throws EmptyStackException {
        if(!drawnShapes.empty()) {
            undoneShapes.push(drawnShapes.pop());
        }
        else {
            System.out.println("Nothing to undo");
        }
    }

    void addShape(ShapeI shape) {
        drawnShapes.push(shape);
    }

    void redoShape() {
        if(!undoneShapes.empty()) {
            drawnShapes.push(undoneShapes.pop());
        }
        else {
            System.out.println("Nothing to redo");
        }
    }

    DrawnShapesI loadDrawnShapes(String name) {

    }

    void saveDrawnShapes(String name) {

    }
}