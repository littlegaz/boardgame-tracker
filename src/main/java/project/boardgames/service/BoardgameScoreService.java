package project.boardgames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.boardgames.model.BoardgameScore;
import project.boardgames.repository.BoardgameScoreRepository;

import java.util.List;

@Service
public class BoardgameScoreService {

    @Autowired
    private BoardgameScoreRepository boardgameScoreRepository;

    public BoardgameScore save(BoardgameScore boardgameScore) {
        return boardgameScoreRepository.save(boardgameScore);
    }

    public List<BoardgameScore> getScoresByBoardgameId(int boardgameId) {
        return boardgameScoreRepository.findByBoardgameId(boardgameId);
    }

    public int getHighestScore(int boardgameId) {
        return boardgameScoreRepository.findByBoardgameId(boardgameId)
                .stream()
                .mapToInt(BoardgameScore::getScore)
                .max()
                .orElse(0);
    }

    public double getAverageScore(int boardgameId) {
        return boardgameScoreRepository.findByBoardgameId(boardgameId)
                .stream()
                .mapToInt(BoardgameScore::getScore)
                .average()
                .orElse(0.0);
    }
}
