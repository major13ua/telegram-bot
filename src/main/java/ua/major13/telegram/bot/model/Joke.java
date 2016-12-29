package ua.major13.telegram.bot.model;

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * Created by ichupryna on 30/12/16.
 * { "id": 488, "joke": "Chuck Norris doesn't pair program.", "categories": ["nerdy"] }
 */
public class Joke {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    private Integer id;

    private String joke;

    public Joke() {

    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(getId()).addValue(getJoke()).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getJoke());
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
        Joke joke = (Joke) obj;
        return Objects.equals(getId(), joke.getId()) && Objects.equals(getJoke(), joke.getJoke());
    }

}
