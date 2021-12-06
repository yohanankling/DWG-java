import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.Graph_Algo;
import com.google.gson.Gson;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
//    public static DirectedWeightedGraph getGrapg(String json_file) {
//        DirectedWeightedGraph ans = null;
//        // ****** Add your code here ******
//        //
//        // ********************************
//        return ans;
//    }
//    /**
//     * This static function will be used to test your implementation
//     * @param json_file - a json file (e.g., G1.json - G3.gson)
//     * @return
//     */
//    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
//        DirectedWeightedGraphAlgorithms ans = null;
//        // ****** Add your code here ******
//        //
//        // ********************************
//        return ans;
//    }
//    /**
//     * This static function will run your GUI using the json fime.
//     * @param json_file - a json file (e.g., G1.json - G3.gson)
//     *
//     */
//    public static void runGUI(String json_file) {
//        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
//        // ****** Add your code here ******
//        //
//        // ********************************
//    }

    public static void main(String[] args) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("G1.json"));
            DirectedWeightedGraphAlgorithms gra = gson.fromJson(reader, Graph_Algo.class);
            System.out.println(gra.toString());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }}
}