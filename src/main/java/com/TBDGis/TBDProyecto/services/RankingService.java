package com.TBDGis.TBDProyecto.services;

import com.TBDGis.TBDProyecto.models.Ranking;
import com.TBDGis.TBDProyecto.repositories.RankingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "ranking")
public class RankingService {
    public final RankingRepository rankingRepository;
    RankingService(RankingRepository rankingRepository){
        this.rankingRepository = rankingRepository;
    }

    @GetMapping("/getAll")
    public List<Ranking> getAllRankings(){
        return rankingRepository.getAllRankings();
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Ranking getRankingById(@PathVariable(value = "id") Integer id){
        return this.rankingRepository.getRankingById(id);
    }

    @GetMapping("/count")
    public String countRankings(){
        int total = rankingRepository.countRankings();
        return String.format("Tienes %s Rankings!!", total);
    }

    @PostMapping("/createRanking")
    @ResponseBody
    public Ranking createRanking(@RequestBody Ranking ranking){
        Ranking result = rankingRepository.createRanking(ranking);
        return result;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateRanking(@PathVariable(value = "id") int id, Ranking ranking) {
        rankingRepository.updateRanking(id, ranking);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteRanking(@PathVariable(value = "id") int id, Ranking ranking){
        rankingRepository.deleteRanking(id, ranking);
    }
}
