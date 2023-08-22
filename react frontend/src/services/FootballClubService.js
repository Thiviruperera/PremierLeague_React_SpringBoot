import axios from "axios";

const FOOTBALLCLUB_API_BASE_URL = "http://localhost:8080/api/v1/footballClubs";

class FootballClubService {
  getAllFootballClub() {
    return axios.get(FOOTBALLCLUB_API_BASE_URL);
  }

  createFootballClub(footballClub) {
    return axios.post(FOOTBALLCLUB_API_BASE_URL, footballClub);
  }

  getFootballClubById(footballClubId) {
    return axios.get(FOOTBALLCLUB_API_BASE_URL + "/" + footballClubId);
  }

  updateFootballClub(footballClub, footballClubId) {
    return axios.put(FOOTBALLCLUB_API_BASE_URL + "/" + footballClubId, footballClub);
  }
  deleteFootballClub(footballClubId) {
    return axios.delete(FOOTBALLCLUB_API_BASE_URL + "/" + footballClubId);
  }
}

export default new FootballClubService();
