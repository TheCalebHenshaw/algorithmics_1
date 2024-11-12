public class VertexDistance implements Comparable<VertexDistance> {
    int vertexIndex;
    int distance;


    public VertexDistance(int vertexIndex, int distance){
        this.vertexIndex = vertexIndex;
        this.distance = distance;
    }
    @Override
    public int compareTo(VertexDistance other){
        return Integer.compare(this.distance, other.distance);
    }
}
