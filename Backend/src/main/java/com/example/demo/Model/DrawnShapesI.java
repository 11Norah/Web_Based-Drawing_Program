import com.example.demo.shapes;

public interface DrawnShapesI {
    DrawnShapesI loadDrawnShapes(String name);
    void saveDrawnShapes(String name);
    void undoShapes();
    void addShape(ShapeI shape);
    void redoShape();
}