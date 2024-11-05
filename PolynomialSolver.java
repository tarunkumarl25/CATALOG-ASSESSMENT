import org.json.JSONObject;
import org.json.JSONException;
import java.util.HashMap;
import java.util.Map;

public class PolynomialSolver {
    public static void main(String[] args) {
                String jsonString = "{ \"keys\": { \"n\": 4, \"k\": 3 }, \"1\": { \"base\": \"10\", \"value\": \"4\" }, \"2\": { \"base\": \"2\", \"value\": \"111\" }, \"3\": { \"base\": \"10\", \"value\": \"12\" }, \"6\": { \"base\": \"4\", \"value\": \"213\" } }";
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    Map<Integer, Double> points = readPoints(jsonObject);
                    for (Map.Entry<Integer, Double> entry : points.entrySet()) {
                        System.out.println("Point " + entry.getKey() + ": " + entry.getValue());
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
    }
            private static Map<Integer, Double> readPoints(JSONObject jsonObject) throws JSONException {
                JSONObject keys = jsonObject.getJSONObject("keys");
                int n = keys.getInt("n");

                Map<Integer, Double> points = new HashMap<>();

                for (String key : jsonObject.keySet()) {
                    if (!key.equals("keys")) {
                        JSONObject point = jsonObject.getJSONObject(key);
                        int base = Integer.parseInt(point.getString("base"));
                        String value = point.getString("value");
                        points.put(Integer.parseInt(key), convertToDecimal(value, base));
                    }
                }
                return points;
            }

            private static double convertToDecimal(String value, int base) {
                return Integer.parseInt(value, base);
            }
}
