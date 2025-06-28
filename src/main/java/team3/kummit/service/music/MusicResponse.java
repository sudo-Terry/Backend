package team3.kummit.service.music;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MusicResponse(String artworkUrl100,
                        String trackName,
                        String artistName,
                        String previewUrl) {}
