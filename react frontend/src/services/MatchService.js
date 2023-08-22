import axios from "axios";

const MATCH_API_BASE_URL = "http://localhost:8080/api/v1/match";

class MatchService {
  playMatch() {
    return axios.get(MATCH_API_BASE_URL + "/playMatch");
  }

  getMatchesStatistics() {
    return axios.get(MATCH_API_BASE_URL + "/matchesStatistics");
  }
}

export default new MatchService();
