package com.example.wenkun.finalproject;


import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import com.afollestad.bridge.Bridge;
import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.Form;
import com.afollestad.bridge.Request;
import com.afollestad.bridge.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AccountLib extends Application {

    public String server = "https://android.iebep.cn/";
    public int user_id = 0;
    public String access_token = "";
    public String username;
    public String email;

    public AccountLib() {
    }

    JSONObject get(String endPoint) throws BridgeException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Log.wtf("bridge", "url: " + server + endPoint);
        Request request  = Bridge
                .get(server + endPoint)
                .header("access-token", access_token)
                .header("user-id", user_id)
                .throwIfNotSuccess()
                .request();
        Response response = request.response();
        JSONObject resp = response.asJsonObject();
        Log.wtf("bridge", "resp: " + resp);
        return resp;
    }

    JSONObject post(String endPoint, JSONObject data) throws BridgeException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Log.wtf("bridge", "url: " + server + endPoint);
        Log.wtf("bridge", "req: " + data);
        Form form = new Form();
        form.add("request_data", data);
        Request request = Bridge
                .post(server + endPoint)
                .header("access-token", access_token)
                .header("user-id", user_id)
                .body(form)
                .throwIfNotSuccess()
                .request();
        Response response = request.response();
        JSONObject resp = response.asJsonObject();
        Log.wtf("bridge", "resp: " + resp);
        return resp;
    }


    boolean login(String username, String password) throws JSONException, BridgeException {
        JSONObject obj = new JSONObject();
        obj.put("username", username);
        obj.put("password", password);
        JSONObject resp = post("user/login", obj);
        if (resp.getInt("status") == 0) {
            access_token = resp.getString("access_token");
            user_id = resp.getInt("user_id");
            email = resp.getString("email");
            return true;
        } else {
            return false;
        }
    }


    boolean register(String username, String password, String email) throws JSONException, BridgeException {
        JSONObject obj = new JSONObject();
        obj.put("username", username);
        obj.put("password", password);
        obj.put("email", email);
        JSONObject resp = post("user/register", obj);
        if (resp.getInt("status") == 0) {
            return true;
        } else {
            return false;
        }
    }



    List<Essay> getEssay() throws BridgeException, JSONException {
        JSONObject resp = get("notes/list");
        List<Essay> L = new ArrayList<Essay>();
        if (resp.getInt("status") == 0) {
            JSONArray arr = resp.getJSONArray("data");
            for (int i = 0, size = arr.length(); i < size; i++) {
                JSONObject essay = arr.getJSONObject(i);
                Essay e = new Essay(
                        essay.getString("title"),
                        essay.getString("last_modify"),
                        essay.getString("content"),
                        essay.getInt("id")
                );
                L.add(e);
            }
        }
        return L;
    }



    Essay update(Essay e) throws Exception {
        if (e.id == 0) {
            Integer id = add(e);
            e.id = id;
        } else {

            JSONObject obj = new JSONObject();
            obj.put("id", e.id);
            obj.put("title", e.getTitle());
            obj.put("text", e.getDetails());
            JSONObject resp = post("notes/update", obj);
            if (resp.getInt("status") != 0) {
                throw new Exception("Server Error");
            }
        }
        return e;
    }



    Integer add(Essay e) throws JSONException, BridgeException { // return server-side ID
        JSONObject obj = new JSONObject();
        obj.put("id", e.id);
        obj.put("title", e.getTitle());
        obj.put("text", e.getDetails());
        JSONObject resp = post("notes/new", obj);
        if (resp.getInt("status") == 0) {
            return resp.getInt("id");
        }
        return -1;
    }


    Boolean delete(Integer id) throws BridgeException, JSONException {
        Log.wtf("delete", ""+ id);
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        try {
            JSONObject resp = post("notes/delete", obj);
            return resp.getInt("status") == 0;
        } catch (Exception e) {
            Log.wtf("error", "" + e);
        }
        return false;
    }
}
