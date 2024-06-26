package project.boardgames.service;/* specifies that the class BoardgameScoreService belongs to project.boardgame.service */

import org.springframework.beans.factory.annotation.Autowired;/* used for automatic dependancy injection */
import org.springframework.stereotype.Service; /* indicates that this class is a service of the spring framework*/
import project.boardgames.model.BoardgameScore; /* links to a model class called boardgame Scores */
import project.boardgames.repository.BoardgameScoreRepository; /* links to a repository interface for CRUD operations*/

import java.util.List; /* used for managing lists */

@Service /* indicates that this is a spring service component*/
public class BoardgameScoreService {
 
    @Autowired /* this injects an instance of BoardgameScoreRepository into the spring class*/
    private BoardgameScoreRepository boardgameScoreRepository;

    public BoardgameScore save(BoardgameScore boardgameScore) {
        return boardgameScoreRepository.save(boardgameScore);
    }/* this saves a score into the database */

    public List<BoardgameScore> getScoresByBoardgameId(int boardgameId) {
        return boardgameScoreRepository.findByBoardgameId(boardgameId);
    } /* retrieves all scores for a certain boardgame based on ID */

    public int getHighestScore(int boardgameId) {
        return boardgameScoreRepository.findByBoardgameId(boardgameId)
                .stream()
                .mapToInt(BoardgameScore::getScore) /* converts all scores to integers*/
                .max() /* finds the maximum*/
                .orElse(0); /* if no scores, returns 0*/
    } /* retrieves the highestscore for a certain boardgame based on ID */

    public double getAverageScore(int boardgameId) {
        return boardgameScoreRepository.findByBoardgameId(boardgameId)
                .stream()
                .mapToInt(BoardgameScore::getScore) /* converts all scores to integers*/
                .average() /* finds the average */
                .orElse(0.0); /* returns 0 if no scores */
    } /* calculates the average score for a certain boardgame */
}/* these encapsulate the business logic related to managing board game scores in the boardgamescore repository*/
