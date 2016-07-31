package com.yang.parse;

import com.yang.bean.GreetingCard;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 10191509 on 5/7/2016.
 */
public class SocialMomentParse{
    public GreetingCard parseGreetingCard(String result){
        try {
            GreetingCard card = new GreetingCard();
            JSONObject object = new JSONObject(result);
            JSONObject giftingJson = object.getJSONObject("GiftingProduct");
            GreetingCardParse greetingCardParse = new GreetingCardParse();
            card = greetingCardParse.parseGreetingCard(giftingJson.toString());
            return card;
        }catch (JSONException e){
            return null;
        }
    }
}
