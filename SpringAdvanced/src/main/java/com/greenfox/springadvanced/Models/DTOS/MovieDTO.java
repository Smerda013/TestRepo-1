package com.greenfox.springadvanced.Models.DTOS;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MovieDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Mid;

    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("budget")
    private Integer budget;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("status")
    private String status;
    @SerializedName("title")
    private String title;
    @SerializedName("video")
    private Boolean video;
    @SerializedName("vote_count")
    private Integer voteCount;
    
}

