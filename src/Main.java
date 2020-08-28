import vertex.RectVertex;

/**
 * @author Yip Coekjan
 * @Date 8/28/2020
 */
public class Main {
    public static void main(String[] args) {
        RectVertex[] vs = new RectVertex[4];
        for(int i = 0; i < vs.length; ++i) {
            vs[i] = new RectVertex(2, 2);
        }
        vs[0].linkTo(vs[1]);
        vs[1].linkTo(vs[2]);
        vs[2].connectWith(vs[3]);
        vs[3].connectWith(vs[0]);
        for(RectVertex v : vs) {
            System.out.println(v.getColor() + "|" + v.listVer());
        }
    }
}
