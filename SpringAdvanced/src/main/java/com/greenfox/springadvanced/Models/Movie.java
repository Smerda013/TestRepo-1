package com.greenfox.springadvanced.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Mid;

    @JsonProperty("adult")
    private Boolean adult;
    @JsonProperty("budget")
    private Integer budget;
    @JsonProperty("homepage")
    private String homepage;
    @JsonProperty("status")
    private String status;
    @JsonProperty("title")
    private String title;
    @JsonProperty("video")
    private Boolean video;
    @JsonProperty("vote_count")
    private Integer voteCount;

}
