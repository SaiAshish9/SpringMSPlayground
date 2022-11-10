package com.example.moviecatalogservice.resources;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        RestTemplate restTemplate = new RestTemplate();
        List<Rating> ratings = Arrays.asList(
                new Rating("1",1),
                new Rating("2",2)
                );
        return ratings.stream().map(rating -> {
                Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                return new CatalogItem(movie.getName(),"4",rating.getRating());
                })
                .collect(Collectors.toList());
    }
// return Collections.singletonList(
// new CatalogItem("1","1",1)
// )
}
