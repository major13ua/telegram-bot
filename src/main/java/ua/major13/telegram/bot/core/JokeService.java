package ua.major13.telegram.bot.core;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.major13.telegram.bot.model.JokeResponce;

import javax.annotation.PostConstruct;

/**
 * Created by ichupryna on 29/12/16.
 */
@Component
public class JokeService {

    private static final Logger LOG = LoggerFactory.getLogger(JokeService.class);

    private static String CMP_NAME="JOKE_SERVICE";

    @Value("${joke.url}")
    private String ulr;

    @Value("${joke.connection.timeout}")
    private Integer connectionTimeout;

    @Value("${joke.read.timeout}")
    private Integer readTimeout;

    private RestTemplate template;

    @PostConstruct
    public void init() {
        LOG.info("init extCmp={} url={} connectionTimeout={} readTimeout={}", CMP_NAME, ulr, connectionTimeout, readTimeout);
        template = new RestTemplateBuilder()
                .setConnectTimeout(connectionTimeout)
                .setReadTimeout(readTimeout)
                .build();
    }

    public String getJoke() {

        Stopwatch watch = Stopwatch.createStarted();
        LOG.info("start extCmp={}", CMP_NAME);
        try {
            ResponseEntity<JokeResponce> response = template.getForEntity(ulr, JokeResponce.class);
            LOG.info("stop extCmp={} url={} wTime={}", CMP_NAME, ulr, watch);
            if (HttpStatus.OK.equals(response.getStatusCode())) {
                Preconditions.checkNotNull(response.getBody());
                Preconditions.checkNotNull(response.getBody().getValue());
                Preconditions.checkNotNull(response.getBody().getValue().getJoke());
                return response.getBody().getValue().getJoke();
            } else {
                return "Joke Service not working. Error HTTP[" + response.getStatusCode() + "]";
            }
        } catch (Exception e) {
            LOG.error("extCmp={} url={} wTime={}", CMP_NAME, ulr, watch, e);
            return "Error: " + e.getMessage();
        }

    }

}
