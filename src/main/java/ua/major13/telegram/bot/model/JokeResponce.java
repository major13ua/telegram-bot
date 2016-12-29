package ua.major13.telegram.bot.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by ichupryna on 30/12/16.

 { "type": "success",

 "value": { "id": 488, "joke": "Chuck Norris doesn't pair program.", "categories": ["nerdy"] } }
 */


public class JokeResponce implements Serializable {

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public Joke getValue() {
        return value;
    }

    public void setValue(Joke value) {
        this.value = value;
    }

    private Joke value;

    public JokeResponce() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(type).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        JokeResponce jokeResponce = (JokeResponce)obj;
        return Objects.equals(getType(), jokeResponce.type) && Objects.equals(getValue(), jokeResponce.getValue());
    }
}
