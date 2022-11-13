package com.ap.apmenu.model;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.ap.apmenu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FoodArrayListModel {

    public ArrayList<FoodModel> generateDummyData() {
//        ArrayList<FoodModel> foodList = new ArrayList<>();
//        foodList.add(new FoodModel( "PR01", R.drawable.pr01, "Pasta 1", 36.0));
//        foodList.add(new FoodModel( "PR02", R.drawable.pr02, "Pasta 2", 42.0));
//        return foodList;
        return getFoodArrayListFromURL("");
    }

    private static JSONObject getJsonFromURL(String urlString) {
        try {

            URL url = new URL(urlString);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null)
                stringBuffer.append(line);

            return new JSONObject(stringBuffer.toString());
        } catch (Exception e) {
            Log.e("FoodArrayListModel",
                    "Failed to JSON from "+urlString+":"+e.getMessage());

            return null;
        }
    }


    /**
     * Downloads a image from URL and returns a corresponding Drawable.
     * @param urlString URL of the Image.
     * @param srcName Source name of the Drawable.
     * @return Drawable containing the downloaded image, or null when failed to download.
     */
    public static Drawable getDrawableImageFromUrl(String urlString, String srcName) {
        try {
            InputStream is = (InputStream) new URL(urlString).getContent();
            return Drawable.createFromStream(is, srcName);
        } catch (Exception e) {
            Log.e("FoodArrayListModel",
                    "Failed to load image from "+urlString+":"+e.getMessage());

            return null;
        }
    }

    public ArrayList<FoodModel> getFoodArrayListFromURL(String url) {
        ArrayList<FoodModel> foodList = new ArrayList<>();
        //TODO: Populate this with JSON response
//        JSONObject json = FoodArrayListModel.getJsonFromURL(url);
        String dummyJsonString = "    {\n" +
                "        \"response\" : [\n" +
                "            {\n" +
                "                \"foodID\" : \"PR01\",\n" +
                "                \"name\" : \"Pasta 1\",\n" +
                "                \"price\" : 36.0,\n" +
                "                \"imageURL\" : \"https://5b0988e595225.cdn.sohucs.com/images/20180723/5c9272dc8a28431ebea05e028ccaecf1.jpeg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"foodID\" : \"PR02\",\n" +
                "                \"name\" : \"Pasta 2\",\n" +
                "                \"price\" : 42.0,\n" +
                "                \"imageURL\" : \"https://5b0988e595225.cdn.sohucs.com/images/20180723/5c9272dc8a28431ebea05e028ccaecf1.jpeg\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }";

        try {
            JSONObject json = new JSONObject(dummyJsonString);
            FoodArrayListModel.populateFoodArrayListWithJson(json, foodList);
        }
        catch (JSONException jsonException) {
            Log.e("FoodArrayListModel",
                    jsonException.getMessage());
        }


        return foodList;
    }

    /*
    Example Response String Format:
    {
        "response" : [
            {
                "foodID" : "PR01",
                "name" : "Pasta 1",
                "price" : 36.0,
                "imageURL" : "https://5b0988e595225.cdn.sohucs.com/images/20180723/5c9272dc8a28431ebea05e028ccaecf1.jpeg"
            },
            {
                "foodID" : "PR02",
                "name" : "Pasta 2",
                "price" : 42.0,
                "imageURL" : "https://5b0988e595225.cdn.sohucs.com/images/20180723/5c9272dc8a28431ebea05e028ccaecf1.jpeg"
            }
        ]
    }
     */

    private static void populateFoodArrayListWithJson(JSONObject root, List<FoodModel> foodList) {
        try {
            JSONArray responseArray = root.getJSONArray("response");
            for (int i = 0; i < responseArray.length(); i++) {
                JSONObject food = responseArray.getJSONObject(i);
                String foodID = food.getString("foodID");
                String foodName = food.getString("name");
                double foodPrice = food.getDouble("price");
                String foodImageURL = food.getString("imageURL");
                FoodModel foodModel = new FoodModel(foodID, foodImageURL, foodName, foodPrice);
                foodList.add(foodModel);
            }

        }
        catch (JSONException jsonException) {
            Log.e("FoodArrayListModel",
                    jsonException.getMessage());
        }

    }


}
