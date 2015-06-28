package sample;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by w00lf on 28.06.2015.
 */
public class ApiClient {
    private final CloseableHttpClient client;
    private static final String REQUEST_BASE = "http://192.168.74.130:3000";
    private static final String FLATS_BASE = "flats";

    ApiClient() {
        client = HttpClients.createDefault();
    }

    public final FlatList getFlats(final int page, final int count)
            throws IOException {
        HttpGet request = new HttpGet(REQUEST_BASE + "/" + FLATS_BASE + ".json?page=" + page + "&per_page=" + count);
        HttpResponse response = client.execute(request);
//        BufferedReader rd = new BufferedReader(
//                new InputStreamReader(response.getEntity().getContent()));
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity,"UTF-8");
        JSONObject obj = new JSONObject(result);
        JSONArray arr = obj.getJSONArray("flats");
        FlatList flats = new FlatList();
        for (int i = 0; i < arr.length(); i++)
        {
            int id = arr.getJSONObject(i).getInt("id");
            int square = arr.getJSONObject(i).getInt("square");
            int floor = arr.getJSONObject(i).getInt("floor");
            int totalFloors = arr.getJSONObject(i).getInt("house_floors");
            int price = arr.getJSONObject(i).getInt("price");
            String address = arr.getJSONObject(i).getString("address");
            flats.add(new Flat(id, square, floor, totalFloors, price, address));
        }
        return flats;
    }

    private Date convertStringToDate(final String str) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
            System.out.println("date : " + simpleDateFormat.format(date));
        } catch (ParseException ex) {
            System.out.println("Exception " + ex);
        }
        return date;
    }
}
