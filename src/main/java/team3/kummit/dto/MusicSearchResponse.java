package team3.kummit.dto;

import team3.kummit.service.music.MusicResponse;

import java.util.List;

public record MusicSearchResponse (Integer resultCount, List<MusicResponse> musics ){

}
