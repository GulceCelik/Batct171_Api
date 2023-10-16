package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {
    /*
    Parametreler icin wrapper class kullnamamizin amaci null olarak kullanim istedigimizde kullanabilmek.
     */
    public Map<String,Object> getPayLoad(Integer userId, String title,Boolean completed) {
        Map<String,Object> payload = new HashMap<>();
        if (userId != null ){
            payload.put("userId",userId);
        }

        if (title != null) {
            payload.put("title",title);
        }

        if(completed!=null) {
            payload.put("completed",completed);
        }

      return payload;
    }
    public Map<String,Object> getPayLoad(Integer userId , Integer id, String title , Boolean completed){

        Map<String,Object> payLoad = new HashMap<>();
        payLoad.put("userId", userId);
        payLoad.put("id", id);
        payLoad.put("title", title);
        payLoad.put("completed", completed);

        return payLoad;
    }
}
