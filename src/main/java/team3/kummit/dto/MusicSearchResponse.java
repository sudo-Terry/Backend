package team3.kummit.dto;

import java.util.List;

import team3.kummit.service.music.MusicResponse;

public record MusicSearchResponse (Integer resultCount, List<MusicResponse> musics ){

}
