import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C10_JasonPathUsage {

    @Test
    public void method01(){


        JSONObject personalInfo = new JSONObject();


        JSONObject addressInfo = new JSONObject();


        JSONArray phoneInfoArray = new JSONArray();

        JSONObject cellPhone = new JSONObject();

        JSONObject homePhone = new JSONObject();

        addressInfo.put("streetAddress","naist street");
        addressInfo.put("city","Nara");
        addressInfo.put("postalCode","630-0192");

        cellPhone.put("type","iPhone");
        cellPhone.put("number","0123-4567-8888");

        homePhone.put("type","home");
        homePhone.put("number","0123-4567-8910");

        phoneInfoArray.put(cellPhone);
        phoneInfoArray.put(homePhone);

        personalInfo.put("firstName","John");
        personalInfo.put("lastName","Doe");
        personalInfo.put("age",26);
        personalInfo.put("address",addressInfo);
        personalInfo.put("phoneNumbers", phoneInfoArray);

        System.out.println(personalInfo.get("firstName"));
        System.out.println(personalInfo.get("lastName"));
        System.out.println(personalInfo.getJSONObject("address").get("streetAddress"));
        System.out.println(personalInfo.getJSONObject("address").get("city"));
        System.out.println(personalInfo.getJSONArray("phoneNumbers").getJSONObject(0).get("number"));

    }
}
