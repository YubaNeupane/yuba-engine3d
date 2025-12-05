package Engine.Component;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class TransformComponent extends Component {
    private final String id;
    private final String modelId;
    private Matrix4f modelMatrix;
    private Vector3f position;
    private Quaternionf rotation;
    private float scale;

    public TransformComponent() {
        this.id = "";
        this.modelId = "";
        modelMatrix = new Matrix4f();
        position = new Vector3f();
        rotation = new Quaternionf();
        scale = 1;
    }

    public String getId() {
        return id;
    }

    public String getModelId() {
        return modelId;
    }

    public Matrix4f getModelMatrix() {
        return modelMatrix;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Quaternionf getRotation() {
        return rotation;
    }

    public float getScale() {
        return scale;
    }

    public final void setPosition(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
        updateModelMatrix();
    }

    public void setRotation(float x, float y, float z, float angle) {
        this.rotation.fromAxisAngleRad(x, y, z, angle);
        updateModelMatrix();
    }

    public void setScale(float scale) {
        this.scale = scale;
        updateModelMatrix();
    }

    private void updateModelMatrix() {
        modelMatrix.translationRotateScale(position, rotation, scale);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void start() {

    }

    @Override
    public void render() {

    }
}
